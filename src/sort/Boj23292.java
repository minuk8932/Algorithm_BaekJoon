package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Boj23292 {

    private static final int DELIMITER = 100;

    private static Queue<BioRhythm> pq;
    private static BioRhythm birth;

    private static class BioRhythm {
        private int year;
        private int month;
        private int day;

        public BioRhythm(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public int getYear() {
            return this.year;
        }

        public int getMonth() {
            return this.month;
        }

        public int getDay() {
            return this.day;
        }

        public int getBioRhythm() {
            return -(sigma(birth.year, this.year, 4)
                    * sigma(birth.month, this.month, 2)
                    * sigma(birth.day, this.day, 2));
        }

        public int sigma(int birth, int code, int loop) {
            int v1 = birth;
            int v2 = code;

            int sum = 0;

            while(loop-- > 0) {
                int diff = (v1 % 10) - (v2 % 10);

                sum += (diff * diff);
                v1 /= 10;
                v2 /= 10;
            }

            return sum;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        birth = stringToDate(br.readLine());

        pq = new PriorityQueue<>(Comparator.comparingInt(BioRhythm::getBioRhythm)
                .thenComparingInt(BioRhythm::getYear)
                .thenComparingInt(BioRhythm::getMonth)
                .thenComparingInt(BioRhythm::getDay));

        int N = Integer.parseInt(br.readLine());
        while(N-- > 0) {
            pq.offer(stringToDate(br.readLine()));
        }

        System.out.println(print());
    }

    private static int print() {
        BioRhythm bio = pq.poll();

        int date = bio.year;
        date *= DELIMITER;

        date += bio.month;
        date *= DELIMITER;

        date += bio.day;

        return date;
    }

    private static BioRhythm stringToDate(String str) {
        int value = Integer.parseInt(str);
        BioRhythm rhythm = new BioRhythm(0, 0, 0);

        rhythm.day = value % DELIMITER;
        value /= DELIMITER;

        rhythm.month = value % DELIMITER;
        value /= DELIMITER;

        rhythm.year = value;

        return rhythm;
    }
}
