package menu.elements.other;


import lab2.Animal;
import lab2.Herbivorous;

/**
 * Created by v on 27.09.16.
 */
public class HerbivorousSearchForFoodElement extends  MenuElement{
    private Herbivorous herbivorous;

    public HerbivorousSearchForFoodElement(String name, Herbivorous herbivorous) {
        super(name);
        this.herbivorous = herbivorous;
    }

    @Override
    public void Execute() {
        herbivorous.searchForFood();
    }
}
