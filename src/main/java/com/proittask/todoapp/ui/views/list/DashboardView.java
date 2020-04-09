package com.proittask.todoapp.ui.views.list;

import com.proittask.todoapp.backend.service.ToDoService;
import com.proittask.todoapp.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Map;

@PageTitle("Dashboard | ToDo App")
@Route(value = "dashboard", layout = MainView.class)
public class DashboardView extends VerticalLayout {

    private final ToDoService toDoService;

    public DashboardView(ToDoService toDoService) {
        this.toDoService = toDoService;

        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(
                new H3("Chart View of Item Status"),
                getItemChart(),
                getItemStats()

        );
    }

    private Span getItemStats() {
        Span stats = new Span(toDoService.count() + " items");
        stats.addClassName("item-stats");
        return stats;
    }

    private Component getItemChart() {
        Chart item = new Chart(ChartType.PIE);
        DataSeries dataSeries = new DataSeries();
        Map<String, Integer> stats = toDoService.getStats();
        stats.forEach((name, number) ->
                dataSeries.add(new DataSeriesItem(name, number))
        );
        item.getConfiguration().setSeries(dataSeries);
        return item;
    }
}
