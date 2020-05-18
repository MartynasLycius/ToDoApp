package com.nazmul.todo.chart.view;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.config.ChartConfig;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.config.PieChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.data.PieDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.nazmul.todo.constant.CategoryType;
import com.nazmul.todo.constant.ViewIdentifires;
import com.nazmul.todo.domain.TodoItem;
import com.vaadin.cdi.CDIView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

@CDIView(ViewIdentifires.STATS_VIEW)
public class ChartView extends MVerticalLayout implements View {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private IChartDataPresenter presenter;
    
    private GridLayout chartGridLayout;

    @PostConstruct
    public void initComponent() {

        this.setSpacing(true);
        this.setSizeFull();

        MHorizontalLayout header = generateHeaderLayout();
        initializeChartGridLayout();
        
        addComponents(header, chartGridLayout);
        setExpandRatio(header, .1f);
        setExpandRatio(chartGridLayout, .9f);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    private MHorizontalLayout generateHeaderLayout() {
        MHorizontalLayout mh = new MHorizontalLayout()
                .withSpacing(true)
                .withMargin(false)
                .withFullWidth()
                .withHeight("60px");

        MLabel label = new MLabel("Your Stats of todos for the past week")
                .withUndefinedSize()
                .withStyleName(ValoTheme.LABEL_H2 + " " + ValoTheme.LABEL_COLORED);
        
        MButton backbButton = new MButton()
                .withStyleName(ValoTheme.BUTTON_BORDERLESS + " " +
                        ValoTheme.BUTTON_ICON_ONLY + " " +
                        ValoTheme.BUTTON_BORDERLESS)
                .withIcon(VaadinIcons.ARROW_LEFT);
        
        backbButton.addClickListener(() -> {
            presenter.navigateTo(ViewIdentifires.TODOS_VIEW);
        });

        mh.add(backbButton, Alignment.MIDDLE_LEFT);
        mh.add(label, Alignment.MIDDLE_LEFT);
        
        mh.setExpandRatio(backbButton, .1f);
        mh.setExpandRatio(label, .9f);

        return mh;
    }

    private void initializeChartGridLayout() {
        chartGridLayout = new GridLayout(2, 2);
        chartGridLayout.setSizeFull();

        ChartConfig lineChartConfig = generatePerformanceLineCharConfig("Performance based on completion.");
        MVerticalLayout lineChart = initializePerformanceLineChart(lineChartConfig);
        
        ChartConfig categoryWiseBarChart = generateCategoryWiseTotalCountBarCharConfig("Category wise total todos");
        MVerticalLayout barChart = initializePerformanceLineChart(categoryWiseBarChart);
        
        ChartConfig completedPieChart = generateCategoryWiseOnCompletionPieCharConfig("Category wise total completed todos",
                TodoItem::getDone);
        MVerticalLayout compPiechart = initializePerformanceLineChart(completedPieChart);
        
        ChartConfig incompletedPieChart = generateCategoryWiseOnCompletionPieCharConfig("Category wise total incompleted todos", 
                (t) -> !t.getDone());
        MVerticalLayout incompPiechart = initializePerformanceLineChart(incompletedPieChart);
        
        chartGridLayout.addComponents(lineChart, barChart, compPiechart, incompPiechart);
    }

    private MVerticalLayout initializePerformanceLineChart(ChartConfig config) {
        MVerticalLayout mv = new MVerticalLayout()
                .withMargin(false)
                .withFullWidth()
                .withHeight("50%");

        ChartJs chart = new ChartJs(config);
        chart.setResponsive(true);
        chart.setJsLoggingEnabled(true);
        chart.setSizeFull();
        chart.setWidth("100%");
        chart.setHeight(25f, Unit.PERCENTAGE);

        mv.add(chart);
        
        return mv;
    }

    private ChartConfig generatePerformanceLineCharConfig(String caption) {
        
        LineDataset completedDataset = new LineDataset();
        completedDataset.type()
                .label("Completed")
                .borderColor("green")
                .borderWidth(2)
                .dataAsList(presenter.getCountOfTodosByDateOnBasedOnFilter(
                        TodoItem::getDone,
                        LocalDate.now(),
                        6));
        
        LineDataset unCompletedDataset = new LineDataset();
        unCompletedDataset.type()
                .label("Incompleted")
                .borderColor("red")
                .borderWidth(2)
                .dataAsList(presenter.getCountOfTodosByDateOnBasedOnFilter(
                        (t) -> !t.getDone(),
                        LocalDate.now(),
                        6));
        
        LineChartConfig config = new LineChartConfig();
        config.data()
                .labels(presenter.getFormattedPastDaysDates(LocalDate.now(), 6))
                .addDataset(completedDataset)
                .addDataset(unCompletedDataset);
        
        config.options()
                .responsive(true)
                .title()
                .display(true)
                .position(Position.LEFT)
                .text(caption)
                .fontColor("rgba(255, 255, 255, 1)")
                .and()
                .done();
        
        return config;
    }

    private ChartConfig generateCategoryWiseTotalCountBarCharConfig(String caption) {
        
        BarDataset workDataset = new BarDataset();
        workDataset.type()
                .label(CategoryType.WORK.getKey().toUpperCase())
                .backgroundColor(CategoryType.WORK.getColor())
//                .borderColor("green")
                .borderWidth(2)
                .dataAsList(presenter.getCountOfTodosByDateOnBasedOnFilter(
                        (t) -> Objects.equals(t.getCategory().getId(), 
                                                CategoryType.WORK.getId()),
                        LocalDate.now(),
                        6));
        
        BarDataset homedDataset = new BarDataset();
        homedDataset.type()
                .label(CategoryType.HOME.getKey().toUpperCase())
                .backgroundColor(CategoryType.HOME.getColor())
//                .borderColor("red")
                .borderWidth(2)
                .dataAsList(presenter.getCountOfTodosByDateOnBasedOnFilter(
                        (t) -> Objects.equals(t.getCategory().getId(), 
                                                CategoryType.HOME.getId()),
                        LocalDate.now(),
                        6));
        
        BarDataset personalDataset = new BarDataset();
        personalDataset.type()
                .label(CategoryType.PERSONAL.getKey().toUpperCase())
                .backgroundColor(CategoryType.PERSONAL.getColor())
//                .borderColor("red")
                .borderWidth(2)
                .dataAsList(presenter.getCountOfTodosByDateOnBasedOnFilter(
                        (t) -> Objects.equals(t.getCategory().getId(), 
                                                CategoryType.PERSONAL.getId()),
                        LocalDate.now(),
                        6));
        
        BarChartConfig config = new BarChartConfig();
        config.data()
                .labels(presenter.getFormattedPastDaysDates(LocalDate.now(), 6))
                .addDataset(workDataset)
                .addDataset(homedDataset)
                .addDataset(personalDataset);

        config.options()
                .responsive(true)
                .title()
                .display(true)
                .position(Position.LEFT)
                .text(caption)
                .fontColor("rgba(255, 255, 255, 1)")
                .and()
                .done();
        
        return config;
    }

    private ChartConfig generateCategoryWiseOnCompletionPieCharConfig(String caption, Predicate<TodoItem> filter) {
        String[] categoriesLabel = presenter.getCategoriesLabel(filter);
        
        
        PieDataset categoryDataset = new PieDataset();
        categoryDataset.label("1")
                .backgroundColor(CategoryType.getCategoryTypeColorByEnumNames(categoriesLabel))
                .borderColor("green")
                .borderWidth(2)
                .dataAsList(presenter.getCountOfTodosByCategories(filter))
                .pie();
        
        PieChartConfig config = new PieChartConfig();
        
        config.data()
                .labels(categoriesLabel)
                .addDataset(categoryDataset);
        
        config.options()
                .responsive(true)
                .title()
                .display(true)
                .position(Position.LEFT)
                .text(caption)
                .fontColor("rgba(255, 255, 255, 1)")
                .and()
                .done();
        
        return config;
    }

}
