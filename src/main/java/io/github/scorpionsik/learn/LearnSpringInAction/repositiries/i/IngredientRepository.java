package io.github.scorpionsik.learn.LearnSpringInAction.repositiries.i;

import io.github.scorpionsik.learn.LearnSpringInAction.models.Ingredient;

import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}
