package breadth_first_search;

import java.util.LinkedList;
import java.util.Queue;

/*
 *  Memory를 많이 잡아 먹는다.
 *  그러나 탐색 속도 매우 빠름
 *  
 */

public class BFS_Ex {
	public static void main(String[] args) throws Exception {
		int[] tree = {-1,0,0,1,1,4,4,2,2,7,8};	// 각 노드는 자신의 부모의 값을 가지고 있음 : 포인터로 가리키고 있어야 하니까
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(0);
		
		while(!queue.isEmpty()){
			int current = queue.poll();		// 들어가있는 아이 빼겠습니다
			
			System.out.print(current + " ");
			
			for(int i = 0; i < 11; i++){
				if(tree[i] == current){		// poll 했던 값들 중에 배열에 있는 값과 같으면, 즉 부모의 값(tree[i])이 current인 것을
											// -> level order 합니다 
					queue.offer(i);			// queue에 다시 넣어 주세요
				}
			}
		}
	}

}
