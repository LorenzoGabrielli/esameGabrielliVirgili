package Model;

public class Season 
{

	public int id;
	public Date startDate;
	public Date endDate;
	public int currentMatchDay;
	public Team winner;
	
	//Costruttore di Default
		public Season()
		{
			this.id=0;
			this.startDate=new Date();
			this.endDate=new Date();
			this.currentMatchDay=0;
			this.winner=new Team();
		}
		
		
		//Costruttore
			public Season(int id, Date startDate, Date endDate, int currentMatchDay,Team winner)
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
	
	
				public Date getStartDate() {
					return startDate;
				}
	
	
				public void setStartDate(Date startDate) {
					this.startDate = startDate;
				}
	
	
				public Date getEndDate() {
					return endDate;
				}
	
	
				public void setEndDate(Date endDate) {
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
