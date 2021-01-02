package day21;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day21 {

    private static int counter = 0;

    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input21test.txt");
        List<Food> foods = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String[] inputParts = input.get(i).split("[()]");
            List<Ingredient> foodIngredients = Arrays.stream(inputParts[0].split(" "))
                    .map(Ingredient::new)
                    .collect(Collectors.toList());
            List<Allergen> foodAllergens = Arrays.stream(inputParts[1].replace("contains ", "").split(", "))
                    .map(Allergen::new)
                    .collect(Collectors.toList());
            Food food = new Food ("Food " + i, foodIngredients, foodAllergens);
            foods.add(food);
        }
        Set<Ingredient> ingredients = foods.stream()
                .map(f -> f.ingredients.stream().map(i -> i.name).collect(Collectors.toSet()))
                .flatMap(Set::stream)
                .collect(Collectors.toSet())
                .stream()
                .map(Ingredient::new)
                .collect(Collectors.toSet());
        Set<Allergen> allergens = foods.stream()
                .map(f -> f.allergens.stream().map(i -> i.name).collect(Collectors.toSet()))
                .flatMap(Set::stream)
                .collect(Collectors.toSet())
                .stream()
                .peek(System.out::println)
                .map(Allergen::new)
                .collect(Collectors.toSet());

        Map<Allergen, List<Food>> allergenFoodMap = allergens.stream()
                .collect(Collectors.toMap(a -> a, a -> foods.stream()
                        .peek(f -> System.out.println(f + " " + f.allergens.toString()))
                        .filter(f -> f.hasAllergen(a.name))
                        .collect(Collectors.toList())));
        boolean cont = true;
        List<Ingredient> ingredientsWithoutAllergen = new ArrayList<>();
        List<Ingredient> solvedIngredients = new ArrayList<>();
        for (Allergen allergen: allergenFoodMap.keySet()) {
            List<List<Ingredient>> potentialIngredientsForAllergen = allergenFoodMap.get(allergen).stream()
                    .map(f -> f.ingredients)
                    .collect(Collectors.toList());
            List<Ingredient> potentialIngredientsForAllergenSet = potentialIngredientsForAllergen.stream()
                    .flatMap(List::stream).collect(Collectors.toList());
            for (Ingredient potentialIngredientForAllergen : potentialIngredientsForAllergenSet) {
                if (potentialIngredientsForAllergen.stream().filter(l -> l.contains(potentialIngredientForAllergen)).count() == potentialIngredientsForAllergen.size()) {
                    solvedIngredients.add(potentialIngredientForAllergen);
                }
            }



        }

        System.out.println();
//        Map<Ingredient, List<Allergen>> ingredientAllergenMap = new HashMap<>();
//        for (Ingredient ingredientKey: ingredients) {
//            List<Allergen> allergensForIngredient = allergens.stream()
//                    .filter(a -> foods.stream().anyMatch(f -> f.foodContainsIngredientAndAllergen(ingredientKey, a)))
//                    .collect(Collectors.toList());
//            ingredientAllergenMap.put(ingredientKey, allergensForIngredient);
//        }
//        boolean solved = true;
//        do {
//            Map<Ingredient, List<Allergen>> finalIngredientAllergenMap = ingredientAllergenMap;
//            List<Ingredient> solvedIngredients = ingredientAllergenMap.keySet().stream()
//                    .filter(i -> finalIngredientAllergenMap.get(i).size() == 1)
//                    .collect(Collectors.toList());
//            solvedIngredients.stream().forEach(i -> i.allergen = finalIngredientAllergenMap.get(i).get(0));
//            Set<Allergen> solvedAllergens = solvedIngredients.stream()
//                    .map(i -> i.allergen)
//                    .collect(Collectors.toSet());
//            ingredientAllergenMap = ingredientAllergenMap.keySet().stream()
//                    .filter(i -> finalIngredientAllergenMap.get(i).size() != 1)
//                    .collect(Collectors.toMap(i -> i, i -> finalIngredientAllergenMap.get(i)));
//            for (Ingredient ingredient: ingredientAllergenMap.keySet()) {
//                List<Allergen> newAllergens = ingredientAllergenMap.get(ingredient).stream()
//                        .filter(a -> solvedAllergens.stream().anyMatch(sa -> sa.name.equals(a.name)))
//                        .collect(Collectors.toList());
//                ingredientAllergenMap.put(ingredient, newAllergens);
//            }
//            solved = solvedIngredients.size() > 0;
//
//
//
//
//        } while (!solved);

    }

}
