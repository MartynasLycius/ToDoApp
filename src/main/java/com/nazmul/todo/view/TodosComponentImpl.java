/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.view;

import com.nazmul.todo.constant.CategoryType;
import com.nazmul.todo.MainUI;
import com.nazmul.todo.constant.ViewIdentifires;
import com.nazmul.todo.util.Util;
import com.vaadin.cdi.ViewScoped;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.themes.ValoTheme;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MCheckBox;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MGridLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MMarginInfo;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 *
 * @author nazmul
 */
@ViewScoped
public class TodosComponentImpl extends CustomComponent implements ITodosComponent{
    
    @Inject
    private ITodosPresenter presenter;
    
    private MVerticalLayout mainLayout;
    private MGridLayout categoriesGridlayout;
    private MVerticalLayout todosLayout;


    @PostConstruct
    public void init() {
        presenter.setView(this);
        
        mainLayout = new MVerticalLayout()
                .withFullSize()
                .withSpacing(true)
                .withMargin(new MMarginInfo(false, true, true, true));
        
        initComponent(MainUI.getInstance().getSelectedDate());
        
        setCompositionRoot(mainLayout);
    }
    
    private void initComponent(LocalDate date) {
        mainLayout.removeAllComponents();
        initilizeLayouts();
        presenter.generateTodoCategoriesSection(date);
        generateAddNewTodoItemButton();
        presenter.generateTodosSection();
    }
    
    private void initilizeLayouts() {
        
        categoriesGridlayout = new MGridLayout(4, 1)
                .withFullWidth()
                .withMargin(false);
        
        categoriesGridlayout.setColumnExpandRatio(0, .1f);
        categoriesGridlayout.setColumnExpandRatio(1, .1f);
        categoriesGridlayout.setColumnExpandRatio(2, .1f);
        categoriesGridlayout.setColumnExpandRatio(3, .1f);
        
        todosLayout = new MVerticalLayout()
                .withFullWidth()
                .withSpacing(true)
                .withMargin(new MMarginInfo(true, false, false, false));
        
        MHorizontalLayout header = generateHeader();
        mainLayout.add(header, Alignment.TOP_CENTER);
        
        mainLayout.add(categoriesGridlayout, Alignment.TOP_CENTER);
        mainLayout.add(todosLayout, Alignment.MIDDLE_CENTER);
    }
    
    private MHorizontalLayout generateHeader() {
        MHorizontalLayout mh = new MHorizontalLayout()
                .withFullWidth()
                .withHeight("60px")
                .withStyleName("header")
                .withMargin(new MMarginInfo(false, true, false, true));
        
        MLabel welcomeLabel = new MLabel("Hi, welcome to your todos world!!")
                .withUndefinedSize()
                .withStyleName(ValoTheme.LABEL_NO_MARGIN);
        
        MCssLayout cssLayout = new MCssLayout()
                .withStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        
        
        MButton statsButton = new MButton()
                .withIcon(VaadinIcons.CHART_GRID)
                .withStyleName(ValoTheme.BUTTON_ICON_ONLY);
        
        
        statsButton.addClickListener(() -> {
            presenter.navigateTo(ViewIdentifires.STATS_VIEW, null);
        });
        
        DateField dateField = generateDateField();
        
        cssLayout.addComponents(dateField, statsButton);
        
        mh.add(welcomeLabel, Alignment.MIDDLE_LEFT);
        mh.add(cssLayout, Alignment.MIDDLE_RIGHT);
        return mh;
    }

    private DateField generateDateField() {
        DateField dateField = new DateField();
        dateField.setValue(MainUI.getInstance().getSelectedDate());
        
        dateField.addValueChangeListener((event) -> {
            MainUI.getInstance().setSelectedDate(event.getValue());
            initComponent(event.getValue());
        });
        
        return dateField;
    }

    @Override
    public void addCategoryLayout(String name, Long total, Long completed, CategoryType type) {
        
        MVerticalLayout mv = generateCategoryLayout(type);
        
        MLabel iconlLabel = new MLabel(type.getIcon().getHtml())
                .withStyleName(ValoTheme.LABEL_H2)
                .withContentMode(ContentMode.HTML);
                
        MLabel nameLabel = new MLabel(name)
                .withStyleName(ValoTheme.LABEL_LARGE, " ", ValoTheme.LABEL_BOLD);
        
        ProgressBar pBar = new ProgressBar(total == 0 ? 0 : completed / total);
        pBar.setWidth("80%");
        
        MLabel countLabel = new MLabel(completed + "/" + total)
                .withStyleName(ValoTheme.LABEL_SMALL);
        
        mv.add(iconlLabel, Alignment.TOP_CENTER);
        mv.add(nameLabel, Alignment.MIDDLE_CENTER);
        mv.add(pBar, Alignment.MIDDLE_CENTER);
        mv.add(countLabel, Alignment.BOTTOM_LEFT);
        
        categoriesGridlayout.addComponent(mv);
        categoriesGridlayout.setComponentAlignment(mv, Alignment.TOP_CENTER);
    }

