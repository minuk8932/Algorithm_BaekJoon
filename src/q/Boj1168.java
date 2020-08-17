package q;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1168번: 조세퍼스 문제 2
 *
 *	@see https://www.acmicpc.net/problem/1168/
 *
 */
public class Boj1168 {
	private static final String PRE = "<";
	private static final String SPACE = ", ";
	private static final String POST = ">";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken()) - 1;
		
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i = 1; i < N + 1; i++) {
			arr.add(i);
		}
		
		int idx = 0;
		
		StringBuilder sb = new StringBuilder();		
		sb.append(PRE);
		
		while(!arr.isEmpty()) {
			idx += M;
			int size = arr.size();
			
			if(idx >= size) {		// 사이즈보다 인덱스값이 커지는 순간 나머지 연산, 인덱스 범위 벗어나는 것을 방지
				idx %= size;
			}
			
			sb.append(arr.remove(idx)).append(SPACE); // 결과를 버퍼에 담음
		}
		
		System.out.println(sb.substring(0, sb.length() - 2) + POST);	// 결과 값 한번에 출력
	}
}
