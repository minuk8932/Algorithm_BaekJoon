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
		
		int zero = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			if(arr[i] == 0) zero++;
		}

		Arrays.sort(arr);
		int cnt = zero >= 3 ? 1 : 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == j) continue;
				if(isVisited[j]) continue;
				
				int right = N - 1, left = 0, mid = 0;
				
				while (left < right) {
					mid = (left + right + 1) / 2;
					
					int target = arr[j];
					
					if (arr[mid] + arr[i] > target) {
						right = mid - 1;
					}

					else if (arr[mid] + arr[i] < target) {
						left = mid + 1;
					}

					else {
						if(mid == j || mid == i) break;
						isVisited[j] = true;
						
						cnt++;
						break;
					}
				}
			}
		}

		System.out.println(cnt);
	}
}
