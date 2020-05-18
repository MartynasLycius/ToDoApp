package com.eastsenic.security.SecurityService.multifactorauth.model.emailtemplatemodel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OneTimePasswordTemplate {
    private String otp;
    private String otpExpireTimeInMinute;
}
