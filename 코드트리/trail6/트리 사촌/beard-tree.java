import java.util.*;
import java.io.*;

/*
트리 내에서 자기 부모의 자식들을 제외한 같은 depth에 있는 노드 갯수 구하라는 뜻

일단 그래프를 구성해야하는데 재귀적으로 넣으면 될거같음

*/

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final StreamTokenizer st = new StreamTokenizer(br);

    static int read() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }

    static int N, K;
    static ArrayList[] g;
    static int[] pos;
    // 입력만으로 parent를 복원해야할거같음..

    // 범위 이슈..
    // 근데 N이 1000이고 수치는 1_000_000이라 sparse함
    static Map<Integer, Integer> parent = new HashMap<>();

    public static void main(String[] args) throws Exception{
        N = read();
        K = read();
        int max = 0;
        pos = new int[N];
        for(int i = 0; i<N; i++){
            pos[i] = read();
            max = Math.max(max, pos[i]);
        }

        // K가 루트노드면 당연히 0일거임
        if(pos[0] == K){
            System.out.print(0);
            return;
        }

        for(int n : pos){
            parent.put(n, -1);
        }
        // 부모 인덱스를 따로 관리하기
        // 처음엔 값으로 둿는데 이게 마지막꺼만 업데이트돼서 앞에껄 기억못함
        int p = 0;
        for(int i =1 ; i<N;i++){
            parent.put(pos[i], pos[p]);
            if(i < N-1 && pos[i+1] != pos[i] + 1){
                p++;
            }
        }

        int ans = 0;
        for(int n : pos){
            // -1 인덱스 접근 방지
            // 객체로 받아서 nullPointException 방지
            Integer pn = parent.get(n);
            Integer pk = parent.get(K);

            if(pn == -1 || pk == -1)
                continue;

            Integer ppn = parent.get(pn);
            Integer ppk = parent.get(pk);

            if(ppn == null || ppk == null)
                continue;
            // 두 노드의 부모가 달라야 함 && 
            // 두 노드의 부모의 부모가 같음 (형제)
            if(!pn.equals(pk) && ppn.equals(ppk))
                ans++;
        }
        System.out.print(ans);
    }
}