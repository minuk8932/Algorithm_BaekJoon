package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9237번: 이장님 초대
 *
 *	@see https://www.acmicpc.net/problem/9237/
 *
 */
public class Boj9237 {
	private static final int INF = 1_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] trees = new int[INF];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			trees[Integer.parseInt(st.nextToken())]++;
		}
		
		System.out.println(getDay(trees, N));
	}
	
	private static int getDay(int[] arr, int n) {
		boolean[] getMax = new boolean[arr.length];
		int diff = n - 1;
		
		for(int i = arr.length - 1; i >= 0; i--) {
			if(arr[i] <= 0) continue;
			
			while(arr[i] > 0) {		// 가장 오래걸리는 나무에서 N-1을 하나씩 줄여가며 차이를 구해 최대값 배열의 인덱스로 담아줌
				int idx = i - diff;
				arr[i]--;
				diff--;
				
				if(idx < 0) continue;
				getMax[i - (diff + 1)] = true;
			}
		}
		
		int max = 0;
		for(int i = getMax.length - 1; i >= 0; i--) {
			if(getMax[i]) {
				max = i;			// 가장 뒤에 참의 값을 갖는 인덱스
				break;
			}
		}
		
		return n + max + 1;			// 모든 나무를 심는 날짜 + 그 때 가장 오래걸리는 나무의 일수 + 첫째날
	}
}
