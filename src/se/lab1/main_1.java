package se.lab1;

import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.Random;

public class main_1 {
    public static void main(String[] args) {
        menu1 m = new menu1();
    }

    static int arr[][] = new int[100][100];

    public static Graph createDirectedGraph(String filename) throws IOException {
        Graph T = null;
        T = new Graph(filename);
        return T;
    }

    public static String queryBridgeWords(Graph T, String word1, String word2) {
        String result = "";
        String result1 = "";
        if (T.s_to_int.containsKey(word1) && T.s_to_int.containsKey(word2)) {
            for (int i = 0; i < T.num; i++) {
                if (T.G[T.s_to_int.get(word1)][i] != 0 && T.G[i][T.s_to_int.get(word2)] != 0) {
                    result +=  " " + T.int_to_s.get("" + i);
                }
            }
            if (result.equals("")) {
                result = "No bridge words from " + word1 + " to " +word2 +"!";
                return result;
            }
            if (result != "") {
                result = "The bridge words from " + word1 + " to " + word2 + " :" + result + ".";
                return result;
            }
        }
        result = "No " + word1 + " or " + word2 + " in the graph!";
        return result;
    }

    public static String generateNewText(Graph T, String inputText) {
        String[] in1 = inputText.split(" ");
        String result = in1[0];
        String[] bridge;
        String B = "No bridge words from word1 to word2";
        String A = "No word1 or word2 in the graph!";
        for (int i = 0; i < in1.length - 1; i++) {
            String C = queryBridgeWords(T, in1[i], in1[i + 1]);
            if (C.equals(B) || C.equals(A)) {

            } else {
                bridge = queryBridgeWords(T, in1[i], in1[i + 1]).split(" ");
                //Random random = new Random();
                //int index = 1 + random.nextInt(bridge.length - 1);
                result = result + " " + bridge[((int) (Math.random() * 10)) % (bridge.length - 1) + 1];
                //result = result + " " + bridge[index];
            }
            result = result + " " + in1[i + 1];
        }
        return result;
    }

    @SuppressWarnings("unused")
    public static String randomWalk(Graph T, int n, int from) {
        int i, k = 0;
        java.util.Random r = new java.util.Random();
        int[] path = new int[n];
        String result = "";
        for (i = 0; i < T.num; i++) {
            if (T.G[from][i] != 0) // &&T.visited[from][i]==0
            {
                path[k++] = i;
            }
        }
        if (k == 0) {
            return null;
        }
        int to = (r.nextInt(k));
        if (T.visited[from][path[to]] == 1) {
            for (int j = 0; j < n; j++)
                for (int x = 0; x < n; x++)
                    T.visited[j][x] = 1;
            return T.int_to_s.get("" + path[to]) + " no more";
        }
        T.visited[from][path[to]] = 1;
        result = T.int_to_s.get("" + path[to]);
        return result;// +randgo(T,n,path[to]);
    }

//  public static String randomWalk(Graph T, String start) {
//    int from;
//    String result = new String();
//    from = T.s_to_int.get(start);
//    result = randgo(T, T.num, from);
//    return T.int_to_s.get("" + from) + " " + result;
//  }

