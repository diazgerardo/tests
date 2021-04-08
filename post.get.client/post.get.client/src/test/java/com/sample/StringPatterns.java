package com.sample;

import java.util.regex.Pattern;

import junit.framework.Assert;

public class StringPatterns {

	public static void main(String[] args) {
		Assert.assertTrue(matches("aaa","*"));
	}
	
	public static boolean matches(String str, String pattern) {
		Pattern pat = Pattern.compile(pattern);
		if(null==str&&null==pattern)
			return true;
		else if(null==str||null==pattern) {
			return false;
		}
		return pat.matcher(str).matches();
		
	}
}
