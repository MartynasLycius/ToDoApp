package com.interview.task.ui.views;

import java.util.Arrays;

import org.springframework.core.env.Environment;

import com.interview.task.services.UtilityServices;
import com.interview.task.ui.MainView;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "about", layout = MainView.class)
@PageTitle("User Manual")
@CssImport("./styles/views/about/about-view.css")
public class AboutView extends VerticalLayout {
	
	Environment env;
	UtilityServices utilityServices;
	
	public AboutView(Environment env, UtilityServices utilityServices) {
        setId("about-view");
        this.env = env;
        this.utilityServices = utilityServices;
        addHeaderLabel(env.getProperty("about.to.do"));
        addDescriptiveText(env.getProperty("about.description"));
        addBulletedList(env.getProperty("user.manual.features"), env.getProperty("feature.list"));
        addHeaderLabel(env.getProperty("user.manual.heading"));
        addDescriptiveText(env.getProperty("user.manual.desctiption"));
        addSubHeader(env.getProperty("add.task.subheader"));
        addDescriptiveText(env.getProperty("add.task.description"));
        addImage("images/validInsertion.png", "Valid insertion");
        addBulletedList(env.getProperty("validation.error"), env.getProperty("add.task.validations"));
        addDescriptiveText(env.getProperty("add.task.validation.post.description"));
        addSubHeader(env.getProperty("todo.dashboard"));
        addDescriptiveText(env.getProperty("dashboard.intro.description"));
        addImage("images/dashboard.png", "Valid insertion");
        addBulletedList(env.getProperty("dashboard.features"), env.getProperty("dashboard.features.list"));
        addSubHeader(env.getProperty("detail.form"));
        addDescriptiveText(env.getProperty("detail.description"));
        addImage("images/detailForm.png", "Detail Form");
        
    }
	
	private void addHeaderLabel(String text) {
		Label label = new Label(text);
		label.addClassName("heading");
		add(label, new Hr());
	}
	
	private void addDescriptiveText(String text) {
		Label label = new Label(text);
		add(label, new HtmlComponent("br"));
	}
	
	private void addSubHeader(String text) {
		Label label = new Label(text);
		label.addClassName("subHeading");
		add(label, new HtmlComponent("br"));
	}
	
	private void addBulletedList(String listHeader, String listItems) {
		addSubHeader(listHeader);
		UnorderedList ul = new UnorderedList();
		utilityServices.getContextAsList(listItems).forEach(item -> ul.add(new ListItem(item)));
		add(ul);
	}
	
	private void addImage(String path, String altText) {
		Image image = new Image(path, altText);
		image.setMaxWidth("1000px");
		add(image,new HtmlComponent("br"));
	}
	
}
