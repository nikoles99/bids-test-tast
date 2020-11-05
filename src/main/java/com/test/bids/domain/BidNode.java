package com.test.bids.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class BidNode {

	@JsonIgnoreProperties
	private Bid bid;

	public BidNode() {

	}

	public Bid getBid() {

		return bid;
	}

	public void setBid(Bid bid) {

		this.bid = bid;
	}
}
