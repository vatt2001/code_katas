package ru.art_vt.katas.kata02;

public class ObjectOrientedChopper extends AbstractChopper
{
	public int chop(int needle, int[] haystack)
	{
		ChopperExecutor executor = new ChopperExecutor(needle, haystack);

		while (executor.shouldChop()) {
			executor.doChop();
		}

		return executor.getResult();
	}

	private class ChopperExecutor
	{
		private int startIndex;

		private int endIndex;

		private int needle;

		private int[] haystack;

		public ChopperExecutor(int needle, int[] haystack) {
			this.needle = needle;
			this.haystack = haystack;
			this.startIndex = 0;
			this.endIndex = haystack.length - 1;
		}

		public boolean shouldChop()
		{
			return startIndex < endIndex;
		}

		public int getResult()
		{
			if (this.startIndex == this.endIndex) {
				if (this.haystack[this.startIndex] == this.needle) {
					return this.startIndex;
				} else {
					return NOT_FOUND;
				}
			} else {
				return NOT_FOUND;
			}
		}

		public ChopperExecutor doChop()
		{
			if (endIndex > startIndex) {
				int middleIndex	= startIndex + (endIndex - startIndex) / 2; // avoid integer overflow

				if (haystack[middleIndex] >= needle) {
					endIndex = middleIndex;
				} else {
					startIndex = middleIndex + 1;
				}
			}

			return this;
		}
	}
}
