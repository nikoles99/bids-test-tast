package com.test.bids;

import com.test.bids.service.BidTimerJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	private static final int PERIOD_MS = 60000;

	public static void main(String[] args) {

		TimerTask task = new BidTimerJob();
		Timer timer = new Timer();
		timer.schedule(task, PERIOD_MS);
		logger.info("App started");
	}
}
