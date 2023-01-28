import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.valueOf(st.nextToken());
		int E = Integer.valueOf(st.nextToken());
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();
		int minArr[][] = new int[V + 1][V + 1];
		int INF = 5000000;
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<Node>());
			graph.get(i).add(new Node(i, 0));
			Arrays.fill(minArr[i], INF);
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int cost = Integer.valueOf(st.nextToken());
			minArr[from][to] = cost;
			graph.get(from).add(new Node(to, cost));
		}

		for (int mid = 1; mid <= V; mid++) {
			for (int start = 1; start <= V; start++) {
				for (Node end : graph.get(mid)) {
					minArr[start][end.val] = Math.min(minArr[start][end.val], minArr[start][mid] + end.cost);
				}
			}
		}

		int min = Integer.MAX_VALUE;

		for (int from = 1; from <= V; from++) {
			for (int to = 1; to <= V; to++) {
				if (from == to || minArr[from][to] == INF || minArr[to][from] == INF) {
					continue;
				}
				min = Math.min(min, minArr[from][to] + minArr[to][from]);
			}
		}

		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}

	}
}

class Node {
	int val;
	int cost;

	public Node(int val, int cost) {
		this.val = val;
		this.cost = cost;
	}
}