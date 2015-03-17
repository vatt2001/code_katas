package ru.art_vt.katas.kata02.functional_framework;

public interface IReducer<E, R>
{
	public Pair<E[], R> reduce(E[] items, R accumulator);
}
