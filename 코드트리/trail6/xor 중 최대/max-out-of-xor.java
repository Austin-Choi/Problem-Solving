import java.util.*;
import java.io.*;

/*
주어진 정수를 이진수 문자열로 바꾸고 트라이에 넣기
-> 최댓값을 만드려면 높은 비트부터 1을 만들어야하니까
a xor b에서 a의 각 비트에 대해 반대 비트를 가진 수를 찾기

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static class Node{
        Node[] children = new Node[2];
    }

    static Node root = new Node();

    static void insert(int x){
        Node cur = root;;
        for(int i = 30; i>=0; i--){
            int bit = (x >> i) & 1;
            if(cur.children[bit] == null)
                cur.children[bit] = new Node();
            cur = cur.children[bit];
        }
    }

    static int getMax(int x){
        Node cur = root;
        int rst = 0;

        for(int i = 30; i>=0; i--){
            int bit = (x >> i) & 1;
            int opp = bit ^ 1;
            // 반대 비트가 비어있으면 현재 비트로 감
            if(cur.children[opp] == null){
                cur = cur.children[bit];
            }
            // 반대 비트가 있으면 반대 비트로 가고 
            // or (1<<i) -> 상대 수를 이진수로 build
            else{
                rst |= (1<<i);
                cur = cur.children[opp];
            }
        }
        return rst;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] A = new int[N];
        for(int i = 0; i<N; i++){
            int cur = read();
            insert(cur);
            A[i] = cur;
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            ans = Math.max(ans, getMax(A[i]));
        }
        System.out.print(ans);
    }
}