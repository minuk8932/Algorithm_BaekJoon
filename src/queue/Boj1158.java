package queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1158번: 조세퍼스 문제
 *
 *	@see https://www.acmicpc.net/problem/1158/
 *
 */
public class Boj1158 {
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
		for(int i = 1; i < N + 1; i++) {			// 값 초기화
			arr.add(i);
		}
		
		int idx = 0;
		
		StringBuilder sb = new StringBuilder();		
		sb.append(PRE);
		
		while(!arr.isEmpty()) {	// 리스트가 빌때까지
			idx += M;				// 배열 크기를 (M - 1)만큼 키워가며, 리스트의 시작은 0부터 이므로
			int size = arr.size();	// 그때의 배열 사이즈를 변수로 받아오며
			
			if(idx >= size) {		// 사이즈보다 인덱스값이 커지는 순간 나머지 연산, 인덱스 범위 벗어나는 것을 방지
				idx %= size;
			}
			
			sb.append(arr.remove(idx)).append(SPACE);	// idx에 해당하는 값들을 제거하며 순차적으로 버퍼에 담아준 후, 즉 인덱스를 하나씩 당겨옴
		}
		
		System.out.println(sb.substring(0, sb.length() - 2) + POST);	// 결과값 한번에 출력
	}
}
