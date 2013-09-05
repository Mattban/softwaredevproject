package TestGenerator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Parser.ADT;
import Parser.ADTParser;
import Parser.ASpec;
import Parser.ParseException;
import Util.TestProgramGenerator;

public class BlackBoxTestGeneratorCLI {
	
	/** Main entry point. 
	 * 
	 * (path of input file) (path of output file to be generated) [
	 * */
	  public static void main(String args[]) throws ParseException {
		  try {
			if (args.length < 2 || args[0].equals("-h") || args[0].equals("-help"))
			{
				printHelp();
				return;
			}
			ConsoleArgs consoleArgs = ConsoleArgs.ParseArguments(args);
			FileReader reader = new FileReader(args[0]);
			ADTParser parser = new ADTParser(reader);
	        ASpec adt = parser.Input();
	        reader.close();
	        ArrayList<TestCase> testCases = 
	        		TestGenerator.GenerateTests(adt, 
	        				consoleArgs.maxTests, consoleArgs.maxDepth);
	        String s = 
	        		TestProgramGenerator.generateFromTestCases(testCases, 
	        				getAllADTNames(adt), consoleArgs.all);
	        FileWriter writer = new FileWriter(args[1]);
	        writer.write(s);
	        writer.close();
	        System.out.println(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	  }
	  
	  /**
	   * Output command line input specification information to the console
	   */
	  private static void printHelp()
	  {
		  System.out.println("Pass as command line arguments: ([]s indicate optional parameters)\n" +
		  		"(path of input file) (path of output file to be generated) " +
		  		"[-all] [-maxTests (# tests)] [-maxDepth (# deep)]\n" +
		  		"or\n" +
		  		"[-h] [-help] \n\n" +
		  		"Optional Flags:\n\n" +
		  		"-all\n" +
		  		"Output all generated test cases even if an equality test could not be generated\n" +
		  		"(test cases without equality tests will be output as comments in the test program)\n\n" +
		  		"-maxTests (# tests)\n" +
		  		"Explictly set the maximum number of test cases to be generated per function.\n" +
		  		"The maximum number of tests is 100 per function by default.\n\n" +
		  		"-maxDepth (depth)\n" +
		  		"Explictly set the maximum call depth for generated test cases.\n" +
		  		"The maximum depth is 3 by default.\n\n" +
		  		"Passing only -h or -help (without file paths)\n" +
		  		"Shows this help information");
	  }
	  
	  /**
	   * Creates a list containing the name of all the ADTs within the Algebraic Specification
	   * @param a The Algebraic Specification
	   * @return The list of names of ADTs
	   */
	  private static ArrayList<String> getAllADTNames(ASpec a)
	  {
		  ArrayList<String> adts = new ArrayList<String>();
		  for (ADT adt : a.getADT())
			  adts.add(adt.name);
		  return adts;
	  }
}

/**
 * CONSOLE ARGS
 * Local class used to assist with parsing console arguments
 * @author Adam Heaney
 *
 */
class ConsoleArgs
{
	public boolean all;
	public int maxDepth, maxTests;
	
	public ConsoleArgs()
	{
		all = false;
		maxDepth = 3;
		maxTests = 100;
	}
	
	/**
	 * Create an instance of ConsoleArgs from string data
	 * @param args - arguments as string data
	 */
	public static ConsoleArgs ParseArguments(String[] args)
	{
		ConsoleArgs cargs = new ConsoleArgs();
		for (int i = 2; i < args.length; i++)
		{
			if (args[i].equals("-all"))
				cargs.all = true;
			else if (args[i].equals("-maxDepth"))
			{
				if (i < args.length - 1)
				{
					try{
						cargs.maxDepth = Integer.parseInt(args[i + 1]);
						if (cargs.maxDepth < 1)
							throw new NumberFormatException();
						System.out.println("Depth limit set to: " + cargs.maxDepth);
						i++;
					}catch (NumberFormatException e){
						System.out.println("Invalid depth limit. Setting limit to 3 (default)");
						cargs.maxDepth = 3;
					}
				}
			}
			else if (args[i].equals("-maxTests"))
			{
				if (i < args.length - 1)
				{
					try{
						cargs.maxTests = Integer.parseInt(args[i + 1]);
						if (cargs.maxTests < 1)
							throw new NumberFormatException();
						System.out.println("Test limit set to: " + cargs.maxTests);
						i++;
					}catch (NumberFormatException e){
						System.out.println("Invalid test limit. Setting limit to 100 (default)");
						cargs.maxTests = 100;
					}
				}
			}
		}
		return cargs;
	}
}
