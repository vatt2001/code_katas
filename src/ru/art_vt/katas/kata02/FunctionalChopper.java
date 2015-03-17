package ru.art_vt.katas.kata02;

import org.apache.commons.lang3.ArrayUtils;
import ru.art_vt.katas.kata02.functional_framework.*;

import java.util.Arrays;

public class FunctionalChopper extends AbstractChopper
{
	public int chop(int needle, int[] haystack)
	{
		return
			new ReduceExecutor<Integer, ReduceAccumulator<Integer>>()
				.execute(
					ArrayUtils.toObject(haystack),
					new ReduceAccumulator<Integer>(needle, 0, NOT_FOUND),
					new BinarySearchReducer<Integer>()
				)
					.getResultIndex();
	}

	private class ReduceAccumulator<E>
	{
		private E needle;
		private int firstItemOffset;
		private int resultIndex;

		private ReduceAccumulator(E needle, int firstItemOffset, int resultIndex) {
			this.needle = needle;
			this.firstItemOffset = firstItemOffset;
			this.resultIndex = resultIndex;
		}

		public E getNeedle() {
			return needle;
		}

		public int getFirstItemOffset() {
			return firstItemOffset;
		}

		public int getResultIndex() {
			return resultIndex;
		}

		public ReduceAccumulator<E> spawnNew(int firstItemOffset, int resultIndex) {
			return
				new ReduceAccumulator<E>(
					this.needle,
					firstItemOffset,
					resultIndex
				);
		}
	}

	private class BinarySearchReducer<E extends Comparable<E>> implements IReducer<E, ReduceAccumulator<E>>
	{
		@Override
		public Pair<E[], ReduceAccumulator<E>> reduce(E[] items, ReduceAccumulator<E> accumulator) {
			if (items.length > 1) {
				if (items[items.length / 2].compareTo(accumulator.getNeedle()) > 0) {
					return
						new Pair<E[], ReduceAccumulator<E>>(
							Arrays.copyOfRange(items, 0, items.length / 2),
							accumulator.spawnNew(
								accumulator.getFirstItemOffset(),
								NOT_FOUND
							)
						);
				} else {
					return
						new Pair<E[], ReduceAccumulator<E>>(
							Arrays.copyOfRange(items, items.length / 2, items.length),
							accumulator.spawnNew(
								accumulator.getFirstItemOffset() + items.length / 2,
								NOT_FOUND
							)
						);
				}
			} else if (items.length == 1) {
				if (items[0] == accumulator.getNeedle()) {
					return
						new Pair<E[], ReduceAccumulator<E>>(
							emptyArray(),
							accumulator.spawnNew(
								accumulator.getFirstItemOffset(),
								accumulator.getFirstItemOffset()
							)
						);
				} else {
					return
						new Pair<E[], ReduceAccumulator<E>>(
							emptyArray(),
							accumulator.spawnNew(
								accumulator.getFirstItemOffset(),
								NOT_FOUND
							)
						);
				}
			} else {
				return
					new Pair<E[], ReduceAccumulator<E>>(
						emptyArray(),
						accumulator.spawnNew(
							accumulator.getFirstItemOffset(),
							NOT_FOUND
						)
					);
			}
		}

		@SuppressWarnings("unchecked")
		private E[] emptyArray()
		{
			return (E[]) new Comparable[0];
		}
	}
}

