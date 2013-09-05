package TestGenerator;

import java.util.ArrayList;

import Parser.BooleanValue;
import Parser.FunctionCall;
import Parser.IExpression;
import Parser.IntValue;
import Parser.PrimitiveValue;

public class PrimitiveOperations {
	
	/**
	 * Rewrites a given FunctionCall to use Primitive Operations
	 * PRECONDITION: c has no function calls as arguments
	 * @param c The FunctionCall
	 * @return Returns IExpression representing the Primitive Operation
	 */
	public static <T> IExpression rewriteCallToPrimitiveOperation(FunctionCall c)
	{
		String operation = c.getName();
		if (operation.equals("="))
		{
			ArrayList<PrimitiveValue<T>> values = new ArrayList<PrimitiveValue<T>>();
			for (IExpression exp : c.getParameters())
				values.add((PrimitiveValue<T>) exp);
			return equals(values);
		}
		else if (operation.equals("not"))
		{
			return not((PrimitiveValue<T>)c.getParameters().get(0));
		}
		ArrayList<PrimitiveValue<Integer>> values = new ArrayList<PrimitiveValue<Integer>>();
		for (IExpression exp : c.getParameters())
			values.add((PrimitiveValue<Integer>) exp);
		if (operation.equals("+"))
			return add(values);
		else if (operation.equals("-"))
			return subtract(values);
		else if (operation.equals("*"))
			return multiply(values);
		else if (operation.equals("<"))
			return lessThan(values);
		else
			return greaterThan(values);
	}
	
	/**
	 * Adds all the values in a list together
	 * @param values The list of values to be added together
	 * @return The IntValue representing the total sum
	 */
	public static IntValue add(ArrayList<PrimitiveValue<Integer>> values)
    {
    	int sum = 0;
    	for (PrimitiveValue<Integer> val : values)
    		sum += val.getValue();
    	return new IntValue(sum);
    }
	
	/**
	 * Subtracts all the values in a list from each other
	 * @param values The list of values to be subtracted from each other
	 * @return The IntValue representing the end total
	 */
    public static IntValue subtract(ArrayList<PrimitiveValue<Integer>> values)
    {
    	if (values.size() == 1)
    		return new IntValue(- values.get(0).getValue());
    	int result = values.get(0).getValue();
    	for (int i = 1; i < values.size(); i++)
    		result -= values.get(i).getValue();
    	return new IntValue(result);
    }
    
    /**
     * Multiplies all the values in a list from each other
     * @param values The list of values to be multiplied together
     * @return The IntValue representing the end total
     */
    public static IntValue multiply(ArrayList<PrimitiveValue<Integer>> values)
    {
    	int result = 1;
    	for (PrimitiveValue<Integer> val : values)
    		result *= val.getValue();
    	return new IntValue(result);
    }
    
    /**
     * Gives the opposite boolean value from what is input
     * @param value The given input
     * @return The opposite boolean of the input
     */
    public static <T> BooleanValue not(PrimitiveValue<T> value)
    {
    	BooleanValue falseVal = new BooleanValue(false);
    	return new BooleanValue(value.getValue().equals(falseVal.getValue()));
    }
    
    /**
     * Checks if the values in a list are the same
     * PRECONDITION: values has size >= 2
     * @param values The list of values to be checked against each other
     * @return boolean value representing the answer
     */
    public static <T> BooleanValue equals(ArrayList<PrimitiveValue<T>> values)
    {
    	PrimitiveValue<T> v = values.get(0);
    	for (int i = 1; i < values.size(); i++)
    		if (!values.get(i).getValue().equals(v.getValue()))
    			return new BooleanValue(false);
    	return new BooleanValue(true);
    }
    
    /**
     * Checks if each value in a list of integers is less than the next value
     * @param values The list of integers
     * @return boolean value representing the answer
     */
    public static BooleanValue lessThan(ArrayList<PrimitiveValue<Integer>> values)
    {
    	PrimitiveValue<Integer> last = values.get(0);
    	for (int i = 1; i < values.size(); i++)
    		if (values.get(i).getValue() <= last.getValue())
    			return new BooleanValue(false);
    		else
    			last = values.get(i);
    	return new BooleanValue(true);
    }
    
    /**
     * Checks if each value in a list of integers is greater than the next value
     * @param values The list of integers
     * @return boolean value representing the answer
     */
    public static BooleanValue greaterThan(ArrayList<PrimitiveValue<Integer>> values)
    {
    	PrimitiveValue<Integer> last = values.get(0);
    	for (int i = 1; i < values.size(); i++)
    		if (values.get(i).getValue() >= last.getValue())
    			return new BooleanValue(false);
    		else
    			last = values.get(i);
    	return new BooleanValue(true);
    }
}
