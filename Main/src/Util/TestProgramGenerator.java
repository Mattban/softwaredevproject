package Util;
import java.util.ArrayList;
import java.util.HashMap;

import TestGenerator.TestCase;
/**
 * Class used to create a runnable Scheme program from the created tests
 *
 */
public class TestProgramGenerator {

	public static String generateFromTestCases(ArrayList<TestCase> testCases, ArrayList<String> adtDependencies, boolean showTestsWithoutOutput)
	{
		StringBuilder s = new StringBuilder(
				";;; A black-box test program for ");
		
		for (String adt : adtDependencies)
			s.append(adt + " ");
		
		s.append("\n" +
				"(import (rnrs base)\n" +
				"        (rnrs exceptions)        ; for the guard syntax\n" +
				"        (rnrs io simple)         ; for display etc");
		
		for (String dependency : adtDependencies)
			s.append("\n        (testing " + dependency + ")");
		
		s.append(")\n\n;;; Counters for the summary report when testing is complete.\n" +
				"\n" +
				"(define tests-run 0)\n" +
				"(define tests-passed 0)\n" +
				"(define tests-failed 0)\n" +
				"\n" +
				";;; Syntax to make testing more convenient.\n" +
				";;;\n" +
				";;; (test <name> <expr>) evaluates <expr>.\n" +
				";;; If <expr> evaluates to a true value (any value other #f),\n" +
				";;; then the test has been passed.\n" +
				";;; If <expr> evaluates to #f, then the test has been failed.\n" +
				";;; If an exception occurs during evaluation of <expr>, then\n" +
				";;; the test has been failed.\n" +
				"\n" +
				"(define-syntax test\n" +
				"  (syntax-rules ()\n" +
				"    ((_ name expr)\n" +
				"     (begin (set! tests-run (+ tests-run 1))\n" +
				"            (if (guard (exn (else #f))\n" +
				"                  expr)\n" +
				"                (set! tests-passed (+ tests-passed 1))\n" +
				"                (begin (set! tests-failed (+ tests-failed 1))\n" +
				"                       (display \"Failed test: \")\n" +
				"                       (display name)\n" +
				"                       (newline)))))))\n" +
				"\n" +
				";;; The black-box tests.\n");
		
		s.append(getTestOutput(testCases, showTestsWithoutOutput));
		
		s.append("\n\n;;; Summary of results.\n\n" +
				"(display \"SUMMARY: failed \")\n" +
				"(display tests-failed)\n" +
				"(display \" of \")\n" +
				"(display tests-run)\n" +
				"(display \" tests.\")\n" +
				"(newline)\n\n" +
				";;; Sanity check.\n\n" +
				"(if (not (= tests-run\n" +
				"            (+ tests-passed tests-failed)))\n" +
				"    (begin (display \"Oops...\") (newline)))\n");
		
		return s.toString();
	}
	/**
	 * Changes testCases into properly formatted Scheme code
	 * @param testCases The list of test cases to be formatted
	 * @param showTestsWithoutOutput boolean value that turns on and off printing of test cases that we could not come up with an expected value for
	 * @return The formatted testCases
	 */
	private static String getTestOutput(ArrayList<TestCase> testCases, boolean showTestsWithoutOutput)
	{
		StringBuilder s = new StringBuilder();
		HashMap<String, Integer> functionTestCounters = new HashMap<String, Integer>();
		for (TestCase t : testCases)
		{
			if (t.getExpected() != null)
			{
				int testidx = 1;
				if (!functionTestCounters.containsKey(t.getActual().funcName))
					functionTestCounters.put(t.getActual().funcName, 1);
				else
				{
					testidx = functionTestCounters.get(t.getActual().funcName) + 1;
					functionTestCounters.put(t.getActual().funcName, testidx);
				}
				s.append("\n(test \"" + t.getActual().funcName + testidx + 
						"\" (" + getComparisonFunctionNameForType(t.getComparisonType()) + " " +
						t.getExpected().toString() + " " + t.getActual().toString() + "))");
			}
			else if (showTestsWithoutOutput)
				s.append("\n;;; expected output for case unknown : " + t.getActual().toString());
		}
		return s.toString();
	}
	/**
	 * Gets the proper scheme comparison function according to the type
	 * @param type the type being checked
	 * @return String representation of the proper comparison function
	 */
	private static String getComparisonFunctionNameForType(String type)
	{
		if (type.equals("int"))
			return "=";
		return "equal?";
	}
}
