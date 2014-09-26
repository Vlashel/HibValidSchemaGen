package com.vlashel.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by vshel on 9/26/2014.
 */
public class AddressDto {

    @Size(max = 30)
    @NotNull
    private String city;
    @Size(max = 30)
    @NotNull
    private String street;
    @Max(50000)
    @NotNull
    private Long postcode;

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

    public Long getPostcode() {
        return postcode;
    }

    public void setPostcode(Long postcode) {
        this.postcode = postcode;
    }
}
