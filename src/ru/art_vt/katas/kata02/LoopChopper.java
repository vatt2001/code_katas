package ru.art_vt.katas.kata02;

public class LoopChopper extends AbstractChopper
{
	public int chop(int needle, int[] haystack)
	{
		int startIndex = 0;
		int endIndex = haystack.length - 1;

		if (haystack.length == 0) {
			return NOT_FOUND;
		}

		while (startIndex <= endIndex) {
			if (endIndex == startIndex) {
				if (haystack[startIndex] == needle) {
					return startIndex;
				} else {
					return NOT_FOUND;
				}
			} else {
				int middleIndex	= startIndex + (endIndex - startIndex) / 2; // avoid integer overflow

				if (haystack[middleIndex] >= needle) {
					endIndex = middleIndex;
				} else {
					startIndex = middleIndex + 1;
				}
			}
		}

		return NOT_FOUND;
	}
}
