package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;


public class Object_Sort {
	private static String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		Person[] persons = new Person[5];
		
		ArrayList<Person> persons = new ArrayList<>();
		
		for(int i = 0; i < persons.size(); i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			
			persons.add(new Person(Integer.parseInt(st.nextToken()), st.nextToken()));
		}
		
		System.out.println();
		System.out.println("before");
		
		for (Person p : persons){
			System.out.println(p.age + " " + p.name);
		}
		
		System.out.println();
		System.out.println("after");
		
		Collections.sort(persons);
		
		for (Person p : persons){
			System.out.println(p.age + " " + p.name);
		}
		
	}

	private static class Person implements Comparable<Person> {
		public int age;
		public String name;
		
		public Person(int age, String name){
			this.age = age;
			this.name = name;
		}

		@Override
		public int compareTo(Person p) {
			if(this.age < p.age){
				return -1;
			}
			else if(this.age == p.age){
				return this.name.compareTo(p.name);
			}
			else{
				return 1;
			}
		}
		
		
	}	
}
