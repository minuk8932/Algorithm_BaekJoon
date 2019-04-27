package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2659번: 십자 카드 문제
 *
 *	@see https://www.acmicpc.net/problem/2659/
 *
 */
public class Boj2659 {
	private static HashSet<Integer> hs = new HashSet<>();
	private static PriorityQueue<Integer> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getCrossNumber(arr));
	}
	
	private static int getCrossNumber(int[] arr) {
		int target = 10_000;
		
		for(int i = 0; i < arr.length; i++) {
			int loop = 4;
			int pow = 1000;
			
			int value = 0, index = i;
			
			while(loop-- > 0) {
				value += arr[index % 4] * pow;
				pow /= 10;
				index++;
			}
			
			target = Math.min(target, value);		// 입력 값 중 가장 작은 십자수를 뽑아옴
		}
		
		backTracking(1, 0, 0);			// 존재 가능한 모든 십자수 구하기
		
		int result = 0;
		while(!pq.isEmpty()) {
			int num = pq.poll();
			if(num > target) break;
			result++;
		}
		
		return result;
	}
	
	private static void backTracking(int current, int count, int value) {
		if(count == 4) {
			value = sorting(value);
			
			if(hs.contains(value)) return;
			hs.add(value);					// 생성 된 십자수 중 중복 없이 오름차순으로 저장
			pq.offer(value);
			return;
		}
		
		for(int next = 1; next < 10; next++) {
			backTracking(next, count + 1, (int) (next * Math.pow(10, count)) + value);	// 십자수 생성
		}
	}
	
	private static int sorting(int v) {
		char[] arr = String.valueOf(v).toCharArray();
		int result = 10_000;
		
		for(int i = 0; i < arr.length; i++) {				// 존재 가능한 십자수 중 최솟값
			int loop = 4;
			int pow = 1000;
			
			int value = 0, index = i;
			
			while(loop-- > 0) {
				value += (arr[index % 4] - '0') * pow;
				pow /= 10;
				index++;
			}
			
			result = Math.min(result, value);
		}

		return result;
	}
}
