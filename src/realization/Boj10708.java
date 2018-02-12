package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10708번 : 크리스마스 파티
 *
 *	@see https://www.acmicpc.net/problem/10708
 *
 */
public class Boj10708 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M= Integer.parseInt(br.readLine());
		
		int[] A = new int[M + 1];
		int[] res = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 1; i < M + 1; i++){											// 타겟 설정
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] games = new int[M + 1][N + 1];							// [게임수][인원수]
		for(int i = 1; i < M + 1; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			for(int j = 1; j < N + 1; j++){
				games[i][j] = Integer.parseInt(st.nextToken());
				
				if(A[i] == games[i][j]){										// 타겟을 맞춘 경우
					res[j]++;														// 정답자에게 1점
				}
				
				else{																	// 타겟을 못 맞춘 경우
					res[A[i]]++;													// 해당 타겟에서 1점
				}
			}
		}
		br.close();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < N + 1; i++){
			sb.append(res[i]).append(NEW_LINE);						// 총점을 버퍼에 저장
		}
		
		System.out.println(sb.toString());								// 이후 한번에 값 출력
	}
}
