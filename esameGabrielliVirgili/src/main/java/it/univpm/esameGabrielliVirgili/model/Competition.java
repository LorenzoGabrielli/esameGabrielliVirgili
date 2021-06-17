package it.univpm.esameGabrielliVirgili.model;

/**
 * 
 * @author lorenzo gabrielli e claudio virgili
 *
 */
public class Competition {
	public int id;
	public Area area;
	public String name;
	public String code;
	public Season currentSeason;
	public int numberOfAvaiable;

	
	//Costruttore di Default
		public Competition()
		{
			this.id=0;
			this.area=new Area();
			this.name=null;
			this.code=null;
			this.currentSeason=new Season();
			this.numberOfAvaiable=0;
				
		}
		
		//Costruttore
			public Competition(int id, Area area, String name, String code, Season currentSeason, int numberOfAvaiable)
			{
			  this.id=id;
			  this.area=area;
			  this.name=name;
			  this.code=code;
			  this.currentSeason=currentSeason;
			  this.numberOfAvaiable=numberOfAvaiable;
			}

			
			//Get-Set
			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public Area getArea() {
				return area;
			}

			public void setArea(Area area) {
				this.area = area;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getCode() {
				return code;
			}

			public void setCode(String code) {
				this.code = code;
			}

			public Season getCurrentSeason() {
				return currentSeason;
			}

			public void setCurrentSeason(Season currentSeason) {
				this.currentSeason = currentSeason;
			}

			public int getNumberOfAvaiable() {
				return numberOfAvaiable;
			}

			public void setNumberOfAvaiable(int numberOfAvaiable) {
				this.numberOfAvaiable = numberOfAvaiable;
			}

			//Metodo to String
				@Override
				public String toString() 
				{
					
				return "Competition [id=" + id + ", name=" + name + ", code=" + code + ", numberOfAvaiable="
						+ numberOfAvaiable + "]";
				}
}
