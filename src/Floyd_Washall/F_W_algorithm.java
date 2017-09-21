package Floyd_Washall;

/*
 * 		Floyd - Washall Algorithm
 * 		- 3중 for문을 이용해 구현		
 * 
 * 		시간 복잡도 : O(n^3)
 * 		배열을 무조건 MAX 값으로 채워주고 시작 Arrays.fill
 * 		자신에서 자신을 가는 부분은 0으로 초기화	
 * 
 * 			서울	대전	대구	부산
 *	서울	  0	  1		  3	  3
 *	
 *	대전 	  1		  0	  2	  3
 *	
 *	대구			  2	  0	  1
 *	
 *	부산	  				  1		  0
 *
 *				F-W Algorithm
 *				for(중간)
 *					for(시작)
 *						for(끝)
 *							matrix[S][E] = Math.min(matrix[S][E], matrix[S][V] + matrix[V][E]);
 *							// 처음부터 끝까지 바로 가는것이 빠른지, 아니면 중간에 경유를 해서 가는 것이 빠른지 비교후 거리가 짧은것을 할당
 *							// S : 시작, E : 끝, V : via 경유지
 *
*/

public class F_W_algorithm {
	public static void main(String[] args) {
		

	}

}
