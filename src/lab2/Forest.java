package lab2;

import lab3.ForestDataBase;
import lab3.ForestLogger;
import menu.*;
import menu.elements.creators.GrassCreatorElement;
import menu.elements.creators.HerbivorousCreatorElement;
import menu.elements.creators.PredatorCreatorElement;
import menu.elements.creators.TreeCreatorElement;
import menu.elements.other.KillGrassElement;
import menu.elements.other.KillTreeElement;
import menu.elements.other.SwitchMenuElement;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Вариант №2.
 
   1. Разработать модель леса, в котором присутствуют следующие классы: лес, растения, животные. +
   Растения подразделяются на деревья и траву различных видов.+ Животные подразделяются на хищников и травоядных различного размера.+
   2. Реализовать у животного метод поиска в лесу пропитания с последующим поеданием (уничтожением). +
   3. Разработать программу для демонстрации классов.
 
   Необходимо учесть, что травоядные питаются только определенным видом растений, а хищники могут съесть только животных меньше себя.
 */
public class Forest {
	private static ArrayList<Animal> sAnimals = new ArrayList<Animal>();
	private static ArrayList<Grass> sGrass = new ArrayList<Grass>();
    private static ArrayList<Tree> sTrees = new ArrayList<Tree>();

	private static int sCurrentAnimalId = 0;
	private static int sCurrentGrassId = 0;
    private static int sCurrentTreeId = 0;

    private static ForestDataBase dataBase = new ForestDataBase();
	private static ForestLogger logger = new ForestLogger();

    private static Menu currentMenu;

    private static Menu plantMenu;
    private static Menu animalMenu;
    private static Menu grassMainMenu;
    private static Menu treesMainMenu;
    private static Menu predatorsMainMenu;
    private static Menu herbivorousMainMenu;
	
	public static void main(String[] args) {
		logger.writeProgramStart();

        createMenu();
        getUserMenuInput();

        logger.writeProgramEnd();
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

        currentMenu.AddMenuElement(new SwitchMenuElement("animals", animalMenu));

        //plant menu added here
        plantMenu = new Menu("plants",mainMenu);
        grassMainMenu = new Menu("grass", plantMenu);
        grassMainMenu.AddMenuElement(new GrassCreatorElement("create grass"));
        treesMainMenu = new Menu("trees", plantMenu);
        treesMainMenu.AddMenuElement(new TreeCreatorElement("create trees"));

        plantMenu.AddMenuElement(new SwitchMenuElement("grass", grassMainMenu));
        plantMenu.AddMenuElement(new SwitchMenuElement("trees", treesMainMenu));

        currentMenu.AddMenuElement(new SwitchMenuElement("plants", plantMenu));
    }

    public static void SwitchToMenu(Menu menu) {
        currentMenu = menu;
    }

    private static void getUserMenuInput() {
        Scanner scanner  = new Scanner(System.in);
        while (currentMenu != null) {
            System.out.println("====================");
            System.out.println("| Menu: " + currentMenu.getName()+" |");
            if (currentMenu.getDescription() != "")
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

	public static ArrayList<Grass> getsGrass() {
		return sGrass;
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
		for (Animal animal : sAnimals){
			if (animal.getSize() < size){
				return animal;
			}
		}
		return null;
	}

	public static void removeAnimalFromForest(int id){
		for(int i=0; i < sAnimals.size(); i++) {
			if (sAnimals.get(i).getId() == id)
			{
				sAnimals.remove(i);
			}
		}
	}

	public static void removeGrassFromForest(int id){
		
		for(int i=0; i < sGrass.size(); i++) {
			if (sGrass.get(i).getId() == id)
			{
                logger.writeOtherMessage("grass of type " + sGrass.get(i).getType().toString() +  " removed");
				sGrass.remove(i);
			}
		}
	}

    public static void removeTreeFromForest(int id){

        for(int i=0; i < sTrees.size(); i++) {
            if (sTrees.get(i).getId() == id)
            {
                logger.writeOtherMessage("tree of type " + sTrees.get(i).getType().toString() +  " removed");
                sTrees.remove(i);
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
        tree.setId(sCurrentGrassId);
        sCurrentTreeId++;
        logger.writeOtherMessage("tree of type " + tree.getType().toString() +  " created");
        createTreeMenu(tree);
    }
	
	public static void addAnimalToForest(Animal animal) {
		sAnimals.add(animal);
		animal.setId(sCurrentAnimalId);
		sCurrentAnimalId++;
	}

    private static void createGrassMenu(Grass grass) {
        String typeName = grass.getType().toString().toLowerCase();
        String name = typeName + " " + grass.getId();

        Menu grassMenu = new Menu(name, grassMainMenu);
        SwitchMenuElement grassSwitchElement = new SwitchMenuElement(name, grassMenu);
        grassMainMenu.AddMenuElement(grassSwitchElement);
        grassMenu.AddMenuElement(new KillGrassElement("remove", grass, grassSwitchElement, grassMainMenu));
    }

    private static void createTreeMenu(Tree tree) {
        String typeName = tree.getType().toString().toLowerCase();
        String name = typeName + " " + tree.getId();

        Menu treeMenu = new Menu(name, treesMainMenu);
        SwitchMenuElement treeSwitchElement = new SwitchMenuElement(name, treeMenu);
        treesMainMenu.AddMenuElement(treeSwitchElement);
        treeMenu.AddMenuElement(new KillTreeElement("remove", tree, treeSwitchElement, treesMainMenu));
    }
}
