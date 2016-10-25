package com.reader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import lombok.Data;

@Data
public class Headline {
	String symbol;
	HashMap properties;

	public Headline(String symbol) {
		setSymbol(symbol);
		properties = new LinkedHashMap();
	}

	public void addProperty(String key, String value) {
		properties.put(key, value);
	}
	
	public String buildPropertiesResult(){
		String result = getSymbol() + " ";
		Set<Object> keys = properties.keySet();
		for(Object key: keys){
			if(properties.get( (String) key) == "") result += key + " ";
			else result += key + "=" + "\"" + properties.get((String) key) + "\" ";
		}
		
		
		
		return result;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		if (properties.isEmpty())
			return symbol;
		else
			return buildPropertiesResult();
	}

}
