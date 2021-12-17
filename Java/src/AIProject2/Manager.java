package AIProject2;

import java.util.*;

public class Manager {
    public List<NQueen> history = new ArrayList<>();
    public int temperature = 1000000000;

    public int solve(int n) {
        if (n == 2 || n == 3) {
            return 0;
        }
        int counter = 1, E;
        NQueen oldState = new NQueen(n, null), newState;
        history.add(oldState);
        if (n != 1) {
            while (true) {

                if (oldState.cost == 0) {
                    break;
                }
                newState = oldState.randomNeighbor();
//                newState = oldState.bestNeighbor();
                history.add(newState);
                E = oldState.cost - newState.cost;
                if (E >= 0 || probability(E)) {
                    oldState = newState;
                }
                counter++;
                temperature--;
            }
        }
        return counter;
    }

    public boolean probability(int E) {
        if (temperature == 0) return false;
        double temp = Math.pow(2, E / temperature);
        double rand = Math.random();
        return temp > rand;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < history.size(); i++) {
            s += String.format("number %d \n\n%s\n--------------------------------------------------", i + 1, history.get(i).toString());
        }
        return s;
    }
}
