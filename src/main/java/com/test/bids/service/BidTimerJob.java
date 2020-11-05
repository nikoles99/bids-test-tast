package com.test.bids.service;

import com.test.bids.domain.Bid;

import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class BidTimerJob extends TimerTask {

	public BidTimerJob() {

		init();
	}

	@Override
	public void run() {

		System.out.println(1);
	}

	public void init() {

		BidFileReader bidFileReader = new BidFileReader();
		List<Bid> bids = bidFileReader.read();
		List<String> availableBidTypes = bids.stream().map(Bid::getName).collect(Collectors.toList());
		availableBidTypes.forEach(bidType -> {

		});
	}

}
