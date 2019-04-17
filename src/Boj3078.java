import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3078 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] friend = new int[N];
		int[] length = {21, 0};
		
		for(int i = 0; i < N; i++) {
			friend[i] = br.readLine().length();
			
			if(friend[i] < length[0]) length[0] = friend[i];
			if(friend[i] > length[1]) length[1] = friend[i];
		}
		
		System.out.println(getPair(N, K, friend, length[0], length[1]));
	}
	
	private static long getPair(int n, int interval, int[] arr, int min, int max) {
		long count = 0;
		
		for(int leng = min; leng < max + 1; leng++) {
			for(int idx = 0; idx < n; idx++) {
				if(arr[idx] == leng) {
					
				}
			}
		}
		
		return count;
	}
}
