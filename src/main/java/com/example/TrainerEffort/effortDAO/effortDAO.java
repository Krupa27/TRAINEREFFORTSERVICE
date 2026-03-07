package com.example.TrainerEffort.effortDAO;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity

public class effortDAO {
	
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name="ID", column= @Column(name="ID", length=50)),
		@AttributeOverride(name="cohortCode", column= @Column(name="cohort_code", length=50)),
		@AttributeOverride(name = "date", column = @Column(name = "date"))

	})
	
	private Info info;
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	private String mode;
	private String reason;
	private String EffortHours;
	private String Topic;
	private String Highlights;
	private String status="Pending";
	@Column(name = "remarks")
	private String remarks;
	
	
	
	
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getMode() {
		return this.mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getReason() {
		return this.reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getEffortHours() {
		return EffortHours;
	}
	public void setEffortHours(String effortHours) {
		EffortHours = effortHours;
	}
	
	public String getTopic() {
		return Topic;
	}
	public void setTopic(String topic) {
		Topic = topic;
	}
	public String getHighlights() {
		return Highlights;
	}
	public void setHighlights(String highlights) {
		Highlights = highlights;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public effortDAO(Info info, String mode, String reason, String effortHours, String topic,
			String highlights, String status,String remarks) {
		super();
		this.info = info;
		this.mode = mode;
		this.reason = reason;
		EffortHours = effortHours;
		Topic = topic;
		Highlights = highlights;
		this.status = status;
		this.remarks=remarks;
	}
	public effortDAO() {

	}
	@Override
	public String toString() {
		return "effortDAO [info=" + info + ", mode=" + mode + ", reason=" + reason + ", EffortHours=" + EffortHours
				+ ", Topic=" + Topic + ", Highlights=" + Highlights + ", status=" + status + ", remarks=" + remarks
				+ "]";
	}
	
	
	

	
	

}
