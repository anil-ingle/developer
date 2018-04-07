package com.cidac.onlineparking.utilty;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	static ObjectMapper mapper = null;
	static {
		mapper = new ObjectMapper();

	}

	public static String convertJavaToJson(Object obj) throws JsonProcessingException {
		String jsonStr = "{}";
		try {
			jsonStr = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException jpe) {
			throw jpe;
		} catch (Exception e) {
			throw e;
		}
		return jsonStr;
	}// end of method

	public static <T> T convertJsonToJava(String jsonStr, Class<T> cls)
			throws JsonParseException, JsonMappingException, IOException {
		T response = null;
		try {
			response = mapper.readValue(jsonStr, cls);
		} catch (JsonParseException jparsee) {
			throw jparsee;
		} catch (JsonMappingException jme) {
			throw jme;
		} catch (IOException ioe) {
			throw ioe;
		} catch (Exception e) {
			throw e;
		}
		return response;
	}// end of method

}// end of class