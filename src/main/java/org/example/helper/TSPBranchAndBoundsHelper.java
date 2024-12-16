package org.example.helper;

import java.util.Arrays;

import static org.example.constants.ApplicationConstants.N;
import static org.example.service.TSPBranchAndBoundsService.final_res;
import static org.example.util.TSPBranchAndBoundsUtil.*;

public class TSPBranchAndBoundsHelper {

    // visited[] keeps track of the already visited nodes
    // in a particular path
    public static boolean visited[] = new boolean[N];

    // function that takes as arguments:
    // curr_bound -> lower bound of the root node
    // curr_weight-> stores the weight of the path so far
    // level-> current level while moving in the search
    //         space tree
    // curr_path[] -> where the solution is being stored which
    //             would later be copied to final_path[]
    public static void tspRec(int adj[][], int curr_bound, int curr_weight,
                              int level, int curr_path[]) {
        // base case is when we have reached level N which
        // means we have covered all the nodes once
        if (level == N) {
            // check if there is an edge from last vertex in
            // path back to the first vertex
            if (adj[curr_path[level - 1]][curr_path[0]] != 0) {
                // curr_res has the total weight of the
                // solution we got
                int curr_res = curr_weight +
                        adj[curr_path[level - 1]][curr_path[0]];

                // Update final result and final path if
                // current result is better.
                if (curr_res < final_res) {
                    copyToFinal(curr_path);
                    final_res = curr_res;
                }
            }
            return;
        }

        // for any other level iterate for all vertices to
        // build the search space tree recursively
        for (int i = 0; i < N; i++) {
            // Consider next vertex if it is not same (diagonal
            // entry in adjacency matrix and not visited
            // already)
            if (adj[curr_path[level - 1]][i] != 0 &&
                    visited[i] == false) {
                int temp = curr_bound;
                curr_weight += adj[curr_path[level - 1]][i];

                // different computation of curr_bound for
                // level 2 from the other levels
                if (level == 1)
                    curr_bound -= ((firstMin(adj, curr_path[level - 1]) +
                            firstMin(adj, i)) / 2);
                else
                    curr_bound -= ((secondMin(adj, curr_path[level - 1]) +
                            firstMin(adj, i)) / 2);

                // curr_bound + curr_weight is the actual lower bound
                // for the node that we have arrived on
                // If current lower bound < final_res, we need to explore
                // the node further
                if (curr_bound + curr_weight < final_res) {
                    curr_path[level] = i;
                    visited[i] = true;

                    // call TSPRec for the next level
                    tspRec(adj, curr_bound, curr_weight, level + 1,
                            curr_path);
                }

                // Else we have to prune the node by resetting
                // all changes to curr_weight and curr_bound
                curr_weight -= adj[curr_path[level - 1]][i];
                curr_bound = temp;

                // Also reset the visited array
                Arrays.fill(visited, false);
                for (int j = 0; j <= level - 1; j++)
                    visited[curr_path[j]] = true;
            }
        }
    }
}
