package array;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2920 {
	public static void main(String args[]) throws Exception{ 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		String[] strArr = line.split(" "); 
		//공백을 기준으로 String을 각개 하나로 자름
		br.close();
		
		int before = Integer.parseInt(strArr[0]);
		// 0번째 str배열을 갖는 정수형 변수 선언. -> 앞 뒤 숫자의 크기 비교를 위함.
		boolean IsAscending = false;
		boolean IsDescending = false;
		
		for(int i =1; i<strArr.length ; i++){ 
			// 1부터 받는 이유는 앞서 받은 before 변수가 0번째를 받기 때문
			int num = Integer.parseInt(strArr[i]);
			
			if(before < num){
				IsAscending = true;
			
			}else if(before > num){
				IsDescending = true;
				
			}else{
				IsAscending = true;
				IsDescending = true;
				
			}
			before = num;
			// before 변수에 현재 변수였던 num을 넣고 
			//그 다음 i+1번째 배열의 내용과 비교하기위해 다시 for문 작동.
		}
		
		if(IsAscending && !IsDescending){ //boolean 값을 통한 비교.
			System.out.println("ascending");
		}else if(IsDescending && IsAscending){
			System.out.println("mixed");
		}else{
			System.out.println("descending");
		}
			
		
		
	}
}
