import java.util.*;
import java.io.*;

// 현재 배열 상태와 거기까지의 누적 비용을 표현하는 클래스
class State implements Comparable<State>{
    int cost;
    int[] arr;

    public State(int cost, int[] arr){
        this.cost = cost;
        this.arr = arr;
    }

    @Override
    public int compareTo(State o){
        return this.cost - o.cost;
    }
}

// 스왑 연산 정보 저장 클래스
class Info{
    int l,r,c;

    public Info(int l, int r, int c){
        this.l = l;
        this.r = r;
        this.c = c;
    }
}
public class Main {
    static int N, M;
    static int[] A;
    static List<Info> ops;
    static int[] sorted;
    static StringBuilder sb = new StringBuilder();
    static int[] swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return arr;
    }
    static void dijkstra(){
        PriorityQueue<State> pq =new PriorityQueue<>();
        Set<String> visited = new HashSet<>();

        pq.offer(new State(0, A));

        while(!pq.isEmpty()){
            State cur = pq.poll();

            // 배열 상태를 문자열로 변환해 방문 체크
            String key = Arrays.toString(cur.arr);
            if(visited.contains(key))
                continue;
            visited.add(key);

            // 정렬 완료 여부 확인
            if(Arrays.equals(cur.arr, sorted)){
                sb.append(String.valueOf(cur.cost));
                sb.append("\n");
                return;
            }

            // 가능한 모든 스왑 연산 적용
            for(Info op : ops){
                int [] next = cur.arr.clone();
                pq.offer(new State(cur.cost + op.c, swap(next, op.l, op.r)));
            }
        }

        sb.append("-1\n");
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        ops = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            ops.add(new Info(l, r, c));  // 스왑 연산 저장
        }

        // 정렬 기준 상태 만들기
        sorted = A.clone();
        Arrays.sort(sorted);

        dijkstra();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
