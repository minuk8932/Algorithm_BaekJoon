package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 9082번: 지뢰 찾기
 *
 *	@see https://www.acmicpc.net/problem/9082/
 *
 */
public class Boj9082 {
	private static final String NEW_LINE = "\n";
	private static final char MINE = '*';
	
	private static int count;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] count = new int[N];
			
			char[] input = br.readLine().toCharArray();
			for(int i = 0; i < N; i++) {
				count[i] = input[i] - '0';
			}
			
			sb.append(counting(N, count, br.readLine().toCharArray())).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static int counting(int n, int[] arr, char[] state) {
		count = 0;
		
		for(int i = 0; i < state.length; i++) {		// 미리 주어진 지뢰 제거
			if(state[i] != MINE) continue;

			arr[i]--;
			if(i > 0) arr[i - 1]--;
			if(i < n - 1) arr[i + 1]--;
			count++;
		}
		
		search(arr, 0);								// 찾기
		return count;
	}
	
	private static void search(int[] arr, int depth) {
		if(depth >= arr.length) return;
	
		if(arr[depth] > 0) {
			if(depth != 0 && arr[depth - 1] == 0) search(arr, depth + 1);		// 이전까지 이미 지뢰를 다찾은 경우 한칸 뒤로
			
			if(depth < arr.length - 1) {
				if(arr[depth + 1] != 0) {										// 모든 칸에서 범위내 지뢰 존재 가능 갯수
					arr[depth] -= 1;
					if(depth != arr.length - 1) arr[depth + 1] -= 1;
					if(depth != 0) arr[depth - 1] -= 1;
					count++;
				}
			}
			else if(depth == arr.length - 1) {									// 마지막칸 지뢰 존재 여부
				arr[depth] -= 1;
				arr[depth - 1] -= 1;
				count++;
			}

			if(arr[depth] == 1) search(arr, depth + 1);							// 지뢰 찾기 전 갯수가 2개인 경우
			else search(arr, depth + 2);
		}
        else {																	// 해당 칸에 지뢰가 없음
			search(arr, depth + 2);
		}
	}
}
