package ru.art_vt.katas.kata02.functional_framework;

public class ReduceExecutor<E, R>
{
	public R execute(E[] items, R initialAccumulatorValue, IReducer<E, R> reducer)
	{
		E[] currentItems = items;
		Pair<E[], R> reduceResult;
		R accumulator = initialAccumulatorValue;

		while (currentItems.length > 0) {
			reduceResult = reducer.reduce(currentItems, accumulator);
			currentItems = reduceResult.getFirst();
			accumulator = reduceResult.getSecond();
		}

		return accumulator;
	}
}
