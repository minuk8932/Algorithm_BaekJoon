package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2920 {
	public static void main(String args[]) throws Exception{ 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		String[] strArr = line.split(" "); 
		//������ �������� String�� ���� �ϳ��� �ڸ�
		br.close();
		
		int before = Integer.parseInt(strArr[0]);
		// 0��° str�迭�� ���� ������ ���� ����. -> �� �� ������ ũ�� �񱳸� ����.
		boolean IsAscending = false;
		boolean IsDescending = false;
		
		for(int i =1; i<strArr.length ; i++){ 
			// 1���� �޴� ������ �ռ� ���� before ������ 0��°�� �ޱ� ����
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
			// before ������ ���� �������� num�� �ְ� 
			//�� ���� i+1��° �迭�� ����� ���ϱ����� �ٽ� for�� �۵�.
		}
		
		if(IsAscending && !IsDescending){ //boolean ���� ���� ��.
			System.out.println("ascending");
		}else if(IsDescending && IsAscending){
			System.out.println("mixed");
		}else{
			System.out.println("descending");
		}
			
		
		
	}
}
