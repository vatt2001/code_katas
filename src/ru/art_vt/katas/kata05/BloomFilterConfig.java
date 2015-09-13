package ru.art_vt.katas.kata05;

class BloomFilterConfig
{
	private int sizeInBytes;
	private int numberOfHashFunctions;

	BloomFilterConfig(int sizeInBytes, int numberOfHashFunctions)
	{
		this.sizeInBytes = sizeInBytes;
		this.numberOfHashFunctions = numberOfHashFunctions;
	}

	public int getSizeInBytes()
	{
		return sizeInBytes;
	}

	public int getNumberOfHashFunctions()
	{
		return numberOfHashFunctions;
	}

	@Override
	public String toString()
	{
		return String.format("size in bytes = %d, number of hash functions = %d", sizeInBytes, numberOfHashFunctions);
	}
}
