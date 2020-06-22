package com.backend.boiler.plate.app.base.data;

import java.util.Map;
import javax.servlet.http.HttpServletResponse;

public class ResponseGeneratorBase {
	public Map<String, Object> success(Map<String, Object> response) {
		response.put("success", true);
		return response;
	}
	public Map<String, Object> error(Map<String, Object> response, HttpServletResponse rHttpServletResponse) {
		rHttpServletResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
		response.put("success", false);
		return response;
	}
}
