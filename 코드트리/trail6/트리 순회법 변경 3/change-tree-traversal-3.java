import java.util.*;
import java.io.*;

/*
후위순회 맨뒤가 루트임
루트 값 중위에서 어디있는지 찾아가지고 왼쪽 오른쪽 자식 파트 나누고 
출력하려는 방법대로 dfs 호출이랑 루트처리 
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[] in;
    static int[] post;
    static Map<Integer, Integer> nToI = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    static void dfs(int ep, int si, int ei){
        if(si > ei)
            return;
        
        int root = post[ep];
        int rootIdx = nToI.get(root);
        int rightSize = ei-rootIdx;

        sb.append(root).append(" ");
        dfs(ep-rightSize-1, si, rootIdx-1);
        dfs(ep-1, rootIdx+1, ei);
    }

    public static void main(String[] args) throws IOException{
        N = read();
        in = new int[N];
        post = new int[N];

        for(int i = 0; i<N; i++){
            in[i] = read();
            nToI.put(in[i], i);
        }
        
        for(int i = 0; i<N; i++){
            post[i] = read();
        }
        
        dfs(N-1, 0, N-1);
        System.out.print(sb);
    }
}