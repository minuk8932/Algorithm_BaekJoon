package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10865번: 친구 친구
 *
 *	@see https://www.acmicpc.net/problem/10865/
 *
 */
public class Boj10865 {
	private static final String SPACE = " ";
	private static final String END_LINE = "\n";
	
	private static int N = 0;
	private static int M = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] map = new int[N + 1];
		
		
		for(int i = 0; i < M; i++){												// 친구를 가진 내역이 나오는대로 친구의 수 +1;
			st = new StringTokenizer(br.readLine(), SPACE);
			map[Integer.parseInt(st.nextToken())]++;
			map[Integer.parseInt(st.nextToken())]++;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < N + 1; i++){							// 버퍼에 친구의 수를 담아줌
			sb.append(map[i]).append(END_LINE);
		}
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
}
