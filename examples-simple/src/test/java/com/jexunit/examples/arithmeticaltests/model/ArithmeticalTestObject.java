package com.jexunit.examples.arithmeticaltests.model;

/**
 * This simulates a model-class to show how you can use your model-classes in the test-commands.
 * <p>
 * In your excel-file you can set the parameter-names to the attribute-names of your model class.
 * The framework will automatically match the values and create your model file that can be put into
 * your test-command-method.
 * </p>
 * 
 * @author fabian
 * 
 */
public class ArithmeticalTestObject {

	private double param1;
	private double param2;
	private double param3;
	private double result;

	public double getParam1() {
		return param1;
	}

	public void setParam1(double param1) {
		this.param1 = param1;
	}

	public double getParam2() {
		return param2;
	}

	public void setParam2(double param2) {
		this.param2 = param2;
	}

	public double getParam3() {
		return param3;
	}

	public void setParam3(double param3) {
		this.param3 = param3;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}
}
