package TestGenerator;

import Parser.IExpression;

/**
 * Interface for a function object that contains a procedure that will 
 * generate input for a specific type
 * @author Adam
 */
public interface ITestInstanceGenerator {
	IExpression toExpression();
	String getType();
}
