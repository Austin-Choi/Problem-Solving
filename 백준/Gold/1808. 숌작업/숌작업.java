import java.io.*;
import java.util.*;

public class Main{
    static int N, H;
    static int[] parents;
    static int[] levels;
    static HashSet<Integer>[] children;

    static void setDepth(int node, int h) {
        levels[node] = h;
        for (int child : children[node]) {
            setDepth(child, h + 1);
        }
    }

    static int shom(int parent, int child) {
        children[parent].add(child);
        children[parents[child]].remove(child);
        parents[child] = parent;

        int gap = levels[child] - levels[parent] - 1;
        setDepth(parent, levels[parent]);
        return gap;
    }

    static int findMaxIdx() {
        int value = 0;
        int idx = 0;
        for (int i = 0; i < levels.length; i++) {
            if (value < levels[i]) {
                value = levels[i];
                idx = i;
            }
        }
        return idx;
    }

    static int solution() {
        int cnt = 0;

        while (true) {
            int target = findMaxIdx();

            if (levels[target] > H) {
                int gap = levels[target] - H;
                int child = target;
                int h = Math.min(2, H + 1);

                while (levels[child] > h) {
                    child = parents[child];
                }

                int p;
                if (levels[child] > H) {
                    p = child;
                    for (int i = 0; i < gap + 1; i++) {
                        p = parents[p];
                    }
                } else {
                    p = 0;
                }

                cnt += shom(p, child);
            } else {
                return cnt;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        parents = new int[N];
        levels = new int[N];
        children = new HashSet[N];

        for (int i = 0; i < N; i++) {
            children[i] = new HashSet<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            parents[b] = a;
            children[a].add(b);
        }

        setDepth(0, 0);

        H = Integer.parseInt(br.readLine());

        int ans = solution();
        System.out.println(ans);
    }
}