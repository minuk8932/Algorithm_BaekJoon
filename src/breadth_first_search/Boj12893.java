package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 12893번: 적의 적
 *
 *	@see https://www.acmicpc.net/problem/12893/
 *
 */
public class Boj12893{	
    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());

    	ArrayList<Integer>[] relate = new ArrayList[N];
    	for(int i = 0; i < N; i++) {
    		relate[i] = new ArrayList<>();
    	}

    	while(M-- > 0){
    		st = new StringTokenizer(br.readLine());
    		int A = Integer.parseInt(st.nextToken()) - 1;
    		int B = Integer.parseInt(st.nextToken()) - 1;
    		
    		relate[A].add(B);
    		relate[B].add(A);
    	}
    	
    	System.out.println(search(N, relate));
    }
    
    private static int search(int n, ArrayList<Integer>[] list) {
    	int[] visit = new int[n];
    	
    	for(int start = 0; start < n; start++) {
    		if(visit[start] != 0) continue;
    		
    		Queue<Integer> q = new LinkedList<>();
    		q.offer(start);
    		
    		visit[start] = 1;										// 1, -1로 구분
    		while(!q.isEmpty()) {
    			int current = q.poll();
    			
    			for(int next: list[current]) {
    				if(visit[next] == visit[current]) return 0;		// 적인데 같은 값인 경우
    				
    				if(visit[next] == 0) {
    					visit[next] = -visit[current];				// 적 판별
    					q.offer(next);
    				}
    			}
    		}
    	}    	
    	
    	return 1;
    }
}