package com.test.pranto.client.ui;

import java.util.List;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class Menu extends CssLayout {

	private Navigator navigator;
	private Tree barTree;

	public Menu(Navigator navigator) {
		this.navigator = navigator;
		setPrimaryStyleName(ValoTheme.MENU_ROOT);

		barTree = new Tree();
		barTree.setWidth("100%"); //$NON-NLS-1$
		barTree.setSelectable(true);
		barTree.addStyleName(ValoTheme.MENU_PART);
		addComponent(barTree);

		barTree.addItemClickListener(event -> doExpandOrCollapseTree(navigator, event));

	}

	public Component buildUserMenu() {
		final MenuBar settings = new MenuBar();

		settings.addItem("", FontAwesome.USER, null); //$NON-NLS-1$

		return settings;
	}

	/**
	 * Register a pre-created view instance in the navigation menu and in the
	 * {@link Navigator}.
	 *
	 * @see Navigator#addView(String, View)
	 *
	 * @param view
	 *            view instance to register
	 * @param name
	 *            view name
	 * @param caption
	 *            view caption in the menu
	 * @param icon
	 *            view icon in the menu
	 */

	public void removeAllViews(List<String> views) {
		for (String view : views) {
			navigator.removeView(view);
		}
	}

	public void addChildTreeItem(String viewName, Class<? extends View> viewClass, String parentName, Resource icon,
			boolean isHasChild) {
		if (viewClass != null) {
			navigator.addView(viewName.replaceAll("\\s+", ""), viewClass); //$NON-NLS-1$ //$NON-NLS-2$
		}

		barTree.addItem(viewName);
		barTree.setChildrenAllowed(viewName, isHasChild);
		if (parentName != null) {
			barTree.setParent(viewName, parentName);
		}
		if (viewClass != null) {
			barTree.setItemIcon(viewName, icon);
		}
	}

	/**
	 * Highlights a view navigation button as the currently active view in the
	 * menu. This method does not perform the actual navigation.
	 *
	 * @param viewName
	 *            the name of the view to show as active
	 */

	public void setActiveTreeView(String viewName) {
		barTree.expandItem(viewName);
		navigate(viewName);
	}

	public void navigate(String viewName) {
		navigator.navigateTo(viewName);
	}

	private void doExpandOrCollapseTree(Navigator navigator, ItemClickEvent event) {
		try {
			String itemId = (String) event.getItemId();
			menuSelected(navigator, itemId);
		} catch (Exception e) {
		}
	}

	public void menuSelected(String itemId) {
		menuSelected(navigator, itemId);
		barTree.select(itemId);
	}

	private void menuSelected(Navigator navigator, String itemId) {
		if (barTree.hasChildren(itemId)) {
			if (!barTree.isExpanded(itemId)) {
				barTree.expandItem(itemId);
			} else {
				barTree.collapseItem(itemId);
			}
		} else {
			navigator.navigateTo(itemId.replaceAll("\\s+", "")); //$NON-NLS-1$ //$NON-NLS-2$
			int windowWidth = UI.getCurrent().getPage().getBrowserWindowWidth();
			if (windowWidth > 0 && windowWidth < 800) {
				setVisible(false);
			}
		}
	}

	public void expandItem(String id) {
		barTree.expandItem(id);
	}

	public void setSelected(String navigateTo) {
		barTree.select(navigateTo);
	}
}
