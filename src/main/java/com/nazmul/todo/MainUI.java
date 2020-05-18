package com.nazmul.todo;

import com.nazmul.todo.service.NavigationEvent;
import com.nazmul.todo.service.NavigationService;
import com.nazmul.todo.view.TodosView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@CDIUI("")
@Theme("apptheme")
@Title("todo")
public class MainUI extends UI implements NavigationService {

    private static final long serialVersionUID = 1L;
    static final String APP_NAME = "todo";
    
    private Navigator navigator;
    
    @Inject
    private CDIViewProvider viewProvider;
    
    @Inject
    private WelComeView welcomeView;
    
    @Inject
    private ErrorView errorView;
    
    private LocalDate selectedDate = LocalDate.now();

    @Override
    public void init(VaadinRequest request) {
        runMigration();
        navigator = new Navigator(this, this);
        navigator.addView("", welcomeView);
        navigator.setErrorView(errorView);
        navigator.addProvider(viewProvider);
        
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }
    
    public static MainUI getInstance() {
        return (MainUI) UI.getCurrent();
    }

    @Override
    public void onNavigationEvent(@Observes NavigationEvent event) {
        try {
            String viewUri = event.getItemId() == null ? event.getNavigateTo()
                    : event.getNavigateTo() + "/" + event.getItemId();
            
            getNavigator().navigateTo(viewUri);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void runMigration() {
         
        try {
            
            File file = new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF");
            URL[] toURL = {file.toURI().toURL()};
            ClassLoader loader = new URLClassLoader(toURL);
            ResourceBundle bundle = ResourceBundle.getBundle("config", Locale.ROOT, loader);
            
            FlywayMigrationHelper.runMigration(
                    bundle.getString("dbUri"), 
                    bundle.getString("dbUserName"),
                    bundle.getString("dbPassword")
            );
        } catch (Exception e) {
            Logger.getLogger(MainUI.class.getName())
                    .log(Level.SEVERE,
                            "Problem while running the migration.",
                            e);
        }
    }
    
}



