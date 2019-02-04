import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Boj2529 {
	private static ArrayDeque<Integer> stack = new ArrayDeque<>();
	private static boolean[] isVisited = new boolean[10];
	private static String minStr = "", maxStr = "";
	
	private static final String LEFT = ">";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
	
		boolean[] inSign = new boolean[k];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < k; i++) {
			if(st.nextToken().equals(LEFT)) inSign[i] = true;
			else inSign[i] = false;
		}
		
		for(int i = 0; i < 10; i++) {
			if(minStr.length() != k + 1) isVisited = new boolean[10];
			if(isVisited[i]) continue;
			
			topologySort(k, inSign, i, 0);
			stack.push(i);
			
			getMinMax();
		}
		
		System.out.println(maxStr + NEW_LINE + minStr);
	}
	
	private static void topologySort(int size, boolean[] sign, int current, int depth) {
		if(depth == size) return;
		
		if(isVisited[current]) return;
		isVisited[current] = true;
		
		for(int next = 0; next < 10; next++) {
			if(isVisited[next]) continue;
			topologySort(size, sign, next, depth + 1);
		}
		
		if(stack.isEmpty()) {
			stack.push(current);
		}
		else {
			int peek = stack.peek();
			
			if(sign[depth]) {
				if(current < peek) stack.push(current);
			}
			else {
				if(current > peek) stack.push(current);
			}
		}
	}
	
	private static void getMinMax() {
		StringBuilder sb = new StringBuilder();
		long max = 0, min = Long.MAX_VALUE;
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		String sbStr = sb.toString();
		long resNum = Long.parseLong(sbStr);
		
		if(resNum > max) {
			max = resNum;
			maxStr = sbStr;
		}
		
		if(resNum < min) {
			min = resNum;
			minStr = sbStr;
		}
	}
}
