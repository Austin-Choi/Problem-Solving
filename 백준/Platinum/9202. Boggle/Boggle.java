
/*
일단 사전에 있는거 트라이에 넣음
child, isEnd
보드에서 8방향 dfs하면서 한칸 한번만 방문가능하니까 visited 백트래킹해주고
다음 갈 칸의 pruning 필요하니까 child 정보 필요해서 상태는 i,j,node
isEnd = true면 단어 완성한거라 점수, 가장긴 단어 뭐 그런거 갱신하는거 하고
false로 바꿔서 중복찾기 방지함
 */
import java.io.*;
public class Main {
    static int[] di = {-1,-1,0,1,1,1,0,-1};
    static int[] dj = {0,1,1,1,0,-1,-1,-1};
    static int w;
    static int b;
    static char[][] board;
    static class Node{
        Node[] children = new Node[26];
        boolean isEnd;
        String actual;
        int lastFoundBoard = -1;
    }
    // 트라이는 한번만 boggle DFS는 각각의 board에 대해 여러번
    static Node trie = new Node();
    static void insert(String s){
        Node node = trie;
        for(int i = 0; i<s.length(); i++){
            int idx = s.charAt(i) - 'A';
            if(node.children[idx] == null)
                node.children[idx] = new Node();
            node = node.children[idx];
        }
        node.isEnd = true;
        node.actual = s;
    }
    static int score;
    static int getScore(String s){
        if(s.length() <= 2)
            return 0;
        if(s.length() <= 4)
            return 1;
        if(s.length() <= 5)
            return 2;
        if(s.length() <= 6)
            return 3;
        if(s.length() <= 7)
            return 5;
        return 11;
    }
    static String maxLenStr;
    static boolean[][] visited;
    static int found;
    static void dfs(int i, int j, Node cur, int b){
        if(cur.isEnd && cur.lastFoundBoard != b){
            cur.lastFoundBoard = b;
            String ss = cur.actual;
            score += getScore(ss);
            found++;

            if(maxLenStr == null)
                maxLenStr = ss;
            else{
                if(maxLenStr.length() == ss.length()){
                    if(maxLenStr.compareTo(ss) > 0)
                        maxLenStr = ss;
                }
                else if(maxLenStr.length() < ss.length())
                    maxLenStr = ss;
            }
            // 리턴없이 더깊게찾기
        }

        for(int d=0; d<8; d++){
            int ni = i + di[d];
            int nj = j + dj[d];
            if(ni < 0 || nj < 0 || ni >= 4 || nj >= 4)
                continue;
            if(cur.children[board[ni][nj]-'A'] == null)
                continue;
            if(visited[ni][nj])
                continue;
            visited[ni][nj] = true;
            dfs(ni,nj,cur.children[board[ni][nj]-'A'], b);
            visited[ni][nj] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        w = Integer.parseInt(br.readLine());
        for(int i =0; i<w; i++){
            insert(br.readLine());
        }
        br.readLine();
        StringBuilder sb = new StringBuilder();
        b = Integer.parseInt(br.readLine());
        while(b>0){
            score = 0;
            maxLenStr = null;
            found = 0;
            visited = new boolean[4][4];

            board = new char[4][4];
            for(int i=0; i<4; i++){
                board[i]=br.readLine().toCharArray();
            }
            if(b > 1)
                br.readLine();

            // dfs로 트라이 정보로 프루닝해서 모든 경우
            // 브루트포스로 해결
            for(int i = 0; i<4; i++){
                for(int j = 0; j<4; j++){
                    int idx = board[i][j] - 'A';
                    if(trie.children[idx] == null)
                        continue;
                    visited[i][j] = true;
                    dfs(i,j,trie.children[idx],b);
                    visited[i][j] = false;
                }
            }
            sb.append(score).append(" ").append(maxLenStr).append(" ").append(found).append("\n");
            b--;
        }
        System.out.print(sb);
    }
}