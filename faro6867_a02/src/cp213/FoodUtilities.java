package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Food objects.
 *
 * @author your name, id, email here
 * @version 2023-05-07
 */
public class FoodUtilities {

	/**
	 * Determines the average calories in a list of foods. No rounding necessary.
	 * Foods list parameter may be empty.
	 *
	 * @param foods a list of Food
	 * @return average calories in all Food objects
	 */
	public static int averageCalories(final ArrayList<Food> foods) {

		// your code here
		int sum = 0;
		for (int i = 0; i < foods.size(); i++) {
			sum += foods.get(i).getCalories();
		}
		sum /= foods.size();
		return sum;
	}

	/**
	 * Determines the average calories in a list of foods for a particular origin.
	 * No rounding necessary. Foods list parameter may be empty.
	 *
	 * @param foods  a list of Food
	 * @param origin the origin of the Food
	 * @return average calories for all Foods of the specified origin
	 */
	public static int averageCaloriesByOrigin(final ArrayList<Food> foods, final int origin) {

		// your code here
		int sum = 0;
		int counter = 0;
		for (int i = 0; i < foods.size(); i++) {
			int food = foods.get(i).getOrigin();
			if (food == origin) {
				sum += foods.get(i).getCalories();
				counter++;
			}
		}
		sum /= counter;

		return sum;
	}

	/**
	 * Creates a list of foods by origin.
	 *
	 * @param foods  a list of Food
	 * @param origin a food origin
	 * @return a list of Food from origin
	 */
	public static ArrayList<Food> getByOrigin(final ArrayList<Food> foods, final int origin) {

		// your code here
		ArrayList<Food> originList = new ArrayList<>();
		for (int i = 0; i < foods.size(); i++) {
			if (foods.get(i).getOrigin() == origin) {
				originList.add(foods.get(i));
			}
		}

		return originList;
	}

	/**
	 * Creates a Food object by requesting data from a user. Uses the format:
	 *
	 * <pre>
	Name: name
	Origins
	 0 Canadian
	 1 Chinese
	...
	11 English
	Origin: origin number
	Vegetarian (Y/N): Y/N
	Calories: calories
	 * </pre>
	 *
	 * @param keyboard a keyboard Scanner
	 * @return a Food object
	 */
	public static Food getFood(final Scanner keyboard) {

		// your code here
		boolean isVegetarian = true;
		System.out.print("Name: ");
		String name = keyboard.nextLine();
		System.out.print(Food.originsMenu());
		System.out.print("Origin: ");
		String originString = keyboard.nextLine();
		int origin = Integer.parseInt(originString);
		System.out.print("Vegetarian (Y/N): ");
		String vegetarianString = keyboard.nextLine();
		if (vegetarianString == "N") {
			isVegetarian = false;
		}
		System.out.print("Calories: ");
		String caloriesString = keyboard.nextLine();
		int calories = Integer.parseInt(caloriesString);
		Food foodz = new Food(name, origin, isVegetarian, calories);

		return foodz;
	}

	/**
	 * Creates a list of vegetarian foods.
	 *
	 * @param foods a list of Food
	 * @return a list of vegetarian Food
	 */
	public static ArrayList<Food> getVegetarian(final ArrayList<Food> foods) {

		// your code here
		ArrayList<Food> vegetarianFood = new ArrayList<>();
		for (int i = 0; i < foods.size(); i++) {
			if (foods.get(i).isVegetarian() == true) {
				vegetarianFood.add(foods.get(i));
			}
		}

		return vegetarianFood;
	}

	/**
	 * Creates and returns a Food object from a line of string data.
	 *
	 * @param line a vertical bar-delimited line of food data in the format
	 *             name|origin|isVegetarian|calories
	 * @return the data from line as a Food object
	 */
	public static Food readFood(final String line) {

		// your code here
		String[] arr = line.split("\\|");
		String name = arr[0];
		int origin = Integer.parseInt(arr[1]);
		boolean isVegetarian = Boolean.parseBoolean(arr[2]);
		int calories = Integer.parseInt(arr[3]);
		Food food = new Food(name, origin, isVegetarian, calories);
		return food;
	}

	/**
	 * Reads a file of food strings into a list of Food objects.
	 *
	 * @param fileIn a Scanner of a Food data file in the format
	 *               name|origin|isVegetarian|calories
	 * @return a list of Food
	 */
	public static ArrayList<Food> readFoods(final Scanner fileIn) {

		// your code here
		ArrayList<Food> listFood = new ArrayList<>();
		while (fileIn.hasNextLine()) {
			String line = fileIn.nextLine();
			Food food = readFood(line);
			listFood.add(food);
		}

		return listFood;
	}

	/**
	 * Searches for foods that fit certain conditions.
	 *
	 * @param foods        a list of Food
	 * @param origin       the origin of the food; if -1, accept any origin
	 * @param maxCalories  the maximum calories for the food; if 0, accept any
	 * @param isVegetarian whether the food is vegetarian or not; if false accept
	 *                     any
	 * @return a list of foods that fit the conditions specified
	 */
	public static ArrayList<Food> foodSearch(final ArrayList<Food> foods, final int origin, final int maxCalories,
			final boolean isVegetarian) {

		// your code here
		ArrayList<Food> newFood = new ArrayList<>();
		for (int i = 0; i < foods.size(); i++) {
			if (origin == foods.get(i).getOrigin() || origin == -1) {
				if (maxCalories >= foods.get(i).getCalories() || maxCalories == 0) {
					if (isVegetarian == foods.get(i).isVegetarian() || isVegetarian == false) {
						newFood.add(foods.get(i));
					}
				}
			}
		}

		return newFood;
	}

	/**
	 * Writes the contents of a list of Food to a PrintStream.
	 *
	 * @param foods a list of Food
	 * @param ps    the PrintStream to write to
	 */
	public static void writeFoods(final ArrayList<Food> foods, PrintStream ps) {

		// your code here
		for (int i = 0; i < foods.size(); i++) {
			foods.get(i).write(ps);
		}

	}
}
