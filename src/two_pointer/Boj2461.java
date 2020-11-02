package two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2461번: 대표 선수
 *
 * @see https://www.acmicpc.net/problem/2461
 *
 */
public class Boj2461 {
    private static PriorityQueue<Student> pq = new PriorityQueue<>();

    private static class Student implements Comparable<Student>{
        int index;
        int value;

        public Student(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Student s) {
            return this.value - s.value;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] students = new int[2_000][2_000];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(students[i], Integer.MAX_VALUE);

            for(int j = 0; j < M; j++) {
                students[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(player(N, M, students));
    }

    private static int player(int n, int m, int[][] arr) {
        int[] pos = new int[n];
        int result = Integer.MAX_VALUE;
        int max = class1Classify(n, arr);

        int loop = n * (m - 1);

        while(loop-- > 0) {                                 // total calculation
            Student current = pq.poll();
            result = Math.min(result, max - current.value); // find min diff

            pos[current.index]++;
            if(pos[current.index] == n) break;

            max = Math.max(max, arr[current.index][pos[current.index]]);    // max data save
            pq.offer(new Student(current.index, arr[current.index][pos[current.index]]));
        }

        return result;
    }

    private static int class1Classify(int n, int[][] arr) {
        int max = 0;

        for(int i = 0; i < n; i++) {                // get max in class 1
            Arrays.sort(arr[i]);

            max = Math.max(max, arr[i][0]);
            pq.offer(new Student(i, arr[i][0]));
        }

        return max;
    }
}
