package com.example.TrainerEffort.effortDAO;


public class Joined {

	
	private String CohortCode;
	private String Skill ;
	private String MappedTrainerType ;
	private String TrainerID;

    private String TrainerRole; 
    private String Mode;
    private String Reason;
    private String AreaofWork;
    private String EffortHrs;
    private String Date;
	public String getCohortCode() {
		return CohortCode;
	}
	public void setCohortCode(String cohortCode) {
		CohortCode = cohortCode;
	}
	public String getSkill() {
		return Skill;
	}
	public void setSkill(String skill) {
		Skill = skill;
	}
	public String getMappedTrainerType() {
		return MappedTrainerType;
	}
	public void setMappedTrainerType(String mappedTrainerType) {
		MappedTrainerType = mappedTrainerType;
	}
	public String getTrainerID() {
		return TrainerID;
	}
	public void setTrainerID(String trainerID) {
		TrainerID = trainerID;
	}
	public String getTrainerRole() {
		return TrainerRole;
	}
	public void setTrainerRole(String trainerRole) {
		TrainerRole = trainerRole;
	}
	public String getMode() {
		return Mode;
	}
	public void setMode(String mode) {
		Mode = mode;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public String getAreaofWork() {
		return AreaofWork;
	}
	public void setAreaofWork(String areaofWork) {
		AreaofWork = areaofWork;
	}
	public String getEffortHrs() {
		return EffortHrs;
	}
	public void setEffortHrs(String effortHrs) {
		EffortHrs = effortHrs;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public Joined(String cohortCode, String skill, String mappedTrainerType, String trainerID, String trainerRole,
			String mode, String reason, String areaofWork, String effortHrs, String date) {
		super();
		CohortCode = cohortCode;
		Skill = skill;
		MappedTrainerType = mappedTrainerType;
		TrainerID = trainerID;
		TrainerRole = trainerRole;
		Mode = mode;
		Reason = reason;
		AreaofWork = areaofWork;
		EffortHrs = effortHrs;
		Date = date;
	}
	
	    
	    
	    
		
		
		
		


	


}
