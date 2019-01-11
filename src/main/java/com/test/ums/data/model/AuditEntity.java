package com.test.ums.data.model;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Auditable;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AuditEntity implements Auditable<String, String> {
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@Version
	private Long version;
	@Indexed
	@CreatedDate
	private DateTime createdDate;
	@LastModifiedDate
	private DateTime lastModifiedDate;
	private String createdBy;
	private String lastModifiedBy;

	@Override
	public boolean isNew() {
		return id == null;
	}
}