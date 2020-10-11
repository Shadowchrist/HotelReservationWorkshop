package com.workshop.hotelreservation;

public class HotelConstants {
	public String hotelName;
	public int weekdayRates;
	public int weekendRates;
	public int specialWeekdayRates;
	public int specialWeekendRates;
	public int rating;
	
	public HotelConstants(String name, int weekdayRates)
	{
		this.hotelName=name;
		this.weekdayRates=weekdayRates;
	}
	
	public void displayHotelDetails()
	{
		System.out.println("Hotel Name: "+ hotelName);
		System.out.println("Weekday Rates: "+ weekdayRates);
	}
}
