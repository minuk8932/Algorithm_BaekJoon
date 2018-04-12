package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10409 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n, T, cnt = 0;
		int time = 0;
		
		StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
		StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st1.nextToken());
		T = Integer.parseInt(st1.nextToken());
		
		for(int i = 0; i < n; i++){
			time += Integer.parseInt(st2.nextToken());
			
			if(time > T){
				cnt++;
			}
		}
		sb.append(n - cnt);
		System.out.println(sb.toString());
		
	}

}
