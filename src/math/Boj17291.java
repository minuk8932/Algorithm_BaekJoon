package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 17291번: 새끼치기
 *
 *	@see https://www.acmicpc.net/problem/17291/
 *
 */
public class Boj17291 {
	private static class Cell{
		int divide;
		int count;
		
		public Cell(int divide, int count) {
			this.divide = divide;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(counting(N));
	}
	
	private static int counting(int n) {
		Cell[] cell = new Cell[n];
		cell[0] = new Cell(0, 1);
		
		for(int year = 1; year < n; year++) {
			int sum = 0, cache = 0;
			
			for(int before = 0; before < year; before++) {
				cell[before].divide++;
				
				if(before % 2 == 0) {
					if(cell[before].divide == 3) {			// 홀수년도 -> 3회 분열시 사라짐
						cache += cell[before].count;
						cell[before].count = 0;
					}
				}
				else {
					if(cell[before].divide == 4) {			// 짝수년도 -> 4회 분열시 사라짐
						cache += cell[before].count;
						cell[before].count = 0;
					}
				}
				
				sum += cell[before].count;
			}

			sum += cache;					// 사라진 세포를 일단 더해서 다음 분열 갯수 생성
			int make = sum * 2;
			make -= sum;					// 전체 갯수에서 현재 년도의 세포 수를 구함
			
			cell[year] = new Cell(0, make);
		}
		
		int count = 0;
		for(int i = 0; i < n; i++) {		// n년까지 살아남은 세포의 수
			count += cell[i].count;
		}
		
		return count;
	}
}
