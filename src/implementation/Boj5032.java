package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5032 {
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int emptyBottle = Integer.parseInt(st.nextToken());
		int findBottle = Integer.parseInt(st.nextToken());
		int changeBottle = Integer.parseInt(st.nextToken());
		
		int totalHave = emptyBottle + findBottle;
		int Q = 0, spare = 0;
		int drink = 0;
		int cnt = 0;
		
		while(totalHave / changeBottle >= 1){
			Q = totalHave / changeBottle;
			spare = totalHave % changeBottle;
			totalHave = Q + spare;			
			
			drink += Q;
			cnt++;
		}
		System.out.println(drink);
		
	}

}
