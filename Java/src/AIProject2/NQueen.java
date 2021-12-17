package AIProject2;

import java.lang.reflect.Array;
import java.util.*;

public class NQueen {
    public int n;
    public List<Map<String, Integer>> queenPlaces;
    public List<Integer> theArray = new ArrayList<>();
    public int cost;

    public NQueen(int n, List<Map<String, Integer>> queenPlaces) {
        this.n = n;
        for (int i = 0; i < n; i++) {
            theArray.add(i);
        }
        if (queenPlaces == null) {
            this.queenPlaces = randomState();
        } else {
            this.queenPlaces = new ArrayList<>(queenPlaces);
        }
        cost = temperature();
    }

    public List<Map<String, Integer>> randomState() {
        /*
         در این متد رندوم بخاطر هوشمند بودن آن هیچ دو وزیری در یک سطر و یا یک ستون قرار نمی‌گیرند.
         بدین شکل که به تعداد شماره های سطر و ستون در دو آرایه ارقام ریخته شده،
         سپس این دو آرایه به صورت رندوم بهم ریخته شده و بعد شماره سطر و ستون از آن گرفته می‌شود.
         نکته و هوشمند سازی اینجاست که شماره هر سطر و هر ستون فقط و فقط یکبار استفاده می‌شود،
         به همین دلیل هیچ دو وزیری در یک سطر یا ستون قرار نمی‌گیرند.
         */
        List<Map<String, Integer>> queens = new ArrayList<>();
        List<Integer> columns = new ArrayList<>(theArray), rows = new ArrayList<>(theArray);
        Collections.shuffle(columns);
        Collections.shuffle(rows);
        for (int i = 0; i < n; i++) {
            queens.add(Map.of("column", columns.get(i), "row", rows.get(i)));
        }
        return queens;
    }

    public NQueen randomNeighbor() {
        List<Map<String, Integer>> temp = new ArrayList<>(this.queenPlaces);
        int t1 = (int) Math.floor(Math.random() * n), t2;
        do {
            t2 = (int) Math.floor(Math.random() * n);
        } while (t2 == t1);
        Integer tempRow = temp.get(t1).get("row");
        temp.set(t1, Map.of("column", temp.get(t1).get("column"), "row", temp.get(t2).get("row")));
        temp.set(t2, Map.of("column", temp.get(t2).get("column"), "row", tempRow));
        return new NQueen(this.n, temp);
    }

    public NQueen bestNeighbor() {
        List<NQueen> neighbors = new ArrayList<>();

        NQueen temp, min = new NQueen(n, this.queenPlaces);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                temp = new NQueen(n, this.queenPlaces);
                Integer tempRow = temp.queenPlaces.get(i).get("row");
                temp.queenPlaces.set(i, Map.of("column", temp.queenPlaces.get(i).get("column"), "row", temp.queenPlaces.get(j).get("row")));
                temp.queenPlaces.set(j, Map.of("column", temp.queenPlaces.get(j).get("column"), "row", tempRow));
                if (min.cost > temp.cost) {
                    neighbors.add(temp);
                    min = temp;
                } else {
                    neighbors.add(temp);
                }
            }
        }
        List<NQueen> theList = new ArrayList<>();
        for (NQueen neighbor : neighbors) {
            if (neighbor.cost == min.cost) {
                theList.add(neighbor);
            }
        }
        int x = (int) Math.floor(Math.random() * theList.size());
        return theList.get(x);
    }

    public int temperature() {
        // از اونجایی که در حالت رندوم هوشمند هیچ دو وزیری در یک سطر و یک ستون قرار نمیگیرند، پس این دو حالت در اینجا چک نمی‌شود.
        int confluence = 0;
        Map<String, Integer> temp1, temp2;
        for (int i = 0; i < n - 1; i++) {
            temp1 = queenPlaces.get(i);
            for (int j = i + 1; j < n; j++) {
                temp2 = queenPlaces.get(j);
                if (Math.abs(temp2.get("column") - temp1.get("column")) == Math.abs(temp2.get("row") - temp1.get("row"))) {
                    confluence++;
                }
            }
        }
        return confluence;
    }

    @Override
    public String toString() {
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(arr[i], 0);
        }
        for (Map<String, Integer> queen : queenPlaces) {
            arr[queen.get("row")][queen.get("column")] = 1;
        }

        String s = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s += "\t" + arr[i][j];
            }
            s += "\n";
        }
        return s;
    }
}