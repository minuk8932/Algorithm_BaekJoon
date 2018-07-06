package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1946번: 신입사원
 *
 *	@see https://www.acmicpc.net/problme/1946/
 *
 */
public class Boj1946 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			
			Employee[] e = new Employee[N];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				e[i] = new Employee(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			Arrays.sort(e);	// 신입 사원의 순위를 서류 점수에 따라 정렬
			
			int recruit = 1;
			int comp = e[0].interview;
			
			for(int i = 1; i < N; i++) {
				if(e[i].interview < comp) {		// 서류 성적순으로 검사하면서, 면접 성적 순위가 더 높은 경우 신입사원 +1
					recruit++;
					comp = e[i].interview;
				}
			}
			
			sb.append(recruit).append(NEW_LINE);		// 신입 사원의 수를 버퍼에 담음
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 성적 순위 저장 클래스
	 * @author minchoba
	 *
	 */
	private static class Employee implements Comparable<Employee>{
		int paper;
		int interview;
		
		public Employee(int paper, int interview){	// 서류, 면접
			this.paper = paper;
			this.interview = interview;
		}

		@Override
		public int compareTo(Employee e) {
			if(this.paper < e.paper) {			// 서류 성적 순으로 정렬
				return -1;
			}
			else if(this.paper > e.paper) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}
}
