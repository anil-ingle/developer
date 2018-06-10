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

	public static String convertJavaToJson(Object obj){
		String jsonStr = "{}";
		try {
			jsonStr = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException jpe) {
			try {
				throw jpe;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return response;
	}// end of method

}// end of class