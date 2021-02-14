package com.test.pranto.client;

import com.test.pranto.client.notification.NotificationUtill;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public abstract class FormEditorView<T> extends VerticalLayout implements View {
	protected Button btnSave = new Button("Save");
	protected Button btnClose = new Button("Close");

	public void setCancelButtonVisible(boolean visible) {
		btnClose.setVisible(visible);
	}

	protected BeanFieldGroup<T> binder;

	private T bean;
	private boolean viewInitialized = false;

	private Label heading;

	public void setHeading(String caption) {
		heading.setValue(caption);
	}

	public FormEditorView() {
	}

	@Override
	public void enter(ViewChangeEvent event) {
		bean = getBean();
		if (bean == null)
			return;

		if (!viewInitialized) {
			initUI();
			viewInitialized = true;
		}
	}

	public boolean isViewInitialized() {
		return viewInitialized;
	}

	@SuppressWarnings("unchecked")
	private void initUI() {
		setSizeFull();

		btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnClose.addStyleName(ValoTheme.BUTTON_DANGER);

		binder = new BeanFieldGroup<T>((Class<T>) bean.getClass());
		binder.setItemDataSource(bean);

		AbstractOrderedLayout formLayout = createForm(binder);

		btnSave.addClickListener(event -> doSaveChenges());
		btnClose.addClickListener(event -> doClose());

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		buttonLayout.addStyleName("csslayout-margin-bottom csslayout-margin-right csslayout-margin-top");
		buttonLayout.addComponent(btnSave);
		buttonLayout.addComponent(btnClose);
		addButton(buttonLayout);
		buttonLayout.setComponentAlignment(btnSave, Alignment.MIDDLE_CENTER);
		buttonLayout.setComponentAlignment(btnClose, Alignment.MIDDLE_CENTER);

		HorizontalLayout contentLayout = new HorizontalLayout();
		contentLayout.setMargin(false);
		formLayout.addComponent(contentLayout);
		initComponents(contentLayout);
		Panel container = new Panel();
		container.setSizeFull();
		container.setContent(formLayout);

		HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.addStyleName("csslayout-margin-left");
		headerLayout.setHeight("40px");
		headerLayout.setWidth("100%");
		headerLayout.setSpacing(true);

		heading = new Label();
		heading.addStyleName(ValoTheme.LABEL_BOLD);

		headerLayout.addComponent(heading);
		headerLayout.setComponentAlignment(heading, Alignment.MIDDLE_LEFT);
		headerLayout.setExpandRatio(heading, 1);

		addComponents(headerLayout, container, buttonLayout);
		setComponentAlignment(buttonLayout, Alignment.MIDDLE_RIGHT);
		setExpandRatio(container, 1);

		updateForm(bean);
	}

	private void doSaveChenges() {
		try {
			if (doCheckValidation()) {
				return;
			}

			binder.commit();
			if (!save())
				return;

			NotificationUtill.showMessage("Successfully updated.");

		} catch (CommitException e1) {
			NotificationUtill.showMessage("Please fill in all required fields.", 500);
		} catch (Exception e2) {
			e2.printStackTrace();
			NotificationUtill.showMessage("Error: " + e2.getMessage(), 500);
		}
	}

	protected boolean doCheckValidation() {
		return false;
	}

	protected abstract boolean save();

	protected abstract void doClose();

	public abstract AbstractOrderedLayout createForm(BeanFieldGroup<T> binder);

	protected void updateForm(T bean) {
	};

	public abstract void initComponents(HorizontalLayout contentLayout);

	protected void addButton(HorizontalLayout buttonLayout) {

	}

	public T getBean() {
		return this.bean;
	}

	public void setBean(T bean) {
		this.bean = bean;
	}

	public T getProduct() {
		return binder.getItemDataSource().getBean();
	}

}
