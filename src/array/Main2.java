package array;

import java.util.Arrays;
import java.util.Comparator;

public class Main2 {
	public static void main(String args[]) throws Exception {
		Person[] persons = new Person[5];
		persons[0] = new Person(1, 50);
		persons[1] = new Person(3, 10);
		persons[2] = new Person(5, 30);
		persons[3] = new Person(2, 30);
		persons[4] = new Person(4, 100);

		Arrays.sort(persons, new PersonComporator());

		for (Person p : persons) {
			System.out.println(p.idx + " " + p.score);
		}
	}

	private static class Person {
		public int idx;
		public int score;

		public Person(int idx, int score) {
			this.idx = idx;
			this.score = score;
		}
	}
	
	public static class PersonComporator implements Comparator<Person> {

		@Override
		public int compare(Person p1, Person p2) {
			if (p1.score > p2.score) {
				return -1;
			}

			else if (p1.score == p2.score) {
				if (p1.idx < p2.idx) {
					return -1;
				}

				else if (p1.idx == p2.idx) {
					return 0;
				}

				else {
					return 1;
				}
			}

			else {
				return 1;
			}
		}
		
	}
}