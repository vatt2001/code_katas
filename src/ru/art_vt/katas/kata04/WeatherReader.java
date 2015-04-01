package ru.art_vt.katas.kata04;

public class WeatherReader extends BaseReader {

	@Override
	protected String getResourceName()
	{
		return "ru/art_vt/katas/kata04/resources/weather.dat";
	}

	protected boolean shouldParseRow(String row)
	{
		return row.length() > 5 && row.substring(2, 4).trim().matches("\\d{1,2}");
	}

	protected String[] parseRow(String row)
	{
		return new String[] {
			row.substring(2, 4).trim(),
			row.substring(6, 8).trim(),
			row.substring(12, 14).trim(),
		};
	}
}



