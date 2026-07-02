import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
        int Q = Integer.parseInt(br.readLine());
        int s = 0;
        StringBuilder sb = new StringBuilder();

        while(Q-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            
            if(cmd.equals("clear")){
                s = 0;
            }
            else{
                int cur = Integer.parseInt(st.nextToken());
                // 집합에 추가니까 OR연산
                if(cmd.equals("add"))
                    s |= (1<<cur);
                
                // 집합에서 제거
                // 삭제해야하는 비트를 0으로 만들고 and 연산
                else if(cmd.equals("delete"))
                    s &= ~(1<<cur);
                
                // bitmask에서 방문확인이엇나 여튼 존재하는지 확인
                else if(cmd.equals("print"))
                    sb.append((((s >> cur) & 1) != 0) ? "1\n" : "0\n");
                
                //토글은 xor로
                else if(cmd.equals("toggle"))
                    s ^= (1 << cur);
                
                // 공집합은 0
                else
                    s = 0;
            }
        }
        System.out.print(sb);
    }
}