package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1188 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		char[] N = new char[str.length()];
		N = str.toCharArray();
		
		int[] num = new int[N.length];
		
		for(int i = 0; i < N.length; i++){
			num[i] = Character.getNumericValue(N[i]);
		}
		
		int[] chk = new int[10];
		
		int cnt = 0;
		int max = 0;
		
		for(int i = 0; i < N.length; i++){
			switch(num[i]){
			
			case 1:				
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 0:
			chk[num[i]]++;
				break;
			}
		}
		
		cnt = chk[6] + chk[9];
		cnt = (cnt / 2) + (cnt % 2);
		
		for(int i = 0; i < chk.length; i++){
			if(i != 6 && i != 9){
				if(chk[i] >= max){
					max = chk[i];
				}
			}
		}
		
		System.out.println(Math.max(max, cnt));
	}

}
