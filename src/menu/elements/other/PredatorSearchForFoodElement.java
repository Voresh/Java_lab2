package menu.elements.other;


import lab2.Animal;
import lab2.Predator;

/**
 * Created by v on 27.09.16.
 */
public class PredatorSearchForFoodElement extends  MenuElement{
    private Predator predator;

    public PredatorSearchForFoodElement(String name, Predator predator) {
        super(name);
        this.predator = predator;
    }

    @Override
    public void Execute() {
        predator.searchForFood();
    }
}
