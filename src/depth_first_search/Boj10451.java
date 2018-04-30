package depth_first_search;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/**
 * 
 * 	@author minchoba
 *	백준 10451번: 순열 사이클
 *
 *	@see https://www.acmicpc.net/problem/14051/
 *
 */
public class Boj10451 {
	private static final String END_LINE = "\n";
	
	private static int n = 0;
	private static int res = 0;
	private static ArrayList<Integer>[] term = null;
	private static boolean[] isVisited = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			n = Integer.parseInt(br.readLine());
			term = new ArrayList[n + 1];
			isVisited = new boolean[n + 1];
			
			for(int i = 0; i < n + 1; i++){
				term[i] = new ArrayList<>();
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i < n + 1; i++){
				term[i].add(Integer.parseInt(st.nextToken()));
			}
			
			res = 0;
			
			for(int i = 1; i < n + 1; i++){
				if(!isVisited[i]){					// 이미 체크된 수인지 확인
					isCycle(i);					// 순열의 사이클을 찾는다
					res++;		//	연산(1) : 재귀가 끝나고 나왔다 -> 사이클을 찾아냈다!
				}
			}
			
			sb.append(res).append(END_LINE);		// 사이클 갯수를 테스트케이스마다 버퍼에 담아주고
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	/**
	 * 깊이 우선 탐색 알고리즘
	 * @param current: 현재 탐색하는 수
	 */
	private static void isCycle(int current){
		if(isVisited[current]) 	return;		// 현재 탐색하는 수가 이미 탐색 했던 것이면 종료
		
		isVisited[current] = true;			// 탐색시작
		
		for(int next : term[current]){		// 인접리스트를 검색하면서
			if(!isVisited[next]){			// 인접리스트 내의 데이터가 방문하지 않았다면,
				isCycle(next);			// 깊이우선탐색 재귀 호출, next가 current가 되면서 조건문을 검색하고, 만약에 탐색 되었던것이라면 이는 사이클 이므로, 연산 (1)에서 +1해줌
			}
		}
	}
}
