package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5800번 : 성적 통계
 *
 *	@see https://www.acmicpc.net/problem/5800/
 *
 */
public class Boj5800 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String CLASS = "Class";
	private static final String MAX = "Max ";
	private static final String MIN = ", Min ";
	private static final String GAP = ", Largest gap ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		int loop = 1;
		
		while(loop <= K){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			
			int N = Integer.parseInt(st.nextToken());				// 학생수
			int[] score = new int[N];										// 학생별 점수
			int diff = 0, maxDiff = 0;										// 차이값과 최대 차이값
			
			for(int i = 0; i < N; i++){
				score[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(score);											// 오름차순 정렬
			
			for(int i = 1; i < N; i++){
				diff = score[i] - score[i - 1];							// 각 점수별 차이 계산
				maxDiff = Math.max(maxDiff, diff);					// 차이 중 가장 큰 차이값
			}
			
			// Class 별 최대, 최소, 최대 점수차이를 양식에 맞춰 버퍼에 담아줌
			sb.append(CLASS).append(SPACE).append(loop++).append(NEW_LINE);
			sb.append(MAX).append(score[N - 1]).append(MIN).append(score[0]).append(GAP).append(maxDiff).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());							// 결과 값 한번에 출력
	}
}
