package com.techelevator.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MatchmakingCompany {
	private int companyId;
	private String companyName;
	private String positionAvailable;
	private int repId;
	private String repName;
	private String repEmail;
	private int roleId;
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getRepId() {
		return repId;
	}
	public void setRepId(int repId) {
		this.repId = repId;
	}
	
	public String getPositionAvailable() {
		return positionAvailable;
	}
	public void setPositionAvailable(String positionAvailable) {
		this.positionAvailable = positionAvailable;
	}
	public String getRepName() {
		return repName;
	}
	public void setRepName(String repName) {
		this.repName = repName;
	}
	public String getRepEmail() {
		return repEmail;
	}
	public void setRepEmail(String repEmail) {
		this.repEmail = repEmail;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return this.companyName;
	}
}