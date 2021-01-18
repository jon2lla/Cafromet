package com.cafromet.servertest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.server.Logger;

public class LoggerTest {

	Logger logger;
	Logger logger1;
	@Before
	public void setup() {
		logger = new Logger();
		logger1 = new Logger();
	}

	@Test
	public void test() {
		assertNotEquals(logger, logger1);
	}
}
