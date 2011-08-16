#######################PROJECT DESCRIPTION###########################


######################INTRUCTIONS###############################
- Compile
Compile from the parent folder, with
			mvn clean install

- Configure
Configure data source as show bellow.
Create DB tables as shown in DDL & DML sql files from oracle-hibernate-mappings project.

- Deploy
And deploy the jpa-ejb3-jsf-ear-1.0.ear in jpa-ejb3-jsf-project/jpa-ejb3-jsf-ear/target
For JBoss just copy it to server/default/deploy

- JBoss 6 Maven plugin configuration
http://mojo.codehaus.org/jboss-maven-plugin/plugin-info.html
It's configured in jpa-ejb3-jsf-ear project, to run it type
			mvn jboss:deploy
	and
			mvn jboss:undeploy

- JBoss 7 Maven Plugin configuration
Check plugin configuration at http://download.jboss.org/jbossas/7.0/jboss-as-7.0.0.Final/jboss-as-quickstarts-7.0.0.Final-dist.zip
It's configured in jpa-ejb3-jsf-ear project, it requires less configuration than previous version, to run it type
			mvn jboss-as:deploy
			mvn jboss-as:undeploy

- To check it
http://localhost:8080/jsf-webapp

#######################CONFIGURATION DONE#########################
VERY IMPORTANT TO RUN AND DEPLOY THIS MAVEN PROJECT TO AND JEE APPLICATION SERVER

This project works on JBoss AS 6
Many configurations had to be done in order for this to work. Here are resumed, please consider them in other JPA,EJB, JSF projects.
It war more difficult than expected thanks to the lack of knowledge to pass from EJB development to deployment.

-Installing Oracle ojdbc14 for oracle xe
	Downloaded from oracle site (10.2.0.4) http://www.oracle.com/technetwork/database/enterprise-edition/jdbc-10201-088211.html
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.4.0 -Dpackaging=jar -Dfile=ojdbc14.jar -Djavadoc=javadoc.zip

-For JPA projects
	We need an JPA annotations implementations jar, and JPA Implementation, here provided by hibernate
In the tests we have an specific persistence.xml for JSE JPA version using hibernate, we use test resource
filtering to place it in /META-INF/ in order to Persistence.createEntityManagerFactory("") to find it.
	When deploying the persistence unit to an application server we need maven resource configuration to move
persistence.xml inside the resulting jar in /META-INF/.
			<resource>
				<directory>src/main/resources</directory>
				<includes>
					<include>persistence.xml</include>
				</includes>
				<targetPath>META-INF</targetPath>
			</resource>
	A different persistence context is used for application server deployment, this one only has a JNDI
reference to the data source to be used. This has to be configured in application server.
	When deploying to jboss be careful not tu include the hibernate implementation used to test to avoid errors.

- For DataSource JBoss 6
	Copy the oracle ojdbc.jar in server/default/lib to be available to JBoss AS.
	Create a data source in the application server that has the same name as src/main/resources/persistence.xml
in this case java:/OracleDS check the file for the definition used in JBoss 6: OracleDS-ds.xml . This definition must be
saved in server/default/deploy

- For DataSource JBoss 7
	Download oracle driver: ojdbc5.jar or ojdbc6.jar it contains necessary deployment descriptors to install in
application server. Copy it on jboss-as-7.0.0.Final/standalone/deployments/ or in admin console Deployments->Manage
deployments->Add content, and select the jar file.
	Next go to Profile->Connector->Datasources->New datasource give the OracleDS name and java:/OracleDS as JNDI name,
then select the recently installed ojdbc driver, then give the connection data.

- For DataSource Glassfish 3.1.1
	Copy ojdbc5.jar or ojdbc6.jar to glassfish3/glassfish/domains/domain1/lib/ and start glassfish
Enter asadmin session and type:
			create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname oracle.jdbc.pool.OracleDataSource --property "user=HR:password=HR:url=jdbc\\:oracle\\:thin\\:@localhost\\:1521\\:xe" OracleDS
Described here http://download.oracle.com/docs/cd/E18930_01/html/821-2432/gkyan.html

-For EJB jar
	For EJBs we need a jar with the javax.ejb annotations we can do this in two different ways(also applicable for
javax.persistence annotations). One is including the sole dependency for EJB3 or include the full set of JEE 6 from JBoss.
Include them as <scope>provided</scope> since the target AS must have them, and we just need them to compile the project.
		    <dependency>
    			<groupId>org.jboss.spec.javax.ejb</groupId>
    			<artifactId>jboss-ejb-api_3.1_spec</artifactId>
    			<version>1.0.1.Final</version>
    			<scope>provided</scope>
    		</dependency>
    or
			<dependency>
				<groupId>org.jboss.spec</groupId>
				<artifactId>jboss-javaee-6.0</artifactId>
				<version>1.0.0.Final</version>
				<type>pom</type>
				<scope>provided</scope>
			</dependency>

	The last one has all JEE dependencies (servlet, jsp, jsf, jpa) which can be really useful. What is imported with
this can be checked with the command:
			mvn dependency:resolve
	Here configure the maven-ejb-plugin to use EJB 3.1 version. Also include the JPA persisted objects jar as dependency

-For web application war
	Include the EJB DAO jar file, but include only for compiling, choose provided scope. This is really important
at deployment time at JBoss AS since if jpa-mappings is pulled from the war file and the ear file an error
arise related to double deployment of local EJBs. This way we avoid inclusion of the jar file in WEB-INF/lib
and we just include in ear file lib/ folder.
	Deployed on http://localhost:8080/jsf-webapp

-For ear file
	Here we define the EJB modules and war modules, it's important that in dependency we specify the <type>
otherwise the ear created will not be valid.
	Very important here is to make sure that EJB definitions only happens in lib/ directory of the ear file
and not pulled from any war files WEB-INF/lib.
	Other important consideration is to avoid placing other hibernate definitions inside lib/ directory, because
version errors occurs when deploying in JBoss.

- Imported to subversion
			svn import jpa-ejb3-jsf-project svn://localhost/jpa-ejb3-jsf-project

##############################TODO#############################
+Clean up sample fils
+Test it in jboss 7
Extract dao class a parameter and use generics
+Import it into subversion
Try to run it from glassfish using the plugin
Map the rest of the entities and make a quick unit test for them
Create DAO EJB's
Try to test the EJBs in integration test
In webapp import jsf dependencies
Create pages in jsf+primefaces to list every entity