    private MVerticalLayout generateCategoryLayout(CategoryType type) {
        MVerticalLayout mv = new MVerticalLayout()
                    .withSpacing(true)
                    .withWidth("70%")
                    .withStyleName("shadowLayout" + " "
                            + "clickable" + " " 
                            + "category-layout-background " + " " 
                            + presenter.getCategoryLayoutStyle(type.getId()))
                    .withMargin(new MMarginInfo(false, true, false, true));
    
        mv.addLayoutClickListener((event) -> {
            //removes all todos laout and reinitialize again filtering with the clicked category type.
            todosLayout.removeAllComponents();
            generateAddNewTodoItemButton();
            presenter.generateTodosSectionFilteredbyCategory(type);
            
            //now change style of the clicked category layout
            int columns = categoriesGridlayout.getColumns();
            
            //change the style of the clicked category's layout and the previous clicked
            //category's layout.
            for(int i = 0; i < 4; i++) {
                Component component = categoriesGridlayout.getComponent(i, 0);
                
                if(i == type.getId().intValue()) {
                    component.addStyleNames("shadowLayout");
                } else {
                    component.removeStyleName("shadowlayout");
                }
            }
        });
        
        return mv;
    }

    @Override
    public void addTodoLayout(Long id, String name, String description, boolean completed, CategoryType type) {

        //Used this extra csslayout to group some component together. But it seems
        //its not working for some reason. Need to look at it later.
        MCssLayout cssLayout = new MCssLayout()
                .withWidth("80%")
                .withStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        
        MHorizontalLayout mh = new MHorizontalLayout()
                .withWidth("80%")
                .withStyleName("shadowLayout"
                        + " " + ValoTheme.LAYOUT_CARD
                        + " " + "clickable"
                        + " " + "todo-editor-backgroud")
                .withSpacing(true);
        
        mh.addLayoutClickListener((event) -> {
            presenter.navigateTo(ViewIdentifires.EDITOR_VIEW, id);
        });
        
        //generate category icon and date section
        MVerticalLayout mv = new MVerticalLayout()
                .withHeight("60px")
                .withSpacing(true)
                .withMargin(false);
        
        MLabel iconlLabel = new MLabel(type.getIcon().getHtml())
                .withContentMode(ContentMode.HTML);
                
        MLabel dateLabel = new MLabel(Util.formatLocalDate(MainUI.getInstance().getSelectedDate()))
                .withStyleName(ValoTheme.LABEL_SMALL);
        
        mv.add(iconlLabel, Alignment.TOP_CENTER);
        mv.add(dateLabel, Alignment.BOTTOM_CENTER);
        
        MLabel nameLabel = new MLabel(name).withStyleName(ValoTheme.LABEL_LARGE);
        MLabel descLabel = new MLabel(description)
                .withStyleName(ValoTheme.LABEL_SMALL
                                + " " + "long-sentence-wrap");
        
        MHorizontalLayout editorLayout = generateTodosEditorLayout(cssLayout, id, completed);
        
        mh.add(mv, Alignment.MIDDLE_LEFT);
        mh.add(nameLabel, Alignment.MIDDLE_CENTER);
        mh.add(descLabel, Alignment.MIDDLE_CENTER);
        
        mh.setExpandRatio(mv, .15f);
        mh.setExpandRatio(nameLabel, .25f);
        mh.setExpandRatio(descLabel, .6f);
        
        cssLayout.add(mh, editorLayout);
        todosLayout.add(cssLayout, Alignment.MIDDLE_CENTER);
    }

    private void generateAddNewTodoItemButton() {
        MButton button = new MButton()
                .withIcon(VaadinIcons.PLUS)
                .withWidth("40%")
                .withStyleName(ValoTheme.BUTTON_LARGE, " ", ValoTheme.BUTTON_ICON_ONLY);
        
        button.addClickListener(() -> {
            presenter.navigateTo(ViewIdentifires.EDITOR_VIEW, null);
        });
        
        todosLayout.add(button, Alignment.TOP_CENTER);
    }
    
    public void setPresenter(ITodosPresenter presenter) {
        this.presenter = presenter;
    }

    private MHorizontalLayout generateTodosEditorLayout(CssLayout cl, Long id, boolean completed) {
        MHorizontalLayout editorLayout = new MHorizontalLayout()
                .withSpacing(true)
                .withStyleName("shadowLayout"
                        + " " + ValoTheme.LAYOUT_CARD)
                .withHeight("60px")
                .withMargin(false);
        
        MCheckBox box = new MCheckBox()
                .withStyleName(ValoTheme.CHECKBOX_LARGE)
                .withValue(completed);
        
        box.addValueChangeListener((event) -> {
            //Chage the completness property of the todo item.
            presenter.updateCompleteness(id, event.getValue());
            categoriesGridlayout.removeAllComponents();
            
            //As the completeness state is change so re-calculate category wise counter (e.g. on todos completeness)
            //and re-generate the category section.
            presenter.generateTodoCategoriesSection(MainUI.getInstance().getSelectedDate());
        });
        
        MButton deleteButton = new MButton()
                .withIcon(VaadinIcons.TRASH)
                .withStyleName(ValoTheme.BUTTON_DANGER, " ",
                        ValoTheme.BUTTON_BORDERLESS, " ",
                        ValoTheme.BUTTON_ICON_ONLY);
        
        deleteButton.addClickListener(() -> {
            //removed the todo item.
            ConfirmDialog cd = ConfirmDialog.show(MainUI.getInstance(),
                    "Do you really want to remove this todo?",
                    (dialog) -> {
                        if(dialog.isConfirmed()) {
                            presenter.removeTodoItem(id);
                            todosLayout.removeComponent(cl);
                            categoriesGridlayout.removeAllComponents();

                            //As the one todo item is removed so re-calculate category wise counter
                            //and re-generate the category section.
                            presenter.generateTodoCategoriesSection(MainUI.getInstance().getSelectedDate());
                        }
                    });
        });
        
        editorLayout.add(box, Alignment.MIDDLE_RIGHT);
        editorLayout.add(deleteButton, Alignment.MIDDLE_RIGHT);
        
        return editorLayout;
        
    }
    
}
