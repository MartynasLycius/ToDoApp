package com.interview.task.services;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.vaadin.flow.component.html.Label;

@Service
public class UtilityServices {

	public String abbreviateText(String text, int lengthBeforeAbbr) {
		return text.length() > lengthBeforeAbbr ? text.substring(0, lengthBeforeAbbr) + "..." : text;
	}
	
	public Label getFormatToBePerformed(LocalDateTime tobePerformed) {
		Label taskTimeLabel = new Label();
		String month = tobePerformed.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		String day = tobePerformed.getDayOfMonth() < 10 ? "0" + tobePerformed.getDayOfMonth()
				: "" + tobePerformed.getDayOfMonth();
		int year = tobePerformed.getYear();
		String hour = tobePerformed.getHour() < 10 ? "0" + tobePerformed.getHour() : tobePerformed.getHour() + "";
		String minute = tobePerformed.getMinute() < 10 ? "0" + tobePerformed.getMinute()
				: tobePerformed.getMinute() + "";
		StringBuilder formattedToBePerformed = new StringBuilder();
		formattedToBePerformed.append("at ").append(hour).append(":").append(minute).append(" on ").append(month)
				.append(" ").append(day).append(", ").append(year);
		if (tobePerformed.isBefore(LocalDateTime.now())) {
			formattedToBePerformed.append(" OVERDUE!");
			taskTimeLabel.getStyle().set("color", "red");
		}
		taskTimeLabel.setText(formattedToBePerformed.toString());
		return taskTimeLabel;
	}
	
	public boolean isNullOrEmpty(String text) {
		return text == null || text.isEmpty();
	}
	
	public List<String> getContextAsList(String text) {
		return Arrays.asList(text.split("\\|"));
	}
	
}
