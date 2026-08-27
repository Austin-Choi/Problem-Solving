import java.util.*;
import java.io.*;



public class Main {
    static class Node{
        Node[] children = new Node[10];
        boolean isEnd;

        Node(){
            this.isEnd = false;
            for(int i =0 ; i<10; i++){
                children[i] = null;
            }
        }
    }

    static Node root = new Node();
    static boolean insert(String s){
        Node t = root;
        boolean is = false;

        for(int i = 0; i<s.length(); i++){
            int idx = s.charAt(i) - '0';
            // 접두사 판정 
            // -> 지금 넣는 문자열이 다른 문자열을 접두사로 이용하는 경우
            if(t.isEnd)
                is = true;
            if(t.children[idx] == null){
                t.children[idx] = new Node();
            }
            t = t.children[idx];
        }

        // 지금 넣는 문자열 자체가 다른 문자열의 접두사가 되는 경우
        for(int i = 0; i<10; i++){
            if(t.children[i] != null){
                is = true;
                break;
            }
        }
        t.isEnd = true;
        return is;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i<N; i++){
            if(insert(br.readLine())){
                System.out.print(0);
                return;
            }
        }
        System.out.print(1);
    }
}