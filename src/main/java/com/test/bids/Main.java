package com.test.bids;

import com.test.bids.notification.BidProcessingObservable;
import com.test.bids.service.BidTimerJob;
import com.test.bids.service.BidProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	private static final int PERIOD_MS = 60000;

	public static void main(String[] args) {

        BidProcessingObservable observable = new BidProcessor();
		TimerTask task = new BidTimerJob(observable);
		Timer timer = new Timer();
		timer.schedule(task, PERIOD_MS, PERIOD_MS);
		logger.info("App started");
	}
}
