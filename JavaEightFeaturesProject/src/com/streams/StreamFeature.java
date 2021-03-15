package com.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import static java.util.stream.Collectors.*;

public class StreamFeature {

	public static List<Person> people = Arrays.asList(new Person("gurman", Gender.FEMALE, 21),
			new Person("roop", Gender.MALE, 22), new Person("raji", Gender.FEMALE, 19),
			new Person("karan", Gender.MALE, 18), new Person("jot", Gender.FEMALE, 22),
			new Person("dolly", Gender.FEMALE, 17), new Person("sehaj", Gender.MALE, 18));

	public void createPersons() {

	}

	// get list of names of females older than 18, in uppercase ,using external loop
	public void getAdultFemalesOld() {

		List<String> namesOfAdultGirls = new ArrayList<String>();

		for (Person p : people) {
			if (p.getAge() > 18) {
				namesOfAdultGirls.add(p.getName().toUpperCase());
			}
		}

		System.out.println(namesOfAdultGirls);

	}

	/*
	 * get list of names of females older than 18, in uppercase , using internal
	 * iterator i.e. streams e.g.- loop replaced by stream, if condition replaced by
	 * filter, map for any manipulation required on data and at the end collect in
	 * list , array or other collectors
	 */

	public void getAdultFemalesNew() {

		List<String> namesOfAdultGirls = people.stream()
				.filter(p -> p.getAge() > 17)
				.map(p -> p.getName().toUpperCase())
				.collect(toList());

		System.out.println(namesOfAdultGirls);

	}

	// print all males Names
	public void prinlAllMales() {

		people.stream()
				.filter(person -> Gender.MALE.equals(person.getGender()))
				.forEach(System.out::println);
				
	}

	/*
	 * print total of age of every person using reduce having accumulator 
	 * accumulator function to reduce by applying logic. u can use sum as well for
	 * stream of integers .u can use reduce(0, Integer::sum)
	 */	public void printTotalAge() {
		System.out.println(
		people.stream()
		.map(Person::getAge)
		.reduce(0,(carry,age)-> carry+age )
		);
	}
	
	//print total of age of every person using sum on stream of integers 
		public void printTotalAgeViaSum() {
			System.out.println(
			people.stream()
			.mapToInt(Person::getAge)
			.sum()
			);
		}
	
		//find oldest person using max > add comparator using lambdas to max
		public void printOldestPerson() {
			System.out.println(
			people.stream()
			.max( (p1,p2) -> p1.getAge()>p2.getAge() ?1:0 )
			);
		}
		
		//count non-adults
		public void countNonAdults() {
			System.out.println(
			people.stream()
			.filter( p1 -> p1.getAge()<18 )
			.count()
			);
		}
		
		//print list having names of non adult people . never perform mutation on existing collection using streams : 
		public void notRecommendedMutation() {
			
			List<String> pList = new ArrayList<String>();
			
			people.stream()
			.filter(p -> p.getAge() > 17)
			.map(p -> p.getName().toUpperCase())
			.forEach(p -> pList.add(p));
			
			System.out.println(pList);	
		} 
		
	/*print list having names of non adult people . never perform mutation on existing collection using streams : 
		 *u dont give anthing in paranthesis and give you back object is called supplier ,
		 *  so first paranthesis of collect is supplier.it provides you with initial value
		 *  also, collect manages concurrency of the code in it , so if you are changing any list in it,
		 *  there is no concurrency issue.in line 110, v did add on external object which is evil whereas 
		 *  in line 123 v changed internal temporary object, which is not shared by any other objects. 
		 *  this is what is done in toList(). so, when you want to do any custom change in toList(),
		 *  u can use it this way. Simplification : toList() , benefit : ThreadSafety
		 *  
		*/
		public void recommendedMutationOfList() {
					
					List<String> pList = people.stream()
					.filter(p -> p.getAge() > 17)
					.map(p -> p.getName().toUpperCase())
					.collect( () -> new ArrayList<>(), //supplier method
							(list,name)-> list.add(name), //BiConsumer method - accumulator method
							(list1,list2)-> list2.addAll(list1) //biconsumer method - combiner method
							);
					
					System.out.println(pList);	
				} 
		
		//to print different names of adult males : therefore set is used instead of list to print unique elements of collector.
		public void adultMaleNames() {
			
			Set<String> males = people.stream()
			.filter(p -> Gender.MALE.equals(p.getGender()))
			.map(p -> p.getName().toUpperCase())
			.collect( toSet());
			
			System.out.println(males);	
		} 
		
		// to create person map in key value form
      public void createPersonMap() {
			
			Map<String,Person> personMap = people.stream()
			.collect( toMap(
					(person)-> person.getName() + ":" + person.getAge(), //key keyMapper function
					person-> person //value valueMapper function - identity operation
					));
			
			System.out.println(personMap);	
		} 
      
		/*
		 * if there are diff persons with same name and you want group the map on the basis of
		 * key as name only , you can use groupingBy in collect for such requirement.
		 * grouping by returns colector implementing group by operation on input elts , 
		 * grouping elts on the basis of classification function used as key of map returned
		 */
      public void createPersonNameKeyMap() {
			
			Map<String,List<Person>> personNameKeyMap = people.stream()
			.collect( groupingBy(person-> person.getName() ) //classifier function
					);
			
			personNameKeyMap.forEach( (key,value)-> { System.out.println(key + "-->" + value ); } 
			); //foreach requires biconsumer function for map
			
			//System.out.println(personNameKeyMap);	
		} 
      
		/*
		 * find first person whose name is four letter but is older than 25 : streams
		 * are doing lazy evaluations until terminal operation(findfirst) is added i.e. v dont work if boss is not there.
		 * therefore we have infinite streams : short-circuiting intermediate and terminal operations.
		 */
      
		
		

}
