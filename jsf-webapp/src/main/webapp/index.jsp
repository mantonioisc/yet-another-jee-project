<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h2>Hello World!</h2>

Tags <br />
<c:if test="${not empty tags}">

	<c:forEach var="tag" items="${tags}">
		${tag.id} - ${tag.name} <br />
	</c:forEach>

</c:if>

Users <br />
<c:if test="${not empty users}">

	<c:forEach var="user" items="${users}">
		${user.id} - ${user.name} ${user.lastName}<br />
	</c:forEach>

</c:if>

</body>
</html>
