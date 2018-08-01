import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1253 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		boolean[] isVisited = new boolean[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int res = 0;

		for (int i = 0; i < N; i++) {
			int target = arr[i];
			if (isVisited[i]) continue;

LOOP:		for (int j = 0; j < N && j != i; j++) {			
				int left = j, right = N - 1, mid = 0;
				
				isVisited[i] = true;
				
				while(left < right) {
					mid = (left + right) / 2;
					
					if(i == 4) System.out.println(arr[left] + " " + arr[right] + " " + target);
					
					if(arr[left] + arr[right] > target) {
						right = mid;
					}
					
					else if(arr[left] + arr[right] < target) {
						left = mid;
					}
					
					else {
						res++;
						break LOOP;
					}
				}
			}
		}

		System.out.println(res);
	}
}
