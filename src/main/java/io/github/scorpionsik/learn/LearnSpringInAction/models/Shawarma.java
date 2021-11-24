package io.github.scorpionsik.learn.LearnSpringInAction.models;

import lombok.Data;

import java.util.List;

@Data
public class Shawarma {
    private String name;
    private List<Ingredient> ingredients;
}