    public static void showDirectedGraph(Graph T) {
        String type = "gif";
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());
        for (int i = 0; i < T.num; i++) {
            for (int j = 0; j < T.num; j++) {
                if (T.G[i][j] != 0) {
                    gv.addlnlabel(T.int_to_s.get("" + i) + "->" + T.int_to_s.get("" + j), "" + T.G[i][j]);
                }
            }
        }
        gv.addln(gv.end_graph());
        File out = new File("D:\\javaproject\\sy1new\\picture." + type); // Windows
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
    }

    public static String calcShortestPath(Graph T, String word1, String word2) {
        int max = 9999;
        int i = 0 , j = 0;
        int start = T.s_to_int.get(word1);
        int end = T.s_to_int.get(word2);
        int n = T.num;
        int[][] G = new int[n][n];
        String result = "";
        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++) {
                G[i][j] = T.G[i][j];
                if (T.G[i][j] == 0) {
                    G[i][j] = max;
                }
            }
        Stack p[] = new Stack[100];
        int d = showpath(T, p, T.G, n, start, end);
        int io;
        for (i = 0; i < d; i++) {
            j = 0;
            while (!p[i].isEmpty()) {
                arr[i][j] = (int) p[i].pop();
                j++;
            }
            if (i == 0) {
                io = j;
            }
            arr[i][j] = -1;
        }
        arr[i][0] = -1;
        for (i = j - 1; i >= 0; i--) {
            result = result + " " + T.int_to_s.get("" + arr[0][i]);
        }
        return result;
    }

    public static void updataA(int G[][], int A[][], Stack stack, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                {
                    if ((stack.search(i) == -1 && stack.search(j) == -1)) {
                        A[i][j] = 0;
                    }
                }
            }
        }
    }

    public static int showpath(Graph T, Stack p[], int G[][], int n, int start, int end) {
        int min_length = 10000;
        int sum = 0, d = 0, ele, h = 0, xx = 0;
        int v[] = new int[n];
        int A[][] = new int[n][n];
        int aa[] = new int[n];
        Stack mystack = new Stack();
        for (int i = 0; i < n; i++) {
            v[i] = 0;
            for (int j = 0; j < n; j++) {
                A[i][j] = 0;
            }
        }
        mystack.push(start);
        v[start] = 1;
        while (!mystack.isEmpty()) {
            ele = (int) mystack.peek();
            if (ele == end) {
                if (sum < min_length) {
                    d = 0;
                    h = 0;
                    min_length = sum;
                    p[d] = new Stack();
                    while (!mystack.isEmpty()) {
                        aa[h++] = (int) mystack.pop();
                    }
                    xx = h - 1;
                    while (xx >= 0) {
                        mystack.push(aa[xx]);
                        p[d].push(aa[xx]);
                        xx--;
                    }
                    d = d + 1;
                } else if (sum == min_length) {
                    p[d] = new Stack();
                    h = 0;
                    while (!mystack.isEmpty()) {
                        aa[h++] = (int) mystack.pop();
                    }
                    xx = h - 1;
                    while (xx >= 0) {
                        mystack.push(aa[xx]);
                        p[d].push(aa[xx]);
                        xx--;
                    }
                    d = d + 1;
                }
                v[ele] = 0;
                updataA(G, A, mystack, n);
                if (!mystack.isEmpty()) {
                    mystack.pop();
                }
                if (!mystack.isEmpty()) {
                    sum = sum - G[(int) mystack.peek()][ele];
                }
            } else {
                int i;
                for (i = 0; i < n; i++) {
                    if (v[i] == 0 && A[ele][i] == 0 && G[ele][i] != 0) {
                        sum += G[ele][i];
                        v[i] = 1;
                        A[ele][i] = 1;
                        if (sum > min_length) {
                            sum -= G[ele][i];
                            v[i] = 0;
                            A[ele][i] = 0;
                            continue;
                        }
                        mystack.push(i);
                        break;
                    }
                }
                if (i == n) {
                    v[ele] = 0;
                    updataA(G, A, mystack, n);
                    if (!mystack.isEmpty()) {
                        mystack.pop();
                    }
                    if (!mystack.isEmpty()) {
                        sum = sum - G[(int) mystack.peek()][ele];
                    }
                }
            }
        }
        return d;
    }

    public static Stack[] all_calcShortestPath(Graph T, String word) {
        int maxint = 1000;
        int A[][] = new int[T.num][T.num];
        for (int i = 0; i < T.num; i++) {
            for (int j = 0; j < T.num; j++) {
                if (T.G[i][j] == 0) {
                    A[i][j] = maxint;
                } else {
                    A[i][j] = T.G[i][j];
                }
            }
        }
        int v0 = T.s_to_int.get(word);
        int dist[] = new int[T.num];
        int prev[] = new int[T.num];
        boolean s[] = new boolean[T.num];
        for (int i = 0; i < T.num; i++) {
            dist[i] = A[v0][i];
            s[i] = false;
            if (dist[i] == maxint) {
                prev[i] = -1;
            } else {
                prev[i] = v0;
            }
        }
        dist[v0] = 0;
        s[v0] = true;
        for (int i = 1; i < T.num; i++) {
            int min = maxint;
            int u = v0;
            for (int j = 0; j < T.num; j++) {
                if (!s[j] && dist[j] < min) {
                    u = j;
                    min = dist[j];
                }
            }
            s[u] = true;
            for (int j = 0; j < T.num; j++) {
                if (!s[j] && dist[u] + A[u][j] < dist[j]) {
                    {
                        dist[j] = dist[u] + A[u][j];
                        prev[j] = u;
                    }
                }
            }
        }
        Stack stack = new Stack();
        Stack pp[] = new Stack[T.num];
        int i = 0;
        for (int j = 0; j < T.num; j++) {
            pp[i] = new Stack();
            if (j != v0) {
                int k = j;
                while (k != v0 && k != -1) {
                    if (dist[k] < maxint) {
                        pp[i].push(T.int_to_s.get("" + k));
                    }
                    k = prev[k];
                }
            }
            i++;
        }
        return pp;
    }
}