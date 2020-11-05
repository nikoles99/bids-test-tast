package com.test.bids.service;

import com.test.bids.domain.Bid;

import java.util.Collection;

public class BidTypeReaderImpl<T> implements BidTypeReader<T> {

	private BidFileReader reader = new BidFileReader();

	@Override
	public Collection<Bid> obtain(T bidType) {

		return reader.read();
	}
}
