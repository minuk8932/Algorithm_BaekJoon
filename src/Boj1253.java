import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
		
		Arrays.sort(arr);
		int cnt = 0;
		
		for(int i = 0; i < N; i++) {
			int left = 0, right = N - 1;
			int mid = 0;
			
			while(left < right) {
				mid = (left + right) / 2;
				
				int tmp = arr[left] + arr[right];
				
				if(left == mid || right == mid) break;
				
				if(arr[mid] > tmp) {
					right = mid - 1;
				}
				else if(arr[mid] < tmp) {
					left = mid + 1;
				}
				else {
					cnt++;
					System.out.println(left + " " + right + " " + arr[mid]);
					break;
				}
			}
		}
		
		System.out.println(cnt);
	}
}
