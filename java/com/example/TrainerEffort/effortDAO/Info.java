package com.example.TrainerEffort.effortDAO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Embeddable;

@Embeddable
public class Info implements Serializable {
	
	
	@JsonProperty("iD")
	private String ID;
	private String cohortCode;
	private String date;

	
	public Info(){}
	
	
	
	



	public String getDate() {
		return date;
	}







	public void setDate(String date) {
		this.date = date;
	}







	public String getID() {
		return ID;
	}



	public void setID(String iD) {
		this.ID = iD;
	}



	public String getCohortCode() {
		return cohortCode;
	}



	public void setCohortCode(String cohortCode) {
		this.cohortCode = cohortCode;
	}







	public Info(String iD, String cohortCode, String date) {
		super();
		ID = iD;
		this.cohortCode = cohortCode;
		this.date = date;
	}







	@Override
	public int hashCode() {
		return Objects.hash(cohortCode, ID, date);
	}







	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Info other = (Info) obj;
		return Objects.equals(cohortCode, other.cohortCode) && Objects.equals(ID, other.ID)
				&& Objects.equals(date, other.date);
	}







	@Override
	public String toString() {
		return "Info [ID=" + ID + ", CohortCode=" + cohortCode + ", date=" + date + "]";
	}


	





	



	
	
	
	
	
	

}
