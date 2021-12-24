package io.github.scorpionsik.learn.LearnSpringInAction.models;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ShawarmaOrder {
    private Long id;
    private Date placedAt;

    //delivery fields
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    private String deliveryState;

    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    //payment fields
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])[\\/](\\d{2})$",
            message = "Must be formatted MM/YY")
    private String ccExpiration;

    //@Pattern(regexp = "^\\d{3}$", message = "Invalid CVV")
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private List<Shawarma> shawarmas = new ArrayList<>();

    public void addShawarma(Shawarma shawarma){
        this.shawarmas.add(shawarma);
    }
}
