package menu.elements.other;


import lab2.Predator;

/**
 * Created by v on 27.09.16.
 */
public class PredatorSearchForFoodElement extends MenuElement {
    private Predator mPredator;

    public PredatorSearchForFoodElement(String name, Predator predator) {
        super(name);
        this.mPredator = predator;
    }

    @Override
    public void execute() {
        mPredator.searchForFood();
    }
}
