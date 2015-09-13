package ru.art_vt.katas.kata05;

public interface IHasher<T>
{
	public byte[] hash(T value);
}
