package day21;

import java.util.List;

public class Food {
    String name;
    List<Ingredient> ingredients;
    List<Allergen> allergens;

    Food(String name, List<Ingredient> ingredients, List<Allergen> allergens) {
        this.name = name;
        this.ingredients = ingredients;
        this.allergens = allergens;
    }

    boolean foodContainsIngredientAndAllergen(Ingredient ingredient, Allergen allergen) {
        return ingredients.contains(ingredient) && allergens.contains(allergen);
    }

    public boolean hasAllergen(String name) {
        return allergens.stream().anyMatch(a -> a.name.equals(name));
    }
}

