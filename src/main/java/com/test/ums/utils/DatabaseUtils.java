package com.test.ums.utils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteConcern;
import com.test.ums.data.model.Token;
import com.test.ums.data.model.Users;

@Repository
public class DatabaseUtils {

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostConstruct
	public void writeConcern() {
		mongoTemplate.setWriteConcern(WriteConcern.ACKNOWLEDGED);
	}

	public <T> void save(T entityToSave) {
		if (null == entityToSave) {
			return;
		} else {
			mongoTemplate.save(entityToSave);
		}
	}

	public <T> T findOne(Map<String, Object> params, Class<T> entityClass) {
		Query query = new Query();
		if (params == null || params.isEmpty()) {
			return null;
		} else {
			for (Entry<String, Object> entry : params.entrySet()) {
				query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
			}
		}
		return mongoTemplate.findOne(query, entityClass);
	}

	public <T> T findOne(Query query, Class<T> entityClass) {
		return mongoTemplate.findOne(query, entityClass);
	}

	public <T> List<T> find(Query query, Class<T> entityClass) {
		return mongoTemplate.find(query, entityClass);
	}

	public <T> List<T> findAll(Class<T> entityClass) {
		return mongoTemplate.findAll(entityClass);
	}

	public <T> List<T> findAll(Map<String, Object> params, Class<T> entityClass) {
		Query query = new Query();
		if (params == null || params.isEmpty()) {
			return null;
		} else {
			for (Entry<String, Object> entry : params.entrySet()) {
				query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
			}
		}
		return mongoTemplate.find(query, entityClass);
	}

	public <T> T findAndModify(String collectionName, Class<T> entityClass, Integer increment) {
		Query query = new Query();
		if (collectionName == null || collectionName.isEmpty()) {
			return null;
		} else {
			if (increment == null || increment == 0) {
				increment = 1;
			}
			query.addCriteria(Criteria.where("name").is(collectionName));
			Update update = new Update();
			update.inc("seqId", increment);
			return mongoTemplate.findAndModify(query, update, entityClass);
		}
	}

	public Users findUserByMobile(String mobile) {
		if (null == mobile || mobile.isEmpty()) {
			return null;
		}
		return mongoTemplate.findOne(new Query(Criteria.where("mobile").is(mobile)), Users.class);
	}

	public Users findUserByEmail(String email) {
		if (null == email || email.isEmpty()) {
			return null;
		}
		return mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), Users.class);
	}

	public Token getTokenByTokenId(String tokenId) {
		if (null == tokenId || tokenId.isEmpty()) {
			return null;
		}
		return mongoTemplate.findOne(new Query(Criteria.where("tokenId").is(tokenId)), Token.class);
	}

	public Long getNextSequenceId(String string, Class<Users> class1) {
		// TODO Auto-generated method stub
		return null;
	}

}
