package com.interview.task.ui.customizedComponents;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import dev.mett.vaadin.tooltip.Tooltips;

@CssImport("./styles/customComponents/tinyButton/tiny-button.css")
public class TinyButton extends Button {
	
	public TinyButton() {
		super();
	}
	
	public TinyButton(VaadinIcon iconName, String iconColor) {
		super();
		Icon icon = new Icon(iconName);
		icon.setColor(iconColor);
		this.setIcon(icon);
		setDefaultStyles();
	}

	public TinyButton(VaadinIcon iconName) {
		super();
		Icon icon = new Icon(iconName);
		this.setIcon(icon);
		setDefaultStyles();
	}
	
	private void setDefaultStyles() {
		this.setText("");
		this.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
		this.addClassName("tiny-button-default");
	}
	
	public void setLeftMargin() {
		this.removeClassName("tiny-button-default");
		this.addClassName("tiny-button-left-margin");
	}
	
	public void setIcon(VaadinIcon iconName) {
		Icon icon = new Icon(iconName);
		this.setIcon(icon);
	}
	
	public void setToolTip(String toolTipText) {
		Tooltips.getCurrent().setTooltip(this, toolTipText);
	}
	
}
