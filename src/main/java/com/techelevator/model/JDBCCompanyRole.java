package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCCompanyRole implements CompanyRoleDAO {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCCompanyRole(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveRole(CompanyRole role) {
		String sqlInsertRole = "INSERT INTO company_role (company_id, job_title) VALUES(?, ?);";
		jdbcTemplate.update(sqlInsertRole, role.getCompanyId(), role.getJobTitle());
	}
	
	@Override
	public CompanyRole getRole() {
		CompanyRole role = new CompanyRole();
		
		return role;
	}
	
	//@Override
	//public List<CompanyRole> getAllRoles(){
		
	//	return
	//}
}