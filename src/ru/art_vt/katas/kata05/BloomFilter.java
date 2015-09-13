package ru.art_vt.katas.kata05;

public class BloomFilter<T> {

	private IHasher<T> hasher;

	private BloomFilterConfig config;

	private byte[] data;

	private int singleHashLengthInBits;

	public BloomFilter(BloomFilterConfig config, IHasher<T> hasher)
	{
		this.hasher = hasher;
		this.config = config;
		this.data = new byte[config.getSizeInBytes()];
		this.singleHashLengthInBits = getBitsQtyForAddressingDataBits(config.getSizeInBytes() * 8 - 1);
	}

	public BloomFilter add(T value)
	{
		byte[] hash = hasher.hash(value);
		int hashValue;

		for (int hashIndex = 0; hashIndex < config.getNumberOfHashFunctions(); hashIndex++) {
			hashValue = extractHashValue(hash, hashIndex);
			setDataBit(hashValue);
		}

		return this;
	}

	public boolean isAdded(T value)
	{
		byte[] hash = hasher.hash(value);
		int hashValue;
		boolean isValueAdded = true;

		for (int hashIndex = 0; isValueAdded && hashIndex < config.getNumberOfHashFunctions(); hashIndex++) {
			hashValue = extractHashValue(hash, hashIndex);
			isValueAdded = isValueAdded && isDataBitSet(hashValue);
		}
		return isValueAdded;
	}

	private boolean isDataBitSet(int offset)
	{
		assertDataOffset(offset);
		return ((data[offset / 8] >> (offset % 8)) & 1) != 0;
	}

	private void setDataBit(int offset)
	{
		assertDataOffset(offset);
		data[offset / 8] |= 1 << (offset % 8);
	}

	private int extractHashValue(byte[] wholeHash, int hashOffset)
	{
		int expectedHashLength = (int)Math.ceil((hashOffset + 1) * singleHashLengthInBits / 8.0);

		if (wholeHash.length < expectedHashLength) {
			throw new RuntimeException(
				String.format(
					"Bloom filter got very short hash. Expected at least %d byte(s), got %d byte(s)",
					expectedHashLength,
					wholeHash.length
				)
			);
		}

		int hashValue = 0;
		byte currentBitValue;
		int wholeHashBitIndex;
		for (int hashBitIndex = 0; hashBitIndex < singleHashLengthInBits; hashBitIndex++) {
			wholeHashBitIndex = hashOffset * singleHashLengthInBits + hashBitIndex;
			currentBitValue = (byte)((wholeHash[wholeHashBitIndex / 8] >> (wholeHashBitIndex % 8)) & 1);
			hashValue |= currentBitValue << hashBitIndex;
		}

		return hashValue;
	}

	private void assertDataOffset(int offset)
	{
		if (offset < 0 || offset >= config.getSizeInBytes() * 8) {
			throw new IndexOutOfBoundsException(
				String.format(
					"Bloom filter offset is out of range. Valid: 0 <= offset < %d, got %d, singleHashLengthInBits = %d",
					config.getSizeInBytes() * 8,
					offset,
					singleHashLengthInBits
				)
			);
		}
	}

	private int getBitsQtyForAddressingDataBits(int dataBitsQty)
	{
		int bitQtyForAddressingDataBits = 0;

		while (dataBitsQty > 0) {
			dataBitsQty >>= 1;
			bitQtyForAddressingDataBits++;
		}

		return bitQtyForAddressingDataBits;
	}
}
