package io.github.scorpionsik.learn.LearnSpringInAction;

import io.github.scorpionsik.learn.LearnSpringInAction.models.Ingredient;
import io.github.scorpionsik.learn.LearnSpringInAction.repositiries.IngredientRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.scorpionsik.learn.LearnSpringInAction.models.Ingredient.Type;

@Configuration
public class AppConfig {
    @Bean
    public ApplicationRunner dataLoader(IngredientRepository repo) {
        return args -> {
            repo.save(new Ingredient("FLPT", "Flour Pita", Type.WRAP));
            repo.save(new Ingredient("HOPT", "Hot Chilli Pita", Type.WRAP));
            repo.save(new Ingredient("CHPT", "Cheese Pita", Type.WRAP));
            repo.save(new Ingredient("RSLB", "Roast Lamb", Type.PROTEIN));
            repo.save(new Ingredient("RSBR", "Roast Beef", Type.PROTEIN));
            repo.save(new Ingredient("RSCK", "Roast Chicken", Type.PROTEIN));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            repo.save(new Ingredient("CALE", "Cabbage Leaves", Type.VEGGIES));
            repo.save(new Ingredient("CUCM", "Sliced Cucumbers", Type.VEGGIES));
            repo.save(new Ingredient("POTS", "Baked Diced Potatoes", Type.VEGGIES));
            repo.save(new Ingredient("RUCH", "Russian", Type.CHEESE));
            repo.save(new Ingredient("DUCH", "Dutch", Type.CHEESE));
            repo.save(new Ingredient("SULG", "Sulguni", Type.CHEESE));
            repo.save(new Ingredient("KETC", "Ketchup", Type.SAUCE));
            repo.save(new Ingredient("MASE", "Mayonnaise", Type.SAUCE));
            repo.save(new Ingredient("KESE", "Ketchunaise", Type.SAUCE));
            repo.save(new Ingredient("GARL", "Garlic Sauce", Type.SAUCE));
            repo.save(new Ingredient("AJKA", "Adjika", Type.SAUCE));
        };
    }
}
