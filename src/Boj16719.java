import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj16719 {
	private static final char SPACE = ' ';
	private static final String NEW_LINE = "\n";
	
	private static class Array{
		char c;
		int idx;
		
		public Array(char c, int idx) {
			this.c = c;
			this.idx = idx;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		
		System.out.println(solution(input));
	}
	
	private static String solution(char[] arr) {
		Queue<Array> q = new LinkedList<>();
		
		for(int i = 0; i < arr.length; i++) {
			q.offer(new Array(arr[i], i));
		}
		
		StringBuilder sb = new StringBuilder();
		
		
		return sb.toString();
	}
}
