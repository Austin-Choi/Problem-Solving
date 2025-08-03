import java.util.*;
import java.io.*;
public class Main {
    static int N,M,K;
    static int[] indegree;
    static ArrayList<Integer>[] board;
    static Map<String, Integer> names = new HashMap<>();
    static String[] idxToName;
    static ArrayList<Integer>[] children;
    static ArrayList<Integer> roots = new ArrayList<>();
    static void topologySort(){
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i<N; i++){
            if(indegree[i] == 0){
                q.add(i);
                roots.add(i);
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int next : board[cur]){
                if(--indegree[next] == 0) {
                    children[cur].add(next);
                    q.add(next);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        idxToName = new String[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        board = new ArrayList[N];
        children = new ArrayList[N];
        for(int n = 0; n<N; n++){
            board[n] = new ArrayList<>();
            children[n] = new ArrayList<>();
        }
        indegree = new int[N];

        for(int i = 0; i<N; i++){
            String name = st.nextToken();
            names.put(name, i);
            idxToName[i] = name;
        }
        M = Integer.parseInt(br.readLine());
        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            indegree[names.get(a)]++;
            board[names.get(b)].add(names.get(a));
        }

        topologySort();

        Collections.sort(roots, Comparator.comparing(a -> idxToName[a]));
        for(ArrayList<Integer> li : children){
            Collections.sort(li, Comparator.comparing(a->idxToName[a]));
        }

        StringBuilder sb = new StringBuilder();

        ArrayList<Integer> ppl = new ArrayList<>();
        for(int i = 0; i<N; i++){
            ppl.add(i);
        }
        Collections.sort(ppl, Comparator.comparing(a->idxToName[a]));

        sb.append(roots.size()).append("\n");
        for(int i : roots){
            sb.append(idxToName[i]).append(" ");
        }
        sb.append("\n");
        for(int i : ppl){
            sb.append(idxToName[i]).append(" ");
            sb.append(children[i].size()).append(" ");
            for(int j = 0; j<children[i].size(); j++){
                sb.append(idxToName[children[i].get(j)]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
