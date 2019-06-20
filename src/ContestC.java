import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ContestC {
	private static final String[] MANY = {" bottles of beer on the wall, ",
			" bottles of beer.\nTake one down and pass it around, ",
			" bottles of beer on the wall.\n\n"};
	private static final String[] TWO = {" bottles of beer on the wall, ",
			" bottles of beer.\nTake one down and pass it around, ",
			" bottle of beer on the wall.\n\n"};
	private static final String[] ONE = {" bottle of beer on the wall, ",
			" bottle of beer.\nTake one down and pass it around, no more bottles of beer on the wall.\n\n"};
	private static final String[] LAST = {"No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, ",
			" bottles of beer on the wall.", " bottle of beer on the wall."};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int loop = N + 1;
		
		while(loop-- > 1) {
			if(loop == 1) {
				sb.append(loop).append(ONE[0]).append(loop).append(ONE[1]);
			}
			else {
				if(loop == 2) sb.append(loop).append(TWO[0]).append(loop).append(TWO[1]).append(loop - 1).append(TWO[2]);
				else sb.append(loop).append(MANY[0]).append(loop).append(MANY[1]).append(loop - 1).append(MANY[2]);
			}
		}
		
		if(N == 1) sb.append(LAST[0]).append(N).append(LAST[2]);
		else sb.append(LAST[0]).append(N).append(LAST[1]);
		
		System.out.println(sb.toString());
	}
}
