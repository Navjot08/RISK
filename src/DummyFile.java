import java.util.ArrayList;
import java.util.List;

public class DummyFile {
	
	public static void main(String[] args)
	{
		Country country1 = new Country("India");
		country1.setContinentId(1);
		country1.setCountryId(11);
		country1.setId("yellow");
		country1.setName("India");
		
		Country country2 = new Country("China");
		country2.setContinentId(2);
		country2.setCountryId(21);
		country2.setId("red");
		country2.setName("China");
		
		Country country3 = new Country("Pakistan");
		country3.setContinentId(3);
		country3.setCountryId(31);
		country3.setId("green");
		country3.setName("Pakistan");
		
		List<Country> n_list = new ArrayList<Country>();
		n_list.add(country2);
		n_list.add(country3);
	
		country1.setNeighbors(n_list);
		country1.setNoOfArmies(4);
		country1.set
	}

}