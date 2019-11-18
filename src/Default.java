import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Default {
	private static final int LAST = 18_278;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println(getIndex(br.readLine()));
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