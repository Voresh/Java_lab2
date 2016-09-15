package lab2;

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
		CreateTestPlants();
		CreateTestAnimals();
		
		System.out.println("Initial forest: ");

		DisplayForestGrass();
		DisplayForestAnimals();
		
		RunHerbivorousTest(0);
		RunPredatorTest(2);
	}
	
	public static void RunPredatorTest(int index){
		System.out.println("___PREDATOR_TEST___");
		Animal testPredator = sAnimals.get(index);
		testPredator.SearchForFood();
		
		DisplayForestAnimals();

		testPredator.SearchForFood();
		
		DisplayForestAnimals();
		System.out.println("___PREDATOR_TEST___");
	}
	
	public static void RunHerbivorousTest(int index){
		System.out.println("___HERVIVOROUS_TEST___");
		Animal testHerbivorous = sAnimals.get(index);
		
		testHerbivorous.SearchForFood();
		
		DisplayForestGrass();
		
		testHerbivorous.SearchForFood();
		
		DisplayForestGrass();
		System.out.println("___HERVIVOROUS_TEST___");
	}
	
	public static void CreateTestPlants()
	{
		new Grass(GrassType.RASPBERRY);
		new Grass(GrassType.BLUEBERRY);
		new Grass(GrassType.RASPBERRY);
		new Grass(GrassType.STRAWBERRY);
		
		new Tree(TreeType.BIRCH);
		new Tree(TreeType.OAK);
		new Tree(TreeType.PINE);
	}
	
	public static void CreateTestAnimals()
	{
		new Herbivorous(2, GrassType.RASPBERRY);
		new Predator(4);
		new Predator(3);
	}
	
	public static void DisplayForestGrass() {
		System.out.println("Current grass: ");
		for (Grass _grass : sGrass) {
			System.out.println("Grass " + _grass.getId() + " type of: " + _grass.getType().toString());
		}
	}
	
	public static void DisplayForestAnimals() {
		System.out.println("Current animals: ");
		for (Animal animal : sAnimals) {
			System.out.println("Animal " + animal.getId() + " size of: " + animal.mSize);
		}
	}
	
	public static Grass SearchForGrassOfType(GrassType type){
		for (Grass grass : sGrass){
			if (grass.getType() == type){
				return grass;
			}
		}
		return null;
	}
	
	public static Animal SearchForAnimalsWithSizeLess(int size){
		for (Animal animal : sAnimals){
			if (animal.mSize < size){
				return animal;
			}
		}
		return null;
	}

	public static void RemoveAnimalFromForest(int id){
		for(int i=0; i < sAnimals.size(); i++) {
			if (sAnimals.get(i).getId() == id)
			{
				sAnimals.remove(i);
			}
		}
	}

	public static void RemoveGrassFromForest(int id){
		
		for(int i=0; i < sGrass.size(); i++) {
			if (sGrass.get(i).getId() == id)
			{
				sGrass.remove(i);
			}
		}
	}
	
	public static void AddGrassToForest(Grass grass) {
		sGrass.add(grass);
		grass.setId(sCurrentGrassId);
		sCurrentGrassId++;
	}
	
	public static void AddAnimalToForest(Animal animal) {
		sAnimals.add(animal);
		animal.setId(sCurrentAnimalId);
		sCurrentAnimalId++;
	}
}
