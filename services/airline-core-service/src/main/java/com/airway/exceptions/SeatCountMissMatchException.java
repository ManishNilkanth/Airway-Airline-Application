package com.airway.exceptions;

public class SeatCountMissMatchException extends RuntimeException {
    public SeatCountMissMatchException(String seatCountMismatch) {
        super(seatCountMismatch);
    }
}
