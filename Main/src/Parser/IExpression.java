package Parser;
/**
 * Interface representing Expressions
 *
 */
public interface IExpression
{
	/**
	 * Checks if this is a functionCall
	 */
	public boolean isFunctionCall();
	/**
	 * Checks if this is a rangeVariable
	 */
	public boolean isRangeVariable();
	/**
	 * Checks if this IExpression contains the inputted RangeVariable
	 * @param r The RangeVariable to be checked for
	 */
	public boolean containsRangeVariable(RangeVariable r);
}