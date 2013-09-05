package Parser;
/**
 * Class Structure representing String values
 *
 */
public class StringValue implements IExpression, PrimitiveValue<String>
{
        public String val;

        public StringValue(String s)
        {
                this.val = s;
        }
        /**
         * Returns a String representation of the value
         */
        public String toString()
        {
        	return "\"" + val + "\"";
        }
        /**
         * Retrieves the string
         */
		public String getValue() {
			return val;
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
		 * Checks if this StringValue contains the inputed RangeVariable (always returns false)
		 * @return false
		 */
		public boolean containsRangeVariable(RangeVariable r) {
			return false;
		}
}