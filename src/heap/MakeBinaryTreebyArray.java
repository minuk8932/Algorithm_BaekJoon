package heap;

import java.util.Arrays;

/*
 * 	- Binary Tree (B - Tree)
 *	code를 이용한 생성
 *	2. Array
 *					arr
 * 					0		1		2		3		4		5		6		7		8		9		10
 * 					x		1		7		9		10		20	30	40
 * 
 * 					해당 노드 나누기 - 해당 노드의 root 노드
 * 					해당 노드를 나눈 나머지로 left right를 구분
 * 
 * 		-> 메모리가 충분하다면 속도가 훨씬 빠르므로 효율적
 * 
 * 		단점 : 이후에 자식노드가 더 있는지 없는지 알기 어렵다.
 * 		그러므로 빈 칸에는 Arrays.fill(Integer.MAX_VALUE);
 */

public class MakeBinaryTreebyArray {
	public static int[] arr = new int[10];
	public static void main(String[] args) throws Exception{
		
		Arrays.fill(arr, Integer.MIN_VALUE); 	//이후에 자식노드가 없다면 쓰레기 값으로 채워줌
	}
	
	public void preOrder(int idx){
		if(arr[idx] != 0){
			System.out.println(arr[idx]);
		
			int idxMulti = idx << 1;
			preOrder(idxMulti);
			preOrder(idxMulti+1);
		}
	}

}
