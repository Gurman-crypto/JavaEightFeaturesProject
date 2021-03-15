package com.iterators;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


public class InteratorFeature {

	List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

	// external loop
	public void externalForLooping() {

		for (int i = 0; i < this.numbersList.size(); i++) {

			System.out.println("externalForLooping"+this.numbersList.get(i));
		}
	}

	// external loop with elements
	public void externalLooping() {

		for (Integer element : numbersList) {

			System.out.println("externalLooping"+element);
		}
	}

	// internal loop > polymorphism
	public void internalLooping() {

		this.numbersList.forEach(new Consumer<Integer>() {

			public void accept(Integer element) {

				System.out.println("internalLooping"+element);
			}
		}

		);
	}

	// type inference and method reference
	public void internalLoopWithMethodReference() {

		this.numbersList.forEach(

				// (Integer element) -> System.out.println(element);
				// element -> System.out.println(element);
				System.out::println);

	}
}
