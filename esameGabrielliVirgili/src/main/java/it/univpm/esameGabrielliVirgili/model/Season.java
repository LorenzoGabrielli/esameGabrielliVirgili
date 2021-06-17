/**
 * 
 */
package it.univpm.esameGabrielliVirgili.model;

/**
 * @author lorenzo gabrielli e claudio virgili
 *
 */
public class Season {
	public int id;
	public String startDate;
	public String endDate;
	public int currentMatchDay;
	public Team winner;
	
	//Costruttore di Default
		public Season()
		{
			this.id=0;
			this.startDate=null;
			this.endDate=null;
			this.currentMatchDay=0;
			this.winner=new Team();
		}
		
		
		//Costruttore
			public Season(int id, String startDate, String endDate, int currentMatchDay,Team winner)
			{
				this.id=id;
				this.startDate=startDate;
				this.endDate=endDate;
				this.currentMatchDay=currentMatchDay;
				this.winner=winner;
			}
			

			//Get-Set
				public int getId() {
					return id;
				}
	
	
				public void setId(int id) {
					this.id = id;
				}
	
	
				public String getStartDate() {
					return startDate;
				}
	
	
				public void setStartDate(String startDate) {
					this.startDate = startDate;
				}
	
	
				public String getEndDate() {
					return endDate;
				}
	
	
				public void setEndDate(String endDate) {
					this.endDate = endDate;
				}
	
	
				public int getCurrentMatchDay() {
					return currentMatchDay;
				}
	
	
				public void setCurrentMatchDay(int currentMatchDay) {
					this.currentMatchDay = currentMatchDay;
				}
	
	
				public Team getWinner() {
					return winner;
				}
	
	
				public void setWinner(Team winner) {
					this.winner = winner;
				}


				//Metodo To String
				@Override
				public String toString() 
				{
					return "Season [id=" + id + ", currentMatchDay=" + currentMatchDay + "]";
				}	
			     				
}
