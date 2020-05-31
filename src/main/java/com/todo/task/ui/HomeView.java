package com.todo.task.ui;

import com.todo.conf.SecurityUtils;
import com.todo.layout.HomeLayout;
import com.todo.task.service.CommentService;
import com.todo.task.service.TaskService;
import com.todo.task.ui.view.CalendarView;
import com.todo.task.ui.view.TaskListView;
import com.todo.task.ui.view.TaskView;
import com.todo.user.entity.User;
import com.todo.user.service.UserSerive;
import com.todo.utils.ExposedViews;
import com.todo.utils.LayoutUtils;
import com.todo.utils.Utils;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;

@Tag(Tag.DIV)
@PageTitle("TaskBoard | Todo")
@Route(value = "", layout = HomeLayout.class)
@CssImport(value = "./evo-calendar/css/evo-calendar.css")
@CssImport(value = "./styles/shared-styles.css", themeFor = "vaadin-app-layout")
@NpmPackage(value = "jquery", version = "3.4.1")
@JavaScript(value = "./js/jquery-loader.js")
@JavaScript(value = "./evo-calendar/js/evo-calendar.js")
@JavaScript(value = "./js/home.js")
public class HomeView extends VerticalLayout implements BeforeEnterObserver, HasUrlParameter<String>{
    private static final Logger logger= LogManager.getLogger(HomeView.class);

    private final TaskService taskService;
    private final CommentService commentService;
    private final UserSerive userSerive;

    private final TaskView taskView;
    private final TaskListView taskListView;
    private final CalendarView calendarView;

    private final User loggedUser;

    private LocalDate taskDate;
    public HomeView(@Autowired TaskService taskService,
                    @Autowired CommentService commentService,
                    @Autowired UserSerive userSerive){
        this.taskService=taskService;
        this.commentService=commentService;
        this.userSerive=userSerive;
        this.taskDate= LocalDate.now();

        /*load logged user*/
        String loggedUserName=SecurityUtils.getLoggedUserName();
        logger.debug("loggedUser= "+loggedUserName);
        this.loggedUser= userSerive.loadUserByUserName(loggedUserName);

        /*initialize task component*/
        this.taskView=new TaskView(
                taskService,
                commentService,
                userSerive,
                taskDate,
                loggedUser
        );

        /*initialize taskList view*/
        this.taskListView= new TaskListView(
                taskService,
                userSerive,
                taskView,
                taskDate,
                loggedUser
        );

        /*initialize Calendar component*/
        this.calendarView=new CalendarView(
                taskService,
                userSerive,
                taskListView,
                taskDate,
                loggedUser
        );

        /*make a structure of landing page*/
        addClassName("taskboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        Div taskBoard= new Div();
        Div row1 = new Div();
        row1.addClassName("content-row");
        Div row2 = new Div();
        row2.addClassName("content-row");

        Div col1= new Div();
        col1.addClassName("content-col");
        col1.add(calendarView);
        Div col2= new Div();
        col2.addClassName("content-col");
        col2.add(taskListView);

        row1.add(col1, col2);
        row2.add(taskView);

        taskBoard.add(row1, new HtmlComponent("br"), row2);

        add(LayoutUtils.getBodyContainer(taskBoard));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!SecurityUtils.isUserLoggedIn()){
            event.rerouteTo(Utils.getRelativePath(ExposedViews.LOGIN.getUri()));
        }
    }

    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String parameter) {
    }
}

