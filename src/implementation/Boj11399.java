package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11399 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++){
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		int res = 0, sum = 0;
		
		for(int i = 0; i < N; i++){
			sum += nums[i];
			nums[i] = sum;
			res += nums[i];
		}
		
		System.out.println(res);
	}

}
