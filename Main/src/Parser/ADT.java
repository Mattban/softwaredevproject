package Parser;

import java.util.ArrayList;

/**
 * The class structure representing the Abstract Data Type
 *
 */
public class ADT
{
        private ArrayList<Function> functions;
        public String name;

        public ADT(String n, ArrayList<Function> funcs)
        {
                name = n;
                functions = funcs;
        }
        /**
         * Retrieves the list of Functions contained in this Abstract Data Type
         * @return The list of functions
         */
        public ArrayList<Function> getFunctions()
        {
                return functions;
        }
}
