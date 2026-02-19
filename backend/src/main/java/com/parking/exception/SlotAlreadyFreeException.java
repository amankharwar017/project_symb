package com.parking.exception;

public class SlotAlreadyFreeException extends RuntimeException {
    public SlotAlreadyFreeException(String message) {
        super(message);
    }
}
