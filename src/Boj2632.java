import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2632 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int P = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[] one = new int[m];
		for(int i = 0; i < m; i++) {
			one[i] = Integer.parseInt(br.readLine());
		}
		
		int[] two = new int[n];
		for(int i = 0; i < n; i++) {
			two[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(search(m, n, one, two, P));
	}
	private static int search(int n, int m, int[] arr1, int[] arr2, int target) {
		boolean[][] visit = new boolean[n][m];
		
		
		
		
		return 0;
	}
}
