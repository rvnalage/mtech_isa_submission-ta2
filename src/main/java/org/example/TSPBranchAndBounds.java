package org.example;

import org.example.service.TSPBranchAndBoundsService;

import static org.example.constants.ApplicationConstants.N;
import static org.example.service.TSPBranchAndBoundsService.final_path;


class TSPBranchAndBounds {

    public static void main(String[] args) {
        //Adjacency matrix for the given graph
        int adj[][] = {{0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}};

        new TSPBranchAndBoundsService().solveTSP(adj);

        System.out.printf("Minimum cost : %d\n", TSPBranchAndBoundsService.final_res);
        System.out.printf("Path Taken : ");

        for (int i = 0; i <= N; i++) {
            System.out.printf("%d ", final_path[i]);
        }

    }
}

