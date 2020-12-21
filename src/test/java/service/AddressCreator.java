package service;

import model.Address;

public class AddressCreator {

    public static final String CITY = "МИНСК";
    public static final String STREET = "УМАНСКАЯ УЛ.";
    public static final int HOUSE_NUMBER = 37;

    public static Address withCredentialsFromProperties() {
        return new Address(CITY, STREET, HOUSE_NUMBER);
    }

}
