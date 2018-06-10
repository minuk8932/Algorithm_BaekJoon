import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1253 {
	private static final int INF = 1_000_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] comp = new int[N * N];
		boolean[] isVisited = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int idx = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				comp[idx] = arr[i] + arr[j];
				idx++;
			}
		}
		
		for(int i = 0; i < N - 1; i++) {
			if(comp[i + 1] == arr[i]) comp[i + 1] = INF;
			
			System.out.println(arr[i] + " " + comp[i]);
		}
		
		int res = 0;
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < idx; j++) {
				if(arr[i] == comp[j] && !isVisited[i]) {
					res++;
					isVisited[i] = true;
				}
			}
		}
		
		System.out.println(res);
	}
}
