package model.entities;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exception.DomainException;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //We want to instantiate just one sdf for each reservation, so we use an static type
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException { //RuntimeException does not require throws declaration
		//Defensive Programming: treats the exceptions at the start of the methods
		if(!checkOut.after(checkIn)){
			throw new DomainException("Reservation error. Enter future dates");
			}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	
	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime(); // gets both dates in millisecongs
		return TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
	}
	
	/*public void updateDates(Date checkIn, Date checkOut) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}*/
	
	public void updateDates(Date checkIn, Date checkOut) throws DomainException {
		
		//Update dates must be future dates
		Date now = new Date();
		if(checkIn.before(now)||checkOut.before(now)) {
			throw new DomainException("Reservation error. Enter future dates");
			}
				
		if(!checkOut.after(checkIn)){
			throw new DomainException("Reservation error. Enter future dates");
			}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Room " 
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(this.checkIn)
				+ ", check-out: "
				+ sdf.format(this.checkOut)
				+", "
				+ duration()
				+" "
				+"nights.";
	}
	
}
