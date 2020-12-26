package model;

import java.util.Objects;

public class Address {

    private String city;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String floor;
    private String entrance;

    public Address(String city, String street, String houseNumber, String apartmentNumber, String floor, String entrance) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
        this.floor = floor;
        this.entrance = entrance;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(houseNumber, address.houseNumber) &&
                Objects.equals(apartmentNumber, address.apartmentNumber) &&
                Objects.equals(floor, address.floor) &&
                Objects.equals(entrance, address.entrance);
    }

    public boolean equalsIgnoreCase(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return city.equalsIgnoreCase(address.city) &&
                street.equalsIgnoreCase(address.city) &&
                houseNumber.equalsIgnoreCase(address.houseNumber) &&
                apartmentNumber.equalsIgnoreCase(address.apartmentNumber) &&
                floor.equalsIgnoreCase(address.floor) &&
                entrance.equalsIgnoreCase(address.entrance);
    }


    @Override
    public int hashCode() {
        return Objects.hash(city, street, houseNumber, apartmentNumber, floor, entrance);
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", floor='" + floor + '\'' +
                ", entrance='" + entrance + '\'' +
                '}';
    }
}
