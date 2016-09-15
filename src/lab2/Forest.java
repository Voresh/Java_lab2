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
	
	public static void main(String[] args) {
		CreateTestPlants();
		CreateTestAnimals();
		
		Predator wolf = new Predator(3);
		
		System.out.println("Initial forest: ");
		wolf.SearchForFood();
		DisplayForestGrass();
		DisplayForestAnimals();
		
		System.out.println("Order animal of size 3 to search for food: ");
		wolf.SearchForFood();
	}
	
	public static void CreateTestPlants()
	{
		new Grass(GrassType.BLUEBERRY);
		new Grass(GrassType.BLUEBERRY);
		new Grass(GrassType.RASPBERRY);
		new Grass(GrassType.STRAWBERRY);
		
		new Tree(TreeType.BIRCH);
		new Tree(TreeType.OAK);
		new Tree(TreeType.PINE);
	}
	
	public static void CreateTestAnimals()
	{
		GrassType gtype[] = {GrassType.BLUEBERRY,GrassType.RASPBERRY};
		
		new Herbivorous(2, gtype);
		new Predator(4);
		new Predator(3);
	}
	
	public static void DisplayForestGrass() {
		for (Grass _grass : grass) {
			System.out.println("Grass type of: " + _grass.getType().toString());
		}
	}
	
	public static void DisplayForestAnimals() {
		for (Animal animal : animals) {
			System.out.println("Animal size of: " + animal.size);
		}
	}
	
	public static void AddGrassToForest(Grass _grass) {
		grass.add(_grass);
	}
	
	public static void AddAnimalToForest(Animal animal) {
		animals.add(animal);
	}
}
