import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 사람 수
        int m = Integer.parseInt(st.nextToken()); // 파티 수

        parent = new int[n + 1]; // 1-indexed
        for (int i = 1; i <= n; i++) {
            parent[i] = i; // 자기 자신을 부모로 초기화
        }

        // 진실을 아는 사람들
        st = new StringTokenizer(br.readLine());
        int truthCount = Integer.parseInt(st.nextToken());
        List<Integer> truthPeople = new ArrayList<>();
        for (int i = 0; i < truthCount; i++) {
            truthPeople.add(Integer.parseInt(st.nextToken()));
        }

        // 파티 정보
        List<List<Integer>> parties = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int partySize = Integer.parseInt(st.nextToken());
            List<Integer> party = new ArrayList<>();
            for (int j = 0; j < partySize; j++) {
                party.add(Integer.parseInt(st.nextToken()));
            }
            parties.add(party);

            // 파티에 있는 모든 사람을 같은 집합으로 병합
            for (int j = 1; j < party.size(); j++) {
                union(party.get(0), party.get(j));
            }
        }

        // 진실을 아는 모든 사람들의 대표자 찾기
        int truthLeader = -1;
        if (!truthPeople.isEmpty()) {
            truthLeader = find(truthPeople.get(0));
            for (int person : truthPeople) {
                union(truthLeader, person);
            }
        }

        // 거짓말 가능한 파티 계산
        int result = 0;
        for (List<Integer> party : parties) {
            if (party.isEmpty()) continue;

            int partyLeader = find(party.get(0));
            if (truthLeader == -1 || partyLeader != truthLeader) {
                result++;
            }
        }

        System.out.println(result);
    }

    // Find 연산 (경로 압축 사용)
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union 연산
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}
