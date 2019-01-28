import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;

public class Boj2150 {
	private static ArrayDeque<Integer> stack = new ArrayDeque<>();
	private static ArrayList<ArrayList<Integer>> scc;
	private static ArrayList<Integer> component;
	
	private static boolean[] isVisited;
	
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int V = in.readInt();
		int E = in.readInt();
		
		ArrayList<Integer>[] forward = new ArrayList[V + 1];
		ArrayList<Integer>[] backward = new ArrayList[V + 1];
		scc = new ArrayList<>();
		
		for(int i = 0; i < V + 1; i++) {
			forward[i] = new ArrayList<>();
			backward[i] = new ArrayList<>();
			scc.add(i, new ArrayList<>());
		}
		
		while(E-- > 0) {
			int A = in.readInt();
			int B = in.readInt();
			
			forward[A].add(B);
			backward[B].add(A);
		}
		
		isVisited = new boolean[V + 1];
		for(int i = 1; i < V + 1; i++) {
			if(isVisited[i]) continue;
			isVisited[i] = true;
			
			dfs(forward, i, true);
			stack.push(i);
		}
		
		isVisited = new boolean[V + 1];
		int loop = 0;
		
		while(!stack.isEmpty()) {
			component = new ArrayList<>();
			int start = stack.pop();
			if(isVisited[start]) continue;
			
			component.add(start);
			dfs(backward, start, false);
			scc.add(loop++, component);
		}
		
		System.out.println(getRes(loop));
	}
	
	private static void dfs(ArrayList<Integer>[] arr, int current, boolean save) {
		isVisited[current] = true;
		
		for(int next: arr[current]) {
			if(isVisited[next]) continue;
			isVisited[next] = true;
			
			if(!save) component.add(next);
			dfs(arr, next, save);
			if(save) stack.push(next);
		}
	}

	private static StringBuilder getRes(int size) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < size; i++) {			
			Collections.sort(scc.get(i));
		}
		
		Collections.sort(scc, new ListComparator<>());
		
		sb.append(size).append(NEW_LINE);
		
		int loop = scc.size();
		for(int i = 0; i < loop; i++) {
			if(scc.get(i).size() == 0) continue;
			
			for(int element : scc.get(i)) {
				sb.append(element).append(SPACE);
			}
			sb.append(-1).append(NEW_LINE);
		}
		
		return sb;
	}
	
	private static class ListComparator<T extends Comparable<T>> implements Comparator<List<T>> {
		@Override
		public int compare(List<T> l1, List<T> l2) {
			for (int i = 0; i < Math.min(l1.size(), l2.size()); i++) {
				int c = l1.get(i).compareTo(l2.get(i));
		      
				if (c != 0) return c;
			}
		    
			return Integer.compare(l1.size(), l2.size());
		}
	}
	
	private static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}

		public int readInt() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}
