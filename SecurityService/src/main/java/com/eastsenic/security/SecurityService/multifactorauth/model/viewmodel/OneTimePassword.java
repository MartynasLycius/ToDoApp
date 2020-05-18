package com.eastsenic.security.SecurityService.multifactorauth.model.viewmodel;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OneTimePassword {
    private String sessionId;
    private String otp;
}
