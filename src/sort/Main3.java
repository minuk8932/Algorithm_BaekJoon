package sort;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main3 {
	private static final String NEW_LINE = "\n";

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Person[] persons = new Person[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			persons[i] = new Person(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}

		br.close();

		Arrays.sort(persons, Person.comparator);

		StringBuilder sb = new StringBuilder();

		for (Person p : persons) {
			sb.append(p.name).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}

	public static class Person {
		public String name;
		public int k;
		public int e;
		public int m;

		public Person(String name, int k, int e, int m) {
			this.name = name;
			this.k = k;
			this.e = e;
			this.m = m;
		}

		public static Comparator<Person> comparator = new Comparator<Main3.Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				if (p1.k > p2.k) {
					return -1;
				}

				else if (p1.k == p2.k) {
					if (p1.e < p2.e) {
						return -1;
					}

					else if (p1.e == p2.e) {
						if (p1.m > p2.m) {
							return -1;
						}

						else if (p1.m == p2.m) {
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

				else {
					return 1;
				}
			}
		};
	}
}