package Parser;

import java.util.ArrayList;
/**
 * 
 *The class structure representing Function calls
 */
public class FunctionCall implements IExpression
{
    public String funcName;
    public ArrayList<IExpression> inputs;

    public FunctionCall(String fName, ArrayList<IExpression> in)
    {
            this.funcName = fName;
            this.inputs = in;
    }

    /**
     * Checks if this is the innermost function call
     * @return The boolean value of the answer
     */
    public boolean isInnermostCall()
    {
    	for (IExpression input : inputs)
    		if (input instanceof FunctionCall)
    			return false;
    	return true;
    }
    /**
     * Checks if this function call is a primitive
     * @return The boolean value of the answer
     */
    public boolean isPrimitive()
    {
    	return funcName.equals("+") || funcName.equals("-") || funcName.equals("*") || 
    		   funcName.equals("=") || funcName.equals("<") || funcName.equals(">") || funcName.equals("not");
    }
    
    /**
     * Checks to see if this function call has the same calls as another
     * @param f The input FunctionCall to be checked against
     * @return The boolean value of the answer
     */
    public boolean matchesCallPattern(FunctionCall f)
    {
    	if (!f.getName().equals(getName()))
    		return false;
    	else if (f.getParameters().size() != inputs.size())
    		return false;
    	for (int i = 0; i < inputs.size(); i++)
    	{
    		if (!(inputs.get(i).isFunctionCall()) &&
    			(f.getParameters().get(i).isFunctionCall()))
    			return false;
    		else if (f.getParameters().get(i).isFunctionCall())
    			if (!((FunctionCall)inputs.get(i))
    					.matchesCallPattern((FunctionCall)f.getParameters().get(i)))
    				return false;
    	}
    	return true;
    }
    
    /**
     * Retrieves the name of this function
     * @return The string representation of the name
     */
    public String getName()
    {
    	return funcName;
    }
    /**
     * Retrieves the parameters of this function
     * @return The list of inputs
     */
    public ArrayList<IExpression> getParameters()
    {
    	return inputs;
    }
    /**
     * Checks if this is a functionCall
     * @return true
     */
    public boolean isFunctionCall()
    {
    	return true;
    }
    /**
     * Converts this to a string representation
     */
    public String toString()
    {
    	String output = "(" + funcName;
    	for (IExpression input : inputs)
    	{
    		output += " " + input.toString();
    	}
    	output += ")";
    	return output;
    }
    /**
     * Checks if this is a RangVariable
     */
	public boolean isRangeVariable() {
		return false;
	}
	/**
	 * Checks if the given RangeVariable appears in the input
	 */
	public boolean containsRangeVariable(RangeVariable r) {
		for (IExpression arg : inputs)
			if(arg.containsRangeVariable(r))
				return true;
		return false;
	}
	/**
	 * Retrieves all RangeVariables from the input
	 * @return The list of RangeVariables contained in the input
	 */
	public ArrayList<RangeVariable> getRangeVariables()
    {
    	ArrayList<RangeVariable> rangeVars = new ArrayList<RangeVariable>();
    	for (IExpression e : inputs)
    	{
    		if (e.isRangeVariable())
    			rangeVars.add((RangeVariable)e);
    		else if (e.isFunctionCall())
    			rangeVars.addAll(((FunctionCall)e).getRangeVariables());
    	}
    	return rangeVars;
    }
}