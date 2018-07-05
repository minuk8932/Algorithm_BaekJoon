package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1138번: 한 줄로 서기
 *
 *	@see https://www.acmicpc.net/problem/1138/
 *
 */
public class Boj1138 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] p = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<Integer> seq = new ArrayList<>();
		seq.add(N - 1);			// 키가 제일 큰 사람부터 줄을 세움
		
		int size = 1;
		
		for(int i = N - 2; i >= 0; i--) {
			if(size == p[i]) {		// 만약 현재 줄을 서있는 인원과 줄을 설 p보다 키가 큰 사람의 수가 같다면 가장 뒤에 줄을 세움
				seq.add(i);
			}
			else {					// 그 외, 키가 큰 사람 수 바로 뒤에 해당하는 인덱스에 그 인원의 줄을 세움
				seq.add(p[i], i);
			}
			
			size++;				// 줄을 서 있는 사람 수 +1
		}
		
		for(int i = 0; i < N; i++) {
			sb.append(seq.get(i) + 1).append(SPACE);		// 각 값을 버퍼에 담은 후
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
