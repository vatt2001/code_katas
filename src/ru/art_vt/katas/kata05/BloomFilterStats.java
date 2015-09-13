package ru.art_vt.katas.kata05;

public class BloomFilterStats
{
	private BloomFilterConfig config;
	private int tries = 0;
	private int hits = 0;
	private int misses = 0;
	private int wrongHits = 0;

	public BloomFilterStats(BloomFilterConfig config)
	{
		this.config = config;
	}

	public BloomFilterStats addTry()
	{
		this.tries += 1;
		return this;
	}

	public BloomFilterStats addHit()
	{
		this.hits += 1;
		return this;
	}

	public BloomFilterStats addMiss()
	{
		this.misses += 1;
		return this;
	}

	public BloomFilterStats addWrongHit()
	{
		this.wrongHits += 1;
		return this;
	}

	@Override
	public String toString()
	{
		return String.format("Hits / wrong hits / misses / total tries: %5d / %5d / %5d / %5d", hits, wrongHits, misses, tries);
	}
}
