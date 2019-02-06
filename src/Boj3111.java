import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Boj3111 {
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] A = br.readLine().toCharArray();
		char[] tmp = new char[A.length];
		
		ArrayDeque<Character> stack = new ArrayDeque<>();
		
		for(char w: br.readLine().toCharArray()) {
			stack.push(w);
		}
		
		getWord(A, tmp, stack);
	}
	
	private static void getWord(char[] a, char[] tmp, ArrayDeque<Character> stack) {
		int loop = a.length;
		int idx = 0;
		
		boolean reversed = false;
		
		while(!stack.isEmpty()) {
			char c = !reversed ? stack.removeFirst() : stack.removeLast();	
			
		}
		
		System.out.println(sb);
	}
	
	private static char[] renew(char[] arr, int start, int end, int adder) {
		for(int i = start; i * adder < end * adder; i += adder) {
			if(arr[i] == '\n') continue;

			sb.append(arr[i]);
			arr[i] = '\n';
		}
		
		return arr;
	}
}
