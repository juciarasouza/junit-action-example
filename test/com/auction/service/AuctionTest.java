package com.auction.service;

import org.junit.Test;
import com.auction.domain.Bid;
import com.auction.domain.Auction;
import com.auction.domain.User;

import static org.junit.Assert.assertEquals;

public class AuctionTest {
	
	@Test	
	public void shallReceiveBid(){
		Auction auction = new Auction("Notebook");		
		assertEquals(0,auction.getBids().size());
		
		auction.tender(new Bid(new User("Marcos"),200));
		assertEquals(1,auction.getBids().size());
		assertEquals(200, auction.getBids().get(0).getValue(),0.001);
		assertEquals("Notebook", auction.getDescription());
	}
	
	@Test	
	public void shallReceiveSeveralBids(){
		Auction auction = new Auction("Notebook");		
			
		auction.tender(new Bid(new User("Marcos"),200));
		auction.tender(new Bid(new User("Vivian"),300));
		assertEquals(2,auction.getBids().size());
		assertEquals(200, auction.getBids().get(0).getValue(),0.001);
		assertEquals(300, auction.getBids().get(1).getValue(),0.001);
		assertEquals("Notebook", auction.getDescription());
	}
	
	@Test
	public void shallNotAllowSequentialBidsOfTheSameUser(){
		Auction auction = new Auction ("Notebook");
		User maria = new User("Maria");
		User marcos = new User("Marcos");
		
		auction.tender(new Bid (maria, 100));
		auction.tender(new Bid (marcos, 110));
		auction.tender(new Bid (marcos, 120));
		
		assertEquals(2, auction.getBids().size());
		assertEquals(110, auction.getBids().get(1).getValue(), 0.001);
	}
	
	@Test
	public void shallNotAllowMoreThanFiveBidsOfTheSameUser() {
		Auction auction = new Auction ("Notebook");
		User maria = new User("Maria");
		User marcos = new User("Marcos");
		
		auction.tender(new Bid(maria, 100));
		auction.tender(new Bid (marcos, 110));
		
		auction.tender(new Bid (maria, 130));
		auction.tender(new Bid (marcos, 140));
		
		auction.tender(new Bid (maria, 150));
		auction.tender(new Bid (marcos, 160));
		
		auction.tender(new Bid (maria, 170));
		auction.tender(new Bid (marcos, 180));
		
		auction.tender(new Bid (maria, 190));
		auction.tender(new Bid (marcos, 200));
		
		auction.tender(new Bid (maria, 210));
		
		assertEquals(10, auction.getBids().size());
		assertEquals(180, auction.getBids().get(7).getValue(), 0.001);
		
	}

}
