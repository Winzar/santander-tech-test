package com.example.demo;

public class CommissionCalculator {

    public static final double BID_COMMISSION = 0.001;
    public static final double ASK_COMMISSION = 0.001;
    public double addCommissionBid(double value) {
        value = value - (value * BID_COMMISSION);
        return value;
    }

    public double addCommissionAsk(double value) {
        value = value + (value * ASK_COMMISSION);
        return value;
    }

    // Used to test the commission calculator locally
//    public static void main(String[] args) {
//        CommissionCalculator commissionCalculator = new CommissionCalculator();
//    }
}
