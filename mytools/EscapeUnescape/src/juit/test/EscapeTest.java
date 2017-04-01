/**
 *
 */
package juit.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edwin.tools.escape.Escape;

/**
 * <p>Title:EscapeTest</p>
 * <p>Description:介绍</p>
 * @author Edwin
 * @since 2016-7-30
 * @version 1.0
 */
public class EscapeTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link edwin.tools.escape.Escape#escape(java.lang.String)}.
	 */
	@Test
	public void testEscape() {
		String src = Escape.escape("!@#$%^&*(adafcc和尚123");
		assertEquals("%21@%23%24%25%5E%26*%28adafcc%u548C%u5C1A123",src);
	}

}
