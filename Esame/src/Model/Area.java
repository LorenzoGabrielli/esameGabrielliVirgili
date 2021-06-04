package Model;

public  class Area 
{

	public static  int id;
	public static  String name;
	public static  String countryCode;
	
	//Costruttore di Default
		public Area() 
		{
			Area.id=0;
			Area.name=null;
			Area.countryCode=null;
			
		}
		
		//Costruttore
		public Area(int id, String name, String countryCode) 
		{
			Area.id=id;
			Area.name=name;
			Area.countryCode=countryCode;
			
		}
		
		//Get-Set
			public static int getId() {
				return id;
			}
	
			public static void setId(int id) {
				Area.id = id;
			}
	
			public static String getName() {
				return name;
			}
	
			public static void setName(String name) {
				Area.name = name;
			}
	
			public static String getCountryCode() {
				return countryCode;
			}
	
			public static void setCountryCode(String countryCode) {
				Area.countryCode = countryCode;
			}
			
			
			//Metodo To String
				@Override
				public String toString()
				{
					return "Area [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
							+ super.toString() + "]";
				}			

}
