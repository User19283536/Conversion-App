package com.edp.edp_proj;

public class ConversionData {
    private String from;
    private String to;
    private String amount;
    private String result;
    private final static ConversionData INSTANCE = new ConversionData();

    private ConversionData() {}

    public String getFrom() {
        return from;
    }

    public void setFrom(String From) {
        this.from = From;
        //System.out.print("test6");
    }

    public String getTo() {
        return to;
    }

    public void setTo(String To) {
        this.to = To;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String Amount) {
        this.amount = Amount;
    }

    public static ConversionData getInstance() {
        return INSTANCE;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /*
    public ConversionData(String From, String To, float Amount){
        this.from = From;
        this.to = To;
        this.amount = Amount;
    }
    */

}