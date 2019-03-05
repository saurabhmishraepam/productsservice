package com.epam.productsreview.exceptionHadlers;

import java.time.LocalTime;

/**
 * Created by saurabh on 26/2/19.
 */
public class ErrorDetails {
    private LocalTime timestamp;
    private String message;
    private String details;

    public ErrorDetails() {
    }

    public ErrorDetails(LocalTime time, String message, String details) {

        this.timestamp = time;
        this.message = message;
        this.details = details;

    }


}
