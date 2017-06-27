package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1026 {
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] num1 = new int[N];
		int[] num2 = new int[N];
		int sum = 0;
	
		StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++){
			num1[i] = Integer.parseInt(st1.nextToken());
		}
		
		StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++){
			num2[i] = Integer.parseInt(st2.nextToken());
		}
		
		Arrays.sort(num1);
		Arrays.sort(num2);
		for(int i = 0; i < N; i++){
			sum += num1[i] * num2[N-1-i];
		}
		
		sb.append(sum).append(NEW_LINE);
		System.out.println(sb.toString());
	}

}
