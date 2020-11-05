package com.test.bids.service;

import com.test.bids.domain.Bid;
import com.test.bids.notification.BidProcessingObservable;
import com.test.bids.notification.ProcessType;

import java.util.List;
import java.util.TimerTask;

public class BidTimerJob extends TimerTask {

    private final BidProcessingObservable bidProcessingObservable;

    public BidTimerJob(BidProcessingObservable bidProcessingObservable) {
        this.bidProcessingObservable = bidProcessingObservable;
    }

    @Override
    public void run() {
        FileReader fileReader = new FileReader();
        List<Bid> bids = fileReader.read();
        bids.forEach(bidProcessingObservable::addBid);
        bidProcessingObservable.process(ProcessType.READ);
    }
}
