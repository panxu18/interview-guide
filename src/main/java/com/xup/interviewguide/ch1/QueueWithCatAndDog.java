package com.xup.interviewguide.ch1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class QueueWithCatAndDog {

	public static class Pet {
		private String type;

		public Pet(String type) {
			this.type = type;
		}

		public String getPetType() {
			return this.type;
		}
	}

	public static class Dog extends Pet {
		public Dog() {
			super("dog");
		}
	}

	public static class Cat extends Pet {
		public Cat() {
			super("cat");
		}
	}

	public static class PetEntry {
		Pet pet;
		int index;
		ArrayList<Pet> queue;
		
		@SuppressWarnings("unchecked")
		public PetEntry(Pet pet) {
			this.pet = pet; 
			queue = (ArrayList<Pet>) getQueue();
		}
		
		private ArrayList<? extends Pet> getQueue() {
			return PetEnum.fromClass(pet.getClass()).get().getQueue();
		}
		
		public void add() {
			queue.add(pet);
		}
		
	}
	
	enum PetEnum {
		CAT(Cat.class), DOG(Dog.class);
		private Class<? extends Pet> type;
		ArrayList<? extends Pet> queue;
		
		ArrayList<? extends Pet> getQueue() {
			return queue;
		}
		
		private <T extends Pet> PetEnum(Class<T> clazz) {
			queue = new ArrayList<T>();
			type = clazz;
		}
		
		public static final Map<Class<? extends Pet>, PetEnum> classToEnum = 
				Arrays.stream(values()).collect(Collectors.toMap(e->e.type, e->e));
		
		public static Optional<PetEnum> fromClass(Class<? extends Pet> clazz) {
			return Optional.ofNullable(classToEnum.get(clazz));
		}
	}
	
	public void add(Pet pet) {
		new PetEntry(pet).add();
	}
	
	
	
	public static void main(String[] args) {
		Cat cat1 = new Cat();
		Cat cat2 = new Cat();
		Dog dog1 = new Dog();
		Dog dog2 = new Dog();
		PetEntry pe = new PetEntry(cat1);
		pe.add();
		pe = new PetEntry(cat2);
		pe.add();
		pe = new PetEntry(dog1);
		pe.add();
		pe = new PetEntry(dog2);
		pe.add();
		System.out.println("ok");
	}

}
