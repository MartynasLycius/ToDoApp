package com.interview.task.exceptionHandlers;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.DefaultErrorHandler;
import com.vaadin.flow.server.ErrorEvent;

public class ErrorHandler extends DefaultErrorHandler {
	
    @Override
    public void error(ErrorEvent errorEvent) {
        Throwable throwable = errorEvent.getThrowable();
		if(UI.getCurrent() != null) {
			UI.getCurrent().access(() -> {
				if (throwable.getMessage() == null || throwable.getMessage().isEmpty()) {
					Notification.show("We apologize for the error you faced. Perhaps we implemented the feature incorrectly. Thanks for your patience.");
				} else {
					Notification.show("Error occured. Reason: " + throwable.getMessage());
				}
			});
		}
    }
}
