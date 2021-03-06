package com.jexunit.examples.arithmeticaltests;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jexunit.core.JExUnit;
import com.jexunit.core.JExUnitBase;
import com.jexunit.core.commands.TestCommand;
import com.jexunit.core.data.ExcelFile;
import com.jexunit.core.model.TestCase;
import com.jexunit.examples.arithmeticaltests.model.ArithmeticalTestObject;

/**
 * Simple Test for the framework.
 * <p>
 * This test doesn't extend the base-class ({@link JExUnitBase}). This test works with the
 * <code>@RunWith(GevoTester.class)</code>-Annotation as the integration point for the framework.
 * </p>
 * <p>
 * This test should provide the arithmetical operations MUL and DIV. In this version the automatic
 * object-creation / -matching mechanism is tested. The test-command-methods get the testCase and a
 * model-object as parameters. The model-objects will automatically be created by the framework.
 * Therefore the parameter-names in the excel-file will be matched to the attribute-names of the
 * model-class.
 * </p>
 * <p>
 * The test-command DIV will also be found in another class (that works as an ExcelCommandProvider),
 * but in this test, the method/test-command defined in the class itself should be used!
 * </p>
 * <p>
 * The operations ADD and SUB will be provided by another ExcelCommandProvider.
 * </p>
 * <p>
 * As add-on, there is an additional "normal" JUnit-Test(-Method), to test, if this method won't be
 * ignored!
 * </p>
 * 
 * @author fabian
 * 
 */
@RunWith(JExUnit.class)
public class ArithmeticalTest {

	private static Logger log = Logger.getLogger(ArithmeticalTest.class.getName());

	@ExcelFile
	static String[] excelFiles = new String[] { "src/test/resources/ArithmeticalTests.xlsx",
			"src/test/resources/ArithmeticalTests2.xlsx" };

	@Before
	public void init() {
		log.log(Level.INFO, "BeforeClass - ArithmeticTests");
	}

	@TestCommand(value = "mul")
	public static void runMulCommand(TestCase testCase, ArithmeticalTestObject testObject)
			throws Exception {
		log.log(Level.INFO, "in test command: MUL!");
		assertThat(testObject.getParam1() * testObject.getParam2(), equalTo(testObject.getResult()));
	}

	@TestCommand(value = "div")
	public static void runDivCommand(TestCase testCase, ArithmeticalTestObject testObject)
			throws Exception {
		log.log(Level.INFO, "in test command: DIV!");
		assertThat(testObject.getParam1() / testObject.getParam2(), equalTo(testObject.getResult()));
	}

	@Test
	public void simpleTest() {
		log.info("What about this test?");
	}
}