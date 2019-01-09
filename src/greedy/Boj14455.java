package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14455번: Don't be Last!
 *
 *	@see https://www.acmicpc.net/problem/14455/
 *
 */
public class Boj14455 {
	private static final String DRAW = "Tie";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int count = 0;
		
		HashMap<String, Integer> cows = new HashMap<>();
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int value = Integer.parseInt(st.nextToken());
			
			if(cows.containsKey(name)) {
				cows.put(name, cows.get(name) + value);
			}
			else {
				cows.put(name, value);
				count++;
			}
		}
		
		System.out.println(getSecond(N, cows, count));
	}
	
	private static class Table implements Comparable<Table>{
		String name;
		int score;
		
		public Table(String name, int score) {
			this.name = name;
			this.score = score;
		}

		@Override
		public int compareTo(Table t) {
			return this.score < t.score ? -1: 1;
		}
	}
	
	private static String getSecond(int n, HashMap<String, Integer> hm, int total) {
		PriorityQueue<Table> cows = new PriorityQueue<>();
		int min = Integer.MAX_VALUE;
		
		for(String str: hm.keySet()) {
			int num = hm.get(str);
			cows.offer(new Table(str, num));
			
			if(min > num) min = num;
		}
		
		Table result = getCow(cows, min, total);
		
		return result.score != 1 ? DRAW: result.name;
	}
	
	private static Table getCow(PriorityQueue<Table> pq, int min, int part) {
		boolean isSecond = part == 7 ? false : true;		// 소들의 수가 7보다 작은경우 true, 즉 바로 뒤에서 두번째 수를 뽑을 수 있음
		String res = "";
		int count = 0;
		
		while(!pq.isEmpty()) {
			Table cow = pq.poll();
			
			if(!isSecond) {					// 아직 뒤에서 두번째 소가 나오지 않은 경우
				if(min != cow.score) {		// 꼴찌 소와 현재 소의 점수가 다르면 -> 현재 소가 두번째 소
					isSecond = true;
					min = cow.score;		// 두번째 소가 몇마린지 뽑기위해 최솟값을 해당 소의 점수로 초기화
				}
			}
			
			if(isSecond) {
				if(min == cow.score) {
					res = cow.name;			// 두번째 소의 이름과 해당하는 소의 마릿수를 계산, 여러마리인 경우 이름은 의미없으니 신경쓰지 않는다.
					count++;
				}
			}
		}
		
		return new Table(res, count);		
	}
}
