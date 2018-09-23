import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Boj14543 {
	private static final String CASE = "Equation ";
	private static final String NO_ANSWER = "No solution.";
	private static final String ALWAYS_ANSWER = "More than one solution.";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int P = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < P; i++) {
			String line = br.readLine();			
			
			StringTokenizer st = new StringTokenizer(line, " = ");
			String value = st.nextToken();
			double x = Double.parseDouble(value.substring(0, value.length() - 1));
			
			String plus = st.nextToken();
			double y = Double.parseDouble(st.nextToken());
			double ans = Double.parseDouble(st.nextToken());
			
			
		}
		
		System.out.println(sb.toString());
	}
}
