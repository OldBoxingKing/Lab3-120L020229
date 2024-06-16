package se.lab1;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    int[][] G;
    int num;
    int[][] visited;
    Map<String, Integer> s_to_int = new HashMap();
    Map<String, String> int_to_s = new HashMap();

    public Graph() {
    }

    public Graph(String Filename) throws IOException {
        File inputFile = new File(Filename);
        FileReader in = new FileReader(inputFile);
        int i = 0;
        int j = 0;
        String[] ch = new String[1000];

        while(true) {
            int c;
            while((c = in.read()) != -1) {
                if (c > 64 && c < 91 || c > 96 && c < 123) {
                    if (c > 64 && c < 91) {
                        c += 32;
                    }

                    if (ch[j] == null) {
                        ch[j] = "" + (char)c;
                    } else {
                        ch[j] = ch[j] + (char)c;
                    }

                    ++i;
                } else if (i != 0) {
                    i = 0;
                    ++j;
                }
            }

            if (ch[j] == null) {
                --j;
            }

            int numl = j + 1;
            in.close();
            j = 0;

            for(i = 0; i < numl; ++i) {
                if (!this.s_to_int.containsKey(ch[i])) {
                    this.s_to_int.put(ch[i], j);
                    this.int_to_s.put("" + j, ch[i]);
                    ++j;
                }
            }

            this.num = j;
            this.G = new int[this.num][this.num];
            this.visited = new int[this.num][this.num];

            for(i = 0; i < this.num; ++i) {
                for(j = 0; j < this.num; ++j) {
                    this.visited[i][j] = 0;
                    this.G[i][j] = 0;
                }
            }

            for(i = 0; i < numl - 1; ++i) {
                int var10002 = this.G[(Integer)this.s_to_int.get(ch[i])][(Integer)this.s_to_int.get(ch[i + 1])]++;
            }

            return;
        }
    }
}
