package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2566 {
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int max = 0, num = 0;
		int[][] RC = new int[9][9];
		
		for(int i = 0; i < 9; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < 9; j++){
				num = Integer.parseInt(st.nextToken());
				RC[i][j] = num;
				max = Math.max(num, max);
			}
		}
LOOP:		for(int i = 0; i < 9; i++){
			
			for(int j = 0; j < 9; j++){
				if(max == RC[i][j]){
					sb.append(max).append(NEW_LINE).append((i+1)+" "+(j+1));
					break LOOP;
				}
			}
		}
		System.out.println(sb.toString());
	}

}
