package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author exponential-e
 * 백준 19637번: IF문 좀 대신 써줘
 *
 * @see https://www.acmicpc.net/problem/19637
 *
 */
public class Boj19637 {
    private static TreeMap<Integer, String> title = new TreeMap<>();
    private static Title[] titles;
    private static Query[] q;

    private static final String NEW_LINE = "\n";

    private static class Title {
        int value;
        String name;

        public Title(int value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    private static class Query implements Comparable<Query>{
        int idx;
        int value;

        public Query(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        @Override
        public int compareTo(Query q) {
            return this.value < q.value ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int key = Integer.parseInt(st.nextToken());

            if(title.containsKey(key)) continue;
            title.put(key, name);
        }

        makeTitle();
        q = new Query[M];

        for(int i = 0; i < M; i++) {
            q[i] = new Query(i, Integer.parseInt(br.readLine()));       // make offline query
        }

        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();

        String[] result = new String[q.length];
        Arrays.sort(q);

        int idx = 0;

        for(Query query: q) {
            while(idx < titles.length) {
                if(query.value <= titles[idx].value) {                  // find result by input sequence
                    result[query.idx] = titles[idx].name;
                    break;
                }

                idx++;
            }
        }

        for(String r: result) {
            sb.append(r).append(NEW_LINE);
        }

        System.out.print(sb.toString());
    }

    private static void makeTitle() {
        titles = new Title[title.size()];
        int idx = 0;

        for(int key: title.keySet()) {                                  // premake titles
            titles[idx++] = new Title(key, title.get(key));
        }
    }
}
