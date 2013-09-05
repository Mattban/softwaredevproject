package Parser;

/**
 * Class structure representing RangeVariables
 *
 */
public class RangeVariable implements IExpression
{
    public String name;

    public RangeVariable(String n)
    {
            this.name = n;
    }
    /**
     * Compares this RangeVariable to another Object to see if they are the same
     */
    public boolean equals(Object o)
    {
    	if (!(o instanceof RangeVariable))
    		return false;
    	return ((RangeVariable)o).name.equals(name);
    }
    /**
     * Creates a hashcode for this RangeVariable
     */
    public int hashCode()
    {
    	return name.hashCode() * 2;
    }
    /**
     * Generates a String representation of the name of this RangeVariable
     */
    public String toString()
    {
    	return name;
    }
    /**
     * Checks if this is a FunctionCall
     * @return false
     */
	public boolean isFunctionCall() {
		return false;
	}
	/**
	 * Checks if this is a RangeVariable
	 * @return true
	 */
	public boolean isRangeVariable() {
		return true;
	}
	/**
	 * Checks if this RangeVariable is the same as the inputed RangeVariable
	 */
	public boolean containsRangeVariable(RangeVariable r) {
		return equals(r);
	}
}