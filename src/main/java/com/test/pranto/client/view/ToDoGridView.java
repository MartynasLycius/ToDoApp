package com.test.pranto.client.view;

import java.util.List;

import com.test.pranto.client.BeanGrid;
import com.test.pranto.client.GridExplorerView;
import com.test.pranto.client.form.ToDoform;
import com.test.pranto.client.grid.ToDoGrid;
import com.test.pranto.client.notification.NotificationUtill;
import com.test.pranto.client.notification.YesNoDialog.Callback;
import com.test.pranto.model.ToDo;
import com.test.pranto.model.dao.ToDoDAO;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid.SelectionModel;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * A view for performing create-read-update-delete operations on products.
 *
 * See also {@link InvItemCrudLogic} for fetching the data, the actual CRUD
 * operations and controlling the view based on events from outside.
 */
public class ToDoGridView extends GridExplorerView<ToDo> implements Callback {

	public static final String VIEW_NAME = "Todo";
	private TextField tfNameField;
	private ToDo todo;

	public ToDoGridView() {
	}

	private void doSearch() {
		reloadPage();
	}

	public void clearSelection() {
		grid.getSelectionModel().reset();
	}

	public void selectRow(ToDo row) {
		((SelectionModel.Single) grid.getSelectionModel()).select(row);
	}

	public ToDo getSelectedRow() {
		return (ToDo) grid.getSelectedRow();
	}

	@Override
	protected void doCreateNewItem() {
		Navigator navigator = UI.getCurrent().getNavigator();
		ToDoform view = new ToDoform(this, new ToDo(), "Create new todo");
		navigator.addView(ToDoform.VIEW_NAME, view);
		navigator.navigateTo(ToDoform.VIEW_NAME);
	}

	@Override
	protected void doEditItem(ToDo todo) {
		if (todo == null) {
			NotificationUtill.showErrorMessage("Please select a todo!");
			return;
		}

		Navigator navigator = UI.getCurrent().getNavigator();
		ToDoform view = new ToDoform(this, todo, "Edit " + todo.getItemName());
		navigator.addView(ToDoform.VIEW_NAME, view);
		navigator.navigateTo(ToDoform.VIEW_NAME);
	}

	@Override
	protected void doDeleteItem(ToDo todo) {
		if (todo == null) {
			NotificationUtill.showErrorMessage("Please select a todo!");
			return;
		}
		this.todo = todo;

		NotificationUtill.showYesNoDialog("Delete ", "Are you sure?", this); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public void dialogClosed(boolean isApproved) {
		if (isApproved) {
			onApprove();
		}
	}

	public void onApprove() {
		try {
			ToDoDAO.getInstance().delete(todo);
			grid.remove(todo);
		} catch (Exception e) {
			NotificationUtill.showErrorMessage(e.getMessage());
			return;
		}
		NotificationUtill.showMessage("Delete Successfilly!");
	}

	@Override
	protected BeanGrid<ToDo> createItemGrid() {
		ToDoGrid toDoGrid = new ToDoGrid();
		toDoGrid.addStyleName("custom-grid-cell-focus"); //$NON-NLS-1$
		return toDoGrid;
	}

	@Override
	protected void addButton(HorizontalLayout buttonLayout) {
	}

	@Override
	public void updateView() {
		String searchName = tfNameField.getValue();
		if (searchName != null) {
			searchName = searchName.trim();
		}
		List<ToDo> findAll = ToDoDAO.getInstance().findToDos(searchName);
		grid.setItems(findAll);
	}

	@SuppressWarnings("serial")
	public VerticalLayout createTopBar() {
		ShortcutListener shortcutListener = new ShortcutListener("Enter key pressed", ShortcutAction.KeyCode.ENTER, //$NON-NLS-1$
				null) {
			@Override
			public void handleAction(Object sender, Object target) {
				doSearch();
			}
		};

		CssLayout topLayout = new CssLayout();
		topLayout.setCaption("Search");
		topLayout.addStyleName("");

		tfNameField = new TextField();
		tfNameField.setMaxLength(120);
		tfNameField.setInputPrompt("Todo name");
		tfNameField.addShortcutListener(shortcutListener);
		tfNameField.addStyleName("custom-csslayout-margin"); //$NON-NLS-1$

		Button btnSearch = new Button();
		btnSearch.setIcon(FontAwesome.SEARCH);
		btnSearch.addStyleName("custom-csslayout-margin"); //$NON-NLS-1$
		Button btnClear = new Button("Clear");
		btnClear.addStyleName("custom-csslayout-margin"); //$NON-NLS-1$

		topLayout.addComponents(tfNameField, btnSearch, btnClear);

		btnSearch.addClickListener(event -> doSearch());
		btnClear.addClickListener(event -> {
			doClear();
			doSearch();
		});

		VerticalLayout mainTopLayout = new VerticalLayout();
		mainTopLayout.setWidth("100%"); //$NON-NLS-1$
		mainTopLayout.addComponents(topLayout);
		return mainTopLayout;
	}

	private void doClear() {
		tfNameField.setValue(""); //$NON-NLS-1$
	}

}
