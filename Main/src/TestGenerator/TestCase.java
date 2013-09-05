package TestGenerator;

import Parser.*;

/**
 * Class structure representing a Scheme Expression to be used in the output
 * 
 * @author Adam
 *
 */
public class TestCase {
	private String comparisonType;
	private FunctionCall actual;
	private IExpression expected;
	
	
	public TestCase(FunctionCall actual, String comparisonType)
	{
		this.actual = actual;
		expected = null;
		this.comparisonType = comparisonType;
	}
	
	/**
	 * Gets the comparison type of this TestCase
	 * @return String representing the comparisonType
	 */
	public String getComparisonType()
	{
		return comparisonType;
	}
	/**
	 * Gets the actual value of this TestCase
	 * 
	 * @return FunctionCall representing the actual value
	 */
	public FunctionCall getActual()
	{
		return actual;
	}
	/**
	 * Gets the expected value of this TestCase
	 * @return IExpression representing the expected value
	 */
	public IExpression getExpected()
	{
		return expected;
	}
	/**
	 * Sets the expected result of this test case as the inputed IExpression
	 * @param e
	 */
	public void setExpectedResult(IExpression e)
	{
		expected = e;
	}
	
	/**
	 * Converts the class representation of the expression into a string equivalent, representing the Scheme Expression.
	 * @return The converted Scheme expression
	 */
	public String toString()
	{
		if (expected == null)
			return actual.toString();
		return "(equal? " + actual.toString() + " " + expected.toString() + ")";
	}
}
