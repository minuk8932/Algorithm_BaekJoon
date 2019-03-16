package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * 
 * 	@author minchoba
 *	백준 2661번: 좋은 수열
 *
 *	@see https://www.acmicpc.net/problem/2661/
 *
 */
public class Boj2661 {
	private static LinkedList<Character> res = new LinkedList<>();
	private static int N = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		res.add('1');		// 가장 앞의 수열을 뽑아야 하므로 우선적으로 1을 넣어줌
		backTracking(1);		// 백트래킹 메소드 실행
	
		for(int i = 0; i < N; i++) sb.append(res.get(i));		// 연결리스트의 값들을 버퍼에 담고
		System.out.println(sb.toString());					// 결과 값 한번에 출력
	}
	
	/**
	 * 백트래킹 메소드
	 * 
	 */
	private static void backTracking(int start) {
		if(res.size() == N + 1) return;			// N번째 자리수 까지 중복을 피하기 위해 N + 1에서 메소드 종료
		
		for(int next = 1; next < 4; next++) {		// 다음에 들어갈 수 있는 수 1~3 중
			if(start == next) continue;				// 이전에 들어온 것과 같은 것은 넘김
			int size = res.size();
			
			boolean isSame = comp(size);			// comp 메소드를 통해 각 인접한 자리별로 숫자의 유사성을 비교
			if(isSame) return;						// 같은자리가 있다면 재귀함수 한개 종료
			
			res.add((char) (next + '0'));			// 같은자리가 없다면 다음에 들어갈 숫자를 리스트에 담고
			
			backTracking(next);						// 백트래킹 메소드 재귀 호출
			if(res.size() <= N) res.removeLast();		// 리스트 사이즈가 N보다 작거나 같을때, 같은자리가 존재하면 맨뒤의 한자리 제거
		}
	}
	
	/**
	 * 비교 메소드
	 * 
	 */
	private static boolean comp(int leng) {
		boolean isCorrect = false;
		
		for(int i = 1; i < leng / 2 + 1; i++) {
			String tmp1 = "";
			String tmp2 = "";
			
			int loop1 = leng - (i * 1);		// 인접한 자리들을 비교하기위해 변수 설정, 
			int loop2 = leng - (i * 2);			// ex) 123456 일때 5/6, 34/56, 123/456 이러한 방식으로 비교
			
			for(int x = loop1; x < leng; x++) {
				tmp1 += res.get(x);
			}
			
			for(int x = loop2; x < loop1; x++) {
				tmp2 += res.get(x);
			}
			
			if(tmp1.equals(tmp2)) isCorrect = true;		// 두 인접한 자리가 한번이라도 같은 경우가 있다면 참으로 변경
		}
		
		return isCorrect;
	}
}
