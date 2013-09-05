package TestGenerator;

import java.util.ArrayList;
import java.util.HashMap;

import Parser.ADT;
import Parser.ASpec;
import Parser.Equation;
import Parser.Function;
import Parser.FunctionCall;
import Parser.IExpression;
import Parser.PrimitiveValue;
import Parser.RangeVariable;

/**
 * Rewrites an IExpression based on the equations defined in an algebraic specification
 * @author Adam
 *
 */
public class ExpressionRewriter {
	
	//Maximum number of rewrites
	private static final int MAX_REWRITES = 20;
		
	private ASpec algebraicSpec;
	
	public ExpressionRewriter(ASpec specification)
	{
		this.algebraicSpec = specification;
	}
	/**
	 * Method used to rewrite functionCalls
	 * @param c The FunctionCall to be rewritten
	 * @return the rewritten FunctionCall
	 */
	public IExpression rewriteFunction(FunctionCall c)
	{
		return simplifyExpression(c, MAX_REWRITES);
	}
	/**
	 * Ensures that unused RangeVariables have assignments that are considered valid by the Algebraic Specification
	 * @param c The FunctionCall to be checked
	 */
	private boolean allLooseRangeVariablesAreSpecified(FunctionCall c)
	{
		Function f = findFunction(c.getName());
		Equation eq = lookupEquation(c, f);
		if (eq == null)
			return false;
		HashMap<RangeVariable, IExpression> rvarMappings = getRangeVariableMappings(c, eq);
		for (RangeVariable var : eq.getUnusedInputRangeVariables())
		{
			if (rvarMappings.get(var).isFunctionCall() && 
					!((FunctionCall)rvarMappings.get(var)).getParameters().isEmpty() &&
					!allLooseRangeVariablesAreSpecified((FunctionCall)rvarMappings.get(var)))
				return false;
		}
		return true;
	}
	
	/**
	 * Simplifies a given expression.
	 * @param e - The Expression to be simplified
	 * @return The simplified version of the expression taken.
	 */
	private IExpression simplifyExpression(IExpression e, int rewriteLimit)
	{
		if (rewriteLimit == 0 || !e.isFunctionCall())
			return e;
		FunctionCall currentCall = (FunctionCall)e;
		if (!currentCall.isInnermostCall())
		{
			ArrayList<IExpression> simplifiedInputs = new ArrayList<IExpression>();
			for (IExpression arg : currentCall.inputs)
				simplifiedInputs.add(simplifyExpression(arg, rewriteLimit - 1));
			currentCall = new FunctionCall(currentCall.getName(), simplifiedInputs);
		}
		if (currentCall.isPrimitive())
			if (currentCall.isInnermostCall())
				return PrimitiveOperations.rewriteCallToPrimitiveOperation(currentCall);
			else
				return currentCall;
		Function f = findFunction(currentCall.getName());
		Equation eq = lookupEquation(currentCall, f);
		if (eq != null && allLooseRangeVariablesAreSpecified(currentCall))
			return simplifyExpression(determineOutput(eq, currentCall), rewriteLimit - 1);
		return currentCall;
	}
	
	/**
	 * Find the equation that gives the expected result of a test case t
	 * @param call - call
	 * @param f - the function
	 * @return the equation
	 */
	private Equation lookupEquation(FunctionCall call, Function f)
	{
		for (Equation e : f.getEquations())
		{
			if (call.matchesCallPattern(e.getInput()))
				return e;
		}
		return null;
	}
	
	/**
	 * Determine the expected output of a function call c according to an equation e
	 * @param e - the equation
	 * @param c - the function call
	 * @return expression with the expected output
	 */
	private IExpression determineOutput(Equation e, FunctionCall c)
	{
		HashMap<RangeVariable, IExpression> rangeVarMappings = 
				getRangeVariableMappings(c, e);
		return determineOutput(e.getOuput(), rangeVarMappings);
	}
	
	/**
	 * Determine the expected output for an expression, first checking through the rangeVarMappings to see if we already have it.
	 * @param expr - The expression
	 * @param rangeVarMappings - The HashMap to be check
	 * @return A FunctionCall containing the name and arg list
	 */
	private IExpression determineOutput(IExpression expr, HashMap<RangeVariable, IExpression> rangeVarMappings)
	{
		if (expr.isRangeVariable())
			return rangeVarMappings.get(expr);
		else if (expr instanceof PrimitiveValue<?>)
			return expr;
		ArrayList<IExpression> args = new ArrayList<IExpression>();
		for (IExpression e : ((FunctionCall)expr).getParameters())
			args.add(determineOutput(e, rangeVarMappings));
		return new FunctionCall(((FunctionCall)expr).getName(), args);
	}
	
	/**
	 * Find the values assigned to range variables in the test case
	 * @param rangeVarMappings
	 * @param input
	 * @param expr
	 */
	private void getRangeVariableMappings(HashMap<RangeVariable, IExpression> rangeVarMappings, IExpression input, IExpression expr)
	{
		if (expr.isRangeVariable())
			rangeVarMappings.put((RangeVariable)expr, input);
		else if (expr.isFunctionCall())
			for (int i = 0; i < ((FunctionCall)expr).getParameters().size(); i++)
				getRangeVariableMappings(rangeVarMappings, 
						((FunctionCall)input).getParameters().get(i), 
						((FunctionCall)expr).getParameters().get(i));
	}

	/**
	 * @param input
	 * @param eq
	 * @return The mapping between range variable and their values
	 */
	private HashMap<RangeVariable, IExpression> getRangeVariableMappings(IExpression input, Equation eq)
	{
		HashMap<RangeVariable, IExpression> rangeVarMappings = new HashMap<RangeVariable, IExpression>();
		getRangeVariableMappings(rangeVarMappings, input, eq.getInput());
		return rangeVarMappings;
	}
	
	/**
	 * Finds and returns the function (by name) within the ADT
	 * @param name The name of the function to be found
	 * @return The function
	 */
	private Function findFunction(String name)
	{
		for (ADT a : algebraicSpec.getADT())
			for (Function f : a.getFunctions())
				if (f.getName().equals(name))
					return f;
		return null;
	}
}
