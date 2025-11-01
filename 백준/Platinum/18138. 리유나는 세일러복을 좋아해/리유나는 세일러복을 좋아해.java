/*
비교는 분모 곱해서 정수형으로
셔츠와 카라를 정점으로 보고 카라 조건에 맞게 연결해서 간선 정보를 만듬
가능한 간선 갯수를 최대로 하기..

매칭이 비어있으면 매칭시켜주고 이미 매칭되있으면 가능한 거 재귀로 찾기
매칭 가능할때마다 최대값 갱신하기 -> += 1
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static long[] shirts;
    static long[] collars;
    // i번 카라에 연결된 셔츠 idx 저장
    static int[] connectTo;
    static boolean[] visited;
    static ArrayList<Integer>[] board;

    // s<=2c 4c<=3s
    // s<=c 4c<=5*s
    static boolean can(long s, long c){
        return (s<=2*c && 4*c<=3*s) || (s<=c && 4*c<=5*s);
    }

    // 해당 셔츠한테 카라 가능하면 true 아니면 false
    // 찾은 카라가 이미 다른 셔츠에 할당된거면 그 셔츠한테 다른 카라를 할당시켜줄 수 있는지
    // 재귀로 찾기
    static boolean dfs(int i){
        for(int next : board[i]){
            if(visited[next])
                continue;
            visited[next] = true;
            if(connectTo[next] == -1 || dfs(connectTo[next])){
                connectTo[next] = i;
                return true;
            }
        }
        return false;
    }

    // 카라 쓴거 또쓰면 안되니까 한 셔츠 카라짝 찾아줄때마다 새로 visit 체킹
    // 새 카라짝 찾아줄수 있으면 cnt 늘리고 못찾아주면 넘어가기
    static int sol(){
        connectTo = new int[M];
        Arrays.fill(connectTo, -1);
        int cnt = 0;
        for(int i = 0; i<N; i++){
            visited = new boolean[M];
            if(dfs(i))
                cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        shirts = new long[N];
        collars = new long[M];
        for(int i = 0; i<N; i++){
            shirts[i] = Long.parseLong(br.readLine());
        }
        for(int i = 0; i<M; i++){
            collars[i] = Long.parseLong(br.readLine());
        }
        board = new ArrayList[N];
        for(int i = 0; i<N; i++){
            board[i] = new ArrayList<>();
            for(int j = 0; j<M; j++){
                if(can(shirts[i], collars[j]))
                    board[i].add(j);
            }
        }
        System.out.println(sol());
    }
}