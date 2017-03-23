package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2010 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int sum = 0, plug = 0;
		
		for(int i = 0; i < N; i++){
			plug = Integer.parseInt(br.readLine());
			
			if(i == N-1){
				sum += plug;
			}
			
			else {
				sum += plug - 1;
			}
		}
		System.out.println(sum);
		
		
	}

}
