package com.cafromet.utiltest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.server.Updater;
import com.cafromet.util.JsonToXml;

public class JsonToXmlTest {

	@Test
	public void test() {
		JsonToXml jsonToXml = new JsonToXml();
		boolean rs = jsonToXml.convertJsonToXml("ABANTOTemp2.json", "ABANTO", "ABANTO", "test_" +  "ABANTO.xml", Updater.RUTA_XML);
		assertEquals(true, rs);
	}

}
