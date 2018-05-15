package com.auction.service;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.auction.builder.AuctionCreator;
import com.auction.domain.Bid;
import com.auction.domain.Auction;
import com.auction.domain.User;
import com.auction.service.Evaluator;

public class EvaluatorTest {

	private Evaluator auctioneer;
	private User joao;
	private User maria;
	private User jose;
	private Auction auction;

	@Before
	public void createEvaluator() {

		this.joao = new User("Joao");
		this.maria = new User("Maria");
		this.jose = new User("Jose");

		this.auctioneer = new Evaluator();

	}

	@Test
	public void shallUnderstandBidsAscendingOrder() {

		auction = new AuctionCreator().parameterAuction("Auction Test").bid(maria, 200).bid(jose, 400).build();

		auctioneer.evaluate(auction);

		assertEquals(400, auctioneer.getBiggerValue(), 0.001);
		assertEquals(200, auctioneer.getSmallerValue(), 0.001);
		assertEquals(300, auctioneer.getAverage(), 0.001);

	}

	@Test
	public void shallUnderstandAuctionWithOnlyOneBid() {

		auction = new AuctionCreator().parameterAuction("Auction Test").bid(maria, 50).build();

		auctioneer.evaluate(auction);

		assertEquals(50, auctioneer.getBiggerValue(), 0.001);
		assertEquals(50, auctioneer.getSmallerValue(), 0.001);

	}

	@Test
	public void shallFindThreeBiggerBid() {

		auction = new AuctionCreator().parameterAuction("Auction Test").bid(joao, 1000).bid(maria, 300)
				.bid(jose, 400).bid(maria, 3000).build();

		auctioneer.evaluate(auction);

		List<Bid> bigger = auctioneer.getThreeBigger();
		
		assertEquals(3, bigger.size());
		assertEquals(3000, bigger.get(0).getValue(), 0.001);
		assertEquals(1000, bigger.get(1).getValue(), 0.001);
		assertEquals(400, bigger.get(2).getValue(), 0.001);

	}

	@Test
	public void shallFindBiggerAndSmallerInRandomBids() {

				auction = new AuctionCreator().parameterAuction("Auction Test").bid(joao, 200).bid(maria, 450)
				.bid(jose, 120).bid(maria, 700).bid(maria, 630).bid(maria, 230).build();

		auctioneer.evaluate(auction);

		assertEquals(700, auctioneer.getBiggerValue(), 0.001);
		assertEquals(120, auctioneer.getSmallerValue(), 0.001);

	}

	@Test
	public void shallFindBiggerAndSmallerInDescendingBids() {

		auction = new AuctionCreator().parameterAuction("Auction Test").bid(joao, 400).bid(maria, 300)
				.bid(jose, 200).bid(maria, 100).build();

		auctioneer.evaluate(auction);

		assertEquals(400, auctioneer.getBiggerValue(), 0.001);
		assertEquals(100, auctioneer.getSmallerValue(), 0.001);

	}

	@Test
	public void shallFindBiggerSmallerInBoundaryCases() {

		auction = new AuctionCreator().parameterAuction("Auction Test").bid(joao, 400.01).bid(maria, 400)
				.bid(jose, 101).bid(maria, 101.1).build();

		auctioneer.evaluate(auction);

		assertEquals(400.01, auctioneer.getBiggerValue(), 0.001);
		//assertEquals(100.01, auctioneer.getSmallerValue(), 0.001);
	

	}


	@Test(expected = RuntimeException.class)
	public void shallReturnErrorWhenNoBids() {

		auction = new AuctionCreator().build();

		auctioneer.evaluate(auction);

	}

	@After
	public void finaliza() {
		System.out.println("end");
	}

	@AfterClass
	public static void testandoAfterClass() {
		System.out.println("after class");
	}

}
