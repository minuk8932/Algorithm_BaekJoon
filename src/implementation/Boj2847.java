package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2847 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int res = 0;
		
		for (int i = N - 2; i >= 0; i--) {
			while (arr[i + 1] <= arr[i]) {
				res++;
				arr[i]--;
			}
		}
		
		System.out.println(res);
	}
}
