package com.edp.edp_proj;

public class Entry {

    private final String dateE;
    private final String fromE;
    private final String toE;
    private final String amountE;
    private final String rateE;
    private final String basicRateE;


    public Entry(String dateE, String fromE, String toE, String amountE, String rateE, String basicRateE) {
        this.dateE = dateE;
        this.fromE = fromE;
        this.toE = toE;
        this.amountE = amountE;
        this.rateE = rateE;
        this.basicRateE = basicRateE;
    }

    public String getDateE() {
        return dateE;
    }

    public String getFromE() {
        return fromE;
    }

    public String getToE() {
        return toE;
    }

    public String getAmountE() {
        return amountE;
    }

    public String getBasicRateE() {
        return basicRateE;
    }

    public String getRateE() {
        return rateE;
    }
}
