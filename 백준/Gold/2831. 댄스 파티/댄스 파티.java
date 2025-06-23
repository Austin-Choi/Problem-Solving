import java.io.*;
import java.util.*;


public class Main {
    private static int twoPointer(List<Integer> small, List<Integer> tall){
        int res = 0;
        int s = 0;
        int t = 0;

        while(s < small.size() && t < tall.size()){
            if(Math.abs(small.get(s)) > Math.abs(tall.get(t))){
                res++;
                s++;
                t++;
            }
            else
                t++;
        }
        return res;
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> sMen = new ArrayList<>();
        List<Integer> tMen = new ArrayList<>();

        for(int i = 0; i<N; i++){
            int h = Integer.parseInt(st.nextToken());
            if(h < 0)
                sMen.add(h);
            else
                tMen.add(h);
        }

        st = new StringTokenizer(br.readLine());
        List<Integer> sWomen = new ArrayList<>();
        List<Integer> tWomen = new ArrayList<>();

        for(int i = 0; i<N; i++){
            int h = Integer.parseInt(st.nextToken());
            if(h < 0)
                sWomen.add(h);
            else
                tWomen.add(h);
        }

        tMen.sort(Collections.reverseOrder());
        tWomen.sort(Collections.reverseOrder());
        Collections.sort(sMen);
        Collections.sort(sWomen);

        int ans = twoPointer(sMen, tWomen);

        ans += twoPointer(sWomen, tMen);

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
