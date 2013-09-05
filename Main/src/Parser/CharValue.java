package Parser;

/**
 * Class structure representing Character values
 *
 */
public class CharValue implements IExpression, PrimitiveValue<Character>
{
    public char val;

    public CharValue(char c)
    {
            this.val = c;
    }
    /**
     * Generates a string representing this value
     */
    public String toString()
    {
    	return "#\\" + val;
    }
    /**
     * Retrieves the character from this structure
     */
	public Character getValue() {
		return new Character(val);
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
	 * Checks if this CharValue contains the inputed RangeVariable (always false)
	 * @return false
	 */
	public boolean containsRangeVariable(RangeVariable r) {
		return false;
	}
}