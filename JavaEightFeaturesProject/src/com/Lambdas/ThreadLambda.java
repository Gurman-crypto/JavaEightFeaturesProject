package com.Lambdas;

public class ThreadLambda {

//	Anonymous Inner Runnable Class Example
	public void createThreadByAnonymousClass() {
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread1 from anonymous inner class");
			}
		});
		
		thread1.start();
	}
	
	/*
	 * Runnable using Lambda Expression .U can see compiled class under the hood
	 * using javap - c classname.class >invokeDyanamic feature being used by lambdas
	 */
	public void createThreadByLambda() {
		
		Thread thread2 = new Thread( () -> System.out.println("thread2 from lambdas") );
		
		thread2.start();
     }
	
	
}
