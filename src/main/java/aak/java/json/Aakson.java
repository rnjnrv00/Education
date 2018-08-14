package aak.java.json;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/*
 * 
 */
public class Aakson {
	
	public static Set<String> getOrderedSet(String jsonStringArray) {
		jsonStringArray = jsonStringArray.trim();
		String[] entries = jsonStringArray.substring(1, jsonStringArray.length() - 1).split(",");
		if(entries.length > 0) {
			LinkedHashSet<String> entriesSet = new LinkedHashSet<String>();
			for(String anEntry : entries) {
				entriesSet.add(anEntry);
			}
			return entriesSet;
		}
		return null;
	}
	
	public static String getJsonString(Set<String> entriesSet) {		
		Iterator<String> iter = entriesSet.iterator();
		StringBuilder builder = new StringBuilder("[");
		while(iter.hasNext()) {
			builder.append(iter.next());
			builder.append(",");
		}
		String result = builder.toString();
		result = result.substring(0, result.length() -1) + "]";
		return result;
	}
}
