package lab2;

import lab3.Database;
import lab3.Logger;
import lab4.PerformanceTest;
import menu.*;
import menu.elements.creators.GrassCreatorElement;
import menu.elements.creators.HerbivorousCreatorElement;
import menu.elements.creators.PredatorCreatorElement;
import menu.elements.creators.TreeCreatorElement;
import menu.elements.other.*;

import java.util.*;

/*
 * Вариант №2.
 
   1. Разработать модель леса, в котором присутствуют следующие классы: лес, растения, животные.
   Растения подразделяются на деревья и траву различных видов. Животные подразделяются на хищников и травоядных различного размера.
   2. Реализовать у животного метод поиска в лесу пропитания с последующим поеданием (уничтожением).
   3. Разработать программу для демонстрации классов.
 
   Необходимо учесть, что травоядные питаются только определенным видом растений, а хищники могут съесть только животных меньше себя.
 */
public class Forest {
    private static ArrayList<Predator> sPredators = new ArrayList<Predator>();
    private static ArrayList<Herbivorous> sHerbivorous = new ArrayList<Herbivorous>();
	private static ArrayList<Grass> sGrass = new ArrayList<Grass>();
    private static ArrayList<Tree> sTrees = new ArrayList<Tree>();

	private static int sCurrentGrassId = 0;
    private static int sCurrentTreeId = 0;
    private static int sCurrentPredatorId = 0;
    private static int sCurrentHerbivorousId = 0;

    private static Database sDataBase = new Database();
	private static Logger sLogger = new Logger();
    private static PerformanceTest sPerformanceTest = new PerformanceTest();

    private static Menu sCurrentMenu;

    private static Menu sPlantMenu;
    private static Menu sAnimalMenu;
    private static Menu sGrassMainMenu;
    private static Menu sTreesMainMenu;
    private static Menu sPredatorsMainMenu;
    private static Menu sHerbivorousMainMenu;

    private static Map<Integer,Integer> sGrassMenuElementsId = new HashMap<Integer, Integer>();
    private static Map<Integer,Integer> sTreeMenuElementsId = new HashMap<Integer, Integer>();
    private static Map<Integer,Integer> sPredatorMenuElementsId = new HashMap<Integer, Integer>();
    private static Map<Integer,Integer> sHerbivorousMenuElementsId = new HashMap<Integer, Integer>();

    private static boolean writeForestLog;

    public static void main(String[] args) {
        writeForestLog = MenuElement.getConfirmation("write forest log?");

        if (writeForestLog)
            sLogger.writeProgramStart();

        createMenu();
        getUserMenuInput();

        if (writeForestLog)
            sLogger.writeProgramEnd();

        System.exit(0);
    }

    public static void saveForestDataToDataBase() {
        sDataBase.saveClassesToDataBase(sGrass, sTrees, sHerbivorous, sPredators);
    }

    public static void loadForestDateFromDataBase() {
        clearForest();
        sDataBase.loadClassesFromDataBase();
    }

    private static void createMenu() {
        Menu mainMenu = new Menu("main");
        sCurrentMenu = mainMenu;

        //animal menu added here
        sAnimalMenu = new Menu("animals", mainMenu);
        sPredatorsMainMenu = new Menu("predators", sAnimalMenu);
        sPredatorsMainMenu.addMenuElement(new PredatorCreatorElement("create predator"));
        sHerbivorousMainMenu = new Menu("herbivorous", sAnimalMenu);
        sHerbivorousMainMenu.addMenuElement(new HerbivorousCreatorElement("create herbivorous"));

        sAnimalMenu.addMenuElement(new SwitchMenuElement("predators", sPredatorsMainMenu));
        sAnimalMenu.addMenuElement(new SwitchMenuElement("herbivorous", sHerbivorousMainMenu));

        mainMenu.addMenuElement(new SwitchMenuElement("animals", sAnimalMenu));

        //plant menu added here
        sPlantMenu = new Menu("plants", mainMenu);
        sGrassMainMenu = new Menu("grass", sPlantMenu);
        sGrassMainMenu.addMenuElement(new GrassCreatorElement("create grass"));
        sTreesMainMenu = new Menu("trees", sPlantMenu);
        sTreesMainMenu.addMenuElement(new TreeCreatorElement("create trees"));

        sPlantMenu.addMenuElement(new SwitchMenuElement("grass", sGrassMainMenu));
        sPlantMenu.addMenuElement(new SwitchMenuElement("trees", sTreesMainMenu));

        mainMenu.addMenuElement(new SwitchMenuElement("plants", sPlantMenu));

        mainMenu.addMenuElement(new SaveToDataBaseElement("save"));
        mainMenu.addMenuElement(new LoadFromDataBaseElement("load"));

        Menu performanceMenu = new Menu("performance menu", mainMenu);
        performanceMenu.addMenuElement(new RunArrayTestElement("run Array test",sPerformanceTest));
        performanceMenu.addMenuElement(new RunArrayListTestElement("run ArrayList test",sPerformanceTest));
        mainMenu.addMenuElement(new SwitchMenuElement("performance tests",performanceMenu));

        mainMenu.addMenuElement(new OpenGUIElement("open GUI"));
    }

