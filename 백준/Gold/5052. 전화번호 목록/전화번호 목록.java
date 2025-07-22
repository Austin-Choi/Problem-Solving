import java.io.*;
import java.util.*;
public class Main {
    static int T;
    static int N;
    static int count;
    static class Node{
        int val;
        TreeMap<Integer, Node> m;

        Node(int val){
            this.val = val;
            m = new TreeMap<>();
        }

        public void insert(char[] str, int idx){
            if(idx >= str.length){
                return;
            }
            int num = str[idx] - '0';
            m.putIfAbsent(num, new Node(num));
            m.get(num).insert(str, idx+1);
        }
    }
    static void isRelated(Node cur){
        if(cur.m.isEmpty())
            count++;
        else {
            for (Node n : cur.m.values()) {
                isRelated(n);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T; t++){
            count = 0;
            N = Integer.parseInt(br.readLine());
            Node root = new Node(-1);
            for(int n= 0; n<N; n++){
                char[] tmp = br.readLine().toCharArray();
                root.insert(tmp, 0);
            }
            isRelated(root);
            if(count == N)
                bw.write("YES\n");
            else
                bw.write("NO\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
