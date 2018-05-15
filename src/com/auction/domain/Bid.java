package com.auction.domain;

public class Bid {

	private User user;
	private double value;
	
	public Bid(User user, double value) {
		this.user = user;
		this.value = value;
	}

	public User getUser() {
		return user;
	}

	public double getValue() {
		return value;
	}
	
	
	
}
