package Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * The class structure representing the input put into a Function
 *
 */
public class Invocation
{
        private List<String> parameterTypes;

        public Invocation(ArrayList<String> types)
        {
                parameterTypes = types;
        }
        /**
         * Retrieves the List of parameter types
         * @return The list of strings parameterTypes
         */
        public List<String> getParameterTypes()
        {
                return parameterTypes;
        }
}
