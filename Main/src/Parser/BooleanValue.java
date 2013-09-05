package Parser;

/**
 * Class structure representing Primitive boolean values
 * 
 *
 */
public class BooleanValue implements IExpression, PrimitiveValue<Boolean>
{
        public boolean val;

        public BooleanValue(boolean b)
        {
                this.val = b;
        }
        /**
         * Generates the Scheme string representation of this boolean value
         */
        public String toString()
        {
        	if (val)
        		return "#t";
        	return "#f";
        }
        /**
         * Retrieves the boolean from the structure
         */
		public Boolean getValue() {
			return new Boolean(val);
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
		 * Checks if this BooleanValue contains the inputed RangeVariable (always false)
		 * @return false
		 */
		public boolean containsRangeVariable(RangeVariable r) {
			return false;
		}
}