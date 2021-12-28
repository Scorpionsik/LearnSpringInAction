package io.github.scorpionsik.learn.LearnSpringInAction.repositiries;

import io.github.scorpionsik.learn.LearnSpringInAction.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
