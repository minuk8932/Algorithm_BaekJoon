package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16166번: 서울의 지하철
 *
 *	@see https://www.acmicpc.net/problem/16166/
 *
 */
public class Boj16166 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] line = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			line[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			
			while(size-- > 0) {
				line[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		int finish = Integer.parseInt(br.readLine());
		System.out.println(transfer(N, line, finish));
	}
	
	private static int transfer(int n, ArrayList<Integer>[] list, int end) {
		int[] lines = new int[n];
		int[] isVisited = new int[n];
		
		Arrays.fill(lines, Integer.MAX_VALUE);
		Arrays.fill(isVisited, Integer.MAX_VALUE);
		
		ArrayList<Integer> startLine = getStartLine(n, list, 0);		// 시작 호선을 모두 받아옴
		
		for(int line: startLine) {
			if(list[line].contains(end)) return 0;		// 시작역과 연결된 종착역이면 바로 종료
			lines[line] = 0;
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 0; i < n; i++) {
			if(lines[i] != 0) continue;			// 시작역이 존재하지 않는 호선은 큐에 담지 않음
			isVisited[i] = 1;
			q.offer(i);
		}
		
		int min = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int station: list[current]) {
				for(int next = 0; next < n; next++) {				// 받아온 역번호를 통해 해당역이 존재하는 호선을 모두 큐에 담음
					if(list[next].contains(station)) {
						if(isVisited[next] != Integer.MAX_VALUE) continue;
						isVisited[next] = isVisited[current] + 1;	// 호선 별로 방문 값ㅇ 초기화
						
						if(list[next].contains(end)) min = Math.min(min, isVisited[next]);		// 도착역에 도달할때 그때의 최소 환승 수 저장
						
						q.offer(next);
					}
				}
			}
		}
		
		return min == Integer.MAX_VALUE ? -1 : min - 1;		// 도달 못하는 경우 -1
	}
	
	private static ArrayList<Integer> getStartLine(int n, ArrayList<Integer>[] list, int node) {
		ArrayList<Integer> arr = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			for(int station: list[i]) {
				if(station == node) arr.add(i);					// 출발역이 존재하는 라인의 번호
			}
		}
		
		return arr;
	}
}
