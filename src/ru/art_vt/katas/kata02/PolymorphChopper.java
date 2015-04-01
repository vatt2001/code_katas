package ru.art_vt.katas.kata02;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PolymorphChopper extends AbstractChopper
{
	public int chop(int needle, int[] haystack)
	{
		int[] currentHaystack = haystack;
		int firstItemIndex = 0;

		ChoppingStrategyFactory factory = new ChoppingStrategyFactory(this);
		IChoppingStrategy strategy = factory.spawnStrategy(needle, haystack);

		while (!strategy.isDone()) {
			firstItemIndex = strategy.updateFirstIndex(firstItemIndex, currentHaystack);
			currentHaystack = strategy.chop(currentHaystack);
			strategy = factory.spawnStrategy(needle, currentHaystack);
		}

		return strategy.getResult(firstItemIndex);
	}

	interface IChoppingStrategy
	{
		public int[] chop(int[] haystack);
		public int updateFirstIndex(int firstItemIndex, int[] prevHaystack);
		public boolean isDone();
		public int getResult(int firstItemIndex);
	}

	abstract class nonFinalChopper implements IChoppingStrategy
	{
		@Override
		public int getResult(int firstItemIndex) {
			return NOT_FOUND;
		}

		@Override
		public boolean isDone() {
			return false;
		}
	}

	public class FirstHalfChopper extends nonFinalChopper
	{
		@Override
		public int[] chop(int[] haystack) {
			return Arrays.copyOfRange(haystack, 0, haystack.length / 2);
		}

		@Override
		public int updateFirstIndex(int firstItemIndex, int[] prevHaystack) {
			return firstItemIndex;
		}
	}

	public class SecondHalfChopper extends nonFinalChopper
	{
		@Override
		public int[] chop(int[] haystack) {
			return Arrays.copyOfRange(haystack, haystack.length / 2, haystack.length);
		}

		@Override
		public int updateFirstIndex(int firstItemIndex, int[] prevHaystack) {
			return firstItemIndex + prevHaystack.length / 2;
		}
	}

	abstract class FinalChopper implements IChoppingStrategy
	{
		@Override
		public boolean isDone() {
			return true;
		}

		@Override
		public int[] chop(int[] haystack) {
			return haystack;
		}

		@Override
		public int updateFirstIndex(int firstItemIndex, int[] prevHaystack) {
			return firstItemIndex;
		}
	}

	public class NotFoundChopper extends FinalChopper
	{
		@Override
		public int getResult(int firstItemIndex) {
			return NOT_FOUND;
		}
	}

	public class FoundChopper extends FinalChopper
	{
		@Override
		public int getResult(int firstItemIndex) {
			return firstItemIndex;
		}
	}

	public class ChoppingStrategyFactory
	{
		private PolymorphChopper parentClass;
		private Map<String, IChoppingStrategy> instances = new HashMap<>();

		public ChoppingStrategyFactory(PolymorphChopper parentClass)
		{
			this.parentClass = parentClass;
		}

		public IChoppingStrategy spawnStrategy(int needle, int[] haystack)
		{
			if (haystack.length > 1) {
				if (haystack[haystack.length / 2] > needle) {
					return spawnStrategy(FirstHalfChopper.class);

				} else {
					return spawnStrategy(SecondHalfChopper.class);
				}
			} else if (haystack.length == 1) {
				if (haystack[0] == needle) {
					return spawnStrategy(FoundChopper.class);
				} else {
					return spawnStrategy(NotFoundChopper.class);
				}
			} else {
				return spawnStrategy(NotFoundChopper.class);
			}
		}

		private IChoppingStrategy spawnStrategy(Class<? extends IChoppingStrategy> className)
		{
			String key = className.getSimpleName();
			if (instances.containsKey(key)) {
				return instances.get(key);
			} else {
				try {
					IChoppingStrategy newInstance = (IChoppingStrategy)className.getConstructors()[0].newInstance(parentClass);
					instances.put(key, newInstance);
					return newInstance;
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}

