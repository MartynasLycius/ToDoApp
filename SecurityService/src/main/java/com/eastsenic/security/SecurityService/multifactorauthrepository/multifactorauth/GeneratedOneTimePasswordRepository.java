package com.eastsenic.security.SecurityService.multifactorauthrepository.multifactorauth;

import com.eastsenic.security.SecurityService.multifactorauth.model.multifactorauth.GeneratedOneTimePassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface GeneratedOneTimePasswordRepository extends JpaRepository<GeneratedOneTimePassword, String> {

    GeneratedOneTimePassword findByIdAndCreateDateAfter(String id, Date date);

}
