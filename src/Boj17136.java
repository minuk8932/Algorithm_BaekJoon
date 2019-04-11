import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17136 {
	private static final boolean[][][] PAPERS = {											// [5][5][5]
												{
													{true, false, false, false, false},
													{false, false, false, false, false},
													{false, false, false, false, false},
													{false, false, false, false, false},
													{false, false, false, false, false},
													}
												,{
													{true, true, false, false, false}, 
													{true, true, false, false, false},
													{false, false, false, false, false},
													{false, false, false, false, false},
													{false, false, false, false, false},
													}
												,{
													{true, true, true, false, false}, 
													{true, true, true, false, false}, 
													{true, true, true, false, false},
													{false, false, false, false, false},
													{false, false, false, false, false}
													}
												,{
													{true, true, true, true, false}, 
													{true, true, true, true, false},
													{true, true, true, true, false},
													{true, true, true, true, false},
													{false, false, false, false, false}
													}
												
												,{
													{true, true, true, true, true}, 
													{true, true, true, true, true}, 
													{true, true, true, true, true}, 
													{true, true, true, true, true}, 
													{true, true, true, true, true}
													}
												};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] bg = new boolean[10][10];
		int[] pCount = new int[5];
		Arrays.fill(pCount, 5);
		
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 10; j++) {
				bg[i][j] = Integer.parseInt(st.nextToken()) == 1 ? true: false;
			}
		}
		
		
	}
}
