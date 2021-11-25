package io.github.scorpionsik.learn.LearnSpringInAction.controllers;

import io.github.scorpionsik.learn.LearnSpringInAction.models.Ingredient;
import io.github.scorpionsik.learn.LearnSpringInAction.models.Shawarma;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import io.github.scorpionsik.learn.LearnSpringInAction.models.Ingredient.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("shawarmaOrder")
public class DesignShawarmaController {

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLPT", "Flour Pita", Type.WRAP),
                new Ingredient("HOPT", "Hot Chilli Pita", Type.WRAP),
                new Ingredient("CHPT", "Cheese Pita", Type.WRAP),
                new Ingredient("RSLB", "Roast Lamb", Type.PROTEIN),
                new Ingredient("RSBR", "Roast Beef", Type.PROTEIN),
                new Ingredient("RSCK", "Roast Chicken", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("CALE", "Cabbage Leaves", Type.VEGGIES),
                new Ingredient("CUCM", "Sliced Cucumbers", Type.VEGGIES),
                new Ingredient("POTS", "Baked Diced Potatoes", Type.VEGGIES),
                new Ingredient("RUCH", "Russian", Type.CHEESE),
                new Ingredient("DUCH", "Dutch", Type.CHEESE),
                new Ingredient("SULG", "Sulguni", Type.CHEESE),
                new Ingredient("KETC", "Ketchup", Type.SAUCE),
                new Ingredient("MASE", "Mayonnaise", Type.SAUCE),
                new Ingredient("KESE", "Ketchunaise", Type.SAUCE),
                new Ingredient("GARL", "Garlic Sauce", Type.SAUCE),
                new Ingredient("AJKA", "Adjika", Type.SAUCE)
        );

        Type[] types = Ingredient.Type.values();
        for(Type type : types){
            model.addAttribute(
              type.toString().toLowerCase(),
              filterByType(ingredients, type)
            );
        }
    }

    @GetMapping
    public String showDesignForm(Model model){
        model.addAttribute("shawarma", new Shawarma());
        return "design";
    }

    @PostMapping
    public String processShawarma(Shawarma shawarma){
        log.info("Processing shawarma: " + shawarma);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
