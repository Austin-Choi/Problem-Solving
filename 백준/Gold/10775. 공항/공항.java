import java.io.*;

public class Main {
    private static int G,P;
    private static int[] parent;
    private static int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    private static void union(int x, int y){
        if(y >= parent.length)
            return;
        x = find(x);
        y = find(y);
        parent[y] = x;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        parent = new int[G+1];
        for(int i = 0; i<parent.length; i++){
            parent[i] = i;
        }

        int count = 0;
        int g = 0;
        int idx = 0;
        for(int p = 0; p<P; p++){
            g = Integer.parseInt(br.readLine());
            idx = find(g);
            // 부모를 찾았는데 루트에 있으면 더이상 도킹못함
            if(idx == 0)
                break;
            // 다음 위치를 잡아주는 순서에 맞게 잡아주기
            // 여기서는 g랑 같거나 더작은 가능한 위치를 찾아야 함
            union(idx-1, idx);
            count++;
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }
}
