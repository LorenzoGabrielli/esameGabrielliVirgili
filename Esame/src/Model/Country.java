package Model;

public class Country extends Area
{

	public Area parentArea;
	
	//Costruttore di Default
		public Country()
		{
			this.parentArea=new Area();	
		}
	
		//Costruttore 
		public Country ( int id, String name, String countryCode, Area paentArea, Area parentArea)
		{
			super(id,name,countryCode);
			this.parentArea=parentArea;
		}
		
		
	
}
