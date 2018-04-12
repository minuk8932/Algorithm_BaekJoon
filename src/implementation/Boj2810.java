package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2810 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String seat = br.readLine();
		
		int num = 1;
		
		for(int i = 0; i < seat.length(); i++){
			if(seat.charAt(i) == 'S'){
				num++;
			}
			else{
				num++;
				i++;
			}
		}
		
		
		if(num > N){
			System.out.println(N);
		}
		else{
			System.out.println(num);
		}
	}

}
