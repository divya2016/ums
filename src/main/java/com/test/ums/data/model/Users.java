package com.test.ums.data.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.test.ums.constants.ConfigConstants.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users extends AuditEntity {
	private static final long serialVersionUID = -3818650451647590994L;
	private Long recordId;
	private String name;
	private String email;
	private String mobile;
	private Integer retries;
	private Integer roleId;
	private Status status;
	private String password;
	private UserDetails userDetails;
}
