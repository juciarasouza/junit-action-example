package com.auction.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Auction {

	private String description;
	private List<Bid> bids;
	
	public Auction(String description) {
		this.description = description;
		this.bids = new ArrayList<Bid>();
	}
	
	public void tender(Bid bid) {
		if(bids.isEmpty() || canMakeBid(bid.getUser()))
		bids.add(bid);
	}

	private boolean canMakeBid(User user) {
		return !lastBid().getUser().equals(user) && count(user)<5;
	}

	private int count(User user) {
		int total = 0;
		
		for(Bid bidList : bids){
			if(bidList.getUser().equals(user)) total++;
		}
		return total;
	}

	private Bid lastBid() {
		return bids.get(bids.size()-1);
	}

	public String getDescription() {
		return description;
	}

	public List<Bid> getBids() {
		return Collections.unmodifiableList(bids);
	}

	
	
}
