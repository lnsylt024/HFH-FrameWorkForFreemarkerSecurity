package com.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.app.entity.AccountEntity;

@Repository
public interface AccountDAO extends JpaRepositoryImplementation<AccountEntity,Integer>{

	@Query("from AccountEntity where username like %:username% ")
	AccountEntity findByName(String username);
}
