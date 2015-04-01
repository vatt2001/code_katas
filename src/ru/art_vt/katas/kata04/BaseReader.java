package ru.art_vt.katas.kata04;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

abstract class BaseReader {

	public String getSmallestIndex() throws IOException
	{
		String[][] rows = readRows();
		String smallestDay = "n/a";
		int difference = Integer.MAX_VALUE;
		int currentDifference;

		for (String[] row: rows) {
			currentDifference = Math.abs(Integer.valueOf(row[2]) - Integer.valueOf(row[1]));
			if (currentDifference < difference) {
				smallestDay = row[0];
				difference = currentDifference;
			}
		}

		return smallestDay;
	}

	abstract protected String getResourceName();

	abstract protected boolean shouldParseRow(String row);

	abstract protected String[] parseRow(String row);

	private String[][] readRows() throws IOException
	{
		File file = getResourceFile(getResourceName());

		LineIterator iterator = FileUtils.lineIterator(file, "UTF-8");

		return fetchData(iterator);
	}

	private File getResourceFile(String filePath)
	{
		URL url = this.getClass().getClassLoader().getResource(filePath);
		File file;

		if (url == null) {
			throw new RuntimeException("Can not open file " + filePath);
		}

		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			file = new File(url.getPath());
		}

		return file;
	}

	private String[][] fetchData(Iterator<String> iterator)
	{
		List<String[]> result = new LinkedList<>();
		String row;

		while (iterator.hasNext()) {
			row = iterator.next();
			if (shouldParseRow(row)) {
				result.add(parseRow(row));
			}
		}

		return result.toArray(new String[0][0]);
	}
}



