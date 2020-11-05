package com.test.bids.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.bids.domain.Bid;
import com.test.bids.domain.BidNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    private static final Logger logger = LoggerFactory.getLogger(FileReader.class);

    private static final String SOURCE_PATH = "bids.json";

    private final ObjectMapper objectMapper;

    public FileReader() {

        objectMapper = new ObjectMapper();
    }

    public List<Bid> read() {

        try {
            logger.info("Reading file started");
            URL resource = getClass().getClassLoader().getResource(SOURCE_PATH);
            List<BidNode> bidNodes = objectMapper.readValue(resource, new TypeReference<List<BidNode>>() {
            });
            return bidNodes.stream().map(BidNode::getBid).collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Error during read bids: {}", e.getMessage());
        } finally {
            logger.info("Reading file finished");
        }
        return Collections.emptyList();
    }

}
