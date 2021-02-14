package com.test.pranto.client.notification;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class YesNoDialog extends Window implements ClickListener {

	private Callback callback;
	private Button yes = new Button("Yes", this);
	private Button no = new Button("No", this);
	private boolean noOptionCallback;

	public void setYesButtonCaption(String caption) {
		yes.setCaption(caption);
	}

	public void setNoButtonCaption(String caption) {
		no.setCaption(caption);
	}

	public YesNoDialog(String caption, String question, Callback callback) {
		this(caption, question, callback, true);
	}

	public YesNoDialog(String caption, String question, Callback callback, boolean noOptionCallback) {
		super(caption);
		this.callback = callback;
		this.noOptionCallback = noOptionCallback;

		setModal(true);
		setResizable(false);
		center();
		setClosable(false);
		setWidth("400px"); //$NON-NLS-1$
		setHeight("200px"); //$NON-NLS-1$

		VerticalLayout mainContent = new VerticalLayout();
		mainContent.setMargin(true);
		mainContent.setSpacing(true);
		mainContent.setSizeFull();

		Panel mainPanel = new Panel();
		mainPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
		mainPanel.addStyleName("border-top");
		mainPanel.setHeight("100%"); //$NON-NLS-1$

		if (question != null) {
			Label lblQuestion = new Label(question);
			lblQuestion.setWidth("80%"); //$NON-NLS-1$
			mainPanel.setContent(lblQuestion);
		}

		HorizontalLayout buttonPanel = new HorizontalLayout();
		buttonPanel.setWidth("100%"); //$NON-NLS-1$
		buttonPanel.setSpacing(true);

		yes.addStyleName(ValoTheme.BUTTON_PRIMARY);
		no.addStyleName(ValoTheme.BUTTON_DANGER);

		yes.setSizeFull();
		no.setSizeFull();
		buttonPanel.addComponents(yes, no);

		mainContent.addComponents(mainPanel, buttonPanel);
		mainContent.setExpandRatio(mainPanel, 1);
		mainContent.setComponentAlignment(buttonPanel, Alignment.BOTTOM_RIGHT);
		setContent(mainContent);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getSource() == yes) {
			if (callback != null) {
				callback.dialogClosed(true);
			}
		} else if (event.getSource() == no && noOptionCallback) {
			callback.dialogClosed(false);
		}
		close();
	}

	public interface Callback {
		public void dialogClosed(boolean isApproved);
	}
}