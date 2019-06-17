package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.MatchmakingCompany;

@Component
public class JDBCMatchmakingCompanyDAO implements MatchmakingCompanyDAO {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCMatchmakingCompanyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void addCompany(MatchmakingCompany company) {	
		String sqlInsertSurvey = "INSERT INTO matchmaking_company(company_name) VALUES(?);";
		jdbcTemplate.update(sqlInsertSurvey, company.getCompanyName());
	}
	
	@Override
	public void deleteCompany(int id) {
		
		String sqlInsertSurvey = "DELETE FROM company_role WHERE company_id = ?;"
				+ " DELETE FROM company_rep WHERE company_id = ?;"
				+ " DELETE FROM matchmaking_company WHERE company_id = ?;";
		jdbcTemplate.update(sqlInsertSurvey, id, id, id);
	}
	
	@Override
	public void deleteCompanyPosition(int roleId) {
		String sqlInsertSurvey = "DELETE FROM company_role WHERE role_id = ?;";
		jdbcTemplate.update(sqlInsertSurvey, roleId);
	}
	
	@Override
	public void deleteCompanyRep(int repId) {
		
		String sqlInsertSurvey = "DELETE FROM company_rep WHERE rep_id = ?;";
		jdbcTemplate.update(sqlInsertSurvey, repId);
	}
	
	@Override
	public void saveCompany(String companyName) {
		MatchmakingCompany company = new MatchmakingCompany();
		company.setCompanyName(companyName);
		Long id = getNextIdCompany();
		System.out.println("saving " + companyName);
		String sqlInsertSurvey = "INSERT INTO matchmaking_company (company_id, company_name) VALUES (?, ?);";
		jdbcTemplate.update(sqlInsertSurvey, id, company.getCompanyName());
	}
	
	@Override
	public void saveRep(int companyId, String repName, String repEmail) {
	MatchmakingCompany company = new MatchmakingCompany();
	company.setCompanyId(companyId);
	company.setRepName(repName);
	company.setRepEmail(repEmail);
	Long id = getNextIdRep();
	System.out.println("saving " + repName);
	String sqlInsertSurvey = "INSERT INTO company_rep (rep_id, company_id, representative_name, representative_email) VALUES (?, ?, ?, ?);";
	jdbcTemplate.update(sqlInsertSurvey, id, company.getCompanyId(), company.getRepName(), company.getRepEmail());
}
	
	@Override
	public void saveRep(int companyId, String roleName) {
	MatchmakingCompany company = new MatchmakingCompany();
	company.setCompanyId(companyId);
	company.setPositionAvailable(roleName);
	Long id = getNextIdRole();
	String sqlInsertSurvey = "INSERT INTO company_role (role_id, company_id, job_title) VALUES (?, ?, ?);";
	jdbcTemplate.update(sqlInsertSurvey, id, company.getCompanyId(), company.getPositionAvailable());
}
	
	
	

	@Override
	public List<MatchmakingCompany> showAllCompanies() {
		List<MatchmakingCompany> allCompanies = new ArrayList<MatchmakingCompany>();
		//String sqlSelectResults = "SELECT * FROM matchmaking_company;";
		
		
		String sqlSelectResults = "SELECT matchmaking_company.company_id AS company_id, matchmaking_company.company_name AS company_name, " +
		"company_role.job_title AS job_title, company_rep.rep_id AS rep_id, company_rep.representative_name AS rep_name, " +
		"company_rep.representative_email AS rep_email, company_role.role_id AS role_id " +
		"FROM matchmaking_company " +
		"full join company_rep on matchmaking_company.company_id = company_rep.company_id " +
		"full join company_role on  matchmaking_company.company_id = company_role.company_id;";
		
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectResults);
		MatchmakingCompany company = null;
		
		while(results.next()) {
			company = new MatchmakingCompany();
			
			
			company.setCompanyId(results.getInt("company_id"));
			company.setCompanyName(results.getString("company_name"));
			company.setPositionAvailable(results.getString("job_title"));
			company.setRepId(results.getInt("rep_id"));
			company.setRepName(results.getString("rep_name"));
			company.setRepEmail(results.getString("rep_email"));
			company.setRoleId(results.getInt("role_id"));
			allCompanies.add(company);

			
		}
		return allCompanies;
	}
	
	
	private Long getNextIdCompany() {
		String sqlSelectNextId = "SELECT NEXTVAL('matchmaking_company_company_id_seq')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long id = null;
		if(results.next()) {
			id = results.getLong(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next forum post id from sequence");
		}
		return id;
	}
	
	private Long getNextIdRep() {
		String sqlSelectNextId = "SELECT NEXTVAL('company_rep_rep_id_seq')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long id = null;
		if(results.next()) {
			id = results.getLong(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next forum post id from sequence");
		}
		return id;
	}
	
	private Long getNextIdRole() {
		String sqlSelectNextId = "SELECT NEXTVAL('company_role_role_id_seq')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long id = null;
		if(results.next()) {
			id = results.getLong(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next forum post id from sequence");
		}
		return id;
	}
	
	
}