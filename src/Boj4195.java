import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Boj4195 {
	private static HashMap<String, String> parent;
	private static HashMap<String, Integer> hm = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int T = in.readInt();
		
		while(T-- > 0) {
			int F = in.readInt();
			parent = new HashMap<>();
			
			for(int i = 0; i < F; i++) {
				String name1 = in.readString();
				String name2 = in.readString();
				
				init(name1);
				init(name2);
				
				MST(name1, name2);
				out.printLine(hm.get(name1));
			}
		}
		
		out.flush();
	}
	
	private static void init(String str) {
		if(!parent.containsKey(str)) {
			parent.put(str, str);
			hm.put(str, 0);
		}
	}
	
	private static String find(String str) {
		if(parent.get(str) == str) return str;
		else return find(parent.get(str));
	}
	
	private static void merge(String str1, String str2) {
		str1 = find(str1);
		str2 = find(str2);
		
		int max = Math.max(hm.get(str1), hm.get(str2));
		
		if(isSmaller(str1, str2)) {
			parent.put(str2, str1);
		}
		else {
			parent.put(str1, str2);
		}
		
		hm.put(str1, max);
		hm.put(str2, max);
	}
	
	private static boolean isCycle(String str1, String str2) {
		str1 = find(str1);
		str2 = find(str2);
		
		if(str1 == str2) return true;
		else return false;
	}
	
	private static void MST(String name1, String name2) {				
		if(!isCycle(name1, name2)) {			
			if(hm.get(name1) == 0 && hm.get(name2) == 0) {
				putting(name1, name2, 2, 0);
			}
			else {
				if(hm.get(name1) != 0 && hm.get(name2) != 0) {
					putting(name1, name2, hm.get(name1), hm.get(name2));
				}
				else {					
					putting(name1, name2, Math.max(hm.get(name1), hm.get(name2)), 1);
				}
			}
			
			merge(name1, name2);
		}
	}
	
	private static void putting(String str1, String str2, int cost1, int cost2) {
		int sum = cost1 + cost2;
		
		hm.put(str1, sum);
		hm.put(str2, sum);
	}
	
	private static boolean isSmaller(String str1, String str2) {
		int comp = str1.compareTo(str2);
		return comp == -1 ? true : false;
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

		public String readString() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
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

	private static class OutputWriter {
		private final PrintWriter writer;

		public OutputWriter(OutputStream outputStream) {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
		}

		public void print(Object... objects) {
			for (int i = 0; i < objects.length; i++) {
				if (i != 0) {
					writer.print(' ');
				}
				writer.print(objects[i]);
			}
			writer.flush();
		}

		public void printLine(Object... objects) {
			print(objects);
			writer.println();
			writer.flush();
		}

		public void close() {
			writer.close();
		}

		public void flush() {
			writer.flush();
		}
	}
}
