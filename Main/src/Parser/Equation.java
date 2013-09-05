package Parser;

import java.util.ArrayList;

/**
 * Class representation of an Equation
 *
 */
public class Equation
{
    public FunctionCall input;
    public IExpression output;

    public Equation(FunctionCall in, IExpression out)
    {
            this.input = in;
            this.output = out;
    }
    /**
     * Retrieves the input of this Equation
     * @return The FunctionCall representing the input
     */
    public FunctionCall getInput()
    {
            return input;
    }
    /**
     * Retrieves the output of this Equation
     * @return The IExpression representing the output
     */
    public IExpression getOuput()
    {
            return output;
    }
    /**
     * Creates and returns a list of RangeVariables that appear in the input that aren't used in the output
     * @return The list of RangeVariables that appear in the input that aren't used in the output
     */
    public ArrayList<RangeVariable> getUnusedInputRangeVariables()
    {
    	ArrayList<RangeVariable> rangeVars = input.getRangeVariables();
    	ArrayList<RangeVariable> unusedRangeVars = new ArrayList<RangeVariable>();
    	for (RangeVariable r : rangeVars)
    		if (!output.containsRangeVariable(r))
    			unusedRangeVars.add(r);
    	return unusedRangeVars;
    }
}