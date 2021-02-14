package com.test.pranto.client.form;

import java.util.Date;

import com.test.pranto.client.FormEditorView;
import com.test.pranto.client.GridExplorerView;
import com.test.pranto.client.notification.NotificationUtill;
import com.test.pranto.client.view.ToDoGridView;
import com.test.pranto.model.ToDo;
import com.test.pranto.model.dao.ToDoDAO;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.Navigator;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class ToDoform extends FormEditorView<ToDo> {
	public static final String VIEW_NAME = "AddTodo";

	private GridExplorerView<ToDo> crudView;
	private String caption;

	private TextField tfName;
	private TextArea tfDescription;
	private DateField dpTodoDate;

	public ToDoform(GridExplorerView<ToDo> crudView, ToDo toDo, String caption) {
		this.crudView = crudView;
		this.caption = caption;
		setBean(toDo);
	}

	@Override
	protected void updateForm(ToDo toDo) {
		setHeading(caption);
	}

	@Override
	protected boolean doCheckValidation() {
		String name = tfName.getValue();
		if (name == null || name == "") {
			NotificationUtill.showMessage("Please enter name!");
			return true;
		}
		return false;
	}

	@Override
	protected boolean save() {
		try {
			Date currentTime = new Date();

			BeanItem<ToDo> dataSource = binder.getItemDataSource();
			ToDo toDo = dataSource.getBean();

			if (toDo.getId() == null) {
				toDo.setId("" + System.currentTimeMillis());
				toDo.setCreateDate(currentTime);
				toDo.setLastUpdateTime(currentTime);
			} else {
				toDo.setLastUpdateTime(currentTime);
			}

			ToDoDAO.getInstance().saveOrUpdate(toDo);
			doClose();
			// crudView.refresh(bean);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			NotificationUtill.showMessage("Error: " + e.getMessage(), 500);
		}
		return false;
	}

	@Override
	protected void doClose() {
		Navigator navigator = UI.getCurrent().getNavigator();
		navigator.navigateTo(ToDoGridView.VIEW_NAME);

	}

	@Override
	public AbstractOrderedLayout createForm(BeanFieldGroup<ToDo> binder) {
		FormLayout formLayout = new FormLayout();
		formLayout.setMargin(true);

		tfName = new TextField("Name");
		tfName.setNullRepresentation(""); //$NON-NLS-1$
		tfName.setRequired(true);
		tfName.setMaxLength(120);
		tfName.setWidth("100%"); //$NON-NLS-1$
		binder.bind(tfName, ToDo.PROP_ITEM_NAME);

		tfDescription = new TextArea("Description");
		tfDescription.setNullRepresentation(""); //$NON-NLS-1$
		tfDescription.setMaxLength(250);
		tfDescription.setWidth("100%"); //$NON-NLS-1$
		binder.bind(tfDescription, ToDo.PROP_DESCRIPTION);

		dpTodoDate = new DateField("Date"); //$NON-NLS-1$
		dpTodoDate.setResolution(Resolution.MINUTE);
		dpTodoDate.setWidth("100%"); //$NON-NLS-1$
		dpTodoDate.setDateFormat("yyyy-MM-dd HH:mm");
		binder.bind(dpTodoDate, ToDo.PROP_TO_DO_DATE);

		formLayout.addComponents(tfName, tfDescription, dpTodoDate);

		formLayout.setSpacing(true);
		formLayout.setMargin(true);

		return formLayout;
	}

	@Override
	public void initComponents(HorizontalLayout contentLayout) {
	}

}
