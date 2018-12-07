import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Boj5397 {
	private static final char NEW_LINE = '\n';
	private static int cursor;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			LinkedList<Character> pw = new LinkedList<>();
			cursor = 0;
			
			for(char c: br.readLine().toCharArray()) {
				if(c == '<' || c == '>') cursorMove(c - '=', pw);
				else if(c == '-')pw = removeWord(pw);
				else pw = makeString(c, pw);
			}
			
			sb.append(getString(pw)).append(NEW_LINE);
		}
		
		br.close();
		System.out.println(sb);
	}
	
	private static void cursorMove(int adder, LinkedList<Character> w) {
		cursor += adder;
	}
	
	private static LinkedList<Character> removeWord(LinkedList<Character> tmp) {
		int size = tmp.size();
		
		if(cursor - 1 < 0) {
			return tmp;
		}
		else if(cursor > size) {
			tmp.remove(size - 1);
			cursor = size - 1;
		}
		else {
			tmp.remove(cursor - 1);
			cursor--;
		}
		
		return tmp;
	}
	
	private static LinkedList<Character> makeString(char w, LinkedList<Character> tmp) {
		int size = tmp.size();
		
		if(size == 0) {
			tmp.add(w);
			cursor = 1;
		}
		else {
			if(cursor < 0) {
				tmp.addFirst(w);
				cursor = 1;
			}
			else if(cursor > size) {
				tmp.addLast(w);
				cursor = size + 1;
			}
			else {
				tmp.add(cursor, w);
				cursor++;
			}
		}
		
		return tmp;
	}
	
	private static StringBuilder getString(LinkedList<Character> tmp) {
		StringBuilder sb = new StringBuilder();
		
		while(!tmp.isEmpty()) {
			sb.append(tmp.removeFirst());
		}
		
		return sb;
	}
}
