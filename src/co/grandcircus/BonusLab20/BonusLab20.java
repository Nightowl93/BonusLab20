package co.grandcircus.BonusLab20;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class BonusLab20 {

	public static void main(String[] args) {
		
		//Declares variables and creates scanner
		Scanner scnr = new Scanner(System.in);
		String itemSelected;
		boolean keepShopping = true;
		String userResponse;
		String userResponse2;
		int cartCount = 0;
		boolean newOrder = true;
		
		//Creates Parallel ArrayLists to store shopping cart items and prices
		ArrayList<String> foodCart = new ArrayList<>();
		ArrayList<Double> priceCart = new ArrayList<>();
		
		//Welcomes the user
		System.out.println("Welcome to The Sun Room SuperMarket!\n\n");
		
		//Declare hashmap to store food and prices
		Map<String, Double> groceries = new HashMap<>();
		
		//Inserts contents of hashmap (i.e. enters available groceries)
		stockGroceries(groceries);
		
		//Keeps looping as long as user wishes to place additional orders
		do {
			
			//Loops as long as user wishes to keep adding items to current order
			do {
				//Calls method to print list of available groceries and prices
				showGroceries(groceries);
				
				//Prompts user to select items
				System.out.println("\nWhat item would you like to add to your shopping cart?");
				itemSelected = scnr.nextLine().trim().toUpperCase();
				
				//Adds items to shopping cart (i.e. parallel arrays) if they are  available and keeps count of num items in cart
				if (groceries.containsKey(itemSelected)) {
					System.out.println(itemSelected + " has been added to your cart at $" + groceries.get(itemSelected));
					cartCount++;
					foodCart.add(itemSelected);
					priceCart.add(groceries.get(itemSelected));
				}
				//Informs user if selected item is unavailable, goes back to top of loop to pick different item
				else {
					System.out.println("Sorry, we don't carry the requested item " + itemSelected + ". Please try again.\n");
					continue;
				}
				
				//Prompts user to continue adding items to this order
				System.out.println("Would you like to add more groceries to your cart? (y/n)");
				userResponse = scnr.nextLine().trim().toLowerCase();
				
				
				if (userResponse.startsWith("y")) {
					keepShopping = true;
				}
				
				//If the order is complete, ends current order
				else {
					keepShopping = false;
					System.out.println("You have " + cartCount + " items in your cart. Thanks for your order!");
				}
			}
			while (keepShopping);
			
			System.out.println();	
			
			//Calls showOrder() to print order summary
			showOrder(foodCart, priceCart);
			System.out.println();
			
			//Calls method to get total price, formats it and prints it
			Double totalPrice = cartTotal(priceCart);
			System.out.print("Your total is: $");
			System.out.printf("%.2f", totalPrice);
			System.out.println();
			
			//Calls method which will print average price per item
			cartAverage(priceCart);
			System.out.println();
			
			//Calls method which will print the highest priced item
			expensiveItem(foodCart, priceCart);
			System.out.println();
			
			//Calls method which will print the lowest priced item
			cheapItem(foodCart, priceCart);	
			System.out.println();
			
			//Prompts user to place another order or exit program
			System.out.println("Would you like to place another order? (y/n)");
			userResponse2 = scnr.nextLine();
			
			//If the user wishes to place another order, cart is emptied
			if (userResponse2.startsWith("y")) {
				newOrder = true;
				cartCount = 0;
				foodCart.clear();
				priceCart.clear();
			}
			else {
				newOrder = false;
				System.out.println("Thank you for shopping at The Sun Room SuperMarket! Please come again soon :)");
			}
		}
		while (newOrder);
		
		scnr.close();
		
	}
	
	private static void stockGroceries(Map<String, Double> groceries) {
		groceries.put("PEANUT BUTTER", 3.49);
		groceries.put("OREO ICE-CREAM", 2.67);
		groceries.put("BREAD", 2.79);
		groceries.put("CHEESE", 2.49);
		groceries.put("ORANGES", 3.89);
		groceries.put("CHERRIES", 4.79);
		groceries.put("SPINACH LEAVES", 2.79);
		groceries.put("SALMON", 12.39);
	}
	
	private static void showGroceries(Map<String, Double> groceries) {
		System.out.printf("%-25s %-25s", "Item", "Price");
		System.out.println();
		System.out.printf("%-50s", "=====================================");
		System.out.println();
		
		for (Map.Entry<String, Double> entry : groceries.entrySet()) {
			System.out.printf("%-25s %-25s", entry.getKey(), "$" + entry.getValue());
			System.out.println();
		}
	}
	private static void showOrder(ArrayList<String> foodCart, ArrayList<Double> priceCart) {
		System.out.println("Here is your order summary:\n");
		
		System.out.printf("%-25s %-25s", "Items Ordered", "Price");
		System.out.println();
		System.out.printf("%-50s", "=====================================");
		System.out.println();
		
		int priceCount = 0;
		for (String element : foodCart) {
			System.out.printf("%-25s %-25s", element, priceCart.get(priceCount));
			System.out.println();
			priceCount++;
		}
	}
	
 	private static Double cartTotal(ArrayList<Double> priceCart) {
		Double total = 0.0;
		for (Double price : priceCart) {
			total += price;
		}
		
		return total;
	}
	
	private static void cartAverage(ArrayList<Double> priceCart) {
		Double average = cartTotal(priceCart) / priceCart.size();
		System.out.print("Average price per item in order: $");
		System.out.printf("%.2f", average);
		System.out.println();
	}

	private static void expensiveItem(ArrayList<String> foodCart, ArrayList<Double> priceCart) {
		Double max = 0.0;
		
		for (Double price : priceCart) {
			if (price > max) {
				max = price;
			}
		}
		int index = priceCart.indexOf(max);
		
		System.out.println("Highest priced item in your cart: " + foodCart.get(index));
		
	}

	private static void cheapItem(ArrayList<String> foodCart, ArrayList<Double> priceCart) {
		Double min = -1.0;
		
		for (Double price : priceCart) {
			if (min < 0) {
				min = price;
			}
			
			else if (price < min) {
				min = price;
			}
		}
		int index = priceCart.indexOf(min);
				
		System.out.println("Lowest priced item in your cart: " + foodCart.get(index));
		
	}
}