    public static void switchToMenu(Menu menu) {
        sCurrentMenu = menu;
    }

    private static void getUserMenuInput() {
        Scanner scanner = new Scanner(System.in);
        while (sCurrentMenu != null) {
            System.out.println("====================");
            System.out.println("| Menu: " + sCurrentMenu.getName() + " |");
            if (!sCurrentMenu.getDescription().equals(""))
                System.out.println(sCurrentMenu.getDescription());
            sCurrentMenu.printMenuElements();
            System.out.print("enter your choice: ");
            if (scanner.hasNextInt()) {
                sCurrentMenu.executeElement(scanner.nextInt() - 1);
            } else {
                System.out.println("not int... exit");
                sCurrentMenu = null;
            }
        }
    }

    public static Grass searchForGrassOfType(GrassType type) {
        for (Grass grass : sGrass) {
            if (grass.getType() == type) {
                return grass;
            }
        }
        return null;
    }

    public static Animal searchForAnimalsWithSizeLess(int size) {
        for (Animal animal : sPredators) {
            if (animal.getSize() < size) {
                return animal;
            }
        }
        for (Animal animal : sHerbivorous) {
            if (animal.getSize() < size) {
                return animal;
            }
        }
        return null;
    }

    public static void removePredatorFromForest(int id) {
        for (int i = 0; i < sPredators.size(); i++) {
            if (sPredators.get(i).getId() == id) {
                if (writeForestLog)
                    sLogger.writeOtherMessage("predator of size " + sPredators.get(i).getSize() + " removed");
                sPredators.remove(i);
                sPredatorsMainMenu.removeMenuElement(sPredatorMenuElementsId.get(id));
            }
        }
    }

    public static void removeHerbivorousFromForest(int id) {
        for (int i = 0; i < sHerbivorous.size(); i++) {
            if (sHerbivorous.get(i).getId() == id) {
                if (writeForestLog)
                    sLogger.writeOtherMessage("herbivorous of size " + sHerbivorous.get(i).getSize() + " eating "
                            + sHerbivorous.get(i).getEatableType() + " removed");
                sHerbivorous.remove(i);
                sHerbivorousMainMenu.removeMenuElement(sHerbivorousMenuElementsId.get(id));
            }
        }
    }

    public static void removeGrassFromForest(int id) {
        for (int i = 0; i < sGrass.size(); i++) {
            if (sGrass.get(i).getId() == id) {
                if (writeForestLog)
                    sLogger.writeOtherMessage("grass of type " + sGrass.get(i).getType().toString() + " removed");
                sGrass.remove(i);
                sGrassMainMenu.removeMenuElement(sGrassMenuElementsId.get(id));
            }
        }
    }

    public static void removeTreeFromForest(int id) {
        for (int i = 0; i < sTrees.size(); i++) {
            if (sTrees.get(i).getId() == id) {
                if (writeForestLog)
                    sLogger.writeOtherMessage("tree of type " + sTrees.get(i).getType().toString() + " removed");
                sTrees.remove(i);
                sTreesMainMenu.removeMenuElement(sTreeMenuElementsId.get(id));
            }
        }
    }

    public static void addGrassToForest(Grass grass) {
        sGrass.add(grass);
        grass.setId(sCurrentGrassId);
        sCurrentGrassId++;
        createGrassMenu(grass);

        if (writeForestLog)
            sLogger.writeOtherMessage("grass of type " + grass.getType().toString() + " created");
    }

    public static void addTreeToForest(Tree tree) {
        sTrees.add(tree);
        tree.setId(sCurrentTreeId);
        sCurrentTreeId++;
        createTreeMenu(tree);

        if (writeForestLog)
            sLogger.writeOtherMessage("tree of type " + tree.getType().toString() + " created");
    }

