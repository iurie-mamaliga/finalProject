package com.techelevator.model;

import java.util.List;

public interface MatchmakingCompanyDAO{
	
	public void addCompany(MatchmakingCompany company);
	public void deleteCompany(int id);
	public void deleteCompanyPosition(int roleId);
	public void deleteCompanyRep(int repId);
	public void saveCompany(String companyName);
	public void saveRep(int companyId, String repName, String repEmail);
	public void saveRep(int companyId, String roleName);
	public List<MatchmakingCompany> showAllCompanies();
}