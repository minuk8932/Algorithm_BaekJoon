package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj13300 {
	private static final String SPACE = " ";
	private static final int GRADE = 6;
	private static final int SEX = 2;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int num = Integer.parseInt(st.nextToken());
		int can = Integer.parseInt(st.nextToken());
		int rooms = 0, tmp = 0;
		
		int[][] mems = new int[SEX][GRADE + 1];
		
		for(int i = 0; i < num; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			mems[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] += 1;
		}
		
		
		
		for(int i = 0; i < SEX; i++){
			for(int j = 1; j < GRADE + 1; j++){
				if(mems[i][j] == 0){
					tmp = 0;
				}
				else if (mems[i][j] % can == 0) {
					tmp = mems[i][j] / can;
				} 
				else {
					tmp = (mems[i][j] / can) + 1;
				}
				rooms += tmp;
			}
		}
		
		System.out.println(rooms);
	}
}
