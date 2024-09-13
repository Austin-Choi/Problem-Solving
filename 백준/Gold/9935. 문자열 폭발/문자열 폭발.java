// stack
// c4로 주어졌을때 cc44 있는거 보고 알음

// add하고 contains 찍어보기
// 만약 true 나오면 폭발 문자열 길이만큼 pop하기
// 반복하고 입력 끝났을 때
// isEmpty 한번 해보고 true면 FRULA
// 아니면 문자열 있는거 출력하기

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] list = br.readLine().split("");
        String[] bomb = br.readLine().split("");
        String lastBomb = bomb[bomb.length-1];

        Deque<String> s = new ArrayDeque<>();
        for(int i = 0; i<list.length; i++){
            s.addLast(list[i]);
            Deque<String> temp = new ArrayDeque<>();
            if(s.size() >= bomb.length && s.peekLast().equals(lastBomb)){
                Deque<String> tempStr = new ArrayDeque<>();
                for(int ii = 0; ii<bomb.length; ii++){
                    tempStr.addFirst(s.pollLast());
                }
                if(!tempStr.toString().equals(Arrays.toString(bomb))){
                    s.addAll(tempStr);
                }
            }
        }
        if(!s.isEmpty()){
            StringBuilder sb = new StringBuilder();
            while(!s.isEmpty()){
                sb.append(s.pollFirst());
            }
            bw.write(sb + "\n");
        }
        else
            bw.write("FRULA" + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
