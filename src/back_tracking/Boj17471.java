package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17471번: 게리맨더링
 *
 *	@see https://www.acmicpc.net/problem/17471/
 *
 */
public class Boj17471 {
	private static int size, part;
	private static int min = Integer.MAX_VALUE;
	
	private static int [] p;
	private static int [][] linked;
	
	private static boolean [] check, visit;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        p = new int [N];
        linked = new int [N][N];
        
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
 
        for(int i = 0; i < N; i++) {  
            p[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i = 0; i < N; i++) { 
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            
            while(count-- > 0) {
                int adj = Integer.parseInt(st.nextToken()) - 1;
                linked[i][adj] = 1;  								// check adj
            }
        }
        
        for(int i = 0; i < N / 2 + 1; i++) { 						// set size
            size = i;
            check = new boolean[N];
            
            backTracking(N, 0, 0);
        }
        
        System.out.println(min == Integer.MAX_VALUE ? -1: min);
    }
    
    private static void backTracking(int n, int current, int cnt) {
        if(cnt == size) {
            int[] tmp = new int[n];
            
            for(int i = 0; i < n; i++) {
                if(check[i] == true) tmp[i] = 1;			// divide zero or one
            }
            
            min = Math.min(min, confirm(n, tmp));			// check is adjacent
            return;
        }
        
        for(int i = current; i < n; i++) {
            check[i]=true;
            
            backTracking(n, i + 1, cnt + 1);
            check[i]=false;
        }
    }
    
    private static int confirm(int n, int[] arr) {
        visit = new boolean[n];
        int p1 = gerryMandering(n, arr, 1);
        int p2 = gerryMandering(n, arr, 0);
        
        for(int i = 0; i < n; i++) {
            if(!visit[i]) return Integer.MAX_VALUE;
        }
        
        return Math.abs(p2 - p1);					// get diff
    }
    
    private static int gerryMandering(int n, int[] arr, int target) {
    	int p = 0;
    	
    	for(int i = 0; i < n; i++) {
            if(arr[i] != target || visit[i]) continue;
            part = 0;
            set(n, i, arr);
            
            p = part;
            break;
        }
    	
    	return p;
    }
    
    private static void set(int n, int x, int[] arr) {					// is set?
        visit[x] = true;
        part += p[x];
        
        for(int i = 0; i < n; i++) {
            if(visit[i] || arr[i] != arr[x] || linked[i][x] == 0) continue;
            set(n, i, arr);
        }
    }
}