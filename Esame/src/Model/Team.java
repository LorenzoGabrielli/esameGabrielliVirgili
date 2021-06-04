package Model;

public class Team 
{

	public int id;
	public Area area;
	public String name;
	public String tla;
	public String lastUpdate;
	
	//Costruttorre di Defautlù
		public Team() 
		{
			this.id=0;
			this.area=new Area();
			this.name=null;
			this.tla=null;
			this.lastUpdate=null;
		}
		
		//Costruttore
			public Team(int id, Area area, String name, String tla, String lastUpdate)
			{
			  this.id=id;
			  this.area=area;
			  this.name=name;
			  this.tla=tla;
			  this.lastUpdate=lastUpdate;
			
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
	
				public String getTla() {
					return tla;
				}
	
				public void setTla(String tla) {
					this.tla = tla;
				}
	
				public String getLastUpdate() {
					return lastUpdate;
				}
	
				public void setLastUpdate(String lastUpdate) {
					this.lastUpdate = lastUpdate;
				}

				//Metodo To String
				@Override
				public String toString() {
					return "Team [id=" + id + ", name=" + name + ", tla=" + tla + ", lastUpdate=" + lastUpdate + "]";
				}
				
				
				
			
			
			
	
}
