package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 4641번: double
 *
 *	@see https://www.acmicpc.net/problem/4641/
 *
 */
public class Boj4641 {
	private static final String TERMINATE = "-1";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String input = br.readLine();
			if(TERMINATE.equals(input)) break;		// -1이 입력으로 들어오는 경우 종료
			
			StringTokenizer st = new StringTokenizer(input);
			ArrayList<Integer> arr = new ArrayList<>();
			
			while(true) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 0) break;
				
				arr.add(num);		// 숫자 값이 0인 경우 반복문 종료
			}
			
			int cnt = 0;
			int leng = arr.size();
			
			for(int i = 0; i < leng; i++) {
				for(int val: arr) {
					if(arr.get(i) == val * 2) {		// 리스트의 값이 그 외 나머지 숫자 중 하나의 2배일 때
						cnt++;					// 갯수 증가
						break;
					}
				}
			}
			
			sb.append(cnt).append(NEW_LINE);		// 버퍼에 케이스 별 갯수를 담고
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
}
