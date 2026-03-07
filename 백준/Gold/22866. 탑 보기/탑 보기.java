/*
각 i별로 left right priorityqueue 쓰는데 {번호, 높이}에서 높이 우선으로 넣는거임
pq 비어있을때는 cur보다 큰거
pq 안 비어있을때는 top보다 큰거만 넣음
-> 이렇게하면 10만 *10만이라 터지지 않나
-> 시간초과

Monotonic Stack
-> 단조 스택은 현재 처리하는 것에서 정보가 유의미함
-> 자기보다 큰 값 찾고싶으면 단조감소스택(이 문제)
-> 자기보다 작은 값 찾고싶으면 단조증가스택

왼->오 = cur의 왼쪽에서 보이는 건물 반대는 반대
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[] b;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        b = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            b[i] = Integer.parseInt(st.nextToken());
        }

        //{idx, h}
        Deque<int[]> q = new ArrayDeque<>();
        // 크기
        int[] lc = new int[N];
        // 가장가까우면서작은거
        int[] ln = new int[N];
        // 왼오 단조감소스택
        for(int i = 0; i<N; i++){
            // i에서 볼수 있는 건물
            while(!q.isEmpty() && q.peek()[1] <= b[i])
                q.pop();
            lc[i] = q.size();
            //항상 최근 건물이 top
            if(q.isEmpty())
                ln[i] = -1;
            else
                ln[i] = q.peek()[0];
            q.push(new int[]{i, b[i]});
        }

        q.clear();
        int[] rc = new int[N];
        int[] rn = new int[N];
        for(int i = N-1; i>=0; i--){
            while(!q.isEmpty() && q.peek()[1] <= b[i])
                q.pop();
            rc[i] = q.size();
            if(q.isEmpty())
                rn[i] = -1;
            else
                rn[i] = q.peek()[0];
            q.push(new int[]{i, b[i]});
        }
        for(int i = 0; i<N; i++){
            int size = rc[i]+lc[i];
            if(size == 0){
                sb.append("0\n");
                continue;
            }

            int nearest;
            if(rn[i] == -1)
                nearest = ln[i];
            else if(ln[i] == -1)
                nearest = rn[i];
            else{
                int dl = i - ln[i];
                int dr = rn[i] - i;
                if(dl > dr)
                    nearest = rn[i];
                else
                    nearest = ln[i];
            }
            sb.append(size).append(" ").append(nearest+1).append("\n");
        }
        System.out.print(sb);
    }
}