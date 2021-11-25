package io.github.scorpionsik.learn.LearnSpringInAction.components;

import org.springframework.core.convert.converter.Converter;
import io.github.scorpionsik.learn.LearnSpringInAction.models.Ingredient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    public IngredientByIdConverter(){
        ingredientMap.put("FLPT", new Ingredient("FLPT", "Flour Pita", Ingredient.Type.WRAP));
        ingredientMap.put("HOPT", new Ingredient("HOPT", "Hot Chilli Pita", Ingredient.Type.WRAP));
        ingredientMap.put("CHPT", new Ingredient("CHPT", "Cheese Pita", Ingredient.Type.WRAP));
        ingredientMap.put("RSLB", new Ingredient("RSLB", "Roast Lamb", Ingredient.Type.PROTEIN));
        ingredientMap.put("RSBR", new Ingredient("RSBR", "Roast Beef", Ingredient.Type.PROTEIN));
        ingredientMap.put("RSCK", new Ingredient("RSCK", "Roast Chicken", Ingredient.Type.PROTEIN));
        ingredientMap.put("TMTO", new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
        ingredientMap.put("CALE", new Ingredient("CALE", "Cabbage Leaves", Ingredient.Type.VEGGIES));
        ingredientMap.put("CUCM", new Ingredient("CUCM", "Sliced Cucumbers", Ingredient.Type.VEGGIES));
        ingredientMap.put("POTS", new Ingredient("POTS", "Baked Diced Potatoes", Ingredient.Type.VEGGIES));
        ingredientMap.put("RUCH", new Ingredient("RUCH", "Russian", Ingredient.Type.CHEESE));
        ingredientMap.put("DUCH", new Ingredient("DUCH", "Dutch", Ingredient.Type.CHEESE));
        ingredientMap.put("SULG", new Ingredient("SULG", "Sulguni", Ingredient.Type.CHEESE));
        ingredientMap.put("KETC", new Ingredient("KETC", "Ketchup", Ingredient.Type.SAUCE));
        ingredientMap.put("MASE", new Ingredient("MASE", "Mayonnaise", Ingredient.Type.SAUCE));
        ingredientMap.put("KESE", new Ingredient("KESE", "Ketchunaise", Ingredient.Type.SAUCE));
        ingredientMap.put("GARL", new Ingredient("GARL", "Garlic Sauce", Ingredient.Type.SAUCE));
        ingredientMap.put("AJKA", new Ingredient("AJKA", "Adjika", Ingredient.Type.SAUCE));
    }

    @Override
    public Ingredient convert(String source) {
        return ingredientMap.get(source);
    }
}
