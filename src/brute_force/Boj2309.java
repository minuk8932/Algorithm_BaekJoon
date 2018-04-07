package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 2309번 : 일곱 난쟁이
 *
 *	@see https://www.acmicpc.net/problem/2309/
 *
 */
public class Boj2309 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dwarf = new int[9];
		int sum = 0;
		
		for(int i = 0; i < dwarf.length; i++){						// 난쟁이와 난쟁이들의 합을 구함
			dwarf[i] = Integer.parseInt(br.readLine());
			sum+= dwarf[i];
		}
		
		int notDwarf = sum - 100;									// 가짜 난쟁이를 걸러내기위한 값
		int idx1 = 0, idx2 = 0;
		
		Arrays.sort(dwarf);		
		
		for(int i = 0; i < dwarf.length; i++){
			for(int j = 0; j < dwarf.length; j++){
				if(i != j){
					if(notDwarf == dwarf[i] + dwarf[j]){		// 가짜난쟁이의 합이 해당 변수의 값과 같다면
						idx1 = i;												// 해당 인덱스를 각각 변수에 담아줌
						idx2 = j;
										
						break;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < dwarf.length; i++){						// 가짜 난쟁이에 해당하는 인덱스를 제외하고
			if(i == idx1){
				continue;
			}
			
			if(i == idx2){
				continue;
			}
			
			sb.append(dwarf[i]).append(NEW_LINE);			// 진짜 난쟁이들의 키만 버퍼에 담아줌
		}
		
		System.out.println(sb.toString());						// 결과값 한번에 출력
	}
}
