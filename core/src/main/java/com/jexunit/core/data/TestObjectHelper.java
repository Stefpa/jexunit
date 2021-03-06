package com.jexunit.core.data;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Map;

import com.jexunit.core.model.TestCase;
import com.jexunit.core.model.TestCell;

/**
 * Helper-class for generating entities out of the data of the excel-files. In the excel-file the
 * attributes names and values can be set.
 * 
 * @author fabian
 * 
 */
public class TestObjectHelper {

	/**
	 * Create a new instance of the given type with attributes set out of the TestCase. The TestCase
	 * contains the attributes names and values to set.
	 * 
	 * @param testCase
	 *            TestCase, containing the attributes (names) and values to set
	 * @param clazz
	 *            type of the object/instance to create
	 * @return a new instance of the given type with attributes set defined in the TestCase
	 * @throws Exception
	 */
	public static <T> T createObject(TestCase testCase, Class<T> clazz) throws Exception {
		T obj = clazz.newInstance();
		return createObject(testCase, obj);
	}

	/**
	 * Change the values of the given object set in the TestCase. So you can override the objects
	 * values set in a first command with (some) values set in a l
	 * 
	 * @param testCase
	 *            TestCase containing the attributes (names) and values to change
	 * @param object
	 *            object instance to change the attributes values
	 * @return the given object instance with modified attribute-values
	 * @throws Exception
	 */
	public static <T> T createObject(TestCase testCase, T object) throws Exception {
		for (Map.Entry<String, TestCell> entry : testCase.getValues().entrySet()) {
			setPropertyToObject(object, entry.getKey(), entry.getValue().getValue());
		}
		return object;
	}

	/**
	 * Set the attribute/property of the given object to the given value.
	 * 
	 * @param obj
	 *            object/instance
	 * @param propName
	 *            property-name
	 * @param propValue
	 *            property-value
	 * @throws Exception
	 */
	private static void setPropertyToObject(Object obj, String propName, String propValue)
			throws Exception {
		new PropertyUtils().setProperty(obj, propName, propValue);
	}

	/**
	 * Get the property identified by the given key out of the test-case.
	 * 
	 * @param testCase
	 *            the test-case to get the property from
	 * @param propertyKey
	 *            the identifier for the property (like defined in the excel-file)
	 * @return the property identified by the given propertyKey if found, else null
	 */
	public static String getPropertyByKey(TestCase testCase, String propertyKey) {
		for (Map.Entry<String, TestCell> entry : testCase.getValues().entrySet()) {
			if (entry.getKey().equalsIgnoreCase(propertyKey)) {
				return entry.getValue().getValue();
			}
		}
		return null;
	}

	/**
	 * Convert the given (property-)value (a string) to the given type.
	 * 
	 * @param clazz
	 *            the type of the property (to convert the property to)
	 * @param value
	 *            the property-value (as string)
	 * @return the property-value (converted to the expected type)
	 * @throws ParseException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static Object convertPropertyStringToObject(Class<?> clazz, String value)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException,
			ParseException {
		return PropertyUtils.convertPropertyStringToObject(clazz, value);
	}

}
