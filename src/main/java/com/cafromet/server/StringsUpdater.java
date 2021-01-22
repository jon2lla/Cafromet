package com.cafromet.server;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class StringsUpdater {
	private static final String BUNDLE_NAME = "com.cafromet.server.StringsUpdater"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private StringsUpdater() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
