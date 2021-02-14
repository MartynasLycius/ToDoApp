package com.test.pranto.client.notification;

import com.test.pranto.client.ui.YesNoDialog;
import com.test.pranto.client.ui.YesNoDialog.Callback;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

public class NotificationUtill {
	// private static Logger logger = Logger.getLogger(NotificationUtill.class);

	public static void showMessage(String message) {
		Notification success = new Notification(message);
		success.setDelayMsec(1000);
		success.setStyleName(ValoTheme.NOTIFICATION_SUCCESS);
		success.setPosition(Position.BOTTOM_RIGHT);
		success.show(Page.getCurrent());
	}

	public static void showErrorMessage(String message) {
		Notification success = new Notification(message);
		success.setDelayMsec(1000);
		success.setStyleName(ValoTheme.NOTIFICATION_ERROR);
		success.setPosition(Position.BOTTOM_RIGHT);
		success.show(Page.getCurrent());
	}

	public static void showMessage(String message, int delay) {
		Notification success = new Notification(message);
		success.setDelayMsec(delay);
		success.show(Page.getCurrent());
	}

	public static void showMessage(String message, Type errorMessage) {
		Notification error = new Notification(message, errorMessage);
		error.setDelayMsec(1000);
		error.setStyleName(ValoTheme.NOTIFICATION_ERROR);
		error.setPosition(Position.BOTTOM_RIGHT);
		error.show(Page.getCurrent());
	}

	public static void showYesNoDialog(String caption, String question, Callback callback) {
		YesNoDialog dialog = new YesNoDialog(caption, question, callback);
		UI.getCurrent().addWindow(dialog);
	}

	
}
