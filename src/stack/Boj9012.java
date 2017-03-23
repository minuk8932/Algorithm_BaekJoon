package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9012 {
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		while (T > 0) {
			String VPS = br.readLine();
			int length = VPS.length();
			char[] div = new char[length];

			div = VPS.toCharArray();
			int V = 0, chk = 0;
			
			for (int i = 0; i < length; i++) {
				if (div[i] == '(') {
					V++;
				} else {
					V--;
				}
				if(V < 0){
					chk--;
				}
			}
			
			if(chk >= 0){
				if (VPS.startsWith("(") && VPS.endsWith(")")) {

					if (V == 0) {
						sb.append("YES").append(NEW_LINE);
					} 
					else 	{
						sb.append("NO").append(NEW_LINE);
					}

				} 
				else {
					sb.append("NO").append(NEW_LINE);
				}
			}
			else{
				sb.append("NO").append(NEW_LINE);
			}

			T--;
		}
		System.out.println(sb.toString());
	}

}
