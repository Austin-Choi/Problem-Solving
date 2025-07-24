/*
3월1일 ~ 11월30일
꽃을 시작날짜가 작은 순서대로, 시작이 같으면 끝점이 큰 순서대로 정렬함
-> 제일 큰거 골라야해서

(cover 초기값 3월 1일) , idx = 0;
cover가 11월 30일이 되거나 넘어가면 종료

cover보다 start가 작으면서 end가 제일 큰 꽃을 idx++하면서 찾음
이걸 못찾으면 ans = 0으로 종료해야 하므로 boolean found 변수 사용
찾았으면 cover = maxEnd 해주고 ans++;
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static class Date implements Comparable<Date>{
        int month;
        int day;
        Date(int m, int d){
            this.month = m;
            this.day = d;
        }

        @Override
        public int compareTo(Date o){
            if(this.month == o.month)
                return this.day - o.day;
            return this.month - o.month;
        }
    }
    static class Info implements Comparable<Info>{
        Date start;
        Date end;
        Info(Date s, Date e){
            this.start = s;
            this.end = e;
        }

        @Override
        public int compareTo(Info o){
            if(this.start.compareTo(o.start) == 0)
                return -this.end.compareTo(o.end);
            return this.start.compareTo(o.start);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        Info[] board = new Info[N];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());
            board[i] = new Info(new Date(sm, sd), new Date(em, ed));
        }
        Arrays.sort(board);

        // 꽃 지는 날이 적은 순으로
        Date cover = new Date(3,1);
        Date endDate = new Date(11,30);
        int idx = 0;
        int ans = 0;
        Date maxDate = cover;

        while(cover.compareTo(endDate) <= 0){
            boolean found = false;
            while(idx < N && board[idx].start.compareTo(cover) <= 0){
                if(maxDate.compareTo(board[idx].end)<0){
                    maxDate = board[idx].end;
                    found = true;
                }
                idx++;
            }

            if(!found){
                ans = 0;
                break;
            }

            cover = maxDate;
            ans++;
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
