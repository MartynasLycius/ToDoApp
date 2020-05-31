package com.todo.utils;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.menubar.MenuBar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProfileMenuBar extends MenuBar {
    private static final Logger logger= LogManager.getLogger(ProfileMenuBar.class);

    public ProfileMenuBar(String profileName){
        addClassName("profile-menu-bar");
        setOpenOnHover(true);

        MenuItem profileMenu = addItem("Profile :-: "+profileName);
        profileMenu.getElement().getStyle().set("font-size", "var(--lumo-font-size-s)");
        //profileMenu.getElement().getParent().setProperty("style", "background:none");

        SubMenu profileSubMenu = profileMenu.getSubMenu();
        MenuItem userProfile = profileSubMenu.addItem(
                new Anchor(Utils.getRelativePath(ExposedViews.REGISTRATION.getUri()), "Profile"));
        userProfile.getElement().getStyle().set("font-size", "var(--lumo-font-size-s)");
        MenuItem logOut = profileSubMenu.addItem(
                new Anchor(Utils.getRelativePath(ExposedViews.LOGOUT.getUri()), "Logout"));
        logOut.getElement().getStyle().set("font-size", "var(--lumo-font-size-s)");
    }
}
