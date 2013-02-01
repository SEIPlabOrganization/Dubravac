package project1;

public class Conversion
{
	//Convert from jQuery datepicker value format to MySQL date value format
	//input date format: month/day/year
	//output date format: year-month-day
	public String DateConversion (String year)
	{
		try
		{
			String month = year.substring(0, 2);
			String day = year.substring(3, 5);
			year = year.substring(6);
			day = year + "-" + month + "-" + day;
			return day;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	//Convert from format 'value1;value2' to array[0]="value1" and array[1]="value2"
	//input: string format 'value1;value2'
	public String[] NameConversion (String NameSurname)
	{
		try
		{
			String[] values;
			values = new String[2];
			
			byte index = (byte)NameSurname.indexOf(";");
			values[1] = NameSurname.substring(index+1);
			values[0] = NameSurname.substring(0, index);
			
			return values;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
