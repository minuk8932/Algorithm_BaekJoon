import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj9519 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		ArrayList<Character> word = new ArrayList<>();  // 배열을 각 하나의 값으로 List마다 넣어줄 것이므로 일반 Character로 선언.

		int X = Integer.parseInt(br.readLine());
		
		String str = br.readLine();
		int strLeng = str.length();
		
		char[] tmp = new char[strLeng];
		tmp = str.toCharArray();
		word.add(tmp[0]);
		
		for(int i = 1; i < strLeng; i++){
			word.add(tmp[i]);
		}
		
		
		for (int i = 0; i < X; i++) {
			
			for (int j = strLeng - 1; j >= 0; j--) {
				if (strLeng % 2 == 1) {
					if(j % 2 == 0){
						word.add(strLeng, word.get(j));
						word.remove(j);
						
					}
				}
				
				else{
					if(j % 2 == 1){
						word.add(strLeng, word.get(j));
						word.remove(j);
						
					}
				}
			}
		}
		
		for (int i = 0; i < strLeng; i++) {
			sb.append(word.get(i));
		}
		
		System.out.println(sb.toString());
	}

}
