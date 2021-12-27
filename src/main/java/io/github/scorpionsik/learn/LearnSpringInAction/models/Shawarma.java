package io.github.scorpionsik.learn.LearnSpringInAction.models;

import io.github.scorpionsik.learn.LearnSpringInAction.components.IngredientRef;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Shawarma {

    private Long id;
    private Date createAt = new Date();

    @NotNull
    @Size (min=5, message="Name must be at 5 characters long")
    private String name;

    @NotNull
    @Size(min=1, message = "You must choose at least 1 ingredient")
    private List<IngredientRef> ingredients;
}
