package com.eastsenic.security.SecurityService.multifactorauth.service;

import com.eastsenic.security.SecurityService.multifactorauth.model.multifactorauth.GeneratedOneTimePassword;
import com.eastsenic.security.SecurityService.multifactorauth.model.viewmodel.OneTimePassword;
import com.eastsenic.security.SecurityService.multifactorauthrepository.multifactorauth.GeneratedOneTimePasswordRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class GeneratedOneTimePasswordService {

    private final Random random;
    private String otpExpireInMinutes = "3";
    private GeneratedOneTimePasswordRepository generatedOneTimePasswordRepository;

    public GeneratedOneTimePasswordService(
                                           GeneratedOneTimePasswordRepository generatedOneTimePasswordRepository) {

        this.generatedOneTimePasswordRepository = generatedOneTimePasswordRepository;

        this.random = new Random();
    }

    @Transactional
    public OneTimePassword getOneTimePassword() {
        GeneratedOneTimePassword otp = new GeneratedOneTimePassword();
        otp.setId(UUID.randomUUID().toString());
        otp.setOneTimePassword(this.generatePassword());
        otp.setCreateDate(new Date());
        generatedOneTimePasswordRepository.save(otp);
        return new OneTimePassword(otp.getId(), otp.getOneTimePassword());
    }

    private String generatePassword() {
        int value = ((1 + random.nextInt(2)) * 100000 + random.nextInt(100000));
        return String.valueOf(value);
    }

    public String verifyOnDemandToken(String id, String passcode) {
        String feedbackMgs = "";
        Date currentDate = new Date();
        GeneratedOneTimePassword otp = generatedOneTimePasswordRepository
                .findByIdAndCreateDateAfter(id, subtractMinutes(currentDate, Integer.parseInt(otpExpireInMinutes)));
        if (otp != null) {
            if (otp.getOneTimePassword().equalsIgnoreCase(passcode)) {
                feedbackMgs = "authenticated";
            } else {
                feedbackMgs = "Not authenticated";
            }
        } else {
            feedbackMgs = "Not authenticated";
        }
        return feedbackMgs;
    }

    public static Date subtractMinutes(Date date, int minuets) {
        return addMinutes(date, minuets * -1);
    }
    public static Date addMinutes(Date date, int minuets) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minuets);

        return calendar.getTime();
    }

}
