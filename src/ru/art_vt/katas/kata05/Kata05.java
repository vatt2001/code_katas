package ru.art_vt.katas.kata05;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Kata05 {

	private static final String wordListPath = "/usr/share/dict/words";
	private static final int wordLength  = 5;
	private static Iterator<String> fixedWordListIterator;
	private static List<String> fixedWordList;


	public static void main(String[] argv) throws IOException
	{
		int testsQuantity = 10;
		int wordLength = 5;
//		List<String> wordList = initWordList();
		List<String> wordList = initShortWordList();

		// int sizeInBytes, int numberOfHashFunctions
		BloomFilterConfig[] bloomParamsList = new BloomFilterConfig[]{
			new BloomFilterConfig(1024, 1),
			new BloomFilterConfig(1024, 2),
			new BloomFilterConfig(1024, 4),
			new BloomFilterConfig(1024, 8),
			new BloomFilterConfig(2048, 1),
			new BloomFilterConfig(2048, 2),
			new BloomFilterConfig(2048, 4),
			new BloomFilterConfig(2048, 8),
			new BloomFilterConfig(4096, 1),
			new BloomFilterConfig(4096, 2),
			new BloomFilterConfig(4096, 4),
			new BloomFilterConfig(4096, 8),
			new BloomFilterConfig(16 * 1024, 1),
			new BloomFilterConfig(16 * 1024, 2),
			new BloomFilterConfig(16 * 1024, 4),
			new BloomFilterConfig(16 * 1024, 8),
			new BloomFilterConfig(64 * 1024, 2),
			new BloomFilterConfig(64 * 1024, 4),
			new BloomFilterConfig(64 * 1024, 8),
			new BloomFilterConfig(64 * 1024, 16),
		};

		for (BloomFilterConfig bloomConfig: bloomParamsList) {

			System.out.println("Trying BloomConfig: " + bloomConfig.toString());
			BloomFilter<String> filter = initBloomFilter(bloomConfig, wordList);
			BloomFilterStats stats = new BloomFilterStats(bloomConfig);

			for (int i = 0; i < testsQuantity; i++) {
//				String word = generateWord(wordLength);
				String word = generateKnownWord(wordLength);
				System.out.print(word);

				if (filter.isAdded(word)) {
					stats.addHit();
					System.out.print(" - hit");
					if (!wordList.contains(word)) {
						stats.addWrongHit();
						System.out.print(" (WRONG)");
					}
				} else {
					stats.addMiss();
					System.out.print(" - miss");
				}
				System.out.println();

				stats.addTry();
			}

			System.out.println("Stats: " + stats.toString());
		}

		// Вывести статистику
		System.out.println("OK");
	}

	private static List<String> initWordList() throws IOException
	{
		List<String> list = Files.readAllLines(Paths.get(wordListPath), Charset.forName("UTF-8"));
		ListIterator<String> iterator =  list.listIterator();
		while (iterator.hasNext()) {
			iterator.set(StringUtils.lowerCase(iterator.next()));
		}
		return list;
	}

	private static List<String> initShortWordList()
	{
		return
			new ArrayList<String>(16){{
				add("Alpha");
				add("Beta");
				add("Gamma");
				add("Delta");
				add("Epsilon");
			}};
	}

	private static String generateWord(int length)
	{
		StringBuilder word = new StringBuilder(wordLength);
		for (int i = 0; i < length; i++) {
			word.append((char)('a' + Math.floor(Math.random() * 26)));
		}
		return word.toString();
	}

	private static String generateKnownWord(int length)
	{
		if (fixedWordList == null) {
			fixedWordList =
				new ArrayList<String>(16){{
					add("Alpha");
					add("Beta");
					add("Epsilon");
					add("Theta");
				}};
		}

		if (fixedWordListIterator == null || !fixedWordListIterator.hasNext()) {
			fixedWordListIterator = fixedWordList.iterator();
		}

		return fixedWordListIterator.next();
	}

	private static BloomFilter<String> initBloomFilter(BloomFilterConfig config, List<String> wordList)
	{
		BloomFilter<String> filter = new BloomFilter<>(config, new StringMd5Hasher());
		for (String word : wordList) {
			filter.add(word);
		}

		return filter;
	}
}
