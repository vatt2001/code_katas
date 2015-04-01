package ru.art_vt.katas.kata04;

import java.io.*;

public class Kata04 {
	public static void main(String[] argv) throws IOException
	{
		testReader(new FootballReader(), "Smallest difference number", "Aston_Villa");
		testReader(new WeatherReader(), "Smallest day number", "14");
	}

	private static void testReader(BaseReader reader, String resultDescription, String expectedResult) throws IOException
	{
		String result = String.valueOf(reader.getSmallestIndex());
		System.out.println(resultDescription + ": " + result);
		assertString(expectedResult, result);
	}

	private static void assertString(String expected, String actual)
	{
		if (!expected.equals(actual)) {
			System.err.println("Assertion error. Expected: \"" + expected + "\", got \"" + actual + "\"");
		}
	}
}
