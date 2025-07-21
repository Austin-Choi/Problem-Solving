
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();
    static class Node{
        String val;
        TreeMap<String, Node> children;

        Node(String val){
            this.val = val;
            this.children = new TreeMap<>();
        }

        void insert(String[] path, int idx){
            if(idx >= path.length)
                return;
            String next = path[idx];
            children.putIfAbsent(next, new Node(next));
            children.get(next).insert(path, idx+1);
        }

        void print(int depth){
            if(val != null){
                for(int i= 1; i<depth; i++)
                    sb.append("--");
                sb.append(val).append("\n");
            }
            for(Node child : children.values()){
                child.print(depth + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        // 가상의 최상위 루트, 빈 노드로 설정
        Node root = new Node(null);
        for(int n = 0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            int I = Integer.parseInt(st.nextToken());
            String[] path = new String[I];
            for(int i= 0; i<I; i++)
                path[i] = st.nextToken();
            root.insert(path, 0);
        }

        root.print(0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
