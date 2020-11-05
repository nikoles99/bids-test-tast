package com.test.bids.service;

import com.test.bids.domain.Bid;

import java.util.Collection;

public interface BidTypeReader<T> {

	Collection<Bid> obtain(T bidType);

}
