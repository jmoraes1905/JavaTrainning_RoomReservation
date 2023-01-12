package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		
		System.out.println("Room number");
		int number = sc.nextInt();
		System.out.println("Check-in date: dd/MM/yyyy");
		Date checkIn = sdf.parse(sc.next());
		System.out.println("Check-out date: dd/MM/yyyy");
		Date checkOut = sdf.parse(sc.next());
		
		//Update dates must be future dates
		Date now = new Date();
		if(checkIn.before(now)||checkOut.before(now)) {
			System.out.println("Reservation error. Enter future dates");
		}
		
		else if(!checkOut.after(checkIn)) {
			System.out.println("Data inconsistent");
		}
		
		else {
			Reservation reservation = new Reservation(number,checkIn,checkOut);
			System.out.println("Reservation:" + reservation.toString());
			System.out.println();
			System.out.println("Enter data to update the reservation: ");
			checkIn = sdf.parse(sc.next());
			System.out.println("Check-out date: dd/MM/yyyy");
			checkOut = sdf.parse(sc.next());
			
			
			reservation.updateDates(checkIn, checkOut);
			System.out.println("Reservation:" + reservation.toString());
			
		}
		
		
		
		
		sc.close();
		

	}

}
