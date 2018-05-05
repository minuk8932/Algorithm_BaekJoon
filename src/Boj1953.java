import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1953 {
	private static ArrayList<Node>[] team = null;
	private static int[] isVisited = null;
	
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		isVisited = new int[n + 1];
		team = new ArrayList[n + 1];
		for(int i = 0; i < n + 1; i++){
			team[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < n + 1; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < num; j++){
				int who = Integer.parseInt(st.nextToken());
				
				team[i].add(new Node(who, true));
				team[who].add(new Node(i, true));
			}
		}
		
		bfs(n);
		
		StringBuilder sb = new StringBuilder();
		
		int b = 0, w = 0;
		
		for(int i = 1; i < n + 1; i++){
			if(isVisited[i] == 1){
				b++;
			}
			else{
				w++;
			}
		}
		
		sb.append(b).append(NEW_LINE);
		for(int i = 1; i < n + 1; i++){
			if(isVisited[i] == 1){
				sb.append(i).append(SPACE);
			}
		}
		sb.append(NEW_LINE);
		
		sb.append(w).append(NEW_LINE);
		for(int i = 1; i < n + 1; i++){
			if(isVisited[i] != 1){
				sb.append(i).append(SPACE);
			}
		}
		
		System.out.println(sb.toString());
	}
	
	private static class Node{
		int mem;
		boolean isHate;
		
		public Node(int mem, boolean isHate){
			this.mem = mem;
			this.isHate = isHate;
		}
	}
	
	private static void bfs(int lineup){
		int teamNumber = 1;
		
		for(int p = 1; p < lineup + 1; p++){
			if(isVisited[p] != 0) continue;
			
			isVisited[p] = teamNumber;
			
			Queue<Node> q = new LinkedList<>();
			q.offer(new Node(p, false));
			
			while(!q.isEmpty()){
				int current = q.poll().mem;
				
				for(int next = 1; next < lineup + 1; next++){
					
				}
			}
			
			teamNumber++;
		}
	}
}
