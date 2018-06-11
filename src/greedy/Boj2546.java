package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2546번: 경제학과 정원영
 *
 *	@see https://www.acmicpc.net/problem/2546
 *
 *	ref) 현재 입력데이터 문제인지 BufferedReader로 받으면 RTE 발생. -> Scanner로 통과 시킬 것
 */
public class Boj2546 {
	private static final int INF = 200_001;
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			String waste = br.readLine();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			double N = Double.parseDouble(st.nextToken());
			double M = Double.parseDouble(st.nextToken());
			
			double avgC = 0, avgE = 0;
			int[] cLang = new int[INF];				// C언어 수강생
			int[] econo = new int[INF];				// 경제학 수강생
			boolean[] isTrueC = new boolean[INF];
			
			st = new StringTokenizer(br.readLine());
			
			// C 언어
			for(int i = 0; i < N; i++) {
				double idx = Double.parseDouble(st.nextToken());
		
				cLang[(int) idx]++; 		// 해당 아이큐를 가진 수강생의 수
				avgC += idx;			// 아이큐의 평균을 구하기 위한 합산
			}
			avgC /= N;			// C 언어 수강생의 아이큐 평균

			st = new StringTokenizer(br.readLine());
			
			// 경제학
			for(int i = 0; i < M; i++) {
				double idx = Double.parseDouble(st.nextToken());
				
				econo[(int) idx]++; 
				avgE += idx;
			}
			double tmp = avgE / M;	// 경제학 수강생의 아이큐 평균
			
			for(int i = 1; i < INF; i++) {	// C 언어 수강생 중 아이큐가 평균보다 낮은 사람의 아이큐를 뽑아냄
				if(cLang[i] != 0) {
					if(i < avgC) {
						isTrueC[i] = true;
					}
				}
			}

			for(int i = 1; i < INF; i++) {		// 경제학 아이큐 평균에 C 언어 평균이하의 아이큐를 더해 다시 평균을 구함
				if(isTrueC[i]) {
					double tmpAvg = (avgE + i) / (M + 1.0);
					
					if(tmp >= tmpAvg) {		// 이때 tmp >= tmpAvg 경우 위에서 구한 C 언어의 평균이하 아이큐 수강의 값을 거짓으로 변경
						isTrueC[i] = false;
					}
				}
			}
			
			int res = 0;
			
			for(int i = 1; i < INF; i++) {		// 진리 배열에서 최종 참 값을 가진 인덱스 내의 값 만큼 결과 값에 더해줌
				if(isTrueC[i]) {
					res += cLang[i];
				}
			}
			
			sb.append(res).append(NEW_LINE);	// 결과를 버퍼에 담은 후
		}
		
		System.out.println(sb.toString());		// 한번에 결과값 출력
	}
}
