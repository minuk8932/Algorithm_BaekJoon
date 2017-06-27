package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2399 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		int N = Integer.parseInt(br.readLine());
		long[] nums = new long[N];
		long sum = 0L;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++){
			nums[i] = Long.parseLong(st.nextToken());
		}
		
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(nums[i] - nums[j] < 0){
					sum += -(nums[i] - nums[j]);
				}
				else{
					sum += nums[i] - nums[j];
				}
			}
		}
		System.out.println(sum);
	}

}
