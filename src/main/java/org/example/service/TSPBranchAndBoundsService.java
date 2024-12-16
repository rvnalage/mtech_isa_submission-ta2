package org.example.service;

import org.example.helper.TSPBranchAndBoundsHelper;

import java.util.Arrays;

import static org.example.constants.ApplicationConstants.N;
import static org.example.helper.TSPBranchAndBoundsHelper.*;
import static org.example.util.TSPBranchAndBoundsUtil.firstMin;
import static org.example.util.TSPBranchAndBoundsUtil.secondMin;

public class TSPBranchAndBoundsService {


    // final_path[] stores the final solution ie, the
    // path of the salesman.
    public static int final_path[] = new int[N + 1];

    // Stores the final minimum weight of shortest tour.
    public static int final_res = Integer.MAX_VALUE;


    // This function sets up final_path[]
    public void solveTSP(int adj[][]) {
        int curr_path[] = new int[N + 1];

        // Calculate initial lower bound for the root node
        // using the formula 1/2 * (sum of first min +
        // second min) for all edges.
        // Also initialize the curr_path and visited array
        int curr_bound = 0;
        Arrays.fill(curr_path, -1);
        Arrays.fill(visited, false);

        // Compute initial bound
        for (int i = 0; i < N; i++)
            curr_bound += (firstMin(adj, i) +
                    secondMin(adj, i));

        // Rounding off the lower bound to an integer
        curr_bound = (curr_bound == 1) ? curr_bound / 2 + 1 :
                curr_bound / 2;

        // We start at vertex 1 so the first vertex
        // in curr_path[] is 0
        visited[0] = true;
        curr_path[0] = 0;

        // Call to TSPRec for curr_weight equal to
        // 0 and level 1
        TSPBranchAndBoundsHelper.tspRec(adj, curr_bound, 0, 1, curr_path);
    }


}





