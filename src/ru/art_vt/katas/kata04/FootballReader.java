package ru.art_vt.katas.kata04;

public class FootballReader extends BaseReader{

	@Override
	protected String getResourceName()
	{
		return "ru/art_vt/katas/kata04/resources/football.dat";
	}

	protected boolean shouldParseRow(String row)
	{
		return row.substring(3, 5).trim().matches("\\d{1,2}");
	}

	protected String[] parseRow(String row)
	{
		return new String[] {
			row.substring(7, 21).trim(),
			row.substring(43, 45).trim(),
			row.substring(50, 52).trim(),
		};
	}
}



