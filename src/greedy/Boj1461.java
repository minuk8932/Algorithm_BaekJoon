package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1461번: 도서관
 *
 * @see https://www.acmicpc.net/problem/1461/
 *
 */
public class Boj1461 {
    private static ArrayList<Integer>[] books = new ArrayList[2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        books[0] = new ArrayList<>();
        books[1] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int b = Integer.parseInt(st.nextToken());

            if(b < 0) books[0].add(-b);
            else books[1].add(b);
        }

        Collections.sort(books[0]);
        Collections.sort(books[1]);
        System.out.println(Math.min(getResult(books[0], M, true) + getResult(books[1], M, false)
                , getResult(books[1], M, true) + getResult(books[0], M, false)));
    }

    private static int getResult (ArrayList<Integer> book, int m, boolean flag) {
        int result = 0;
        int size = book.size() - 1;

        if(size == -1) return 0;

        for(int i = size; i >= 0; i -= m) {             // find min distance by m
            int current = book.get(i);
            result += current;
        }

        result *= 2;
        if(flag) result -= book.get(size);

        return result;
    }
}
