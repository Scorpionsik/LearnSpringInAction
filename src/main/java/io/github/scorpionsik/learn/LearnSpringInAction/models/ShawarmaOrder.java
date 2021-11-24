package io.github.scorpionsik.learn.LearnSpringInAction.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShawarmaOrder {
    //delivery fields
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    //payment fields
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private List<Shawarma> shawarmas = new ArrayList<>();

    public void addShawarma(Shawarma shawarma){
        this.shawarmas.add(shawarma);
    }
}
