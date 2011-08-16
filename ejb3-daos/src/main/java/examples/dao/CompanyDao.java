package examples.dao;

import examples.domain.Company;

import javax.ejb.Local;
import java.util.List;

/**
 * Date: 8/15/11
 * Time: 8:59 PM
 */
@Local
public interface CompanyDao {
	Company getCompany(int id);

	List<Company> getCompanyList();
}
