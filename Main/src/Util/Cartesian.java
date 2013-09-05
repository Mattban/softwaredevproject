package Util;
import java.util.ArrayList;

/**
 * Creates a generic representation of the Cartesian product
 *
 */
public class Cartesian {
	
	private static <T> ArrayList<ArrayList<T>> computeProduct(ArrayList<T> leftSet, ArrayList<ArrayList<T>> sets)
	{
		ArrayList<ArrayList<T>> output = new ArrayList<ArrayList<T>>();
		
		if (sets.size() == 0)
		{
			for (T x : leftSet)
			{
				ArrayList<T> toAdd = new ArrayList<T>();
				toAdd.add(x);
				output.add(toAdd);
			}
		}else{
			ArrayList<T> nextSet = sets.remove(0);
			ArrayList<ArrayList<T>> rightSet = computeProduct(nextSet, sets);
			for (ArrayList<T> rightPart : rightSet)
			{
				for (T leftPart : leftSet)
				{
					ArrayList<T> toAdd = new ArrayList<T>();
					toAdd.add(leftPart);
					toAdd.addAll(rightPart);
					output.add(toAdd);
				}
			}
		}
		
		return output;
	}
	/**
	 * Computes the Cartesian Product 
	 * @param sets The input sets
	 * @return The computed product
	 */
	public static <T> ArrayList<ArrayList<T>> computeProduct(ArrayList<ArrayList<T>> sets)
	{
		if (sets.size() == 0)
			return new ArrayList<ArrayList<T>>();
		ArrayList<T> leftSet = sets.remove(0);
		return computeProduct(leftSet, sets);
	}
}