    public static void addPredatorToForest(Predator predator) {
        sPredators.add(predator);
        predator.setId(sCurrentPredatorId);
        sCurrentPredatorId++;
        createPredatorMenu(predator);

        if (writeForestLog)
            sLogger.writeOtherMessage("predator of size " + predator.getSize() + " created");
    }

    public static void addHerbivorousToForest(Herbivorous herbivorous) {
        sHerbivorous.add(herbivorous);
        herbivorous.setId(sCurrentHerbivorousId);
        sCurrentHerbivorousId++;
        createHerbivorousMenu(herbivorous);

        if (writeForestLog)
            sLogger.writeOtherMessage("herbivorous of size " + herbivorous.getSize() + " eating " + herbivorous.getEatableType() + " created");
    }

    public static void clearForest() {
        for (Grass grass : sGrass) {
            sGrassMainMenu.removeMenuElement(sGrassMenuElementsId.get(grass.getId()));
        }
        sGrass.clear();

        for (Tree tree : sTrees) {
            sTreesMainMenu.removeMenuElement(sTreeMenuElementsId.get(tree.getId()));
        }
        sTrees.clear();

        for (Herbivorous herbivorous : sHerbivorous) {
            sHerbivorousMainMenu.removeMenuElement(sHerbivorousMenuElementsId.get(herbivorous.getId()));
        }
        sHerbivorous.clear();

        for (Predator predator : sPredators) {
            sPredatorsMainMenu.removeMenuElement(sPredatorMenuElementsId.get(predator.getId()));
        }
        sPredators.clear();
    }

    private static void createGrassMenu(Grass grass) {
        String typeName = grass.getType().toString().toLowerCase();
        String name = typeName + " (" + grass.getId() + ")";

        Menu grassMenu = new Menu(name, sGrassMainMenu);
        SwitchMenuElement grassSwitchElement = new SwitchMenuElement(name, grassMenu);
        sGrassMainMenu.addMenuElement(grassSwitchElement);
        grassMenu.addMenuElement(new KillGrassElement("remove", grass, grassSwitchElement, sGrassMainMenu));
        sGrassMenuElementsId.put(grass.getId(), grassSwitchElement.getId());
    }

    private static void createTreeMenu(Tree tree) {
        String typeName = tree.getType().toString().toLowerCase();
        String name = typeName + " (" + tree.getId() + ")";

        Menu treeMenu = new Menu(name, sTreesMainMenu);
        SwitchMenuElement treeSwitchElement = new SwitchMenuElement(name, treeMenu);
        sTreesMainMenu.addMenuElement(treeSwitchElement);
        treeMenu.addMenuElement(new KillTreeElement("remove", tree, treeSwitchElement, sTreesMainMenu));
        sTreeMenuElementsId.put(tree.getId(), treeSwitchElement.getId());
    }

    private static void createPredatorMenu(Predator predator) {
        String typeName = "predator of size " + predator.getSize();
        String name = typeName + " (" + predator.getId() + ")";

        Menu predatorMenu = new Menu(name, sPredatorsMainMenu);
        SwitchMenuElement predatorSwitchElement = new SwitchMenuElement(name, predatorMenu);
        sPredatorsMainMenu.addMenuElement(predatorSwitchElement);
        predatorMenu.addMenuElement(new KillPredatorElement("remove", predator, predatorSwitchElement, sPredatorsMainMenu));
        predatorMenu.addMenuElement(new PredatorSearchForFoodElement("search for food", predator));
        sPredatorMenuElementsId.put(predator.getId(), predatorSwitchElement.getId());
    }

    private static void createHerbivorousMenu(Herbivorous herbivorous) {
        String typeName = "herbivorous of size " + herbivorous.getSize() + " eating " + herbivorous.getEatableType().toString().toLowerCase();
        String name = typeName + " (" + herbivorous.getId() + ")";

        Menu herbivorousMenu = new Menu(name, sHerbivorousMainMenu);
        SwitchMenuElement herbivorousSwitchElement = new SwitchMenuElement(name, herbivorousMenu);
        sHerbivorousMainMenu.addMenuElement(herbivorousSwitchElement);
        herbivorousMenu.addMenuElement(new KillHerbivorousElement("remove", herbivorous, herbivorousSwitchElement, sHerbivorousMainMenu));
        herbivorousMenu.addMenuElement(new HerbivorousSearchForFoodElement("search for food", herbivorous));
        sHerbivorousMenuElementsId.put(herbivorous.getId(), herbivorousSwitchElement.getId());
    }
}
