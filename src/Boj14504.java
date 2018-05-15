import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14504 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++){
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		
		while(M-- > 0){
			st = new StringTokenizer(br.readLine());
			
			int q = Integer.parseInt(st.nextToken());
			
			if(q == 1){
				int idx1 = Integer.parseInt(st.nextToken());
				int idx2 = Integer.parseInt(st.nextToken());
				int element = Integer.parseInt(st.nextToken());
				
				int cnt = 0;
				
				for(int i = idx1; i < idx2 + 1; i++){
					if(nums[i] > element){
						cnt++;
					}
				}
				
				sb.append(cnt).append(NEW_LINE);
				
				continue;
			}
			
			if(q == 2){
				int idx = Integer.parseInt(st.nextToken());
				int swap = Integer.parseInt(st.nextToken());
				
				nums[idx] = swap;
			}
		}
		
		System.out.println(sb.toString());
	}
}
