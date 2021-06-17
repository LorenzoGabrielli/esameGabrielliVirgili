/**
 * 
 */
package it.univpm.esameGabrielliVirgili.model;

/**
 * @author lorenzo gabrielli e claudio virgili
 *
 */
public class Team {
	public int id;
	public String name;
	
	//Costruttorre di Default
		public Team() 
		{
			this.id=0;
			this.name=null;
		}
		
		//Costruttore
			public Team(int id, String name)
			{
			  this.id=id;
			  this.name=name;
			
			}
			
			//Get-Set
				public int getId() {
					return id;
				}
	
				public void setId(int id) {
					this.id = id;
				}
	
				public String getName() {
					return name;
				}
	
				public void setName(String name) {
					this.name = name;
				}
	
				//Metodo To String
				/*@Override
				public String toString() {
					return "Team [id=" + id + ", name=" + name + ", tla=" + tla + ", lastUpdate=" + lastUpdate + "]";
				}*/
}
