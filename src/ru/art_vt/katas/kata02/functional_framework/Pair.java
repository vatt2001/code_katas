package ru.art_vt.katas.kata02.functional_framework;

public class Pair<A, B>
{
	public A getFirst()
	{
		return a;
	}

	public B getSecond()
	{
		return b;
	}

	private A a;
	private B b;

	public Pair(A a, B b)
	{
		this.a = a;
		this.b = b;
	}
}
