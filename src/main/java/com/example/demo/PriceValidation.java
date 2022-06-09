package com.example.demo;

public class PriceValidation {

    public String bidAskComparison(double bid, double ask) {
        String askBidStatus = "";

        if (bid < ask) {
            askBidStatus = "Success - bid is less than Ask";
        } else {
            throw new RuntimeException("Error - bid is greater than ask");
        }

        return askBidStatus;

    }

    public static void main(String[] args) {
        PriceValidation priceValidation = new PriceValidation();
        System.out.println(priceValidation.bidAskComparison(2, 1));
    }
}

// put main method in here to test then remove post