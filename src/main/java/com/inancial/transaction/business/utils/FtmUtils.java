package com.inancial.transaction.business.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FtmUtils {

	private static final Logger log = LoggerFactory.getLogger(FtmUtils.class);

	public static String md5(String value) {
		try {

			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(value.getBytes(), 0, value.length());

			String md5Result = new BigInteger(1, m.digest()).toString(16);

			System.out.println("MD5: " + md5Result);
			return md5Result;

		} catch (Exception e) {
			log.error("Error converter md5 {} ", value);
			return null;
		}
	}
}
