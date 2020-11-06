package com.test.bids.service;

import com.test.bids.domain.Bid;
import com.test.bids.notification.BidProcessingObservable;
import com.test.bids.notification.ProcessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;

public class BidProcessor implements BidProcessingObservable {

	private static final Logger logger = LoggerFactory.getLogger(BidProcessor.class);

	private final Map<String, BlockingQueue<BidRunner>> bidTypeMap = new HashMap<>();
	private final Map<String, CompletableFuture<Void>> processingQueueState = new HashMap<>();

	@Override
	public void process(ProcessType processType) {

		if (ProcessType.READ.equals(processType)) {
			for (Map.Entry<String, BlockingQueue<BidRunner>> entry : bidTypeMap.entrySet()) {
				String queueType = entry.getKey();
				CompletableFuture<Void> processingCompletableFuture = processingQueueState.get(queueType);
				if (processingCompletableFuture == null || processingCompletableFuture.isDone()) {
					BlockingQueue<BidRunner> queue = entry.getValue();
					CompletableFuture<Void> completableFuture = processQueue(queue, queueType);
					processingQueueState.put(queueType, completableFuture);
				}
			}
		}

	}

	@Override
	public void addBid(Bid bid) {

		if (bid != null) {
			try {
				String bidType = bid.getName();
				BidRunner bidRunner = new BidRunner(bid);
				if (bidType != null && bidTypeMap.containsKey(bidType)) {
					BlockingQueue<BidRunner> queue = bidTypeMap.get(bidType);
					queue.offer(bidRunner);
				} else {
					BlockingQueue<BidRunner> queue = new LinkedBlockingQueue<>();
					queue.offer(bidRunner);
					bidTypeMap.put(bidType, queue);
				}
				logger.info("Added bid {} into queue", bid.toString());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}

	private CompletableFuture<Void> processQueue(BlockingQueue<BidRunner> queue, String queueType) {

		return CompletableFuture.runAsync(() -> {
			logger.info("Read queues type {} started", queueType);
			while (!queue.isEmpty()) {
				BidRunner poll = queue.poll();
				poll.run();
			}
			logger.info("Read queues type {} finished", queueType);
		});
	}
}
