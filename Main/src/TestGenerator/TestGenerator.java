package TestGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.lang.StringBuilder;

import Util.Cartesian;

import Parser.*;

/**
 * The class used to generate each Test Case.
 * 
 * @author Adam
 *
 */
public class TestGenerator {
	
	//Hashmap used to map a type to primitive types
	private HashMap<String, ITestInstanceGenerator> typeGeneratorMapping;
	//The Algebraic Spec to be tested.
	private ASpec toTest;
	//The list of Test Cases being generated
	private ArrayList<TestCase> testCases;
	//Maximum function call depth
	private int MAX_CALL_DEPTH;
	//Maximum number of procedures to generate for a function with one parameter
	private static final int MAX_PROCGEN = 1000;
	//Maximum number of test cases to generate
	private static final int MIN_TESTGEN = 10;
	//Maximum number of test cases to generate
	private int MAX_TESTGEN;
	
	private TestGenerator(ASpec a, int maxDepth, int maxTests)
	{
		MAX_CALL_DEPTH = maxDepth;
		MAX_TESTGEN = maxTests;
		typeGeneratorMapping = new HashMap<String, ITestInstanceGenerator>();
		
		// Seed the hash with generation procedures for the primitive types
		typeGeneratorMapping.put("int", new IntGenerator());
		typeGeneratorMapping.put("boolean", new BooleanGenerator());
		typeGeneratorMapping.put("character", new CharacterGenerator());
		typeGeneratorMapping.put("string", new StringGenerator());
		toTest = a;
		testCases = new ArrayList<TestCase>();
		
		int testCounter = 0;
		do{
			// First generate one test case for every function call
			GenerateTestCases();
			// Then determine the expected output for those test cases
			testCounter += AddExpectedResultsToTestCases();
		} // We continue to generate tests until we surpass the minimum
		while (testCounter < MIN_TESTGEN && testCounter > 0);
	}
	
	/**
	 * Determine the expected output for each generated test case,
	 * based on the equations
	 * @return - number of expected results found for test cases
	 */
	private int AddExpectedResultsToTestCases()
	{
		int generatedTestCounter = 0;
		HashMap<String, Integer> testsPerFunction = new HashMap<String, Integer>();
		for(TestCase t : testCases)
		{
			Integer fcount = testsPerFunction.get(t.getActual().getName());
			if (fcount == null)
				fcount = new Integer(0);
			if (t.getExpected() == null && fcount < MAX_TESTGEN)
			{
				ExpressionRewriter rewriter = new ExpressionRewriter(toTest);
				IExpression output = rewriter.rewriteFunction(t.getActual());
				if (!output.isFunctionCall()){
					t.setExpectedResult(output);
					testsPerFunction.put(t.getActual().getName(), fcount + 1);
				}
			}
		}
		return generatedTestCounter;
	}
	
	/**
	 * Filters through the list of Functions within the Algebraic Spec being tested to find Functions that return a certain returnType
	 * @param returnType The requested return type
	 * @return The list of Functions that return the requested type
	 */
	private ArrayList<Function> filterFunctionsWithReturnType(String returnType)
	{
		ArrayList<Function> functionsWithType = new ArrayList<Function>();
		for (ADT a : toTest.getADT())
			for (Function f : a.getFunctions())
				if (f.getType().equals(returnType))
					functionsWithType.add(f);
		return functionsWithType;
	}
	
    /**
     * Find all procedures for a type t
     * @param type The type
     * @param maxDepth The maximum number of function calls
     * @return The list of Procedures that will generate input for type t
     */
	private ArrayList<ITestInstanceGenerator> getInputGenerators(String type, int maxDepth)
	{
		if (typeGeneratorMapping.containsKey(type))
		{
			ArrayList<ITestInstanceGenerator> output = new ArrayList<ITestInstanceGenerator>();
			output.add(typeGeneratorMapping.get(type));
			return output;
		}
		else if (maxDepth > 0)
			return generateTestInstanceGeneratorsFor(type, maxDepth - 1);
		else
			return new ArrayList<ITestInstanceGenerator>();
	}
	
