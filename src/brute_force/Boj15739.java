package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15739번: 매직스퀘어
 *
 *	@see https://www.acmicpc.net/problem/15739/
 *
 */
public class Boj15739 {
	private static final String CORRECT = "TRUE";
	private static final String DISCORRECT = "FALSE";
	private static final int INF = 10_001;
	
	private static int N = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		boolean[] isVisited = new boolean[INF];		// 중복 숫자 체크 배열
		int[][] arr = new int[N + 1][N + 1];
		for(int i = 1; i < N + 1; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j < N + 1; j++){
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if(isVisited[arr[i][j]]){						// 이미 입력으로 들어왔던 숫자라면
					System.out.println(DISCORRECT);		// FALSE 출력 후 프로그램 종료
					return;
				}
				
				isVisited[arr[i][j]] = true;		// 현재 들어온 입력을 들어왔다는 표시
			}
		}
		
		System.out.println(isCorrect(arr) ? CORRECT : DISCORRECT);	// isCorrect 메소드가 참이면 TRUE 거짓이면 FALSE 출력
	}
	
	/**
	 * 
	 * @param arr: 해당 배열의 매직스퀘어 여부를 확인해야함
	 * @return: 매직스퀘어인지 아닌지 반환해줌
	 */
	private static boolean isCorrect(int[][] arr){
		boolean isMagicSquare = true;
		int totalSum = N * (N * N + 1) / 2;
		int[] rowSum = new int[N + 1];
		int[] colSum = new int[N + 1];
		int ltSum = 0;
		int rtSum = 0;
		
		for(int i = 1; i < N + 1; i++){
			ltSum += arr[i][i];					// 좌측 상단에서부터 대각선합
			rtSum += arr[N + 1 - i][i];		// 우측 상단에서부터 대각선합
			
			for(int j = 1; j < N + 1; j++){
				rowSum[i] += arr[i][j];		// 각 행마다의 합
				colSum[i] += arr[j][i];			// 각 열마다의 합
			}
		}
		
		for(int i = 1; i < N + 1; i++){
			// 총 합들을 각각 매직스퀘어 연산과 비교해서 하나라도 같지않다면, 매직스퀘어 변수를 거짓으로 바꿔줌
			if(totalSum != rowSum[i] || totalSum != colSum[i] || totalSum != ltSum || totalSum != rtSum){
				isMagicSquare = false;
			}
		}
		
		
		return isMagicSquare;		// 결과 반환
	}
}
