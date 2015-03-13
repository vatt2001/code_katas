package ru.art_vt.katas.kata02;

/**
 * Pure functional style is not applicable here (as it degenerates into ResursiveChopper)
 * Hence this is a simple functions implementation
 */
public class FunctionalChopper extends AbstractChopper
{
	final int SHOULD_MAKE_SLICE = -2;

	public int chop(int needle, int[] haystack)
	{
		throw new RuntimeException("Not implemented yet");
	}

	/**
	 * @param needle
	 * @param haystack
	 * @return value >= 0 when found, NOT_FOUND when not found and SHOULD_MAKE_SLICE if array is yet too big to decide
	 */
	private int testSlice(int needle, int[] haystack)
	{
		throw new RuntimeException("Not implemented yet");
	}

	private int[] makeSlice(int needle, int[] haystack)
	{
		throw new RuntimeException("Not implemented yet");
	}
}
