package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1100 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[][] chess = new char[8][8];
		
		for(int i = 0; i < chess.length; i++){
				chess[i] = br.readLine().toCharArray();
		}
		
		int cnt = 0;
		int k = 0;
		for(int i = 0; i < chess.length; i++){
			if(i % 2 == 0){
				k = 0;
			}
			else{
				k = 1;
			}
			for(int j = k; j < chess[i].length; j += 2){
				if(chess[i][j]=='F'){
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

}
