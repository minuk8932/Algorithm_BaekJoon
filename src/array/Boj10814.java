package array;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj10814 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Person[] persons = new Person[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			persons[i] = new Person(i, Integer.parseInt(st.nextToken()), st.nextToken());
		}

		br.close();

		Arrays.sort(persons, Person.comparator);

		StringBuilder sb = new StringBuilder();

		for (Person person : persons) {
			sb.append(person.age).append(SPACE).append(person.name).append(NEW_LINE);
		}

		System.out.println(sb.toString());
	}

	private static class Person {
		public int age;
		public int idx;
		public String name;

		public Person(int idx, int age, String name) {
			this.idx = idx;
			this.age = age;
			this.name = name;
		}

		private static Comparator<Person> comparator = new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				if (p1.age < p2.age) {
					return -1;
				}

				else if (p1.age == p2.age) {
					if (p1.idx < p2.idx) {
						return -1;
					}

					else if (p1.idx == p2.idx) {
						return p1.name.compareTo(p2.name);
					}

					else {
						return 1;
					}
				}

				else {
					return 1;
				}
			}
		};
	}
}
