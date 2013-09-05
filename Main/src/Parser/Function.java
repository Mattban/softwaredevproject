package Parser;

import java.util.ArrayList;

/*
The class structure representing a Function.
*/
/**
 * The class structure representing a Function
 *
 */
public class Function
{
    private String name;
    private Invocation invocation;
    private String type;
    private ArrayList<Equation> equations;

    public Function(String name, Invocation inv, String type)
    {
            this.name = name;
            this.type = type;
            this.invocation = inv;
            this.equations = new ArrayList<Equation>();
    }
    /**
     * Retrieves the name of this Function
     * @return String representation of the function name
     */
    public String getName()
    {
            return name;
    }
    /**
     * Retrieves the type of this Function
     * @return String representation of the function type
     */
    public String getType()
    {
            return type;
    }
    /**
     * Checks if this Function returns a Primitive type
     * @return boolean value representing the answer
     */
    public boolean returnsPrimitiveType()
    {
    	return type.equals("int") || type.equals("string") ||
    		   type.equals("character") || type.equals("boolean");
    }
    /**
     * Retrieves the invocation of this Function
     * @return Invocation
     */
    public Invocation getInvocations()
    {
            return invocation;
    }
    /**
     * Checks if the name of the Function is a primitive operation
     * @return boolean value representing the answer
     */
    public boolean isPrimitive()
    {
    	return name.equals("+") || name.equals("-") || name.equals("*") || 
    		   name.equals("=") || name.equals("<") || name.equals(">") || name.equals("not");
    }
    /**
     * Retrieves the equations of this Function
     * @return List of equations
     */
    public ArrayList<Equation> getEquations()
    {
    	return equations;
    }
    /**
     * Adds an Equation to the list of this Functions equations
     * @param e The equation to be added
     */
    public void addEquation(Equation e)
    {
            this.equations.add(e);
    }
}