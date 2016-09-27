package lab2;

import lab3.ForestDataBase;
import lab3.ForestLogger;
import menu.*;
import menu.elements.creators.GrassCreatorElement;
import menu.elements.creators.HerbivorousCreatorElement;
import menu.elements.creators.PredatorCreatorElement;
import menu.elements.other.KillGrassElement;
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

	private static int sCurrentAnimalId = 0;
	private static int sCurrentGrassId = 0;

    private static ForestDataBase dataBase = new ForestDataBase();
	private static ForestLogger logger = new ForestLogger();

    private static Menu currentMenu;
    private static Menu plantMenu;
    private static Menu grassMainMenu;
	
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

        Menu animalMenu = new Menu("animals", mainMenu);

        Menu predatorMenu = new Menu("predators", animalMenu);
        predatorMenu.AddMenuElement(new PredatorCreatorElement("create predator"));
        Menu herbivorousMenu = new Menu("herbivorous", animalMenu);
        herbivorousMenu.AddMenuElement(new HerbivorousCreatorElement("create herbivorous"));

        animalMenu.AddMenuElement(new SwitchMenuElement("predators", predatorMenu));
        animalMenu.AddMenuElement(new SwitchMenuElement("herbivorous", herbivorousMenu));

        currentMenu.AddMenuElement(new SwitchMenuElement("animals", animalMenu));

        plantMenu = new Menu("plants",mainMenu);
        grassMainMenu = new Menu("grass", plantMenu);
        grassMainMenu.AddMenuElement(new GrassCreatorElement("create grass"));
        plantMenu.AddMenuElement(new SwitchMenuElement("grass", grassMainMenu));

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
                System.exit(0);
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
	
	public static void addGrassToForest(Grass grass) {
		sGrass.add(grass);
		grass.setId(sCurrentGrassId);
		sCurrentGrassId++;
		logger.writeOtherMessage("grass of type " + grass.getType().toString() +  " created");
        createGrassMenu(grass);
	}
	
	public static void addAnimalToForest(Animal animal) {
		sAnimals.add(animal);
		animal.setId(sCurrentAnimalId);
		sCurrentAnimalId++;
	}

	public static void addHerbivorousToForest(Animal animal) {

    }

    private static void createGrassMenu(Grass grass) {
        String typeName = grass.getType().toString().toLowerCase();
        String description = "id: " + grass.getId() + "\n" + "type: " + typeName;

        Menu grassMenu = new Menu("current grass menu", description, grassMainMenu);
        SwitchMenuElement grassSwitchElement = new SwitchMenuElement(typeName + " grass", grassMenu);
        grassMenu.AddMenuElement(new KillGrassElement("remove grass", grass,grassSwitchElement, grassMainMenu));
        grassMainMenu.AddMenuElement(grassSwitchElement);

       // grassMenus.add(grassMenu);
    }

    public static void RemoveGrassMenu(SwitchMenuElement element, Menu ownerMenu) {
        //grassMenus.remove(menu);
        SwitchToMenu(ownerMenu);
        ownerMenu.RemoveMenuElement(element.getId());
    }
}
