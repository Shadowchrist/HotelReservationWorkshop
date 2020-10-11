package com.workshop.hotelreservation;

public class HotelConstants {
	public String hotelName;
	public int weekdayRates;
	public int weekendRates;
	public int rating;
	public int specialWeekdayRates;
	public int specialWeekendRates;
	
	public HotelConstants(String name, int weekdayRates, int weekendRates, int rating, int specialWeekdayRates, int specialWeekendRates)
	{
		this.hotelName=name;
		this.weekdayRates=weekdayRates;
		this.weekendRates=weekendRates;
		this.rating=rating;
		this.specialWeekdayRates=specialWeekdayRates;
		this.specialWeekendRates=specialWeekendRates;
	}
	
	public void displayHotelDetails()
	{
		System.out.println("Hotel Name: "+ hotelName);
		System.out.println("Weekday Rates: "+ weekdayRates);
		System.out.println("Weekend Rates: "+ weekendRates);
		System.out.println("User Rating: "+ rating);
		System.out.println("Weekday Rates as part of the Rewards program: "+ specialWeekdayRates);
		System.out.println("Weekend Rates as part of the Rewards program: "+ specialWeekendRates);
	}
}
