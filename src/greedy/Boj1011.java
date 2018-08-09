package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1011번: Fly me to the centauri
 *
 *	@see https://www.acmicpc.net/problem/1011/
 *
 */
public class Boj1011 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int res = (int) Math.ceil((Math.sqrt(y - x)) * 2 - 1); // 거리에 해당하는 값의 제곱근의 두배 - 1, 횟수의 규칙성을 찾고 거기서 해당 제곱수가 마지막에 나오므로 올림
			
			sb.append(res).append(NEW_LINE);		// 버퍼에 값을 저장 후
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
