package com.zahidul.backend.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.vaadin.flow.server.VaadinService;
import com.zahidul.backend.service.LabelByLocaleService;

@Component
public class LabelByLocaleServiceImpl implements LabelByLocaleService {

	@Autowired
	private MessageSource messageSource;

	@Override
	public String getMessage(String key) {

		Locale locale = VaadinService.getCurrentRequest().getLocale();

		if (!getProvidedLocales().contains(locale)) {
			locale = Locale.US;
		}
		return messageSource.getMessage(key, null, locale);
	}

	private List<Locale> getProvidedLocales() {
		return Collections.unmodifiableList(Arrays.asList(Locale.US));
	}
}
