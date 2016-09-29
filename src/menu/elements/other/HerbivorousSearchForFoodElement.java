package menu.elements.other;


import lab2.Herbivorous;

/**
 * Created by v on 27.09.16.
 */
public class HerbivorousSearchForFoodElement extends MenuElement {
    private Herbivorous mHerbivorous;

    public HerbivorousSearchForFoodElement(String name, Herbivorous herbivorous) {
        super(name);
        this.mHerbivorous = herbivorous;
    }

    @Override
    public void execute() {
        mHerbivorous.searchForFood();
    }
}
