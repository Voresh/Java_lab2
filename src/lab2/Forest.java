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
	private static ArrayList<Animal> animals = new ArrayList<Animal>();
	private static ArrayList<Grass> grass = new ArrayList<Grass>();
	private static int currentAnimalId = 0;
	private static int currentGrassId = 0;
	
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
		Animal testPredator = animals.get(index);
		testPredator.SearchForFood();
		
		DisplayForestAnimals();

		testPredator.SearchForFood();
		
		DisplayForestAnimals();
		System.out.println("___PREDATOR_TEST___");
	}
	
	public static void RunHerbivorousTest(int index){
		System.out.println("___HERVIVOROUS_TEST___");
		Animal testHerbivorous = animals.get(index);
		
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
		for (Grass _grass : grass) {
			System.out.println("Grass " + _grass.getId() + " type of: " + _grass.getType().toString());
		}
	}
	
	public static void DisplayForestAnimals() {
		System.out.println("Current animals: ");
		for (Animal animal : animals) {
			System.out.println("Animal " + animal.getId() + " size of: " + animal.size);
		}
	}
	
	public static Grass SearchForGrassOfType(GrassType type){
		for (Grass _grass : grass){
			if (_grass.getType() == type){
				return _grass;
			}
		}
		return null;
	}
	
	public static Animal SearchForAnimalsWithSizeLess(int size){
		for (Animal animal : animals){
			if (animal.size < size){
				return animal;
			}
		}
		return null;
	}
	
	public static void RemoveAnimalFromForest(int id){
		animals.remove(id);
	}
	
	public static void RemoveGrassFromForest(int id){
		grass.remove(id);
	}
	
	public static void AddGrassToForest(Grass _grass) {
		grass.add(_grass);
		_grass.setId(currentGrassId);
		currentGrassId++;
	}
	
	public static void AddAnimalToForest(Animal animal) {
		animals.add(animal);
		animal.setId(currentAnimalId);
		currentAnimalId++;
	}
}
