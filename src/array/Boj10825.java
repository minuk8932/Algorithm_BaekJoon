package array;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj10825 {
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Person[] person = new Person[N];
		
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			person[i] = new Person(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
										Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(person, Person.comparator);
		
		for(Person persons : person){
			sb.append(persons.name).append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}
	
	private static class Person {
		public String name;
		public int kor;
		public int eng;
		public int math;
		
		private Person(String name, int kor, int eng, int math){
			this.name = name;
			this.kor = kor;
			this.eng = eng;
			this.math = math;
		}
		
		private static Comparator<Person> comparator = new Comparator<Person>() {

			@Override
			public int compare(Person p1, Person p2) {
				if(p1.kor > p2.kor){
					return -1;
				} else if(p1.kor == p2.kor){
					if(p1.eng < p2.eng){
						return -1;
					} else if(p1.eng == p2.eng){
						if(p1.math > p2.math){
							return -1;
						} else if(p1.math == p2.math){
							return p1.name.compareTo(p2.name);
						} else {
							return 1;
						}
					} else {
						return 1;
					}
				} else {
					return 1;
				}
			}
		};
	}

}
