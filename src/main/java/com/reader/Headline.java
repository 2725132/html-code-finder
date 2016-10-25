package com.reader;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import lombok.Data;

@Data
public class Headline {
	String symbol;
	HashMap<Object, String> properties;

	public Headline(String symbol) {
		setSymbol(symbol);
		properties = new LinkedHashMap<Object, String>();
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
		if (properties.isEmpty())
			return symbol;
		else
			return buildPropertiesResult();
	}

}
