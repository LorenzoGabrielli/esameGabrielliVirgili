package Model;

import java.util.Vector;

public class Continent extends Area
{
	public Area parentArea;
	public Vector<Area> childAreans;
	
	//Costruttore di Default
		public Continent()
		{
			this.parentArea=new Area();
			this.childAreans=new Vector<Area>();
			
		}
		
		//Costruttore
		public Continent (int id, String name, String countryCode, Area parentArea, Vector<Area> childAreans)
		{
			super(id, name, countryCode);
			this.parentArea=parentArea;
			this.childAreans=childAreans;
			
		}
}
