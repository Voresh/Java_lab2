package lab2;

import lab3.ForestDataBase;
import lab3.ForestLogger;
import menu.*;
import menu.elements.creators.GrassCreatorElement;
import menu.elements.creators.HerbivorousCreatorElement;
import menu.elements.creators.PredatorCreatorElement;
import menu.elements.creators.TreeCreatorElement;
import menu.elements.other.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

    private static ForestDataBase dataBase = new ForestDataBase();
	private static ForestLogger logger = new ForestLogger();

    private static Menu currentMenu;

    private static Menu plantMenu;
    private static Menu animalMenu;
    private static Menu grassMainMenu;
    private static Menu treesMainMenu;
    private static Menu predatorsMainMenu;
    private static Menu herbivorousMainMenu;

    private static Map<Integer,Integer> sGrassMenuElementsId = new HashMap<Integer, Integer>();
    private static Map<Integer,Integer> sTreeMenuElementsId = new HashMap<Integer, Integer>();
    private static Map<Integer,Integer> sPredatorMenuElementsId = new HashMap<Integer, Integer>();
    private static Map<Integer,Integer> sHerbivorousMenuElementsId = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) {
		logger.writeProgramStart();

        createMenu();
        getUserMenuInput();

        logger.writeProgramEnd();
	}

	public static void saveForestDataToDataBase() {
        dataBase.SaveClassesToDataBase(sGrass,sTrees,sHerbivorous,sPredators);
    }

    public static void loadForestDateFromDataBase() {
        dataBase.LoadClassesFromDataBase();
    }

	private static void createMenu()
    {
        Menu mainMenu = new Menu("main");
        currentMenu = mainMenu;

        //animal menu added here
        animalMenu = new Menu("animals", mainMenu);
        predatorsMainMenu = new Menu("predators", animalMenu);
        predatorsMainMenu.AddMenuElement(new PredatorCreatorElement("create predator"));
        herbivorousMainMenu = new Menu("herbivorous", animalMenu);
        herbivorousMainMenu.AddMenuElement(new HerbivorousCreatorElement("create herbivorous"));

        animalMenu.AddMenuElement(new SwitchMenuElement("predators", predatorsMainMenu));
        animalMenu.AddMenuElement(new SwitchMenuElement("herbivorous", herbivorousMainMenu));

        mainMenu.AddMenuElement(new SwitchMenuElement("animals", animalMenu));

        //plant menu added here
        plantMenu = new Menu("plants",mainMenu);
        grassMainMenu = new Menu("grass", plantMenu);
        grassMainMenu.AddMenuElement(new GrassCreatorElement("create grass"));
        treesMainMenu = new Menu("trees", plantMenu);
        treesMainMenu.AddMenuElement(new TreeCreatorElement("create trees"));

        plantMenu.AddMenuElement(new SwitchMenuElement("grass", grassMainMenu));
        plantMenu.AddMenuElement(new SwitchMenuElement("trees", treesMainMenu));

        mainMenu.AddMenuElement(new SwitchMenuElement("plants", plantMenu));

        mainMenu.AddMenuElement(new SaveToDataBaseElement("save"));
        mainMenu.AddMenuElement(new LoadFromDataBaseElement("load"));
    }

    public static void SwitchToMenu(Menu menu) {
        currentMenu = menu;
    }

    private static void getUserMenuInput() {
        Scanner scanner  = new Scanner(System.in);
        while (currentMenu != null) {
            System.out.println("====================");
            System.out.println("| Menu: " + currentMenu.getName()+" |");
            if (!currentMenu.getDescription().equals(""))
                System.out.println(currentMenu.getDescription());
            currentMenu.printMenuElements();
            System.out.print("enter your choice: ");
            if (scanner.hasNextInt()) {
                currentMenu.executeElement(scanner.nextInt() - 1);
            } else {
                System.out.println("not int... exit");
                currentMenu = null;
            }
        }
    }

	public static Grass searchForGrassOfType(GrassType type){
		for (Grass grass : sGrass){
			if (grass.getType() == type){
				return grass;
			}
		}
		return null;
	}
	
	public static Animal searchForAnimalsWithSizeLess(int size){
		for (Animal animal : sPredators){
			if (animal.getSize() < size){
				return animal;
			}
		}
        for (Animal animal : sHerbivorous){
            if (animal.getSize() < size){
                return animal;
            }
        }
		return null;
	}

	public static void removePredatorFromForest(int id){
		for(int i=0; i < sPredators.size(); i++) {
			if (sPredators.get(i).getId() == id)
			{
                logger.writeOtherMessage("predator of size " + sPredators.get(i).getSize() +  " removed");
				sPredators.remove(i);
                predatorsMainMenu.RemoveMenuElement(sPredatorMenuElementsId.get(id));
			}
		}
	}

    public static void removeHerbivorousFromForest(int id){
        for(int i=0; i < sHerbivorous.size(); i++) {
            if (sHerbivorous.get(i).getId() == id)
            {
                logger.writeOtherMessage("herbivorous of size " + sHerbivorous.get(i).getSize() +  " eating "
                        + sHerbivorous.get(i).getEatableType() + " removed");
                sHerbivorous.remove(i);
                herbivorousMainMenu.RemoveMenuElement(sHerbivorousMenuElementsId.get(id));
            }
        }
    }

	public static void removeGrassFromForest(int id){
		
		for(int i=0; i < sGrass.size(); i++) {
			if (sGrass.get(i).getId() == id)
			{
                logger.writeOtherMessage("grass of type " + sGrass.get(i).getType().toString() +  " removed");
				sGrass.remove(i);
                grassMainMenu.RemoveMenuElement(sGrassMenuElementsId.get(id));
			}
		}
	}

    public static void removeTreeFromForest(int id){

        for(int i=0; i < sTrees.size(); i++) {
            if (sTrees.get(i).getId() == id)
            {
                logger.writeOtherMessage("tree of type " + sTrees.get(i).getType().toString() +  " removed");
                sTrees.remove(i);
                treesMainMenu.RemoveMenuElement(sTreeMenuElementsId.get(id));
            }
        }
    }
	
	public static void addGrassToForest(Grass grass) {
		sGrass.add(grass);
		grass.setId(sCurrentGrassId);
		sCurrentGrassId++;
		logger.writeOtherMessage("grass of type " + grass.getType().toString() +  " created");
        createGrassMenu(grass);
	}

    public static void addTreeToForest(Tree tree) {
        sTrees.add(tree);
        tree.setId(sCurrentTreeId);
        sCurrentTreeId++;
        createTreeMenu(tree);
        logger.writeOtherMessage("tree of type " + tree.getType().toString() +  " created");
    }
	
	public static void addPredatorToForest(Predator predator) {
		sPredators.add(predator);
		predator.setId(sCurrentPredatorId);
		sCurrentPredatorId++;
        createPredatorMenu(predator);
        logger.writeOtherMessage("predator of size " + predator.getSize() +  " created");
	}

    public static void addHerbivorousToForest(Herbivorous herbivorous) {
        sHerbivorous.add(herbivorous);
        herbivorous.setId(sCurrentHerbivorousId);
        sCurrentHerbivorousId++;
        createHerbivorousMenu(herbivorous);
        logger.writeOtherMessage("herbivorous of size " + herbivorous.getSize() +  " eating " + herbivorous.getEatableType() + " created");
    }

    private static void createGrassMenu(Grass grass) {
        String typeName = grass.getType().toString().toLowerCase();
        String name = typeName + " (" + grass.getId() + ")";

        Menu grassMenu = new Menu(name, grassMainMenu);
        SwitchMenuElement grassSwitchElement = new SwitchMenuElement(name, grassMenu);
        grassMainMenu.AddMenuElement(grassSwitchElement);
        grassMenu.AddMenuElement(new KillGrassElement("remove", grass, grassSwitchElement, grassMainMenu));
        sGrassMenuElementsId.put(grass.getId(),grassSwitchElement.getId());
    }

    private static void createTreeMenu(Tree tree) {
        String typeName = tree.getType().toString().toLowerCase();
        String name = typeName + " (" + tree.getId() + ")";

        Menu treeMenu = new Menu(name, treesMainMenu);
        SwitchMenuElement treeSwitchElement = new SwitchMenuElement(name, treeMenu);
        treesMainMenu.AddMenuElement(treeSwitchElement);
        treeMenu.AddMenuElement(new KillTreeElement("remove", tree, treeSwitchElement, treesMainMenu));
        sTreeMenuElementsId.put(tree.getId(),treeSwitchElement.getId());
    }

    private static void createPredatorMenu(Predator predator) {
        String typeName = "predator of size " + predator.getSize();
        String name = typeName + " (" + predator.getId() + ")";

        Menu predatorMenu = new Menu(name, predatorsMainMenu);
        SwitchMenuElement predatorSwitchElement = new SwitchMenuElement(name, predatorMenu);
        predatorsMainMenu.AddMenuElement(predatorSwitchElement);
        predatorMenu.AddMenuElement(new KillPredatorElement("remove", predator, predatorSwitchElement, predatorsMainMenu));
        predatorMenu.AddMenuElement(new PredatorSearchForFoodElement("search for food", predator));
        sPredatorMenuElementsId.put(predator.getId(),predatorSwitchElement.getId());
    }

    private static void createHerbivorousMenu(Herbivorous herbivorous) {
        String typeName = "herbivorous of size " + herbivorous.getSize() + " eating " + herbivorous.getEatableType().toString().toLowerCase();
        String name = typeName + " (" + herbivorous.getId() + ")";

        Menu herbivorousMenu = new Menu(name, herbivorousMainMenu);
        SwitchMenuElement herbivorousSwitchElement = new SwitchMenuElement(name, herbivorousMenu);
        herbivorousMainMenu.AddMenuElement(herbivorousSwitchElement);
        herbivorousMenu.AddMenuElement(new KillHerbivorousElement("remove", herbivorous, herbivorousSwitchElement, herbivorousMainMenu));
        herbivorousMenu.AddMenuElement(new HerbivorousSearchForFoodElement("search for food", herbivorous));
        sHerbivorousMenuElementsId.put(herbivorous.getId(),herbivorousSwitchElement.getId());
    }
}
