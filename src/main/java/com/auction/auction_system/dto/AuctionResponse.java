package com.auction.auction_system.dto;

import com.auction.auction_system.entity.AuctionStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class AuctionResponse {
    private Long id;
    private String productName;
    private String description;
    private BigDecimal reservePrice;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private AuctionStatus status;
    private BigDecimal currentHighestBid;

    public AuctionResponse(Long id, String productName, String description, BigDecimal reservePrice,
                           OffsetDateTime startTime, OffsetDateTime endTime, AuctionStatus status,
                           BigDecimal currentHighestBid) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.reservePrice = reservePrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.currentHighestBid = currentHighestBid;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getReservePrice() {
        return reservePrice;
    }

    public OffsetDateTime getStartTime() {
        return startTime;
    }

    public OffsetDateTime getEndTime() {
        return endTime;
    }

    public AuctionStatus getStatus() {
        return status;
    }

    public BigDecimal getCurrentHighestBid() {
        return currentHighestBid;
    }
}

