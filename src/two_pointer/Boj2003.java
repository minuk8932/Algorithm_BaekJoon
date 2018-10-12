package two_pointer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * 	@author minchoba
 *	백준 2003번: 수들의 합 2
 *
 *	@see https://www.acmicpc.net/problem/2003/
 *
 */
public class Boj2003 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] seq = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) seq[i] = Integer.parseInt(st.nextToken());
		
		Pointer ptr = new Pointer(0, 0);		// 2 포인터 설정
		int res = 0;
		int sum = 0;
		
		while(true) {            
			if(sum >= M){						// 총합이 M보다 크거나 같은 경우 시작 포인터를 뒤로 밀어줌
                sum -= seq[ptr.start++];
            }
			else {
                if(N == ptr.end) break;			// 끝 포인터가 N에 도달 한 경우 반복문 종료
                sum += seq[ptr.end++];			// 아니면 끝 포인터를 뒤로 밀어줌
            }
			
			if(sum == M) res++;					// 총합과 M이 같은경우
		}
		
		System.out.println(res);				// 결과 값 출력
	}
	
	/**
	 * 투 포인터 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Pointer{
		int start;
		int end;
		
		public Pointer(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}
