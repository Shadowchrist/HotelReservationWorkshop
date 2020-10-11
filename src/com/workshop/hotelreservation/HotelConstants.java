package com.workshop.hotelreservation;

public class HotelConstants {
	public String hotelName;
	public int weekdayRates;
	public int weekendRates;
	public int specialWeekdayRates;
	public int specialWeekendRates;
	public int rating;
	
	public HotelConstants(String name, int weekdayRates, int weekendRates, int rating)
	{
		this.hotelName=name;
		this.weekdayRates=weekdayRates;
		this.weekendRates=weekendRates;
		this.rating=rating;
	}
	
	public void displayHotelDetails()
	{
		System.out.println("Hotel Name: "+ hotelName);
		System.out.println("Weekday Rates: "+ weekdayRates);
		System.out.println("Weekend Rates: "+ weekendRates);
		System.out.println("User Rating: "+ rating);
	}
}
