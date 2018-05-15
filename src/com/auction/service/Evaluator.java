package com.auction.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.auction.domain.Bid;
import com.auction.domain.Auction;

public class Evaluator {

	private double biggerValue = Double.NEGATIVE_INFINITY;
	private double smallerValue = Double.POSITIVE_INFINITY;
	private double total = 0;
	private double average = 0;
	private List<Bid> bigger;

	public void evaluate(Auction auction) {
		
		if(auction.getBids().size() == 0)
			throw new RuntimeException("No bids for this auction"); 

		for (Bid bid : auction.getBids()) {

			if (bid.getValue() > biggerValue)
				biggerValue = bid.getValue();
			if (bid.getValue() < smallerValue)
				smallerValue = bid.getValue();

			total += bid.getValue();
		}

		average = total / auction.getBids().size();

		bigger = new ArrayList<Bid>(auction.getBids());
		Collections.sort(bigger, new Comparator<Bid>() {

			public int compare(Bid bid1, Bid bid2) {
				if (bid1.getValue() < bid2.getValue())
					return 1;
				else return -1;
			
			}

		});
		bigger = bigger.subList(0, bigger.size() > 3 ? 3 : bigger.size());

	}

	public double getBiggerValue() {
		return biggerValue;
	}

	public double getSmallerValue() {
		return smallerValue;
	}

	public double getAverage() {
		return average;
	}

	public List<Bid> getThreeBigger() {
		return bigger;
	}
}
