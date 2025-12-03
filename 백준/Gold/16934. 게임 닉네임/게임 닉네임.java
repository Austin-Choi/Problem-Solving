/*
N이 10만이니까 트라이써서 삽입하고
같은게 있으면 계속 더하다가 값이 기존 트라이값과 다른게 나오면
-> 거기서 닉넴 인덱스 구함
만약에 입력이 끝까지 다 들어갔다면 거기를 끝으로 가지는 것들의 count를 1올리고 count가 2 이상일때
입력닉넴 + count로 닉넴 만들기
--
노드 나올수 있는게 10 * 10만이므로
100만의 풀을 인덱스로 잡고 트라이에 넣기
-> GC 최적화
 */
import java.io.*;
public class Main {
    static class Node{
        int[] children;
        int count;
    }

    // 노드 pool 방식
    static final int MAX = 100_000 * 10;
    static Node[] pool = new Node[MAX];
    // ptr = 0 은 루트
    static int ptr = 1;

    static {
        pool[0] = new Node();
    }
    static int newNode(){
        pool[ptr] = new Node();
        return ptr++;
    }
    // 버그 : 전부 겹치는거 null로 나옴
    // -> 다 삽입하고 nickIdx 갱신 안됬으면 입력+반복횟수 가 닉넴
    static String insert(String s){
        // 인덱스기반이라 인덱스 기준
        int cur = 0;
        int nickIdx = -1;
        String nickname = null;

        for(int i = 0; i<s.length(); i++){
            int idx = s.charAt(i) - 'a';
            Node curNode = pool[cur];

            if(curNode.children == null)
                curNode.children = new int[26];

            // 트라이에서 처음보는 문자 나타남
            // -> 여기서 닉넴 범위 인덱스 한번만 저장함
            if(curNode.children[idx] == 0) {
                curNode.children[idx] = newNode();

                // 처음으로 달라지는 곳에서 닉네임 찾아야됨
                // 그 뒤는 트리 삽입때문에 계속됨
                if(nickIdx == -1){
                    nickIdx = i;
                }
            }

            cur = curNode.children[idx];
        }
        Node last = pool[cur];
        last.count++;

        if(nickIdx == -1)
            nickname = s;
        else
            nickname = s.substring(0, nickIdx+1);

        if(last.count > 1)
            nickname += last.count;

        return nickname;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder(N*12);
        while(N-->0)
            sb.append(insert(br.readLine())).append("\n");
        System.out.println(sb);
    }
}
