package com.example.XyhubEmployee.basedata.model;


import java.util.Date;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class IBaseData {
	
	@Column(name="version")
	private String version;
	
	@Column(name="creation_ts")
    @Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
    private Date creationTimestamp;
	
	@Column(name="last_updated_ts")
    @Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
    private Date lastUpdatedTimestamp;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	
	

}
