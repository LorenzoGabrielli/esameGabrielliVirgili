package Model;

public class Date 
{

	public String date;
	public String year;
	public String month;
	public String day;
	
	//Costruttore di Default
		public Date ()
		{
			this.date=null;
			this.year=null;
			this.month=null;
			this.day=null;
			
		}
		
		//Costruttore
			public Date (String date, String year, String month, String day)
			{
				this.date=date;
				this.year=year;
				this.month=month;
				this.day=day;
				
			}

			
			//Get-Set
				public String getDate() {
					return date;
				}
	
				public void setDate(String date) {
					this.date = date;
				}
	
				public String getYear() {
					return year;
				}
	
				public void setYear(String year) {
					this.year = year;
				}
	
				public String getMonth() {
					return month;
				}
	
				public void setMonth(String month) {
					this.month = month;
				}
	
				public String getDay() {
					return day;
				}
	
				public void setDay(String day) {
					this.day = day;
				}
				
					
}
