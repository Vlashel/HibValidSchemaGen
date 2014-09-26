package com.vlashel.dto;



import com.vlashel.util.JacksonJsonExtensions.annotations.SchemaDescription;
import com.vlashel.util.JacksonJsonExtensions.annotations.SchemaTitle;
import com.vlashel.util.JacksonJsonExtensions.annotations.SchemaVersion;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author Vladyslav Shelest
 * @version 1.0
 * @since 24.09.2014
 */
@SchemaTitle("this is the top title")
@SchemaDescription("this is the top description")
@SchemaVersion("http://json-schema.org/draft-03/schema#")
public class UserDto {

    @Size(min = 2, max = 8)
    @NotNull
    @SchemaTitle("this is the name")
    @SchemaDescription("this is the description")
    private String name;
    @Size(min = 8)
    @NotNull
    private String password;
    @Max(5)
    private Integer number;


    @Email
    private String email;


    private List<Address> addresses;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @NotNull
    private List<Integer> list;

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
