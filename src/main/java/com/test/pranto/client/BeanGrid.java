package com.test.pranto.client;

import java.util.Collection;

import com.vaadin.data.Container.Indexed;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.ui.Grid;

public abstract class BeanGrid<T> extends Grid {

	public BeanGrid() {
		setSizeFull();
		setSelectionMode(SelectionMode.SINGLE);
	}

	public void addColumn(String displayString, String fieldName) {
		addColumn(fieldName).setHeaderCaption(displayString);
	}

	@Override
	public T getSelectedRow() throws IllegalStateException {
		return (T) super.getSelectedRow();
	}

	public void setItems(Collection<T> items) {
		BeanItemContainer<T> container = new BeanItemContainer<T>(getReferenceClass());
		if (items != null) {
			container.addAll(items);
		}
		setContainerDataSource(container);
	}

	public void addOrUpdateItem(Object product) {
		Item item = getContainerDataSource().getItem(product);
		if (item != null) {
			MethodProperty p = (MethodProperty) item.getItemProperty("id"); //$NON-NLS-1$
			if (p == null) {
				p = (MethodProperty) item.getItemProperty("autoId"); //$NON-NLS-1$
			}
			p.fireValueChange();
		} else {
			getContainerDataSource().addItem(product);
		}
	}


	public void remove(T product) {
		getContainer().removeItem(product);
	}


	public void removeAll() {
		Indexed source = this.getContainerDataSource();
		if (source != null) {
			source.removeAllItems();
		}
	}

	public BeanItemContainer<T> getContainer() {
		return (BeanItemContainer<T>) super.getContainerDataSource();
	}

	public abstract Class getReferenceClass();

}
