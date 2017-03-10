import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj5024 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		int[] dist = new int[N];
		int sum = 0;
		int minDist = 0;
		
		StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++){
			dist[i] = Integer.parseInt(st1.nextToken());
			sum += dist[i];
		}
		
		Arrays.sort(dist);
		minDist = (sum / p) + dist[N-1];
		
		System.out.println(minDist);
	}

}
