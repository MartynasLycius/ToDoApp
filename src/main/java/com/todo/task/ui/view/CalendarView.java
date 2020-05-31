package com.todo.task.ui.view;

import com.todo.task.service.TaskService;
import com.todo.user.entity.User;
import com.todo.user.service.UserSerive;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.html.Div;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CalendarView extends Div {
    private static final Logger logger= LogManager.getLogger(CalendarView.class);

    private final TaskService taskService;
    private final UserSerive userSerive;
    private final TaskListView taskListView;
    private final User loggedUser;

    private LocalDate taskDate;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public CalendarView(TaskService taskService,
                        UserSerive userSerive,
                        TaskListView taskListView,
                        LocalDate taskDate,
                        User loggedUser){
        this.taskService=taskService;
        this.userSerive=userSerive;
        this.taskListView=taskListView;
        this.taskDate=taskDate;
        this.loggedUser=loggedUser;

        addClassName("calendar-view");
        setId("evoCalendar");

        Div calendar= new Div();
        calendar.setId("todo-calendar");

        getElement().executeJs("var dateBinderElement=null;\n" +
                "window.greet = function greet(element) {\n" +
                "  dateBinderElement=element;\n" +
                "};\n" +
                "\n" +
                "$( document ).ready(function() {\n" +
                "  var calElem= $('#todo-calendar');\n" +
                "  var evoCal = calElem.evoCalendar({\n" +
                "    /*calendarEvents: myEvents,*/\n" +
                "    format: 'mm/dd/yyyy',\n" +
                "    titleFormat: 'MM yyyy',\n" +
                "    eventHeaderFormat: 'MM d, yyyy',\n" +
                "    todayHighlight: true,\n" +
                "    sidebarToggler: true,\n" +
                "    sidebarDisplayDefault: false,\n" +
                "    eventListToggler: false,\n" +
                "    eventDisplayDefault: false,\n" +
                "    onSelectDate:function (event) {\n" +
                "      var date= $(event.target).data('date-val');\n" +
                "      console.log(\"dateSelected.............:: \"+ $(event.target));\n" +
                "      dateBinderElement.$server.setData(date);\n" +
                "    }\n" +
                "  });\n" +
                "});");
        getElement().executeJs("greet($0)",  getElement());

        add( calendar);
    }

    @ClientCallable
    public void setData(String date) {
        taskDate=LocalDate.parse(date.trim(), dateTimeFormatter);
        logger.debug("client selected date : "+ taskDate);
        taskListView.updateList(taskDate);
    }
}
