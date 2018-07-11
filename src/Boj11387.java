import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11387 {
	private static final String NEW_LINE = "\n";
	private static final String UP = "+";
	private static final String DOWN = "-";
	private static final String NOT = "0";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		
		System.out.println(sb.toString());
	}
	
	private static class Inven{
		double sp;
		double str;
		double cp;
		double cd;
		double as;
		
		public Inven(double sp, double str, double cp, double cd, double attackSpeed) {
			this.sp = sp;
			this.str = str;
			this.cp = cp;
			this.cd = cd;
			this.as = as;
		}
	}
}
