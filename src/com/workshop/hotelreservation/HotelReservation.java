package com.workshop.hotelreservation;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

public class HotelReservation {

	public static HashMap<String, HotelConstants> system = new HashMap<String, HotelConstants>();

	@SuppressWarnings("resource")
	public static void enterNewHotelDetails() {
		int flag = 1;
		while (flag == 1) {
			System.out.println("Enter name of the hotel");
			String name = (new Scanner(System.in)).nextLine();
			System.out.println("Enter weekday rates of the hotel");
			int weekdayRates = (new Scanner(System.in)).nextInt();
			System.out.println("Enter weekend rates of the hotel");
			int weekendRates = (new Scanner(System.in)).nextInt();
			int rating=0;
			while (!checkRating(rating)) {
				System.out.println("Enter rating of the hotel (1-5)");
				rating = (new Scanner(System.in)).nextInt();
				if(!checkRating(rating))
					System.out.println("Invalid rating!");
			}
			System.out.println("Enter weekday rates of the hotel as part of the rewards program");
			int specialWeekdaysRates = (new Scanner(System.in)).nextInt();
			System.out.println("Enter weekend rates of the hotel as part of the rewards program");
			int specialWeekendRates = (new Scanner(System.in)).nextInt();
			system.put(name.toUpperCase(), new HotelConstants(name, weekdayRates, weekendRates, rating,
					specialWeekdaysRates, specialWeekendRates));
			System.out.println("Want to add more hotels? (Y/N)");
			char choice = (new Scanner(System.in)).next().charAt(0);
			if (choice == 'N' || choice == 'n') {
				System.out.println("Hotel entries done.\n");
				flag = 0;
				break;
			} else
				continue;
		}
	}

	public static boolean doesDateExists(String date) {
		Pattern p = Pattern.compile(HotelConstants.datePattern);
		Matcher m = p.matcher(date);
		return m.matches();
	}
	
	public static boolean checkRating(int rating) {
		if(rating<1 || rating>5)
			return false;
		return true;
	}

	public static boolean checkDateCorrectness(String date1, String date2) {
		DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate entryDate = LocalDate.parse(date1, myFormat);
		LocalDate exitDate = LocalDate.parse(date2, myFormat);
		if (exitDate.isBefore(entryDate))
			return false;
		return true;

	}

	public static int getNumberOfDays(String date1, String date2) {
		int numberOfDays;
		DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate entryDate = LocalDate.parse(date1, myFormat);
		LocalDate exitDate = LocalDate.parse(date2, myFormat);
		numberOfDays = (int) ChronoUnit.DAYS.between(entryDate, exitDate);
		return numberOfDays + 1;
	}

	public static int getNumberOfWeekends(String date1, String date2) {
		DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate entryDate = LocalDate.parse(date1, myFormat);
		LocalDate exitDate = LocalDate.parse(date2, myFormat);
		int weekends = (int) entryDate.datesUntil(exitDate)
				.filter((x) -> x.getDayOfWeek().getValue() == 6 || x.getDayOfWeek().getValue() == 7).count();
		if (exitDate.getDayOfWeek().getValue() == 6 || exitDate.getDayOfWeek().getValue() == 7)
			return weekends + 1;
		else
			return weekends;
	}

	public static int[] divisionOfDays(String arrivalDate, String departureDate) {
		int[] array = new int[3];
		array[2] = getNumberOfDays(arrivalDate, departureDate);
		array[1] = getNumberOfWeekends(arrivalDate, departureDate);
		array[0] = array[2] - array[1];
		return array;
	}

	public static void findCheapestHotel(String arrivalDate, String departureDate, char selection) {
		int flag = 0;
		while (flag == 0) {
			String cheapestHotel = "";
			if (checkDateCorrectness(arrivalDate, departureDate)) {
				flag = 1;
				int[] days = divisionOfDays(arrivalDate, departureDate);
				int minRate = Integer.MAX_VALUE;
				for (String currentHotel : system.keySet()) {
					int temp;
					if (selection == 'Y' || selection == 'y') {
						temp = system.get(currentHotel).specialWeekdayRates * days[0]
								+ system.get(currentHotel).specialWeekendRates * days[1];
					} else {
						temp = system.get(currentHotel).weekdayRates * days[0]
								+ system.get(currentHotel).weekendRates * days[1];
					}
					if (temp < minRate) {
						minRate = temp;
						cheapestHotel = currentHotel;
					} else if (temp == minRate) {
						String check = (system.get(currentHotel).rating > system.get(cheapestHotel).rating)
								? currentHotel
								: cheapestHotel;
						cheapestHotel = check;
					}
				}
				System.out.println("Cheapest hotel details: \n");
				system.get(cheapestHotel).displayHotelDetails();
				System.out.println("Staying for " + days[2] + " days including " + days[1] + " weekends.");
				System.out.println("Total cost: " + minRate);
			} else if (!checkDateCorrectness(arrivalDate, departureDate))
				System.out.println("Departure date cannot be before the arrival date!!!");
			else if (!doesDateExists(arrivalDate) || !doesDateExists(departureDate))
				System.out.println("Entered date cannot exist!!!");
		}
	}

	public static void findBestRatedHotel(String arrivalDate, String departureDate, char selection) {
		int bestRating = 1;
		String bestRatedHotel = "";
		int[] days = divisionOfDays(arrivalDate, departureDate);
		for (String currentHotel : system.keySet()) {
			if (system.get(currentHotel).rating > bestRating) {
				bestRating = system.get(currentHotel).rating;
				bestRatedHotel = currentHotel;
			}
		}
		int totalCost = system.get(bestRatedHotel).weekdayRates * days[0]
				+ system.get(bestRatedHotel).weekendRates * days[1];
		System.out.println("Best Rated Hotel(s): ");
		system.get(bestRatedHotel).displayHotelDetails();
		System.out.println("Staying for " + days[2] + " days including " + days[1] + " weekends.");
		System.out.println("Total cost: " + totalCost);
	}

	@SuppressWarnings("resource")
	public static void main(String args[]) {
		System.out.println("Welcome to Hotel Reservation System.");
		enterNewHotelDetails();
		System.out.println("THE BOOKING SECTION");
		String arrivalDate="", departureDate="";
		int check=0;
		while (check==0) {
			System.out.println("Enter Arrival Date: (Format: DD/MM/YYYY)");
			arrivalDate = (new Scanner(System.in)).nextLine();
			if(doesDateExists(arrivalDate))
			{
				check=1;
				break;
			}
			else
				System.out.println("Given date cannot be entered!!!");
		}
		check=0;
		
		while (check==0) {
			System.out.println("Enter Departure Date: (Format: DD/MM/YYYY)");
			departureDate = (new Scanner(System.in)).nextLine();
			if(doesDateExists(departureDate))
			{
				check=1;
				break;
			}
			else
				System.out.println("Given date cannot be entered!!!");
		}
		System.out.println("Are you eligible for low charges under the Rewards Program? (Y/N)");
		char selection = (new Scanner(System.in)).next().charAt(0);
		System.out.println("Enter 1 to find the cheapest hotel." + "Enter 2 to find the best rated hotel(s).");
		int choice = (new Scanner(System.in)).nextInt();
		if (choice == 2)
			findBestRatedHotel(arrivalDate, departureDate, selection);
		else
			findCheapestHotel(arrivalDate, departureDate, selection);
	}
}
