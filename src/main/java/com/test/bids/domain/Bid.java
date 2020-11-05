package com.test.bids.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bid {

	private String id;

	@JsonProperty("ts")
	private String timestamp;

	@JsonProperty("ty")
	private String name;

	@JsonProperty("pl")
	private String payload;

	public Bid() {

	}

	public Bid(String id, String timestamp, String name, String payload) {

		this.id = id;
		this.timestamp = timestamp;
		this.name = name;
		this.payload = payload;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getTimestamp() {

		return timestamp;
	}

	public void setTimestamp(String timestamp) {

		this.timestamp = timestamp;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getPayload() {

		return payload;
	}

	public void setPayload(String payload) {

		this.payload = payload;
	}
}
