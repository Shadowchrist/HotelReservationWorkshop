package com.workshop.hotelreservation;

public class HotelConstants {
	public String hotelName;
	public int weekdayRates;
	public int weekendRates;
	public int rating;
	public int specialWeekdayRates;
	public int specialWeekendRates;
	public static String datePattern="^(([0-2][0-8])/(0[1-9]|1[0-2])/([2-9]([0-9]{3})))$|^((29|30)/(0[13-9]|1[0-2])/([2-9]([0-9]{3})))$|^(31/(0[13578]|1[0-2])/([2-9]([0-9]{3})))$|^(30/(0[13-9]|1[0-2])/([2-9]([0-9]{3})))$|^(29/02/(([2-9]?(?:0[2468]0|[2468][48]0|[13579][26]0|0[2468][048]))|(([2468][048]|[3579][26])00)))$";
	
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
