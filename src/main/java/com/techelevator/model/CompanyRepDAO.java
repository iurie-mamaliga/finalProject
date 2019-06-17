package com.techelevator.model;

import java.util.List;

public interface CompanyRepDAO {
	
	public void saveRep(CompanyRep rep);
	public List<CompanyRep> getRepsForCompany(int companyId);
}