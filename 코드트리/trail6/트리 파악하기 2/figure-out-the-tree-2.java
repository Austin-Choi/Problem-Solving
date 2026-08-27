import java.util.*;
import java.io.*;



public class Main {
    static class Node{
        Node[] children = new Node[26];
        boolean isEnd;

        Node(){
            for(int i =0 ; i<26; i++){
                this.children[i] = null;
            }
            this.isEnd = false;
        }
    }

    static Node root = new Node();

    static void insert(ArrayList<Character> s){
        Node t = root;
        for(int i = 0; i<s.size(); i++){
            int idx = s.get(i) - 'A';
            if(t.children[idx] == null){
                t.children[idx] = new Node();
            }
            t = t.children[idx];
        }
        t.isEnd = true;
    }

    static StringBuilder sb = new StringBuilder();

    static void dfs(Node cur, int depth){
        for(int i = 0; i<26; i++){
            if(cur.children[i] != null){
                for(int d = 0; d<depth; d++){
                    sb.append("--");
                }
                sb.append((char) ('A' + i));
                sb.append("\n");

                dfs(cur.children[i], depth+1);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Character>[] li = new ArrayList[N];

        for(int i = 0; i<N; i++){
            li[i] = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            for(int j = 0; j<len; j++)
                li[i].add(st.nextToken().charAt(0));
        }

        for(int i = 0; i<N; i++){
            insert(li[i]);
        }

        dfs(root, 0);
        System.out.print(sb);
    }
}