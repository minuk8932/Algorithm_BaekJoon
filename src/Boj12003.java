import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj12003 {
	private static int[] indexL;
	private static int[] indexR;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] diamond = new int[N];
		for(int i = 0; i < N; i++) {
			diamond[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(diamond);
		System.out.println(makeResult(N, K, diamond));
	}
	
	private static void getRight(int n, int k, int[] arr) {
		indexR = new int[n];
		int j = n - 1;
		
		for(int i = n - 1; i >= 0; i--) {
			while(j >= 0 && arr[j] - arr[i] > k) {
				j--;
			}
			
			indexR[i] = j;
		}
	}
	
	private static void getLeft(int n, int k, int[] arr) {
		indexL = new int[n];
		int j = 0;
		
		for(int i = 0; i < n; i++) {
			while(j < n && arr[i] - arr[j] > k) {
				j++;
			}
			
			indexL[i] = j;
		}
	}
	
	private static int makeResult(int n, int k, int[] arr) {
		getLeft(n, k, arr);
		
		int[] sizeL = new int[n];
		sizeL[0] = 1 - indexL[0];
		
		for(int i = 1; i < n; i++) {
			sizeL[i] = i - indexL[i] + 1;
			sizeL[i] = Math.max(sizeL[i], sizeL[i - 1]);
		}
		
		getRight(n, k, arr);
		
		int[] sizeR = new int[n];
		sizeR[n - 1] = indexR[n - 1] - (n - 1) + 1;
		
		for(int i = n - 2; i >= 0; i--) {
			sizeR[i] = indexR[i] - i + 1;
			sizeR[i] = Math.max(sizeR[i], sizeR[i + 1]);
		}
		
		int result = 0;
		for(int i = 0; i < n - 1; i++) {
			result = Math.max(result, sizeL[i] + sizeR[i + 1]);
		}
		
		return result;
	}
}
