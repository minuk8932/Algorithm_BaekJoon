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
				if(arr[i - 1] > arr[i]) index = i;
			}
		}
		
		System.out.println(index == -1 ? N: getCount(N, arr, index));
	}
	
	private static int getCount(int n, int[] arr, int except) {
		for(int i = 1; i < except; i++) {
			if(arr[i - 1] > arr[i]) return 0;
		}

		for(int i = except + 1; i < n - 1; i++) {
			if(arr[i] > arr[i + 1]) return 0;
		}
		
		if(except == n - 1) {
			if(arr[except] >= arr[except - 2]) return 2;
			else return 1;
		}
		else if(except == 1){
			if(arr[except - 1] <= arr[except + 1]) return 2;
			else return 1;
		}
		else {
			if(arr[except - 2] <= arr[except]) return 2;
			else return 1;
		}
	}
}
