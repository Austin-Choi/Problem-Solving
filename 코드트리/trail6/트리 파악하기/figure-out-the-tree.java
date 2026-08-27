import java.util.*;
import java.io.*;



public class Main {
    static class Node{
        // 사전순 정렬 필요
        TreeMap<String, Node> children = new TreeMap<>();
        boolean isEnd;
    }

    static Node root = new Node();
    static ArrayList<String>[] A;

    static void insert(ArrayList<String> s){
        Node t = root;
        for(String ss : s){
            if(!t.children.containsKey(ss)){
                t.children.put(ss, new Node());
            }
            t = t.children.get(ss);
        }
        t.isEnd = true;
    }


    static StringBuilder sb = new StringBuilder();
    static void dfs(Node cur, int depth){   
        for(Map.Entry<String, Node> e : cur.children.entrySet()){
            for(int i = 0; i<depth; i++){
                sb.append("--");
            }
            sb.append(e.getKey()+"\n");
            dfs(e.getValue(), depth+1);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        A = new ArrayList[N];

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            A[i] = new ArrayList<>();

            for(int j=0; j<c; j++){
                A[i].add(st.nextToken());
            }
        }

        for(int i = 0; i<N; i++){
            insert(A[i]);
        }

        dfs(root, 0);
        System.out.print(sb);
    }
}