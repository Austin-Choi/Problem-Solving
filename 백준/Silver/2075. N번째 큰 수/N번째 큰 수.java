import java.io.*;
import java.util.*;

/*
모든 수는 자신의 한칸 위에 있는 수보다 크므로
현재 i<N 이라고 하고 for 문을 돌면
i랑 i+1<N일 때
i랑 i+1 을 max pq에 넣고 앞에 5개만 추출함.
그리고 i++
반복

12 7 9 15 5 13 8 11 19 6
19 15 13 12 11

19 15 13 12 11 21 10 26 31 16
31 26 21 19 16

31 26 21 19 16 48 14 28 35 25
48 35 28 26 25

48 35 28 26 25 52 20 32 41 49
52 49 48 41 35
 */
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        List<Integer> l = new ArrayList<>();
        for(int i = 0; i<N; i++){
            l.add(Integer.parseInt(st1.nextToken()));
        }
        for(int j = 1; j<N; j++) {
            st1 = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++){
                l.add(Integer.parseInt(st1.nextToken()));
            }
            pq1.addAll(l);
            l.clear();
            for(int i=0; i<N; i++){
                l.add(pq1.poll());
            }
        }
        PriorityQueue<Integer> pq3 = new PriorityQueue<>();
        pq3.addAll(l);
        System.out.println(pq3.poll());
    }
}