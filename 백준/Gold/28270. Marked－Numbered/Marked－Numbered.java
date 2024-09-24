import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int c = Integer.parseInt(br.readLine());
        Map<Integer, Integer> m = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        int before = -1;
        while(st.hasMoreTokens()){
            int temp = Integer.parseInt(st.nextToken());
            if(before == -1){
                if(temp != 1){
                    flag = true;
                    break;
                }
                before = temp;
                m.put(temp, m.getOrDefault(temp, 0)+1);
                sb.append(1).append(" ");
            }
            else{
                if(Math.abs(before - temp)>1){
                    flag = true;
                    break;
                }
                /*
                    (i) 이전의 숫자보다 큰 숫자가 나타나면 1
                    (ii) 이전의 숫자중에 같은게 있으면 그 숫자의 값 += 1
                    (iii) 이전의 숫자보다 작은 숫자면 예전에 저장됬던 키값중 동일한게 있나 보고 그 키값으로 불러와서 +1 하고
                    , 그거보다 큰 키값들 전부 0으로 초기화
                 */
                if(before < temp){
                    m.put(temp, 1);
                    sb.append(1).append(" ");
                }
                else if(before == temp){
                    m.put(temp, m.get(temp)+1);
                    sb.append(m.get(temp)).append(" ");
                }
                else{
                    m.put(temp, m.get(temp)+1);
                    sb.append(m.get(temp)).append(" ");
                    for(int n : m.keySet()){
                        if(n > temp){
                            m.put(n, 0);
                        }
                    }
                }
                before = temp;
            }

        }
        if(flag)
            bw.write(-1+"\n");
        else
            bw.write(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}