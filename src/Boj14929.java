import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14929 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] nums = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++){
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int res = 0;
		
		for(int i = 0; i < n; i++){
			for(int j = i + 1; j < n; j++){
				res += (nums[i]*nums[j]);
			}
		}
		
		System.out.println(res);
	}
}