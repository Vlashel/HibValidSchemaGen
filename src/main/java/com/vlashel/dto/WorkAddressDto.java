package com.vlashel.dto;


import com.vlashel.util.JacksonJsonExtensions.annotations.SchemaDescription;
import com.vlashel.util.JacksonJsonExtensions.annotations.SchemaTitle;
import com.vlashel.util.JacksonJsonExtensions.annotations.SchemaVersion;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by vshel on 9/26/2014.
 */
@SchemaTitle("work address title")
@SchemaDescription("work address description")
@SchemaVersion("http://json-schema.org/draft-03/schema#")
public class WorkAddressDto extends Address {

    @Size(max = 30)
    @NotNull
    @SchemaTitle("this is the work city title")
    @SchemaDescription("this is the city description")
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
