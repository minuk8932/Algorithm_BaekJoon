import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17074 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		int index = -1;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			if(i >= 1) {
				if(arr[i - 1] > arr[i]) index = i - 1;
			}
		}
		
		System.out.println(index == -1 ? N: getCount(N, arr, index));
	}
	
	private static int getCount(int n, int[] arr, int except) {
		boolean[] prev = new boolean[n];
		boolean[] post = new boolean[n];
		
		for(int i = 1; i < except; i++) {
			if(arr[i - 1] > arr[i]) return 0;
			prev[i] = true;
		}

		for(int i = except + 1; i < n - 1; i++) {
			if(arr[i] > arr[i + 1]) return 0;
			post[i + 1] = true;
		}
		
		
		
		return 0;
	}
}
