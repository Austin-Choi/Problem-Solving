import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// deque 써서 뒤집을때마다 poll 해줄 방향 바꿔주기 true
public class Main {
    public static String ac(Deque<Integer> dq, char[] cmds){
        //type R 명령마다 바꿔주기
        boolean type = true;
        for(char cmd : cmds){
            if(cmd == 'R'){
                type = !type;
            }
            else{
                if(dq.isEmpty())
                    return "error";

                if (type)
                    dq.pollFirst();
                else
                    dq.pollLast();
            }

        }
        StringBuilder sb = new StringBuilder("[");
        while(!dq.isEmpty()){
            if(type)
                sb.append(dq.pollFirst());
            else
                sb.append(dq.pollLast());
            if(!dq.isEmpty())
                sb.append(",");
        }
        // 맨 마지막 , 콤마 삭제 => 이거 때문에 안되던거였음!!!!!!!!!
        // sb.delete(sb.length()-1, sb.length());
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // T가 0이 될때까지라는 뜻
        while(T-->0){
            Deque<Integer> dq = new LinkedList<>();
            char[] cmds = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());

            // 빈 배열 들어오면 error
            String cmdStr = br.readLine();
            // [ ] 떼고
            String cmdString = cmdStr.substring(1,cmdStr.length()-1);
            // ,기준으로 숫자 배열 스플릿해서 데크에 넣어줌
            for(String s : cmdString.split(",")){
                if(!s.equals(""))
                    dq.add(Integer.parseInt(s));
            }

            System.out.println(ac(dq, cmds));
        }
    }
}
