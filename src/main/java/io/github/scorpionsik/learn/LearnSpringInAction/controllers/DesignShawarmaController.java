package io.github.scorpionsik.learn.LearnSpringInAction.controllers;

import io.github.scorpionsik.learn.LearnSpringInAction.models.Ingredient;
import io.github.scorpionsik.learn.LearnSpringInAction.models.Shawarma;
import io.github.scorpionsik.learn.LearnSpringInAction.repositiries.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import io.github.scorpionsik.learn.LearnSpringInAction.models.Ingredient.Type;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("shawarmaOrder")
public class DesignShawarmaController {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignShawarmaController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        Type[] types = Ingredient.Type.values();
        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(Locale.ROOT), filterByType((List<Ingredient>) ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm(Model model){
        model.addAttribute("shawarma", new Shawarma());
        return "design";
    }

    @PostMapping
    public String processShawarma(@Valid @ModelAttribute("shawarma") Shawarma shawarma, Errors errors){
        if(errors.hasErrors()) return "design";

        //save
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
