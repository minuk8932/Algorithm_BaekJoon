package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2804 {
	public static final String DOT = ".";
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		br.close();
		
		
		int chkA = 0, chkB = 0;

		char[] aChar = st.nextToken().toCharArray();
		char[] bChar = st.nextToken().toCharArray();
		
		
COMPARE:	for (int i = 0; i < aChar.length; i++) {
				for (int j = 0; j < bChar.length; j++) {

					if (aChar[i] == bChar[j]) {
						chkA = i;
						chkB = j;
						break COMPARE;
					}				
				}
			}

		for (int i = 0; i < bChar.length; i++) {
			for (int j = 0; j < aChar.length; j++) {
				
				if (j == chkA)	{ 
					sb.append(bChar[i]);
				}
				else if(i == chkB) {
					sb.append(aChar[j]);
				} 
				else {
					sb.append(DOT);
				}
			}
			sb.append(NEW_LINE);
		}

		System.out.println(sb.toString());
	}

}
