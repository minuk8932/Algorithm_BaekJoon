package q;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1966번: 프린터 큐
 *
 *	@see https://www.acmicpc.net/problem/1966/
 *
 */
public class Boj1966 {
	private static final String NEW_LINE ="\n";
	
	public static void main(String[] args) throws Exception{
		//  버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			Queue<Document> q = new LinkedList<>();
			PriorityQueue<PriorNumber> pq = new PriorityQueue<>();
			
			int[] arr = new int[N];
			
			st = new StringTokenizer(br.readLine());			
			for(int i = 0; i < N; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				
				arr[i] = tmp;
				q.offer(new Document(i, tmp));
				pq.offer(new PriorNumber(tmp));			// 구하려는 값보다 큰 경우를 배제하기 위함
			}

			int res = 1;
			
			while(!pq.isEmpty()) {
				Document doc = q.peek();			// 각각 가장 앞의 값을 담고
				PriorNumber pn = pq.peek();

				if(doc.num == pn.num) {				// 그 때의 우선순위 값이 같을때
					if(doc.idx == M) break;			// 동시에 인덱스도 같다면 정답을 찾았으므로 반복문 종료
					
					pq.poll();					// 우선순위 값만 같을때 해당값을 빼줌 (큰 값부터 프린팅)
					res++;						// 프린트 횟수 +1
				}
				else {							// 값이 아예 맞지 않는 경우 큐의 가장 뒤로 빼줌
					q.offer(doc);
				}
				
				q.poll();						// 뒤로 빼준 값을 큐에서 제거
			}
			
			sb.append(res).append(NEW_LINE);	// 결과를 위한 프린트 횟수를 버퍼에 담음
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 우선순위 값 이너 클래스
	 *
	 */
	private static class PriorNumber implements Comparable<PriorNumber>{
		int num;
		
		public PriorNumber(int num) {
			this.num = num;
		}

		@Override
		public int compareTo(PriorNumber pn) {
			return this.num > pn.num ? -1 : 1;
		}
	}
	
	/**
	 * 문서 순서 및 우선순위 이너 클래스
	 * 
	 */
	private static class Document{
		int idx;
		int num;
		
		public Document(int idx, int num) {
			this.idx = idx;
			this.num = num;
		}
	}
}
