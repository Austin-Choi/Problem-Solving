import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 선분의 개수
        int[][] segments = new int[n][4];

        // 선분 정보 입력
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            segments[i][0] = Integer.parseInt(st.nextToken());
            segments[i][1] = Integer.parseInt(st.nextToken());
            segments[i][2] = Integer.parseInt(st.nextToken());
            segments[i][3] = Integer.parseInt(st.nextToken());
        }

        // 유니온 파인드 초기화
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // 선분 간 교차 여부 검사 및 그룹 병합
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isIntersect(segments[i], segments[j])) {
                    union(i, j);
                }
            }
        }

        // 그룹 개수와 최대 그룹 크기 계산
        int groupCount = 0;
        int maxSize = 0;

        for (int i = 0; i < n; i++) {
            if (find(i) == i) { // 그룹의 루트 노드
                groupCount++;
                maxSize = Math.max(maxSize, size[i]);
            }
        }

        System.out.println(groupCount);
        System.out.println(maxSize);
    }

    // CCW를 이용한 두 선분 교차 판정
    static boolean isIntersect(int[] s1, int[] s2) {
        int x1 = s1[0], y1 = s1[1], x2 = s1[2], y2 = s1[3];
        int x3 = s2[0], y3 = s2[1], x4 = s2[2], y4 = s2[3];

        int ccw1 = ccw(x1, y1, x2, y2, x3, y3);
        int ccw2 = ccw(x1, y1, x2, y2, x4, y4);
        int ccw3 = ccw(x3, y3, x4, y4, x1, y1);
        int ccw4 = ccw(x3, y3, x4, y4, x2, y2);

        // 일반적인 교차 여부
        if (ccw1 * ccw2 < 0 && ccw3 * ccw4 < 0) {
            return true;
        }

        // 일직선 상에 있는 경우 추가 판정
        return (ccw1 == 0 && isOverlap(x1, y1, x2, y2, x3, y3)) ||
               (ccw2 == 0 && isOverlap(x1, y1, x2, y2, x4, y4)) ||
               (ccw3 == 0 && isOverlap(x3, y3, x4, y4, x1, y1)) ||
               (ccw4 == 0 && isOverlap(x3, y3, x4, y4, x2, y2));
    }

    // CCW 계산
    static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
        long crossProduct = (long) (x2 - x1) * (y3 - y1) - (long) (y2 - y1) * (x3 - x1);
        return Long.compare(crossProduct, 0); // > 0: 반시계, < 0: 시계, == 0: 일직선
    }

    // 선분이 겹치는지 확인
    static boolean isOverlap(int x1, int y1, int x2, int y2, int x3, int y3) {
        if (x1 > x2) { // 정렬
            int temp = x1; x1 = x2; x2 = temp;
            temp = y1; y1 = y2; y2 = temp;
        }
        if (x3 > x1 && x3 > x2) return false;
        return Math.min(x1, x2) <= x3 && x3 <= Math.max(x1, x2) &&
               Math.min(y1, y2) <= y3 && y3 <= Math.max(y1, y2);
    }

    // 유니온 파인드 - Find 연산
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }

    // 유니온 파인드 - Union 연산
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
    }
}
