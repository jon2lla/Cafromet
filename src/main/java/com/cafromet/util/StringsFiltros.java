package com.cafromet.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class StringsFiltros {
	private static final String BUNDLE_NAME = "com.cafromet.util.StringsFiltros"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private StringsFiltros() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
