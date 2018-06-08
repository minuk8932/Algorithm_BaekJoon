import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Boj1406 {
	private static final String PUSH = "D";
	private static final String PULL = "L";
	private static final String REMOVE = "B";
	private static final String ADD = "P";
	private static final char INIT = '0';
	
	private static LinkedList<Character> words = new LinkedList<>();
	private static int cursor = 0;
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception{		
		words.add(INIT);
		char[] tmp = br.readLine().toCharArray();
		for(int i = 0; i < tmp.length; i++) {
			words.add(tmp[i]);
		}
		
		cursor = words.size() - 1;
		
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			char w = ' ';
			
			try {
				w = st.nextToken().charAt(0);
			}
			catch(NoSuchElementException nsee) {
				
			}
			
			int limit = words.size() - 1;
			
			
			
			switch (cmd) {
			case PUSH:
				
				if(cursor == limit) break;
				
				cursor += 1;
				break;
				
			case PULL:
				
				if(cursor == 0) break;
				
				cursor -= 1;
				break;
			
			case REMOVE:
				
				if(cursor == 0) break;
				
				words.remove(cursor);
				cursor -= 1;
				break;
					
			case ADD:
				
				words.add(cursor + 1, w);
				cursor += 1;
				break;
			}	
		}
		
		int loop = words.size();
		for(int i = 1; i < loop; i++) {
			bw.write(words.get(i));
		}
		
		bw.flush();
	}
}
