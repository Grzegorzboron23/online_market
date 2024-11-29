package com.solvd.info;

import com.solvd.exception.InvalidAddressException;

public class AddressInfo {

    private String street;
    private String city;
    private String country;

    public AddressInfo(String street, String city, String country) {
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public AddressInfo() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) throws InvalidAddressException {
        if (street == null || street.length() < 3) {
            throw new InvalidAddressException("Street name must be at least 3 characters long");
        }
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws InvalidAddressException {
        if (city == null || city.length() < 3) {
            throw new InvalidAddressException("City name must be at least 3 characters long");
        }
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws InvalidAddressException {
        if (city == null || city.length() < 3) {
            throw new InvalidAddressException("Country name must be at least 3 characters long");
        }
        this.country = country;
    }
}
