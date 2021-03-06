package controller;

import java.util.*;

import model.Country;
import model.Player;

/**
 * Fortification COntroller has all the methods required during fortification
 * phase of the game
 * 
 * @author bhargav
 * @version 1.0.0
 */
public class FortificationController {
	/**
	 * Gets a list of countries that the player owns
	 * 
	 * @param player: Player object must be given to fetch the countries
	 * @return List of countries owned by the player
	 */
	public List<Country> getMyCountries(Player player) {
		List<Country> countries = new ArrayList<Country>();
		for (Map.Entry<String, Country> entry : ReadingFiles.CountryNameObject.entrySet()) {
			if (entry.getValue().getOwner().equals(player)) {
				countries.add(entry.getValue());
			} else
				continue;
		}
		return countries;
	}

	/**
	 * Method to find if there a path between two countries or not
	 * 
	 * @param source: The country which armies are being moved from
	 * @param destination: The country which armies are being moved to
	 * @return Returns true if there is a path to move the armies between countries
	 */
	public boolean hasPathBFS2(Country source, Country destination) {
		LinkedList<Country> nexttovisit = new LinkedList<Country>();
		HashSet<Country> visited = new HashSet<Country>();
		nexttovisit.add(source);
		while (!nexttovisit.isEmpty()) {
			Country node = nexttovisit.remove();
			if (node.equals(destination)) {
				return true;
			}
			if (visited.contains(node))
				continue;
			visited.add(node);
			for (Country child : node.getNeighbors()) {
				if (child.getOwner().equals(node.getOwner())) {
					nexttovisit.add(child);
				}
			}
		}
		return false;
	}

	/**
	 * Method to validate and move armies between two countries owned by the same
	 * player
	 * 
	 * @param sourcecountry: The country which armies are being moved from
	 * @param destinationcountry: The country which armies are being moved to
	 * @param noofarmiestobemoved: Armies asked by player to move
	 * @return: Returns a string if there is any error or a null string if
	 *          validations succeed
	 */
	public String moveArmies(Country sourcecountry, Country destinationcountry, int noofarmiestobemoved) {
		if (sourcecountry.getNoOfArmies() < 2) {
			return "less army";
		} else if (noofarmiestobemoved >= sourcecountry.getNoOfArmies()) {
			int CanMove = sourcecountry.getNoOfArmies() - 1;
			System.out.println(CanMove);
			return "You can only move"+CanMove;
		} else if (!hasPathBFS2(sourcecountry, destinationcountry)) {

			return "NO path";

		} else {
			sourcecountry.setNoOfArmies(sourcecountry.getNoOfArmies() - noofarmiestobemoved);
			destinationcountry.setNoOfArmies(destinationcountry.getNoOfArmies() + noofarmiestobemoved);
			return "";
		}
	}
}
