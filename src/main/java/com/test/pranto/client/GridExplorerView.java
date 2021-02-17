package com.test.pranto.client;

import com.test.pranto.client.notification.NotificationUtill;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid.SelectionModel;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * A view for performing create-read-update-delete operations on products.
 *
 * See also {@link InvItemCrudLogic} for fetching the data, the actual CRUD
 * operations and controlling the view based on events from outside.
 */
public abstract class GridExplorerView<T> extends CssLayout implements View {
	protected BeanGrid<T> grid;

	private Button btnNew;
	private Button btnEdit;
	private Button btnDelete;

	private boolean viewInitialized = false;
	private boolean showHeader = true;

	public GridExplorerView() {
	}

	public void setShowHeader(boolean showHeader) {
		this.showHeader = showHeader;
	}

	public void initUI() {
		setSizeFull();
		VerticalLayout topLayout = createTopBar();
		if (!showHeader) {
			topLayout.setVisible(false);
		}
		topLayout.addStyleName("border csslayout-margin-bottom custom-csslayout-padding"); //$NON-NLS-1$
		grid = createItemGrid();
		((SelectionModel.Single) grid.getSelectionModel()).setDeselectAllowed(false);

		VerticalLayout barAndGridLayout = new VerticalLayout();
		barAndGridLayout.addStyleName("csslayout-margin-top csslayout-margin-bottom custom-csslayout-padding"); //$NON-NLS-1$
		barAndGridLayout.setSizeFull();
		barAndGridLayout.addComponent(topLayout);

		barAndGridLayout.addComponent(grid);
		barAndGridLayout.setExpandRatio(grid, 2);

		btnNew = new Button("New");
		btnNew.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnEdit = new Button("Edit");
		btnEdit.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnDelete = new Button("Delete");
		btnDelete.addStyleName(ValoTheme.BUTTON_DANGER);

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setWidth("100%"); //$NON-NLS-1$
		horizontalLayout.setSpacing(true);
		horizontalLayout.addStyleName("custom-csslayout-margin"); //$NON-NLS-1$

		horizontalLayout.addComponent(btnNew);
		horizontalLayout.addComponent(btnEdit);
		horizontalLayout.addComponent(btnDelete);
		addButton(horizontalLayout);

		HorizontalLayout space = new HorizontalLayout();
		space.setHeight("40px");
		horizontalLayout.addComponent(space);
		horizontalLayout.setComponentAlignment(space, Alignment.MIDDLE_RIGHT);
		horizontalLayout.setExpandRatio(space, 1);
		barAndGridLayout.addComponents(horizontalLayout);

		addComponent(barAndGridLayout);

		btnNew.addClickListener(event -> doCreateNewItem());
		btnEdit.addClickListener(event -> {
			T selectedRow = grid.getSelectedRow();
			doEditItem(selectedRow);
		});
		btnDelete.addClickListener(event -> {
			T selectedRow = grid.getSelectedRow();
			try {
				doDeleteItem(selectedRow);
			} catch (Exception ex) {
				NotificationUtill.showErrorMessage("Error " + ex.getMessage());
			}
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if (!viewInitialized) {
			initUI();
			viewInitialized = true;
		}
		reloadPage();
	}

	public abstract void updateView();

	protected abstract void doCreateNewItem();

	protected abstract void doEditItem(T selectedRow);

	protected abstract void doDeleteItem(T selectedRow);

	protected abstract BeanGrid<T> createItemGrid();

	public abstract VerticalLayout createTopBar();

	protected abstract void addButton(HorizontalLayout buttonLayout);

	public void setNewProductEnabled(boolean enabled) {
		btnNew.setEnabled(enabled);
	}

	public void setEditProductEnabled(boolean enabled) {
		btnEdit.setEnabled(enabled);
	}

	public void setEditProductVisible(boolean visible) {
		btnEdit.setVisible(visible);
	}

	public void setNewProductVisible(boolean visible) {
		btnNew.setVisible(visible);
	}

	public void setDeleteProductVisible(boolean visible) {
		btnDelete.setVisible(visible);
	}

	public void clearSelection() {
		grid.getSelectionModel().reset();
	}

	public void refresh(T product) {
		if (product == null)
			return;

		grid.addOrUpdateItem(product);
		grid.recalculateColumnWidths();
		grid.clearSortOrder();
	}

	public void reloadPage() {
		updateView();
	}

	public BeanGrid<T> getGrid() {
		return grid;
	}

}
