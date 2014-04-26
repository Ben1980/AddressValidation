package test;

import junit.framework.TestCase;
import util.SimpleValidator;


public class SimpleValidatorTest extends TestCase { 

	public void testIsValidEmail() {
		
		String testMailString = "abc@de.ef";
		assertEquals(true, SimpleValidator.isValidEmail(testMailString));

		testMailString = "@de.ef";
		assertEquals(false, SimpleValidator.isValidEmail(testMailString));

		testMailString = "ccc";
		assertEquals(false, SimpleValidator.isValidEmail(testMailString));

		testMailString = "ccc.de";
		assertEquals(false, SimpleValidator.isValidEmail(testMailString));

		testMailString = "ccc@de";
		assertEquals(false, SimpleValidator.isValidEmail(testMailString));

	}

	public void testIsValidTelNr() {
		
		//positive
		String testTelNrString = "";
		assertEquals(true, SimpleValidator.isValidTelNr(testTelNrString));

		 testTelNrString = "(076)711 11 71";
		assertEquals(true, SimpleValidator.isValidTelNr(testTelNrString));

		testTelNrString = "+41 076 711 11 71";
		assertEquals(true, SimpleValidator.isValidTelNr(testTelNrString));

		testTelNrString = "076-711-11-71";
		assertEquals(true, SimpleValidator.isValidTelNr(testTelNrString));

		testTelNrString = "076.711.11.71";
		assertEquals(true, SimpleValidator.isValidTelNr(testTelNrString));


		
		//negative
		testTelNrString = "++41 7234";
		assertEquals(false, SimpleValidator.isValidTelNr(testTelNrString));

		testTelNrString = "76 7234kk";
		assertEquals(false, SimpleValidator.isValidTelNr(testTelNrString));

		testTelNrString = "abc@de.ef";
		assertEquals(false, SimpleValidator.isValidTelNr(testTelNrString));


		testTelNrString = "41  7234";
		assertEquals(false, SimpleValidator.isValidTelNr(testTelNrString));

		testTelNrString = "076--7234 88";
		assertEquals(false, SimpleValidator.isValidTelNr(testTelNrString));

		testTelNrString = "076 7234 88..";
		assertEquals(false, SimpleValidator.isValidTelNr(testTelNrString));

		
	}

	
	public void testIsValidDate() {
		
		//positive
		String testDateString = "";
		assertEquals(true, SimpleValidator.isValidDate(testDateString));

		testDateString = "1.1.2011";
		assertEquals(true, SimpleValidator.isValidDate(testDateString));

		testDateString = "01.01.2011";
		assertEquals(true, SimpleValidator.isValidDate(testDateString));

		testDateString = "01.01.11";
		assertEquals(true, SimpleValidator.isValidDate(testDateString));

		testDateString = "12.12.2011";
		assertEquals(true, SimpleValidator.isValidDate(testDateString));

		
		//negative
		testDateString = "32.01.2011";
		assertEquals(false, SimpleValidator.isValidDate(testDateString));

		testDateString = "31.02.2011";
		assertEquals(false, SimpleValidator.isValidDate(testDateString));

		testDateString = "01.13.2011";
		assertEquals(false, SimpleValidator.isValidDate(testDateString));

		testDateString = "01.13.2011k";
		assertEquals(false, SimpleValidator.isValidDate(testDateString));

		testDateString = "01..01.2011";
		assertEquals(false, SimpleValidator.isValidDate(testDateString));

		/*
		 * not yet implemented
		*/
		
	}

}
