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

    private final Map<String, BlockingQueue<Runnable>> bidTypeMap = new HashMap<>();

    @Override
    public void process(ProcessType processType) {
        if (ProcessType.READ.equals(processType)) {
            logger.info("Read queues started");
            for (BlockingQueue<Runnable> queue : bidTypeMap.values()) {
                while (!queue.isEmpty()) {
                    Runnable poll = queue.poll();
                    if (poll != null) {
                        CompletableFuture.runAsync(poll);
                    }
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
                    BlockingQueue<Runnable> queue = bidTypeMap.get(bidType);
                    queue.offer(bidRunner);
                } else {
                    BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
                    queue.offer(bidRunner);
                    bidTypeMap.put(bidType, queue);
                }
                logger.info("Added bid {} into queue", bid.toString());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}
