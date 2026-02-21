/*
튜플 우선순위 -> a,b / c,d가 있을때 a,c 비교하고 같으면 b,d 비교하는거
입력받을때 i<j인거만 그래프에 추가함 -> 이게 결국 튜플우선순위 만족함
그러면 -1인 조건은 모든 정점이 연결되지 못하는 그래프 -> union-find

생각해보면 여부 따로 구하지말고 그냥 union-find로 MST 구할때 한번에 하고
i<j&&input true인거
i랑 j 끝점이니까 각각 세고
M개만큼 세는데 서로 다른 집합이라서 연결되어야 할때만 M 세주는거 ++하면
임의의 도시끼리 전부 연결되게 되면서 M개만큼 사전순으로 연결된 게 됨

반례 M개보다 MST 길이가 더 짧으면 추가로 더해줘야함
그걸 extra라고 하고 ee로 따로 count해줌
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static boolean[][] input;
    static ArrayList<Integer>[] board;
    static int[] parent;
    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb)
            return;
        parent[pb] = pa;
    }

    static int find(int x){
        if(parent[x] == x)
            return parent[x];
        return parent[x] = find(parent[x]);
    }
    static int[] cnt;
    static int[] count(ArrayList<Integer>[] b){
        int doro = 0;

        // M개 되기 전에 MST가 끝나버릴수도?
        int extra = M - (N-1);
        int ee = 0;
        outer:
        for (int i = 0; i<N; i++){
            for (int j = 0; j<N; j++){
                if(i<j && input[i][j]){
                    int pi = find(i);
                    int pj = find(j);
                    if(pi != pj){
                        union(i,j);
                        cnt[i]++;
                        cnt[j]++;
                        doro++;
                    }
                    else{
                        if(ee < extra){
                            cnt[i]++;
                            cnt[j]++;
                            doro++;
                            ee++;
                        }
                    }
                }
            }
        }

        int root = find(0);
        boolean connected = true;
        for(int i= 1; i<N; i++){
            if(find(i) != root){
                connected = false;
                break;
            }
        }
        
        if(!connected || doro < M)
            cnt = new int[]{-1};

        return cnt;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new boolean[N][N];
        for(int i = 0; i<N; i++){
            char[] t = br.readLine().toCharArray();
            for(int j =0; j<N; j++){
                if(t[j]=='Y')
                    input[i][j] = true;
            }
        }

        board = new ArrayList[N];
        for(int i = 0; i<N; i++)
            board[i] = new ArrayList<>();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(i<j && input[i][j]){
                    board[i].add(j);
                    board[j].add(i);
                }
            }
        }

        parent = new int[N];
        for(int i = 0; i<N; i++)
            parent[i] = i;

        cnt = new int[N];
        cnt = count(board);

        if(cnt[0] == -1){
            System.out.print(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++)
            sb.append(cnt[i]).append(" ");
        System.out.print(sb);
    }
}
