import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14225 {
	private static boolean[] nums = new boolean[2_000_001];
	private static boolean[] isVisited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		isVisited = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		nums[arr[0]] = true;
		isVisited[0] = true;
			
		dfs(N, arr, 0);
		
	}
	
	private static void dfs(int n, int[] arr, int current) {
		if(nums[current]) return;
		
	}
}
