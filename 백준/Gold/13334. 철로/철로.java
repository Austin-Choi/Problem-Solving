import java.io.*;
import java.util.*;
public class Main {
    static int n;
    static int L;
    static Line[] board;
    static int maxCount = 0;
    static class Line implements Comparable<Line>{
        int start;
        int end;
        Line(int s, int e){
            if(e < s){
                int temp = e;
                e = s;
                s = temp;
            }
            this.start = s;
            this.end = e;
        }

        @Override
        public int compareTo(Line o){
            return this.end - o.end;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        board = new Line[n];
        for(int i =0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            board[i] = new Line(s,e);
        }
        L = Integer.parseInt(br.readLine());
        Arrays.sort(board);

        PriorityQueue<Line> pq
                = new PriorityQueue<>(Comparator.comparingInt((Line o)->o.start));
        for(Line cur : board){
            int start = cur.end - L;

            // pq에서 범위 벗어난 start 제거
            while(!pq.isEmpty() && pq.peek().start < start)
                pq.poll();

            // 현재의 철도가 현재의 시작점도 포함하고 있어야 함
            if(cur.start >= start) {
                pq.add(cur);
                // pq는 각 시점마다 달라지므로 최대값 갱신
                // 더할때가 최대값 달라지므로 여기다가 넣음
                maxCount = Math.max(maxCount, pq.size());
            }
        }

        bw.write(String.valueOf(maxCount));
        bw.flush();
        bw.close();
        br.close();
    }
}
