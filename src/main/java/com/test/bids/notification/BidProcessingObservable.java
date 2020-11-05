package com.test.bids.notification;

import com.test.bids.domain.Bid;

public interface BidProcessingObservable {

    //Event-based processing queues
    void process(ProcessType processType);

    //Event-based adding bid to appropriate queue
    void addBid(Bid bid);
}
