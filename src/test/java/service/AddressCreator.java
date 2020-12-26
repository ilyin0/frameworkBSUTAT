package service;

import model.Address;

public class AddressCreator {

    public static final String TESTDATA_CITY = "testdata.address.city";
    public static final String TESTDATA_STREET = "testdata.address.street";
    public static final String TESTDATA_HOUSE_NUMBER = "testdata.address.housenumber";
    public static final String TESTDATA_APARTMENT_NUMBER = "testdata.address.apartmentnumber";
    public static final String TESTDATA_FLOOR = "testdata.address.floor";
    public static final String TESTDATA_ENTRANCE = "testdata.address.entrance";

    public static Address withCredentialsFromProperties() {
        return new Address(TestDataReader.getTestData(TESTDATA_CITY),
                TestDataReader.getTestData(TESTDATA_STREET),
                TestDataReader.getTestData(TESTDATA_HOUSE_NUMBER),
                TestDataReader.getTestData(TESTDATA_APARTMENT_NUMBER),
                TestDataReader.getTestData(TESTDATA_FLOOR),
                TestDataReader.getTestData(TESTDATA_ENTRANCE));
    }

}
