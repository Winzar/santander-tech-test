package com.example.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Component
public class CSVReader {

    private Map<String, Price> priceMap= new HashMap<>();
    /*
    A hash map was used instead of a standard array list so that only the latest price for a given exchange rate is
    stored
     */

    private Scanner scanner;
    /*
    To simulate a market feed a scanner was used on the locally stored CSV file. In the event of an interface being
    used the code would be restructured accordingly
     */


    @PostConstruct
    private void loadData() {
        try {
            scanner = new Scanner(new File("src/main/java/com/example/demo/Data.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (currentLine.equals("…") || currentLine.equals("")) {
            // CSV file makes use of ellipsis which needs to be filtered for
            } else {
                Price newPrice = getPriceFromLine(currentLine);
                // reads in the data and cleans it, while also validating the data
                if (newPrice!= null) {
                    this.priceMap.put(newPrice.getInstrumentName(), newPrice);
                }
            }

        }

    }

    private Price getPriceFromLine(String line) {
        String[] split = line.split(",");
        int id = Integer.parseInt(split[0]);
        String instrumentName = split[1].trim();
        double bid = Double.parseDouble(split[2]);
        double ask = Double.parseDouble(split[3]);
        String timestamp = split[4].trim();

        // Trim is used due to remove the initial space before the values

        // Price validation is called to ensure that the bidding price is less than the asking price
        PriceValidation priceValidation = new PriceValidation();
        try {
            priceValidation.bidAskComparison(bid, ask);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
            return null;
        }

        // Applies commission to the bid and ask price
        CommissionCalculator commissionCalculator = new CommissionCalculator();
        double bidWithCommission = commissionCalculator.addCommissionBid(bid);
        double askWithCommission = commissionCalculator.addCommissionAsk(ask);

        Price price = new Price(id, instrumentName, bidWithCommission, askWithCommission, timestamp);
        return price;
    }

    // Used in priceController to obtain lines of the Price Object by their instrument name
    public Price getPriceByInstrumentName(String instrumentName){
        return this.priceMap.get(instrumentName);
    }


    // initially used to test the CSVReader locally
//    public static void main(String[] args) {
//        CSVReader csvReader = new CSVReader();
//        csvReader.loadData();
//        System.out.println(csvReader.priceMap);
//    }

}
