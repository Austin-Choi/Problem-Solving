import java.util.*;
import java.io.*;

public class Main {
    private static ArrayList<Integer> ans;
    private static void solution(List<Integer> a, List<Integer> b){
        if(a.isEmpty() || b.isEmpty())
            return;
        int maxA = Collections.max(a);
        int maxB = Collections.max(b);
        int maxAi = a.indexOf(maxA);
        int maxBi = b.indexOf(maxB);

        if(maxA == maxB){
            ans.add(maxA);
            solution(a.subList(maxAi + 1, a.size()), b.subList(maxBi + 1, b.size()));
        }
        else{
            if(maxA > maxB){
                a.remove(maxAi);
                solution(a,b);
            }
            else{
                b.remove(maxBi);
                solution(a,b);
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> nl = new ArrayList<>();

        for(int n = 0; n<N; n++){
            int tmp = Integer.parseInt(st.nextToken());
            nl.add(tmp);
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> ml = new ArrayList<>();

        for(int i = 0; i<M; i++){
            int tmp = Integer.parseInt(st.nextToken());
            ml.add(tmp);
        }

        ans = new ArrayList<>();
        solution(nl, ml);

        StringBuilder sb = new StringBuilder();
        sb.append(ans.size()).append("\n");
        for(int i = 0; i<ans.size(); i++){
            sb.append(ans.get(i)).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
