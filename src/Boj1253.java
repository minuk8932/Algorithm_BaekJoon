import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1253 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		boolean[] isVisited = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int res = 0;
		
		for(int i = 0; i < N; i++) {
			int tmp = arr[i];
			
			for(int j = 0; j < N; j++) {
				if(i == j ) continue;
					
					for(int k = 0; k < N; k++) {
						if(k == j || k == i) continue;
						
						if(arr[i] == arr[j] + arr[k] && !isVisited[i]) {
							res++;
							isVisited[i] = true;
						}
					}
				
			}
		}		
		
		System.out.println(res);
	}
}
