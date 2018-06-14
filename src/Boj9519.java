import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9519 {
	private static char[] arr = null;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		arr = br.readLine().toCharArray();
		
		int s = 0;
		
		if(arr.length % 2 == 0) {
			s = arr.length - 3;
		}
		else {
			s = arr.length - 2;
		}
		
		System.out.println(blink(s, X));
	}
	
	private static String blink(int start, int times) {
		StringBuilder sb = new StringBuilder();
		
//		times %= arr.length - 1;
		
		while(times-- > 0) {
			for(int i = start; i >= 0; i -= 2) {
				char tmp = arr[i];
				
				for(int j = i + 1; j < arr.length; j++) {
					arr[j - 1] = arr[j];
				}
				arr[arr.length - 1] = tmp;
			}
			
			for(int i = 0; i < arr.length; i++) {
				System.out.print(arr[i]);
			}
			System.out.println();
		}
		
		for(int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}

		return sb.toString();
	}
}
