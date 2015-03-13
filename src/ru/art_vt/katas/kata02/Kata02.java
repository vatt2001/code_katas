package ru.art_vt.katas.kata02;

public class Kata02
{
	public static void main(String[] argv)
	{
		testChop(new RecursiveChopper());
		testChop(new LoopChopper());
//		testChop(new StubChopper());

	}

	private static void testChop(IChopper chopper)
	{
		try {
			assertEqual(-1, chopper.chop(3, new int[]{}));
			assertEqual(-1, chopper.chop(3, new int[]{1}));
			assertEqual(0,  chopper.chop(1, new int[]{1}));

			assertEqual(0,  chopper.chop(1, new int[]{1, 3, 5}));
			assertEqual(1,  chopper.chop(3, new int[]{1, 3, 5}));
			assertEqual(2,  chopper.chop(5, new int[]{1, 3, 5}));
			assertEqual(-1, chopper.chop(0, new int[]{1, 3, 5}));
			assertEqual(-1, chopper.chop(2, new int[]{1, 3, 5}));
			assertEqual(-1, chopper.chop(4, new int[]{1, 3, 5}));
			assertEqual(-1, chopper.chop(6, new int[]{1, 3, 5}));

			assertEqual(0,  chopper.chop(1, new int[]{1, 3, 5, 7}));
			assertEqual(1,  chopper.chop(3, new int[]{1, 3, 5, 7}));
			assertEqual(2,  chopper.chop(5, new int[]{1, 3, 5, 7}));
			assertEqual(3,  chopper.chop(7, new int[]{1, 3, 5, 7}));
			assertEqual(-1, chopper.chop(0, new int[]{1, 3, 5, 7}));
			assertEqual(-1, chopper.chop(2, new int[]{1, 3, 5, 7}));
			assertEqual(-1, chopper.chop(4, new int[]{1, 3, 5, 7}));
			assertEqual(-1, chopper.chop(6, new int[]{1, 3, 5, 7}));
			assertEqual(-1, chopper.chop(8, new int[]{1, 3, 5, 7}));

			System.out.println(String.format("%s: success", chopper.getClass().getSimpleName()));
		} catch (RuntimeException e) {
			System.out.println(String.format("%s: ERROR", chopper.getClass().getSimpleName()));
			e.printStackTrace(System.out);
		}
	}

	private static void assertEqual(int expected, int got)
	{
		if (expected != got) {
			throw new RuntimeException(String.format("Expected %d, got %d", expected, got));
		}
	}
}

