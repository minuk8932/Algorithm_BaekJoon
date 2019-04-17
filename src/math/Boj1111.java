package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1111번: IQ Test
 *
 *	@see https://www.acmicpc.net/problem/1111/
 *
 */
public class Boj1111 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] seq = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getFormula(N, seq));
	}
	
	private static String getFormula(int n, int[] arr) {
		if(n == 1) return "A";
		
		else if(n == 2) {
			if(arr[0] == arr[1]) return arr[0] + "";
			else return "A";
		}
		else {
			int a = 0;						// 해당 수열 함수로 정의: y = ax + b;
			int x = arr[1] - arr[0];		// 위의 식을 만족하기 위함: 모든 점이 해당 함수에 직선에 포함되는 점
			int y = arr[2] - arr[1];
			
			if(x != 0) a = y / x;
			int b = arr[1] - a * arr[0];
			
			for(int i = 1; i < n; i++) {
				if(arr[i] != arr[i - 1] * a + b) return "B";		// 구한 수식에 따라 연산시 다른 값이 나오는 경우
			}
			
			return (arr[n - 1] * a + b) + "";
		}
	}
}
