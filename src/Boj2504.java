import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj2504 {
	private static final char SMALL_OPEN = '(';
	private static final char BIG_OPEN = '[';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] line = br.readLine().toCharArray();

		Stack<Character> pt = new Stack<>();
		boolean[] isPoped = new boolean[line.length];
		
		int res = 0;
		
		for(int i = 0; i < line.length; i++) {
			if(pt.isEmpty() && (line[i] != SMALL_OPEN || line[i] != BIG_OPEN)) {
				break;
			}
			
			if(line[i] == SMALL_OPEN) {
				pt.push(line[i]);
				continue;
			}
			
			if(line[i] == BIG_OPEN) {
				pt.push(line[i]);
				continue;
			}
		}
		
		if(!pt.isEmpty()) {
			res = 0;
		}
		
		System.out.println(res);
	}
}
