package lab2;

import lab3.ForestDataBase;
import lab3.ForestLogger;

import java.util.ArrayList;

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
	
	public static void main(String[] args) {
		ForestLogger log = new ForestLogger();
		ForestDataBase db = new ForestDataBase();

		log.writeProgramStart();

		createTestPlants();
		createTestAnimals();
		
		System.out.println("Initial forest: ");

		displayForestGrass();
		displayForestAnimals();
		
		runHerbivorousTest(0);
		runPredatorTest(2);

		log.writeProgramEnd();
	}
	
	public static void runPredatorTest(int index){
		System.out.println("___PREDATOR_TEST___");
		Animal testPredator = sAnimals.get(index);
		testPredator.searchForFood();
		
		displayForestAnimals();

		testPredator.searchForFood();
		
		displayForestAnimals();
		System.out.println("___PREDATOR_TEST___");
	}
	
	public static void runHerbivorousTest(int index){
		System.out.println("___HERBIVOROUS_TEST___");
		Animal testHerbivorous = sAnimals.get(index);
		
		testHerbivorous.searchForFood();
		
		displayForestGrass();
		
		testHerbivorous.searchForFood();
		
		displayForestGrass();
		System.out.println("___HERBIVOROUS_TEST___");
	}
	
	public static void createTestPlants()
	{
		new Grass(GrassType.RASPBERRY);
		new Grass(GrassType.BLUEBERRY);
		new Grass(GrassType.RASPBERRY);
		new Grass(GrassType.STRAWBERRY);
		
		new Tree(TreeType.BIRCH);
		new Tree(TreeType.OAK);
		new Tree(TreeType.PINE);
	}
	
	public static void createTestAnimals()
	{
		new Herbivorous(2, GrassType.RASPBERRY);
		new Predator(4);
		new Predator(3);
	}
	
	public static void displayForestGrass() {
		System.out.println("Current grass: ");
		for (Grass _grass : sGrass) {
			System.out.println("Grass " + _grass.getId() + " type of: " + _grass.getType().toString());
		}
	}
	
	public static void displayForestAnimals() {
		System.out.println("Current animals: ");
		for (Animal animal : sAnimals) {
			System.out.println("Animal " + animal.getId() + " size of: " + animal.mSize);
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
				sGrass.remove(i);
			}
		}
	}
	
	public static void addGrassToForest(Grass grass) {
		sGrass.add(grass);
		grass.setId(sCurrentGrassId);
		sCurrentGrassId++;
	}
	
	public static void addAnimalToForest(Animal animal) {
		sAnimals.add(animal);
		animal.setId(sCurrentAnimalId);
		sCurrentAnimalId++;
	}
}
