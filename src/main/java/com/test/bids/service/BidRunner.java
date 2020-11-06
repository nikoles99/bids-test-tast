package com.test.bids.service;

import com.test.bids.domain.Bid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BidRunner {

	private static final Logger logger = LoggerFactory.getLogger(BidRunner.class);

	private final Bid bid;

	private final int IMITATION_PROCESSING_TIME_MS = 5000;

	public BidRunner(Bid bid) {

		this.bid = bid;
	}

	// imitation bid processing
	public void run() {

		try {
			logger.info("Type {}: Processing for bid {} started", bid.getName(), bid.toString());
			Thread.sleep(IMITATION_PROCESSING_TIME_MS);
			logger.info("Type {}: Processing for bid {} complete", bid.getName(), bid.toString());
		} catch (InterruptedException e) {
			logger.error("Something happened during precessing the bid {}", e.getMessage());
		}

	}
}