	/**
	 * Generate all possible procedures for t
	 * @param type The type
	 * @param maxDepth The maximum number of function calls 
	 * @return The list of Procedures that generate input for type t
	 */
	private ArrayList<ITestInstanceGenerator> generateTestInstanceGeneratorsFor(String type, int maxDepth)
	{
		ArrayList<Function> functions = filterFunctionsWithReturnType(type);
		ArrayList<ITestInstanceGenerator> argSets = new ArrayList<ITestInstanceGenerator>();
		for (Function f : functions)
			argSets.addAll(generateAllArgumentSetsFor(f, maxDepth));
		return argSets;
	}
	
	/**
	 * Find all possible sets of argument procedures for f
	 * @param f
	 * @param maxDepth The maximum number of function calls
	 * @return The list of all possible sets of procedures for f
	 */
	private ArrayList<ComplexGenerator> generateAllArgumentSetsFor(Function f, int maxDepth)
	{
		ArrayList<ComplexGenerator> procs = new ArrayList<ComplexGenerator>();
		if (f.getInvocations().getParameterTypes().size() == 0)
		{
			procs.add(new ComplexGenerator(f, new ArrayList<ITestInstanceGenerator>()));
			return procs;
		}
		ArrayList<ArrayList<ITestInstanceGenerator>> argGens = new ArrayList<ArrayList<ITestInstanceGenerator>>();
		
		int maxProcPerParam = CalculateMaxProceduresToGenerateForAFunction(f);
		for (String pType : f.getInvocations().getParameterTypes())
		{
			ArrayList<ITestInstanceGenerator> toAdd = getInputGenerators(pType, maxDepth);
			if (toAdd.size() > 0)
				argGens.add(Take(toAdd, maxProcPerParam));
			else
				return procs;
		}
		ArrayList<ArrayList<ITestInstanceGenerator>> argSets = Cartesian.computeProduct(argGens);
		for (ArrayList<ITestInstanceGenerator> args : argSets)
			procs.add(new ComplexGenerator(f, args));
		return procs;
	}
	
	/**
	 * Output the first n elements of an arraylist a
	 * @param a generic arraylist
	 * @param n the number of elements to take
	 * @return new arraylist with n elements from a
	 */
	private static <T> ArrayList<T> Take(ArrayList<T> a, int n)
	{
		if (a.size() <= n)
			return a;
		ArrayList<T> out = new ArrayList<T>();
		for (int i = 0; i < n; i++)
			out.add(a.get(i));
		return out;
	}
	
	/**
	 * Calculate the maximum number of procedures that we generate per parameter for a function f
	 * Although we can generate any number of procedures, in practice we need to set this limit
	 * to prevent the JVM from running out of memory when a complex ADT is given as input
	 * @param f function to calculate the limit for
	 * @return the limit per parameter
	 */
	private static int CalculateMaxProceduresToGenerateForAFunction(Function f)
	{
		return (int) Math.floor(Math.pow((
				(double)MAX_PROCGEN), 1.0 / 
				((double)f.getInvocations().getParameterTypes().size())));
	}
	
	/**
	 * Generate all possible sets of argument procedures for f
	 * @param f The Function
	 */
	private void GenerateTestCases(Function f)
	{
		ArrayList<ComplexGenerator> inputGenerators = generateAllArgumentSetsFor(f, MAX_CALL_DEPTH);
		for (ComplexGenerator gen : inputGenerators)
			testCases.add(new TestCase(gen.toFunctionCall(), gen.getType()));
	}
	
	/**
	 * Generates a Test Case for each function within the ADT
	 */
	private void GenerateTestCases()
	{
		for(Parser.ADT s : toTest.getADT())
			for(Function f : s.getFunctions())
				if (f.returnsPrimitiveType() && !f.getEquations().isEmpty())
					GenerateTestCases(f);
	}
	
	/**
	 * Retrieves a list of all test cases from this generator
	 * @return The list of all test cases
	 */
	public ArrayList<TestCase> getTestCases()
	{
		return testCases;
	}
	
	/**
	 * Generates tests for a given Algebraic Specification
	 * @param a The Algebraic Specification for which we are generating tests
	 * @return The list of generated test cases
	 */
	public static ArrayList<TestCase> GenerateTests(ASpec a)
	{
		return GenerateTests(a, 100, 3);
	}
	
