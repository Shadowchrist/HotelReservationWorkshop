package com.workshop.hotelreservation;

import java.util.HashMap;
import java.util.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

public class HotelReservation {
	
	public static HashMap<String, HotelConstants> system=new HashMap<String, HotelConstants>();
	
	@SuppressWarnings("resource")
	public static void enterNewHotelDetails()
	{
		int flag=1;
		while(flag==1) {
			System.out.println("Enter name of the new hotel");
			String name=(new Scanner(System.in)).nextLine();
			System.out.println("Enter weekday rates of the new hotel");
			int weekdayRates=(new Scanner(System.in)).nextInt();
			system.put(name.toUpperCase(), new HotelConstants(name,weekdayRates));
			System.out.println("Want to add more hotels? (Y/N)");
			char choice=(new Scanner(System.in)).next().charAt(0); 
			if(choice=='N' || choice=='n')
			{
				System.out.println("Hotel entries done.\n");
				flag=0;
				break;
			}
			else
				continue;
		}
	}
	
	public static int getNumberOfDays(String date1, String date2)
	{
		int numberOfDays;
		DateTimeFormatter myFormat=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate entryDate=LocalDate.parse(date1, myFormat);
		LocalDate exitDate=LocalDate.parse(date2, myFormat);
		numberOfDays=(int) ChronoUnit.DAYS.between(entryDate, exitDate);
		return numberOfDays;
	}
	
	@SuppressWarnings("resource")
	public static void findCheapestHotel()
	{
		String cheapestHotel = "";
		System.out.println("Enter Arrival Date: ");
		String arrivalDate=(new Scanner(System.in)).nextLine();
		System.out.println("Enter Departure Date: ");
		String departureDate=(new Scanner(System.in)).nextLine();
		int days=getNumberOfDays(arrivalDate,departureDate);
		int minRate=Integer.MAX_VALUE;
		for(String hotels: system.keySet())
		{
			if(system.get(hotels).weekdayRates*days<minRate)
			{
				minRate=system.get(hotels).weekdayRates*days;
				cheapestHotel=hotels;
			}
		}
		System.out.println("Cheapest hotel details: \n");
		system.get(cheapestHotel).displayHotelDetails();
		System.out.println("Total cost: "+ minRate);
	}
	
	public static void main(String args[])
	{
		System.out.println("Welcome to Hotel Reservation System.");
		enterNewHotelDetails();
		findCheapestHotel();
	}
}
