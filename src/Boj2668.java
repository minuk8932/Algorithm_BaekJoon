import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2668 {
	private static final String END_LINE = "\n";
	
	private static int N = 0;
	private static int[] map = null;
	private static int[] isVisited = null;
	private static boolean[] idx = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1];
		isVisited = new int[N + 1];
		idx = new boolean[N + 1];
		
		for(int i = 1; i < N + 1; i++){
			map[i] = Integer.parseInt(br.readLine());
		}
		
		StringBuilder sb = new StringBuilder();
		int max = 0, isSame = 0;
		
		for(int i = 1; i < N + 1; i++){
			int res = map[i] != i ? dfs(i) : 1;
			
			if(res == 1){
				isSame++;
			}
			else{
				if(res > max){
					max = res;
				}
			}
		}
		
		for(int i = 1; i < N + 1; i++){
			if(idx[i]){
				idx[map[i]] = true;
			}
		}
		
		sb.append(max+isSame).append(END_LINE);
		
		for(int i = 1; i < N + 1; i++){
			if(idx[i]){
				sb.append(i).append(END_LINE);
			}
		}
		System.out.println(sb.toString());
	}
	
	private static int dfs(int start){	
		if(isVisited[map[start]] != 0){
			idx[map[start]] = true;
			
			return isVisited[map[start]];
		}
		
		isVisited[start] = 1;
		
		int next = map[start];
		
		if(next > 0 && next < N + 1){
			if(isVisited[next] == 0){
				isVisited[next] = isVisited[start] + 1;
				System.out.print(next + " " + isVisited[next] + " \t");
				dfs(next);
			}
		}
		
		return 0;
	}
}
