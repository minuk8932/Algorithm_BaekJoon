package stack;

import javax.xml.ws.WebServiceRef;

public class StackTest {
	public static void main(String[] args) {
		Stack stk = new Stack();
		stk.push(args); // 지금 안쓰는 겁니다~
		
		try{
			stk.pop();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
		/**
		 * 
		 * @author minchoba
		 *
		 */
	private static class Stack {
		private final int MAX_SIZE = 1000;
		private int tos = -1;
		private Object[] stack = null;

		/**
		 *  
		 *  생성자
		 *  
		 */
		public Stack() {
			stack = new Object[MAX_SIZE];
		}

		/**
		 * 
		 * @param val
		 * @see Stack.hush
		 */
		@Deprecated
		public void push(Object val) {
			stack[++tos] = val;
		}
		
		public void hush(Object obj){
			stack[++tos] = obj;
		}

		public Object pop() throws Exception {
			if(tos == -1){
				throw new Exception("집어 올 것이 없습니다.");
			}
			return stack[tos--];
		}

		public Object size() {
			return tos + 1;
		}

		public boolean isEmpty() {

			return tos == 1 ? true : false;
		}
	}
}
