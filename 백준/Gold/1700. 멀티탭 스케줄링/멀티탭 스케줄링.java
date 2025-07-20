/*
OPT = 최적 페이지 교체 알고리즘, 메모리 매칭 알고리즘중 하나임
전기용품의 사용 순서를 보고
미래를 안다고 가정할 때

1 일단 멀티탭에 자리가 있으면 꼽음
2 이미 꽂혀있으면 안함
3 비어있지 않고 꽂혀있지도 않으면
-> 가장 먼 미래에 사용되거나 사용되지 않을 것을 뽑고 다음 것을 꼽음
ans++

멀티탭은 중복될일 없으니 set
사용되지 않을 전기용품 종류 구하기 -> 함수
현재 i부터 끝까지 임의의 set에 넣고
현재 멀티탭 item들을 하나하나 꺼내서 임의의 set에 contain 되어있지 않으면 break하고 그거 리턴
리턴값 -> int[2] [0] => 종류( 사용되지 않을 전기용품 없으면 -1), [1] => 1이면 사용되지 않을 것 잇음, 0이면 없음

지금 시점에서부터 사용되는 시점이 가장 늦은 전기용품 종류 구하기 -> 함수
tap의 아이템 하나 뽑아서
현재 시점 i부터 k-1 까지 탐색해서
그게 처음 등장할때까지의 길이를 구해놓고
그게 가장 큰 tap의 아이템 리턴하기

 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,K;
    static Set<Integer> tap;
    static int[] board;
    static int getRemoveTarget(int cur){
        int latestIdx = -1;
        int itemToRemove = -1;
        for(int item : tap){
            int nextUse = Integer.MAX_VALUE;
            for(int i = cur; i<K; i++){
                if(item == board[i]){
                    nextUse = i;
                    break;
                }
            }
            if(nextUse == Integer.MAX_VALUE)
                return item;

            if(nextUse > latestIdx){
                latestIdx = nextUse;
                itemToRemove = item;
            }
        }
        return itemToRemove;
    }

    public static void main(String[] args) throws Exception{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 멀티탭 set 크기
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[K];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<K; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        tap = new HashSet<>();
        for(int i = 0; i<K; i++){
            if(tap.size() < N){
                tap.add(board[i]);
            }
            else{
                if(tap.contains(board[i]))
                    continue;
                else{
                    // 미래에 아예 사용되지 않을 물건 찾은 경우 먼저 뺌
                    // 탭에 있는 물건이 전부 미래에 언젠가는 사용된다면
                    // 다음으로 등장하는 텀이 가장 긴 아이템 제거해야함
                    tap.remove(getRemoveTarget(i));
                    // 그리고 뺀 자리에 삽입함
                    tap.add(board[i]);
                    ans++;
                }
            }
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
