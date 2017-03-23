package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1110 {
	public static final int CHECK = 10; // 자릿수 확인
	public static final double REMOVE = 10.0; // 10의 자리 및 1의 자리 제거.
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		
		int time = 0;		
		int factualOne = 0, factualTen = 0;
		
		if(num >= CHECK){
			factualOne = num - (int) (Math.floor(num / REMOVE) * REMOVE);
			factualTen = (num - factualOne) / CHECK;
		}
		else{
			factualOne = num;
		}
		int first = factualOne, second = factualTen;
		
LOOP:	while(true){
			int tmp = factualOne + factualTen;
			factualTen = factualOne;
			
			if(tmp >= CHECK){
				factualOne = tmp - (int) (Math.floor(tmp / REMOVE) * REMOVE);
			}
			else{
				factualOne = tmp;
			}
			
			time++;
			
			if(first == factualOne && second == factualTen){
				break LOOP;
			}
			
		}
		System.out.println(time);
	}
}
