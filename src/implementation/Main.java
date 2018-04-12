package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		String[] strs = line.split(" "); // ���� ���� string �迭�� �߶���
		br.close();
		
		int before = Integer.parseInt(strs[0]);
		boolean isAscending = false;
		boolean isDescending = false;
		
		for ( int i =1; i < strs.length; i++){
			int num = Integer.parseInt(strs[i]);
			
			if( before < num){
				isAscending = true;
			}
			else if(before > num){
				isDescending = true;
			}
			else{
				isAscending = true;
				isDescending = true;
			}
			
			before = num;
		}
		if(isAscending && isDescending){
			System.out.println("mixed");
		}
		else if(isAscending && !isDescending){
			System.out.println("Ascending");
		}
		else{
			System.out.println("Descending");
		}
	}

}
