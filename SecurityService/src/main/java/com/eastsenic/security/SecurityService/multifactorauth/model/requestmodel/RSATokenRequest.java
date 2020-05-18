package com.eastsenic.security.SecurityService.multifactorauth.model.requestmodel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RSATokenRequest {
    private int tokenResponseType;
}
