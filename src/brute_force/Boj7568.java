package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 7568번 : 덩치
 *
 *	@see https://www.acmicpc.net/problem/7568/
 *
 */
public class Boj7568 {
	private static final String SPACE = " ";
	
	private static int N = 0;
	private static int[] rank = null;
	private static Frame[] f = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		f = new Frame[N];
		rank = new int[N];
		
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);										// 덩치 이너 클래스에 몸무게와 키를 차례로 담아줌
			f[i] = new Frame(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		getRank();											// 순위를 지정하는 함수 실행
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++){
			sb.append(rank[i]).append(SPACE);	// 각 인원 별 순위를 버퍼에 담고
		}
		
		System.out.println(sb.toString());		// 한번에 결과 출력
	}
	
	/**
	 * 
	 * @author minchoba
	 *	덩치 이너 클래스
	 */
	private static class Frame{
		int weight;
		int height;
		
		public Frame(int weight, int height){
			this.weight = weight;
			this.height = height;
		}
	}
	
	private static void getRank(){			// 순위 지정 메소드
		for(int i = 0; i < N; i++){
			int rankCnt = 1;						// 1등의 순위: 1
			
			for(int j = 0; j < N; j++){
				if(f[i].weight < f[j].weight && f[i].height < f[j].height){	// 반복문을 돌면서, 몸무게와 키가 모두 작은 i번째가 존재하면 랭크 순위를 +1 증가
					rankCnt++;
				}
				rank[i] = rankCnt;				// 해당 i번째의 순위를 배열에 담아줌
			}			
		}
	}
}
