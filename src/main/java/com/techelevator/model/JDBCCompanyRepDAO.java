package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCCompanyRepDAO implements CompanyRepDAO {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCCompanyRepDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	//will we get one rep at a time from form or a list of reps to insert to database???
	//should first name and last name be 2 fields in database?
	@Override
	public void saveRep(CompanyRep rep) {
		String sqlInsertRep = "INSERT INTO company_rep (company_id, representative_name, representative_email) " +
				"VALUES(?, ?, ?);";
		jdbcTemplate.update(sqlInsertRep, rep.getCompanyId(), rep.getRepName(), rep.getRepEmail());
	}
	
	@Override
	public List<CompanyRep> getRepsForCompany(int companyId){
		List<CompanyRep> repsForCompany = new ArrayList<CompanyRep>();
		String sqlSelectReps = "SELECT * FROM company_rep WHERE company_id = " + companyId + ";";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectReps);
		while(results.next()) {
			CompanyRep rep = mapRowToRep(results);
			repsForCompany.add(rep);
		}
		return repsForCompany;
	}
	
	private CompanyRep mapRowToRep(SqlRowSet results) {
		CompanyRep rep = new CompanyRep();
		rep.setRepId(results.getInt("rep_id"));
		rep.setCompanyId(results.getInt("company_id"));
		rep.setRepName(results.getString("representative_name"));
		rep.setRepEmail(results.getString("representative_email"));
		return rep;
	}
	
}