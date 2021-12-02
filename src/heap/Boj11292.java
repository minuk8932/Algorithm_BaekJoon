package heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 11292번: 키 큰 사람
 *
 * @see https://www.acmicpc.net/problem/11292
 *
 */
public class Boj11292 {

    private static String SPACE = " ";
    private static String NEW_LINE = "\n";

    private static class Student {
        private String name;
        private double height;
        private int index;

        public Student(String name, double height, int index) {
            this.name = name;
            this.height = height;
            this.index = index;
        }

        public double getHeight() {
            return -this.height;
        }

        public int getIndex() {
            return this.index;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            Queue<Student> pq = new PriorityQueue<>(
                    Comparator.comparingDouble(Student::getHeight)
                    .thenComparingInt(Student::getIndex)
            );

            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                pq.offer(new Student(st.nextToken(), Double.parseDouble(st.nextToken()), i));
            }

            List<String> students = getCandidate(pq);
            while(!students.isEmpty()) {
                sb.append(students.remove(0)).append(SPACE);
            }

            sb.append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static List<String> getCandidate(Queue<Student> pq) {
        List<String> result = new LinkedList<>();

        Student top = pq.poll();
        result.add(top.name);

        while(!pq.isEmpty()) {
            Student next = pq.poll();
            if(next.height != top.height) break;

            result.add(next.name);
        }

        return result;
    }
}
