package com.vaadin.zahid.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import javax.annotation.PostConstruct;


public class Layout extends VerticalLayout implements RouterLayout {


  @PostConstruct
  void init() {
    addMenu();
    addHeader();

  }

  private void addMenu() {

    HorizontalLayout menuLayout =new HorizontalLayout();
    MenuBar menuBar=new MenuBar();
    //view list
    RouterLink routerLink = new RouterLink("", MainVIew.class);
    routerLink.add(VaadinIcon.LIST.create(), new Text(""));
    menuBar.addItem(routerLink);

    //add new items
    RouterLink addNew = new RouterLink("", AddItemView.class);
    addNew.add(VaadinIcon.PLUS.create(), new Text(""));
    menuBar.addItem(addNew);
    // Edit Items
    RouterLink editItem = new RouterLink("", EditItemView.class);
    editItem.add(VaadinIcon.EDIT.create(), new Text(""));
    menuBar.addItem(editItem);

    menuLayout.add(menuBar);
    setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    add(menuLayout);
  }


  private void addHeader() {
    Label header = new Label("To-Do");
    Label blank = new Label("");
    Label blank1 = new Label("");
    add(header,blank,blank1);
  }


}
