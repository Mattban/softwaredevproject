package Parser;

/**
 * Class structure representing integer values
 *
 */
public class IntValue implements IExpression, PrimitiveValue<Integer>
{
    public int val;

    public IntValue(int i)
    {
            this.val = i;
    }
    /**
     * Generates a string representation of the integer value
     */
    public String toString()
    {
    	return val + "";
    }
    /**
     * Retrieves the integer
     */
	public Integer getValue() {
		return new Integer(val);
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
	 * @return false
	 */
	public boolean isRangeVariable() {
		return false;
	}
	/**
	 * Check if this IntValue contains the inputed RangeVariable (always returns false)
	 * @return false
	 */
	public boolean containsRangeVariable(RangeVariable r) {
		return false;
	}
}