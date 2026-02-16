package com.example.webapp.data.entiry;


public enum Position {
    HOUSEKEEPING("Housekeeping"),
    FRONT_DESK("FrontDesk"),
    SECURITY("Security"),
    CONCIERGE("Concierge");


    private final String name;

    Position(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
