import java.util.*;
import java.io.*;

class Info implements Comparable<Info>{
    public long num;

    public Info(long num){
        this.num = num;
    }


    @Override
    public int compareTo(Info o) {
        if(Math.abs(this.num) == Math.abs(o.num)){
            return Long.compare(this.num, o.num);
        }
        return Long.compare(Math.abs(this.num), Math.abs(o.num));
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Info> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        for(int n = 0; n<N; n++){
            long num = Long.parseLong(br.readLine());
            if(num != 0){
                pq.add(new Info(num));
            }
            else{
                if(!pq.isEmpty()){
                    long min = pq.peek().num;
                    pq.poll();
                    sb.append(min).append("\n");
                }
                else{
                    sb.append(0).append("\n");
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
