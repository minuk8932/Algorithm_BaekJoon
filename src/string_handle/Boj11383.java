package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11383번: 뚊
 *
 *	@see https://www.acmicpc.net/problem/11383/
 *
 */
public class Boj11383 {
	private static final String AC = "Eyfa";
	private static final String WA = "Not Eyfa";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean isTrue = true;
		
		String[] line = new String[N];
		String[] comp = new String[N];
		
		char[][] w = new char[N][M];
		
		for(int i = 0; i < N; i++) {		// 비교할 문자열 저장, 단 개행이 들어오는 경우 다른 배열로 나누어 담아줌
			line[i] = br.readLine();
			w[i] = line[i].toCharArray();
		}
		
		for(int i = 0; i < N; i++) {
			StringBuilder sb = new StringBuilder();
			comp[i] = br.readLine();			// 비교할 배열을 행에 맞춰 차례로 입력받고
			
			for(int j = 0; j < M; j++) {
				for(int k = 0; k < 2; k++) {		// 처음 받은 문자열을 2개씩 늘려 붙여서 버퍼에 저장해둠
					sb.append(w[i][j]);
				}
			}
			
			if(!sb.toString().equals(comp[i])){		// 버퍼에서 가져온 값이 비교할 문자열과 다르면, 거짓을 저장하고 반복문 바로 종료
				isTrue = false;
				break;
			}
		}		
		
		System.out.println(isTrue ? AC : WA);		// 결과 값 출력
	}
}
