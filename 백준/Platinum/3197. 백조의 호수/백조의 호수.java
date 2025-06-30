
import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int[] parent;
    static Queue<int[]> meltQ = new ArrayDeque<>();
    static int swan1 = -1, swan2 = -1;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int index(int i, int j) {
        return i * C + j;
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) parent[y] = x;
    }

    static void initUnionFind() {
        parent = new int[R * C];
        for (int i = 0; i < R * C; i++) {
            parent[i] = i;
        }
    }

    static void preProcess() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != 'X') {
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dx[d];
                        int nj = j + dy[d];
                        if (ni < 0 || nj < 0 || ni >= R || nj >= C) continue;
                        if (map[ni][nj] != 'X') {
                            union(index(i, j), index(ni, nj));
                        }
                        if (map[ni][nj] == 'X') {
                            meltQ.offer(new int[]{i, j});
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        int swanCount = 0;

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'L') {
                    if (swanCount == 0) {
                        swan1 = index(i, j);
                    } else {
                        swan2 = index(i, j);
                    }
                    map[i][j] = '.'; // 백조 위치도 물로 처리
                    swanCount++;
                }
            }
        }

        initUnionFind();
        preProcess();

        int day = 0;
        while (true) {
            if (find(swan1) == find(swan2)) break;

            Queue<int[]> nextMeltQ = new ArrayDeque<>();

            while (!meltQ.isEmpty()) {
                int[] cur = meltQ.poll();
                int i = cur[0], j = cur[1];

                for (int d = 0; d < 4; d++) {
                    int ni = i + dx[d];
                    int nj = j + dy[d];
                    if (ni < 0 || nj < 0 || ni >= R || nj >= C) continue;

                    if (map[ni][nj] == 'X') {
                        map[ni][nj] = '.';
                        nextMeltQ.offer(new int[]{ni, nj});

                        for (int nd = 0; nd < 4; nd++) {
                            int nni = ni + dx[nd];
                            int nnj = nj + dy[nd];
                            if (nni < 0 || nnj < 0 || nni >= R || nnj >= C) continue;
                            if (map[nni][nnj] == '.') {
                                union(index(ni, nj), index(nni, nnj));
                            }
                        }
                    } else {
                        union(index(i, j), index(ni, nj));
                    }
                }
            }

            meltQ = nextMeltQ;
            day++;
        }

        bw.write(String.valueOf(day));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}