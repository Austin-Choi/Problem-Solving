/*
배열로 전부 처리-> DFS로 하면 시복 터짐
move k : 트리의 현재 노드에서 k만큼 이동, +면 자식쪽으로 -면 부모쪽으로
euler 투어 활용하기 + treeset (화면 구성)

입력
DFS로 in, out, 1의 자식들 screen에 올림

move
curIn으로 higher, lower 사용해서 k번 반복하고 최종 노드 출력
-> treeset higher는 다음 노드, lower는 현재 노드의 바로 위 노드

toggle
close - 서브트리 제거
open - dfs로 서브트리 추가
 */
import java.util.*;
import java.io.*;
public class Main {
    static ArrayList<Integer>[] children;
    // in -> 노드에 들어간 시점 기록
    // out -> 해당 노드의 모든 자식 탐색 끝난 시점
    // 오일러 투어 : 어떤 노드 u의 서브트리는 항상 in[u] ~ out[u] 구간에 정확히 들어감
    static int[] in, out, eulerToNode;
    static int time = 0;

    static boolean[] isOpen;
    static TreeSet<Integer> screen = new TreeSet<>();
    static int cursor;

    //오일러투어
    static void dfs(int n){
        in[n] = ++time;
        eulerToNode[time] = n;

        for(int child : children[n]){
            dfs(child);
        }
        out[n] = time;
    }

    static void open(int n){
        screen.add(in[n]);

        if(isOpen[n]){
            for(int child : children[n])
                open(child);
        }
    }

    static void close(int n){
        // 자기 자신 제외하고 서브트리 제거
        screen.subSet(in[n], false, out[n], true).clear();
    }
    static int N,Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        // 자식 관계 입력 받기
        children = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            children[i] = new ArrayList<>();
        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            for(int j = 0; j<size; j++){
                int u = Integer.parseInt(st.nextToken());
                children[i].add(u);
            }
        }

        in = new int[N+1];
        out = new int[N+1];
        eulerToNode = new int[N+1];
        isOpen = new boolean[N+1];

        //1번부터 오일러번호
        dfs(1);

        //초기 스크린구성
        for(int c : children[1])
            screen.add(in[c]);

        //초기 커서 위치
        cursor = children[1].get(0);

        StringBuilder sb = new StringBuilder();
        while(Q-->0){
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if(cmd.equals("toggle")){
                if(isOpen[cursor]){
                    close(cursor);
                    isOpen[cursor] = false;
                }
                else{
                    isOpen[cursor] = true;
                    for(int child : children[cursor])
                        open(child);
                }
            }
            else{
                int k = Integer.parseInt(st.nextToken());

                int curIn = in[cursor];

                if(k>0){
                    while(k-- > 0){
                        Integer next = screen.higher(curIn);
                        if(next == null)
                            break;
                        curIn = next;
                    }
                }
                else{
                    k = -k;
                    while(k-- > 0){
                        Integer prev = screen.lower(curIn);
                        if(prev == null)
                            break;
                        curIn = prev;
                    }
                }

                cursor = eulerToNode[curIn];
                sb.append(cursor).append("\n");
            }
        }
        System.out.print(sb);
    }
}
