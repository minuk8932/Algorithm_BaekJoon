import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj6209 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int d = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] island = new int[n + 1];
		island[0] = d;
		
		for(int i = 1; i < n + 1; i++) {
			island[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(island);
		System.out.println(binarySearch(n, m, island));
	}
	
	private static int binarySearch(int n, int m, int[] arr) {
		int end = arr[n - 1];
		int start = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			
		}
		
		return 0;
	}
}
