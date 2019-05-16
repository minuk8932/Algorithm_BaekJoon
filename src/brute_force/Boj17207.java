package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17207번: 돌려 막기
 *
 *	@see https://www.acmicpc.net/problem/17207/
 *
 */
public class Boj17207 {
	private static final String[] MEMBER = {"Inseo", "Junsuk", "Jungwoo", "Jinwoo", "Youngki"};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] A = new int[5][5];
		for(int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] B = new int[5][5];
		for(int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				B[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(getTarget(A, B));
	}
	
	private static String getTarget(int[][] a, int[][] b) {
		int[] work = new int[5];
		
		for(int x = 0; x < 5; x++) {					// x번째 사람의 y번째 예상 일량: (x번 사람의 i번째 예상 일량 * i번째 사람의 y번째 예상 일량), 0 <= i < 5
			int sum = 0;
			
			for(int y = 0; y < 5; y++) {
				for(int i = 0; i < 5; i++) {
					sum += (a[x][i] * b[i][y]);
				}
			}
			
			work[x] += sum;
		}
		
		int min = Integer.MAX_VALUE;
		String result = "";
		
		for(int i = 0; i < work.length; i++) {
			if(work[i] <= min) {
				min = work[i];
				result = MEMBER[i];				// 이름 역순 제일 앞의 멤버
			}
		}
		
		return result;
	}
}
