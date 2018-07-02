package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj3047 {
	public static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Number[] arr = new Number[3];
		char[] alpha = new char[3];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new Number(Integer.parseInt(st.nextToken()));
	
		}
		Arrays.sort(arr, Number.comparator);

		alpha = br.readLine().toCharArray();
		for (int i = 0; i < arr.length; i++) {
			switch (alpha[i]) {

			case 'A':
				sb.append(arr[0].num).append(SPACE);
				break;

			case 'B':
				sb.append(arr[1].num).append(SPACE);
				break;

			case 'C':
				sb.append(arr[2].num).append(SPACE);
				break;
			}
		}
		System.out.println(sb.toString());
	}
	
	private static class Number{
		public int num;
		
		private Number(int num){
			this.num = num;
		}
		
		private static Comparator<Number> comparator = new Comparator<Number>() {

			@Override
			public int compare(Number n1, Number n2) {
				if(n1.num < n2.num){
					return -1;
				}
				else{
					return 1;
				}
			}
		};
	}

}
