package Parser;

import java.util.ArrayList;

/**
 * The class structure representing the Algebraic Specification
 *
 */
public class ASpec
{
    private ArrayList<ADT> adts;

    public ASpec()
    {
            adts = new ArrayList<ADT>();
    }
   
    public ASpec(ArrayList<ADT> a)
    {
            adts = a;
    }
    /**
     * Retrieves the list of ADTs in this Algebraic Specification
     * @return The list of ADTs
     */
    public ArrayList<ADT> getADT()
    {
            return adts;
    }
    /**
     * Creates a string containing the names of all ADTs within this Algebraic Specification
     * @return The String containing the names of all the adts
     */
    public String toString()
    {
            StringBuffer sb = new StringBuffer();
            for (ADT a : adts)
            {
                    sb.append(a.name);
            }
            return sb.toString();
    }
}