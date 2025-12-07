/*
주어진 식물 좌표를 주어진 개구리의 방향 따라서 각자 정렬한 리스트 두개를 가지고
현재 위치에서 방향 골랐을때 해당 방향으로 하나 읽어서 visited 체킹해주고 (좌표 문자열 해싱으로 map에 넣어서 체킹)
뭐 이런식으로??

--, ++
-+, +-

다음 좌표 찾아서 정렬된 리스트에서 방문처리함
아 근데 이거 정렬 기준이 2개씩이고..
--
TreeMap써서 하기
같은 대각선에서는 i값만 보면 그 점의 j값을 유도할 수 있음
i를 기준으로 삽입, 삭제 -> key값으로
 */

import java.util.*;
import java.io.*;
public class Main {
    static int N,K;
    static char[] dirs;
    static int[] pi, pj;
    static boolean[] visited;

    static Map<Integer, TreeMap<Integer, Integer>> li1 = new HashMap<>();
    static Map<Integer, TreeMap<Integer, Integer>> li2 = new HashMap<>();

    static int move(int curId, char dir){
        int i = pi[curId];
        int j = pj[curId];
        int id = curId;

        if(dir =='A'){
            int key = i-j;
            TreeMap<Integer, Integer> m = li1.get(key);
            // i+1 이상의 모든 Entry를 정렬순서로 반환
            SortedMap<Integer, Integer> tail = m.tailMap(i+1);
            if(!tail.isEmpty())
                // 제일 가까운거니까 first
                id = tail.get(tail.firstKey());
        }
        else if(dir == 'B'){
            int key = i+j;
            TreeMap<Integer, Integer> m = li2.get(key);
            SortedMap<Integer, Integer> tail = m.tailMap(i+1);
            if(!tail.isEmpty())
                id = tail.get(tail.firstKey());
        } else if (dir == 'C') {
            int key = i+j;
            TreeMap<Integer, Integer> m = li2.get(key);
            // i를 포함하지 않는 (i 미만의) Entry를 정렬순서로 반환
            SortedMap<Integer, Integer> head = m.headMap(i, false);
            if(!head.isEmpty())
                // 제일 가까운거니까 last
                id = head.get(head.lastKey());
        }
        else if(dir == 'D'){
            int key = i-j;
            TreeMap<Integer, Integer> m = li1.get(key);
            SortedMap<Integer, Integer> head = m.headMap(i, false);
            if(!head.isEmpty())
                id = head.get(head.lastKey());
        }

        // 다음 식물로 점프했으면 TreeMap에서 제거하기
        if(id != curId){
            int ni = pi[id];
            int nj = pj[id];
            li1.get(ni - nj).remove(ni);
            li2.get(ni + nj).remove(ni);
            visited[id] = true;
        }

        return id;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dirs = br.readLine().toCharArray();
        pi = new int[N];
        pj = new int[N];
        visited = new boolean[N];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            pi[i] = Integer.parseInt(st.nextToken());
            pj[i] = Integer.parseInt(st.nextToken());

            li1.computeIfAbsent(pi[i]-pj[i], k->new TreeMap<>()).put(pi[i], i);
            li2.computeIfAbsent(pi[i]+pj[i], k->new TreeMap<>()).put(pi[i], i);
        }

        int cur = 0;
        visited[cur] = true;
        li1.get(pi[cur] - pj[cur]).remove(pi[cur]);
        li2.get(pi[cur] + pj[cur]).remove(pi[cur]);


        for(char d : dirs){
            cur = move(cur, d);
        }

        System.out.println(pi[cur]+" "+pj[cur]);
    }
}
