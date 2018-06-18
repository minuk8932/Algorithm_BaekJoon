package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1149번: 수리공 항승
 *
 *	@see https://www.acmicpc.net/problem/1149/
 *
 */
public class Boj1149 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken()) - 1;	// 양쪽 0.5 만큼 제외
		
		int tapes = 0;
		int[] leak = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			leak[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(leak);		// 누수가 발생한 위치를 오름차순 정렬

		for(int i = 0; i < N; i++) {
			int tmp=0;
			tapes++;
			
			for(int j = i + 1; j < N; j++) {
				if(leak[i] + L >= leak[j]) tmp++;	// leak[i] + L >= leak[j]이면 범위 L로 커버 가능
				else break;
			}
			i += tmp;		// L로 커버 가능한 누수의 갯수를 구하여 반복횟수에서 제외시킴
		}
		
		System.out.println(tapes);		// 필요한 테잎 수 출력
	}
}
