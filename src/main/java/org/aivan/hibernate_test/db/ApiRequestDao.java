package org.aivan.hibernate_test.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class ApiRequestDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void create(ApiRequest apiRequest) {
		entityManager.persist(apiRequest);
	}

	public void update(ApiRequest apiRequest) {
		entityManager.merge(apiRequest);
	}

	public ApiRequest getApiRequestById(long id) {
		return entityManager.find(ApiRequest.class, id);
	}

	public void delete(long id) {
		ApiRequest apiRequest = getApiRequestById(id);
		if (apiRequest != null) {
			entityManager.remove(apiRequest);
		}
	}

	public List<ApiRequest> findAll() {
		@SuppressWarnings("unchecked")
		List<ApiRequest> resultList = ( List<ApiRequest>) entityManager.createQuery("select api from ApiRequest api").getResultList();
		return resultList;
		
	}
}