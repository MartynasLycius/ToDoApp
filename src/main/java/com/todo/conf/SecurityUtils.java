package com.todo.conf;

import com.todo.utils.ExposedViews;
import com.vaadin.flow.server.ServletHelper;
import com.vaadin.flow.shared.ApplicationConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

public final class SecurityUtils {
    private static final Logger logger= LogManager.getLogger(SecurityUtils.class);

    private SecurityUtils() {
        // Util methods only
    }

    public static boolean isAppInternalRequest(HttpServletRequest request) {
        final String requestType = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
        logger.debug("request type parameter:: "+requestType);
        logger.debug("request URI:: "+request.getRequestURI());
        //logger.debug("ServletHelper.RequestType.values():: "+ ServletHelper.RequestType.values());
        if (requestType != null
                && Stream.of(ServletHelper.RequestType.values())
                .anyMatch(r -> r.getIdentifier().equals(requestType))){
            return true;
        }
        return ExposedViews.getAllUriList().contains(request.getRequestURI());
    }

    public static boolean isUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null
                && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated();
    }

    public static String getLoggedUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(isUserLoggedIn()){
            User loggedUser= (User) authentication.getPrincipal();
            //logger.debug(loggedUser);
            return loggedUser.getUsername();
        }
        return null;
    }
}
