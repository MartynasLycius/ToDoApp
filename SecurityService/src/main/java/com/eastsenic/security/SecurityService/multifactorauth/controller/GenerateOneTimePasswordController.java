package com.eastsenic.security.SecurityService.multifactorauth.controller;

import com.eastsenic.security.SecurityService.multifactorauth.model.requestmodel.RSATokenRequest;
import com.eastsenic.security.SecurityService.multifactorauth.model.requestmodel.RSATokenVerificationRequest;
import com.eastsenic.security.SecurityService.multifactorauth.model.viewmodel.OneTimePassword;
import com.eastsenic.security.SecurityService.multifactorauth.model.viewmodel.Response;
import com.eastsenic.security.SecurityService.multifactorauth.service.GeneratedOneTimePasswordService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateOneTimePasswordController {

    @Autowired
    private GeneratedOneTimePasswordService generatedOneTimePasswordService;

//    @ApiOperation(value = "Send On Demand Token")
    @RequestMapping(value = "/two/factor/odt", method = RequestMethod.POST)
    public Response<OneTimePassword> getOnDemandToken() {
        Response<OneTimePassword> oneTimePasswordResponse = new Response<>();
        OneTimePassword model = generatedOneTimePasswordService.getOneTimePassword();
        oneTimePasswordResponse.setItems(model);
        return oneTimePasswordResponse;
    }

//    @ApiOperation(value = "Verify On Demand Token")
    @RequestMapping(value = "/two/factor/odt/verification", method = RequestMethod.POST)
    public Response<String> verifyRSAToken(@RequestBody RSATokenVerificationRequest request) {
        Response<String> response = new Response<>();
        String model = generatedOneTimePasswordService.verifyOnDemandToken(request.getAmSessionId(), request.getPasscode());
        response.setItems(model);
        return response;
    }
}
