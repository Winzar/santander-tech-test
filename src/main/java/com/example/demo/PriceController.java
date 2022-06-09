package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/*
Used spring to set up a local server to access data/ simulate endpoint
Example of http request to access data using Postman http://localhost:8080/exchange/eur/usd
*/

@RestController
public class PriceController {

    @Autowired
    private CSVReader csvReader;

    @GetMapping("/exchange/{fromExchangeCurrency}/{toExchangeCurrency}")
    public ResponseEntity<Price> findPriceByInstrumentName(@PathVariable String fromExchangeCurrency, @PathVariable String toExchangeCurrency) {

        //toUpperCase used to prevent errors with case sensitivity
        Price result = this.csvReader.getPriceByInstrumentName(fromExchangeCurrency.toUpperCase() + "/" + toExchangeCurrency.toUpperCase());

        if(result!= null){
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status( HttpStatus.NOT_FOUND).body(null);
    }

}
