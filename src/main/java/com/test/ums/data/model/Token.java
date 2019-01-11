package com.test.ums.data.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token extends AuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3702904925815809362L;
	private String tokenId;
	private Date validTill;

}
