package com.eastsenic.security.SecurityService.multifactorauth.model.multifactorauth;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "generated_one_time_password")
public class GeneratedOneTimePassword {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "otp")
    private String oneTimePassword;

    @Column(name = "create_date")
    private Date createDate;
}
