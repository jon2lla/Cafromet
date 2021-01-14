package com.cafromet.utiltest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.util.JsonToXml;

public class JsonToXmlTest {

	@Test
	public void test() {
		JsonToXml jsonToXml = new JsonToXml();
		boolean rs=jsonToXml.convertJsonToXml("src\\resource\\com\\cafromet\\files\\json\\pueblos.json", "raiz", "pueblo", "pueblos.xml","..\\..\\Desktop\\");
		assertEquals(true, rs);
	}

}
