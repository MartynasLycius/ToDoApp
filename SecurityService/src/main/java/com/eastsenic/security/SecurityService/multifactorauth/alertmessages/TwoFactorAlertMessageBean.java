package com.eastsenic.security.SecurityService.multifactorauth.alertmessages;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TwoFactorAlertMessageBean {
    private String mgsSuccessfulODTSend;
    private String mgsBadODTLimitCross;
    private String mgsInvalidODT;
    private String mgsTwoFactorTokenSessionExpired;
    private String msgFailedToSentOTP;
    private String mgsWrongOnDemandToken;
    private String mgsInvalidAuthMode;

    public TwoFactorAlertMessageBean(@Value("${two.factor.successful.odt.message}") String mgsSuccessfulODTSend,
                                     @Value("${two.factor.account.lock.message}") String mgsBadODTLimitCross,
                                     @Value("${two.factor.general.error.message}") String mgsInvalidODT,
                                     @Value("${two.factor.session.expire.error.message}") String mgsTwoFactorTokenSessionExpired,
                                     @Value("${two.factor.failed.to.send.otp}") String msgFailedToSentOTP,
                                     @Value("${two.factor.wrong.odt}") String mgsWrongOnDemandToken,
                                     @Value("${two.factor.invalid.auth.mode}") String mgsInvalidAuthMode) {

        this.mgsSuccessfulODTSend = mgsSuccessfulODTSend;
        this.mgsBadODTLimitCross = mgsBadODTLimitCross;
        this.mgsInvalidODT = mgsInvalidODT;
        this.mgsTwoFactorTokenSessionExpired = mgsTwoFactorTokenSessionExpired;
        this.msgFailedToSentOTP = msgFailedToSentOTP;
        this.mgsWrongOnDemandToken = mgsWrongOnDemandToken;
        this.mgsInvalidAuthMode = mgsInvalidAuthMode;
    }
}
