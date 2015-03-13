package ru.art_vt.katas.kata02;

import java.util.Arrays;

public class RecursiveChopper extends AbstractChopper
{
	public int chop(int needle, int[] haystack)
	{
		return doChop(needle, haystack, 0);
	}

	private int doChop(int needle, int[] haystack, int firstItemIndex)
	{
		if (haystack.length > 1) {
			if (haystack[haystack.length / 2] > needle) {
				return
					doChop(
						needle,
						Arrays.copyOfRange(haystack, 0, haystack.length / 2),
						firstItemIndex
					);
			} else {
				return
					doChop(
						needle,
						Arrays.copyOfRange(haystack, haystack.length / 2, haystack.length),
						firstItemIndex + haystack.length / 2
					);
			}
		} else if (haystack.length == 1) {
			if (haystack[0] == needle) {
				return firstItemIndex;
			} else {
				return NOT_FOUND;
			}
		} else {
			return NOT_FOUND;
		}
	}
}
