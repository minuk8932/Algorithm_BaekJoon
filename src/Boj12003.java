import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj12003 {
	private static ArrayList<Data> index = new ArrayList<>();
	
	private static class Data{
		int from;
		int to;
		
		public Data(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] diamond = new int[N];
		for(int i = 0; i < N; i++) {
			diamond[i] = Integer.parseInt(br.readLine());
		}
		
		binarySearch(N, K, diamond);
		System.out.println(makeResult(N));
	}
	
	private static void binarySearch(int n, int k, int[] arr) {
		Arrays.sort(arr);
		
		for(int i = 0; i < n; i++) {
			int start = i, end = n - 1;
			int idx = 0;
			
			while(start <= end) {
				int mid = (start + end) / 2;
				int diff = Math.abs(arr[i] - arr[mid]);
				
				if(diff > k) {
					end = mid - 1;
				}
				else {
					start = mid + 1;
					idx = mid;
				}
			}
			
			index.add(new Data(Math.min(i, idx), Math.max(i, idx)));
		}
	}
	
	private static int makeResult(int n) {
		int[] left = new int[n];
		left[0] = 1 - index.get(0).from;
		
		for(int i = 1; i < n; i++) {
			left[i] = Math.max(index.get(i).from, index.get(i - 1).from);
		}
		
		int[] right = new int[n];
		right[n - 1] = index.get(n - 1).to - (n - 1) + 1;
		
		for(int i = n - 2; i >= 0; i--) {
			right[i] = Math.max(index.get(i).to, index.get(i + 1).to);
		}
		
		int result = 0;
		for(int i = 0; i < n - 1; i++) {
			result = Math.max(result, left[i] + right[i + 1]);
		}
		
		return result;
	}
}
