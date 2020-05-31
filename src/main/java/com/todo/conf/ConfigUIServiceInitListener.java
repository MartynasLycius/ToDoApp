package com.todo.conf;

import com.todo.auth.LoginView;
import com.todo.auth.RegistrationView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

public class ConfigUIServiceInitListener{}

/*//@Component
public class ConfigUIServiceInitListener implements VaadinServiceInitListener {
    private static final Logger logger= LogManager.getLogger(ConfigUIServiceInitListener.class);


    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authNavigation);
        });
    }

    private void authNavigation(BeforeEnterEvent event) {
        logger.debug("navigating-to:: "+event.getNavigationTarget());
        boolean exposed=false;
        if (LoginView.class.equals(event.getNavigationTarget())){
            exposed=true;
            logger.debug(event.getNavigationTarget() +" is exposed....");
        }else if(RegistrationView.class.equals(event.getNavigationTarget())){
            exposed=true;
            logger.debug(event.getNavigationTarget() +" is exposed....");
        }else {
            logger.debug("requested object is restricted....");
        }

        if (!exposed) {
            event.rerouteTo(LoginView.class);
        }
    }
}*/
