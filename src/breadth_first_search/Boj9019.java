package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9019번: DSLR
 *
 *	@see https://www.acmicpc.net/problem/9019/
 *
 */
public class Boj9019 {
	private static final char[] DSLR_CHART = {' ', 'D', 'S', 'L', 'R'};
	private static final String NEW_LINE = "\n";
	private static final int INF = 10_000;
	
	private static class Pair{
		int cost;
		long inst;
		
		public Pair(int cost, long inst) {
			this.cost = cost;
			this.inst = inst;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int reg1 = Integer.parseInt(st.nextToken());
			int reg2 = Integer.parseInt(st.nextToken());
			
			long result = bfs(reg1, reg2);
			
			sb.append(getCommand(result)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static long bfs(int value, int target) {
		Pair[] isVisited = new Pair[INF + 1];
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(value);
		
		isVisited[value] = new Pair(1, 0);
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int i = 1; i < 5; i++) {
				int next = getDSLR(current, i);			// 다음 DSLR 값을 받아옴
				if(isVisited[next] != null) continue;
				isVisited[next] = new Pair(isVisited[current].cost + 1, isVisited[current].inst * 10 + i);	// 1: D, 2: S, 3: L, 4: R
				
				if(next == target) break;
				
				q.offer(next);
			}
		}
		
		return isVisited[target].inst;
	}
	
	private static int getDSLR(int num, int count) {
		int tmp = 0;
		
		switch (count) {
		case 1:
			tmp = getDouble(num);		// 2배 메소드
			break;
		case 2:
			tmp = getSubtract(num);		// -1 메소드
			break;
		case 3:
			tmp = getPushLeft(num);		// 왼쪽으로 밀기 ex) 123 -> 1234, 4321 -> 3214
			break;
		case 4:
			tmp = getPushRight(num);	// 오른쪽으로 밀기 ex) 1 -> 1000, 203 -> 3020
			break;
		}
		
		return tmp;
	}
	
	private static int getDouble(int num) {
		return (num * 2) % INF;
	}
	
	private static int getSubtract(int num) {
		return num == 0 ? INF - 1: num - 1;
	}
	
	private static int getPushLeft(int num) {		
		num *= 10;
		int head = num / INF;
		return num = num % INF + head;
	}
	
	private static int getPushRight(int num) {
		int tail = num % 10;
		num /= 10;
		
		return num + tail * (INF / 10);
	}
	
	private static StringBuilder getCommand(long res) {
		StringBuilder maker = new StringBuilder();
		char[] word = String.valueOf(res).toCharArray();
		
		for(int i = 0; i < word.length; i++) {		// 상수 배열에서 해당하는 명령어를 가져와서 빌더에 추가
			maker.append(DSLR_CHART[word[i] - '0']);
		}
		
		return maker;
	}
}
