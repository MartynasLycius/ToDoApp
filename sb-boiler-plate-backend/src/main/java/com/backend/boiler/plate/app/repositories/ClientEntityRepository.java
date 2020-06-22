package com.backend.boiler.plate.app.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.boiler.plate.app.models.OauthClient;

@Repository
public interface ClientEntityRepository extends JpaRepository<OauthClient, Long>  {
	@Transactional
	OauthClient findOneByAdditionalInformation(String additionalInformation);
}
