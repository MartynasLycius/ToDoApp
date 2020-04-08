package com.murad.todoapp.config.log;


import com.murad.todoapp.domain.CustomLog;
import com.murad.todoapp.repository.CustomLogRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
@Controller
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    CustomLogRepositiry customLogRepositiry;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {

        String userAgent = request.getHeader("User-Agent");
       // request.getSession().setMaxInactiveInterval(60*60);
        //SecurityContextHolder.clearContext();
        customLogRepositiry.save(new CustomLog(request.getRemoteAddr(), authentication.getName(),"", userAgent,"LOGIN"));
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
