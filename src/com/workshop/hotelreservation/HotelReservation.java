package com.workshop.hotelreservation;

import java.util.HashMap;
import java.util.Scanner;

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
			system.put(name, new HotelConstants(name,weekdayRates));
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
		System.out.println("Displaying all hotels in the system:\n");
		for(String str: system.keySet())
			system.get(str).displayHotelDetails();
	}
	
	public static void main(String args[])
	{
		System.out.println("Welcome to Hotel Reservation System.");
		enterNewHotelDetails();
	}
}
