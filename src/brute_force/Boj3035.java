package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3035번: 스캐너
 *
 *	@see https://www.acmicpc.net/problem/3035/
 *
 */
public class Boj3035 {
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int ZR = Integer.parseInt(st.nextToken());
		int ZC = Integer.parseInt(st.nextToken());
		
		char[][] scan = new char[R][C];
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < C; j++) {
				scan[i][j] = line.charAt(j);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < R; i++) {
			for(int x = 0; x < ZR; x++) {			// 반복할 세로의 갯수
				for(int j = 0; j < C; j++) {
					for(int y = 0; y < ZC; y++) {		// 반복할 원소의 갯수 x -> x * ZC개
						sb.append(scan[i][j]);
					}
				}
				
				sb.append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
}
