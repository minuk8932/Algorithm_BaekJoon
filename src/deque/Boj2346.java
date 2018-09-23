package deque;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2346번: 풍선 터뜨리기
 *
 *	@see https://www.acmicpc.net/problem/2346/
 *
 */
public class Boj2346 {
	private static final char SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<Balloon> deq = new LinkedList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			deq.offer(new Balloon(i, Integer.parseInt(st.nextToken())));
		}
		
		StringBuilder sb = new StringBuilder();
		Balloon start = deq.poll();						// 가장 첫번째 풍선을 터트리고 해당 번호를 버퍼에 담음
		boolean isMinus = start.seq > 0 ? false : true;
		sb.append(start.idx + 1).append(SPACE);
		
		while(!deq.isEmpty()) {
			int loop = !isMinus ? start.seq - 1: Math.abs(start.seq) - 1;		// 풍선 내의 값이 양수인지 음수인지에 따라 반복 횟수 값 설정
			
			while(loop-- > 0) {				
				Balloon tmp = null;
				
				if(!isMinus) {				// 반복문을 돌면서 풍선 내의 값에 따라 앞뒤로 값들을 빼고 넣고
					tmp = deq.pollFirst();
					deq.offerLast(tmp);
				}
				else {
					tmp = deq.pollLast();
					deq.offerFirst(tmp);
				}
			}
			
			start = !isMinus ? deq.pollFirst() : deq.pollLast();	// 음수면 뒤에서 양수면 앞에서 해당 순번의 풍선을 터트림
			isMinus = start.seq > 0 ? false : true;
			sb.append(start.idx + 1).append(SPACE);				// 풍선의 인덱스 값을 버퍼에 담아줌
		}
		
		System.out.println(sb.toString());				// 결과 값 한번에 출력
	}
	
	/**
	 * 풍선 정보 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Balloon{
		int idx;
		int seq;
		
		public Balloon(int idx, int seq) {
			this.idx = idx;
			this.seq = seq;
		}
	}
}
