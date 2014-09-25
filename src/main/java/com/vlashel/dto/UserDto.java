package com.vlashel.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author Vladyslav Shelest
 * @version 1.0
 * @since 24.09.2014
 */
public class UserDto {

    @Length(min = 2, max = 8)
    @NotEmpty
    private String name;

    @Length(min = 8)
    @NotEmpty
    private String password;
    @Min(5)
    private Integer number;

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
