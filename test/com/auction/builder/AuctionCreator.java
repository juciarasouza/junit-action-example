package com.auction.builder;

import com.auction.domain.Bid;
import com.auction.domain.Auction;
import com.auction.domain.User;

public class AuctionCreator {

	private Auction auction;

	public AuctionCreator parameterAuction(String description) {
		this.auction = new Auction (description);
		return this;
	}

	public AuctionCreator bid(User user, double value) {
		auction.bid(new Bid(user, value));
		return this;
	}
	
	public Auction build() {
		return auction;
	}


}
