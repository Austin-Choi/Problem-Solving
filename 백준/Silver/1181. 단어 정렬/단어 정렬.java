
import java.io.*;
import java.util.*;

class Info implements Comparable<Info>{
    int len;
    String str;

    public Info(String s, int l){
        this.str = s;
        this.len = l;
    }

    @Override
    public int compareTo(Info o){
        if(o.len == this.len){
            char[] c1 = this.str.toCharArray();
            char[] c2 = o.str.toCharArray();
            for(int i = 0; i<this.len; i++){
                if(c1[i] != c2[i])
                    return c1[i] - c2[i];
            }
            return 0;
        }
        return this.len - o.len;
    }
}
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Set<String> s = new HashSet<>();
        for(int n = 0; n<N; n++){
            s.add(br.readLine());
        }

        Iterator<String> it = s.iterator();
        PriorityQueue<Info> pq = new PriorityQueue<>();
        while(it.hasNext()){
            String temp = it.next();
            pq.add(new Info(temp, temp.length()));
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            sb.append(pq.poll().str).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
