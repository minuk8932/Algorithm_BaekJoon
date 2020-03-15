import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj6444 {
	private static final int LAST = 18_278;
	private static final char EQUAL = '=';
	
	private static final String PLUS = "+";
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			HashMap<Integer, Integer> sheets = new HashMap<>();
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < M; j++) {
					String input = st.nextToken();
					int val = 0;
					int target = i * LAST + j;
					
					if(input.charAt(0) == EQUAL) {
						StringTokenizer tokens = new StringTokenizer(input.substring(1), PLUS);
						
						while(tokens.hasMoreTokens()) {
							int idx = getIndex(tokens.nextToken());
							
							if(!sheets.containsKey(idx)) continue;
							val += sheets.get(idx);
						}
					}
					else {
						val = Integer.parseInt(input);
					}

					if(val != 0) sheets.put(target, val);
					sb.append(sheets.containsKey(target) ? sheets.get(target): 0).append(SPACE);
				}
				sb.append(NEW_LINE);
			}
		}

		System.out.println(sb.toString());
	}
	
	private static int getIndex(String spot) {
		char[] c = spot.toCharArray();
		int idx = -1;
		
		StringBuilder build = new StringBuilder();
		
		for(int i = 0; i < c.length; i++) {
			if(c[i] >= '0' && c[i] <= '9' && idx == -1) {
				idx = i;
			}
			
			if(idx != -1) build.append(c[i]);
		}
		
		int fix = LAST * (Integer.parseInt(build.toString()) - 1);
		
		if(idx == 1) return fix + c[0] - 'A';
		else if(idx == 2) return fix + (c[1] - 'A' + 1) * 26 + (c[0] - 'A');
		else return fix + (c[2] - 'A' + 1) * 26 * 26 + (c[1] - 'A' + 1) * 26  + (c[0] - 'A');
	}
}
