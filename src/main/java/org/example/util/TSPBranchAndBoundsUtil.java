package org.example.util;

import static org.example.constants.ApplicationConstants.N;
import static org.example.service.TSPBranchAndBoundsService.final_path;

public class TSPBranchAndBoundsUtil {

    // Function to copy temporary solution to
    // the final solution
    public static void copyToFinal(int curr_path[]) {
        for (int i = 0; i < N; i++)
            final_path[i] = curr_path[i];
        final_path[N] = curr_path[0];
    }

    // Function to find the minimum edge cost
    // having an end at the vertex i
    public static int firstMin(int adj[][], int i) {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < N; k++)
            if (adj[i][k] < min && i != k)
                min = adj[i][k];
        return min;
    }

    // function to find the second minimum edge cost
    // having an end at the vertex i
    public static int secondMin(int adj[][], int i) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int j = 0; j < N; j++) {
            if (i == j)
                continue;

            if (adj[i][j] <= first) {
                second = first;
                first = adj[i][j];
            } else if (adj[i][j] <= second &&
                    adj[i][j] != first)
                second = adj[i][j];
        }
        return second;
    }

}
