package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1547번: 공
 *
 *	@see https://www.acmicpc.net/problem/1547/
 *
 */
public class Boj1547 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		int[] cup = {1, 2, 3};
		
		while(M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
GO:			for(int i = 0; i < cup.length; i++) {
				if(cup[i] == a) {
					
					for(int j = 0; j < cup.length; j++) {
						if(cup[j] == b) {					// 컵 위치 변경
							cup[i] = b;
							cup[j] = a;
							
							break GO;		// 위치 변경 반복문 바로 종료
						}
					}
				}
			}
		}
		
		System.out.println(cup[0]);		// 공이 들어있는 컵의 번호 출력
	}
}
