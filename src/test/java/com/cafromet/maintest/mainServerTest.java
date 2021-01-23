package com.cafromet.maintest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.main.MainServer;

public class mainServerTest {

	@Test
	public void test() {
		assertTrue(MainServer.inicioServer());
	}

}
