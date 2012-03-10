package br.com.sample.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Util {
	
	private static String WEBINF = "WEB-INF";

	/**
	 * Convert a password into a 32 bytes hash string.
	 * @param password
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String md5(String password) {
		try {
			byte[] bytes = password.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(bytes);
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while(hashtext.length() < 32 ){
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}		
	
	public static String getProjectPath () {
		String path = "";
		 
	    URL url = Util.class.getResource("Util.class");
	    String className = url.getFile();
	 
	   	if (className.indexOf(WEBINF) == -1){
	   		path = className.substring(0, className.indexOf("build") + "build".length());
	   	} else {
	   		path = className.substring(0,className.indexOf(WEBINF) + WEBINF.length());
	   	}
	    
	    return path;
	}
}
