package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj10886 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int time = Integer.parseInt(br.readLine());
		int res_1=0;
		int res_2=0;
		
		if(time%2 != 0){
			for ( int i = 0; i < time; i++){
				int chk = Integer.parseInt(br.readLine());
				
				if(chk == 0){
					res_1++;
				}else{
					res_2++;
				}
			}
		}
		
		if(res_1>res_2){
			System.out.println("Junhee is not cute!");
		}else if (res_1<res_2){
			System.out.println("Junhee is cute!");
		}
		
	}
}