	/**
	 * Generates tests for a given Algebraic Specification
	 * @param a The Algebraic Specification for which we are generating tests
	 * @param maxTests Maximum number of test cases
	 * @param maxCallDepth Maximum function call depth of test cases
	 * @return The list of generated test cases
	 */
	public static ArrayList<TestCase> GenerateTests(ASpec a, int maxCallDepth, int maxTests)
	{
		TestGenerator genTests = new TestGenerator(a, maxTests, maxCallDepth);
		return genTests.getTestCases();
	}
}

/**
 * Function object containing a procedure to generate input for function calls
 * 
 * @author Adam
 *
 */
class ComplexGenerator implements ITestInstanceGenerator
{
	private Function f;
	private ArrayList<ITestInstanceGenerator> inputs;
	public ComplexGenerator(Function f, ArrayList<ITestInstanceGenerator> inputs)
	{
		this.f = f;
		this.inputs = inputs;
	}
	
	/**
	 * Changes test instances into FunctionCalls
	 * 
	 */
	public FunctionCall toFunctionCall()
	{
		ArrayList<IExpression> fexps = new ArrayList<IExpression>();
		for(ITestInstanceGenerator gen : inputs)
			fexps.add(gen.toExpression());
		return new FunctionCall(f.getName(), fexps);
	}
	
	/**
	 * Creates a FunctionCall
	 */
	public IExpression toExpression()
	{
		return toFunctionCall();
	}
	/**
	 * Gets the type of the function
	 */
	public String getType() {
		return f.getType();
	}
}

/**
 * Function object containing a procedure to generate input for integers
 * 
 * @author Adam
 *
 */
class IntGenerator implements ITestInstanceGenerator
{
	/**
	 * Generates random integers
	 * @return Random integer
	 */
	public static int generateInt()
	{
		Random rand = new Random();
		return rand.nextInt(1000);
	}
	/**
	 * Generates a Value containing a generated int.
	 * 
	 * @return The IntValue
	 */
	public IExpression toExpression() {
		return new IntValue(generateInt());
	}

	/**
	 * The type of this generator as a string
	 * @return "int" 
	 */
	public String getType() {
		return "int";
	}
}

/**
 * Function object containing a procedure to generate input for booleans
 * 
 * @author Adam
 *
 */
class BooleanGenerator implements ITestInstanceGenerator
{
	/**
	 * Uses Random to generate a random Boolean value.
	 * @return Random boolean value (True/False)
	 */
	public static boolean generateBoolean()
	{
		Random rand = new Random();
		return rand.nextBoolean();
	}
	/**
	 * Generates a Value containing a generated boolean
	 * 
	 * @return The BooleanValue
	 */
	public IExpression toExpression() {
		return new BooleanValue(generateBoolean());
	}
	
	/**
	 * The type of this generator as a string
	 * @return "boolean"
	 */
	public String getType() {
		return "boolean";
	}
}

/**
 * Function object containing a procedure to generate input for characters
 * 
 * @author Adam
 *
 */
class CharacterGenerator implements ITestInstanceGenerator
{
	/**
	 * Generates a random Character
	 *
	 * @return Random character
	 */
	public static char generateCharacter()
	{
		Random rand = new Random();
		return (char)(rand.nextInt(26) + 'a');
	}
	/**
	 * Generates a Value containing a generated character
	 * @return The CharValue
	 */
	public IExpression toExpression()
	{
		return new CharValue(generateCharacter());
	}
	/**
	 * The type of this generator as a string
	 * @return "character"
	 */
	public String getType() {
		return "character";
	}
}

/**
 * Function object containing a procedure to generate input for Strings
 * 
 * @author Adam
 *
 */
class StringGenerator implements ITestInstanceGenerator
{
	/**
	 * Generates a random string up to a given maxLength
	 * @param maxLength The maximum length of the string
	 * @return The generated String
	 */
	public static String generateString(int maxLength)
	{
		Random rand = new Random();
		StringBuilder output = new StringBuilder();
		int len = rand.nextInt(maxLength);
		for (int i = 0; i < len; i++)
			output.append(CharacterGenerator.generateCharacter());
		return output.toString();
	}
	/**
	 * Generates a Value containing a generated string
	 * @return The StringValue with a generated string of length 10
	 */
	public IExpression toExpression()
	{
		return new StringValue(generateString(10));
	}
	
	/**
	 * The type of this generator as a string
	 * @return "string"
	 */
	public String getType() {
		return "string";
	}
}
