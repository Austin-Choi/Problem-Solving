import java.util.*;
import java.io.*;

/*
전위순회 결과중 맨 앞에 루트노드일거고 
그걸 이용해서 중위 순회 결과를 보고 트리를 구성하고
구성된 트리를 후위 순회 돌리기

3 2 1 4
2 3 4 1

    3
  2  1
    4

2 4 1 3


*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;

    static int[] pre;
    static int[] in;
    static Map<Integer, Integer> nToI = new HashMap<>();
    static int root;

    static StringBuilder sb =new StringBuilder();

    // 복원하지 말고 바로 후위로 뽑으려면
    // L , R, root처리
    // preStart (root), inStart, inEnd
    static void dfs(int pi, int si, int ei){
        if(si > ei)
            return;

        int root = pre[pi];
        int rootIdx = nToI.get(root);
        int leftSize = rootIdx - si;

        dfs(pi+1, si, rootIdx-1);
        dfs(pi+leftSize+1, rootIdx+1, ei);
        sb.append(root).append(" ");    
    }

    public static void main(String[] args) throws IOException{
        N = read();

        pre = new int[N];
        in = new int[N];
        for(int i = 0; i<N; i++){
            pre[i] = read();
        }
        for(int i = 0; i<N; i++){
            in[i] = read();
            nToI.put(in[i], i);
        }
        dfs(0,0,N-1);
        System.out.print(sb);
    }
}