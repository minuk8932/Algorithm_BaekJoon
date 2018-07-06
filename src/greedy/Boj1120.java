package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1120번: 문자열
 *
 *	@see https://www.acmicpc.net/problem/1120/
 *
 */
public class Boj1120 {
	private static final int INF = 100;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		char[] a = st.nextToken().toCharArray();
		char[] b = st.nextToken().toCharArray();
		
		int diff = b.length - a.length + 1;		// 길이의 차를 구하고
		int min = INF;
		
		for(int i = 0; i < diff; i++) {		// 짧은 문자열의 인덱스를 하나씩 뒤로 미루
			int cnt = 0;
			
			for(int j = 0; j < a.length; j++) {		// 차이의 수를 계산
				if(b[i + j] != a[j]) cnt++;
			}
			
			min = Math.min(min, cnt);		// 각 미룬 인덱스 계산마다 나온 차이 중 최솟값을 저장
		}
		
		System.out.println(min);		// 결과값 한번에 출력
	}
}
