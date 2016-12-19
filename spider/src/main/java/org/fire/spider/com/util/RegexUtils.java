package org.fire.spider.com.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

	private static final String URL_PATTERN = "[a-zA-z]+://[^\\s]*";
	
	/**
	 * 匹配URL，并且返回list
	 * */
	public static List<String> parseURL(String content){
		Pattern pattern = Pattern.compile(URL_PATTERN);
		Matcher matcher = pattern.matcher(content);
		List<String> urlList = new ArrayList<String>();
		while(matcher.find()){
			urlList.add(matcher.group());
		}
		return urlList;
	}
	
	public static void main(String[] args) throws IOException {
		
		String content = ResourcesUtils.readResources("http://www.baidu.com");
		parseURL(content);
		
	}
	
}
