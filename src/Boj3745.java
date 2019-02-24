import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3745번: 오름세
 *
 *	@see https://www.acmicpc.net/problem/3745/
 *
 */
public class Boj3745 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = "";
		
		while((line = br.readLine()) != null) {
			line = line.trim();
			int N = 0;
			
			try {
				N = Integer.parseInt(line);
			}
			catch(Exception e) {
				continue;
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] stock = new int[N];
			int idx = 0;
			
			while(st.hasMoreTokens()) {
				String num = st.nextToken();
				
				if(num.equals(" ")) continue;
				stock[idx++] = Integer.parseInt(num);
			}
			
			System.out.println(lis(N, stock));
		}
	}
	
	private static int lis(int n, int[] arr) {		// nlogn lis
		int[] dp = new int[n];
		
		dp[0] = arr[0];
		int index = 0;
		
		for(int i = 1; i < n; i++) {
			if(dp[index] == arr[i]) continue;
			
			if(dp[index] > arr[i]) {
				int tmp = binarySearch(dp, 0, index, arr[i]);
				dp[tmp] = arr[i];
			}
			else {
				dp[++index] = arr[i];
			}
		}
		
		return index + 1;			// dp 배열의 길이를 출력
	}
	
	private static int binarySearch(int[] dp, int start, int end, int target) {
		int index = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(target <= dp[mid]) {
				end = mid - 1;
				index = mid;
			}
			else {
				start = mid + 1;
			}
		}
		
		return index;
	}
}
