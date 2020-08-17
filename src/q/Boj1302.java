package q;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 1302번: 베스트 셀러
 *
 *	@see https://www.acmicpc.net/problem/1302/
 *
 */
public class Boj1302 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		HashMap<String, Integer> hm = new HashMap<>();
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			String tmp = br.readLine();
			
			if(hm.containsKey(tmp)) {			// 맵에 이미 저장된 책이라면 value+1
				hm.put(tmp, hm.get(tmp) + 1);
			}
			else {
				hm.put(tmp, 1);
			}
			
			if(hm.get(tmp) > max) max = hm.get(tmp);		// 가장 많이 등장한 책의 수를 저장
		}
		
		PriorityQueue<Book> pq = new PriorityQueue<>();
		for(String key: hm.keySet()) {						// 최대값을 가지는 책의 제목을 우선순위 큐에 담고
			if(hm.get(key) == max) pq.offer(new Book(key));
		}
		
		System.out.println(pq.poll().title);				// 그 중 사전순으로 가장 앞서는 제목을 출력
	}
	
	/**
	 * 책 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Book implements Comparable<Book>{
		String title;
		
		public Book(String title) {
			this.title = title;
		}

		@Override
		public int compareTo(Book b) {
			return this.title.compareTo(b.title);
		}
	}
}
