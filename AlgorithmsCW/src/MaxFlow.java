//Name: K.A.R. Peiris
//
//IIT ID: 2018110
//
//UoW ID: w1761873


import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

class MaxFlow {

    //number of vertices of the graph
    static int vertices;

    //Breadth First Search used to see if there is a path from source to target
    // if a path is available it will return true else will return false
    //parent[] used to save the augmenting path from source to target
    boolean bfs(int residualGraph[][], int source, int target, int parent[])
    {
        //array used to mark the visited nodes
        boolean visited[] = new boolean[vertices];
        //setting all elements in visited array to false as initially no node is visited
        for (int i = 0; i < vertices; ++i)
            visited[i] = false;
        //Linked List used to create a queue and source node is added to it
        LinkedList<Integer> queue
                = new LinkedList<Integer>();
        queue.add(source);
        //setting source as visited as it always starts with the source so it is always visited
        visited[source] = true;
        // -1 was set to identify that there is no node before the source
        parent[source] = -1;

        while (queue.size() != 0) {
            //gets the first element of the linked list and removes it
            //if there is no element in linkedlist it will return a null value
            int firstElem = queue.poll();

            for (int v = 0; v < vertices; v++) {
                // if node "v" is not visited yet and if capacity of edge from
                // firstEl to node "v" is not yet 0
                if (!visited[v] && residualGraph[firstElem][v] > 0) {
                    //check if node "v" is the target
                    //if its the target return true as the path has been established and found
                    if (v == target) {
                        parent[v] = firstElem;
                        return true;
                    }
                    //if node "v" wasnt the target add node "v" to queue
                    // fills the parent(used to store the path from source to target) with node "v"
                    // and marks the node "v" as visited in the visited array so it wont be visited again
                    queue.add(v);
                    parent[v] = firstElem;
                    visited[v] = true;
                }
            }
        }

        //if there was no path from source to target return false
        //which alse means that queue is empty
        return false;
    }

    int fordFulkerson(int graph[][], int source, int target)
    {
        //setting vertices by getting the lenght of the graph(Adjacency Matrix)
        this.vertices = graph.length;

        //setting residual graph to the original graph as all capacities in both graphs are the same
        //in the beginning
        int residualGraph[][] = graph;

        //array used by bfs to store augmenting path
        int parent[] = new int[vertices];

        //setting max_flow variable to zero as no flow is present initially
        int max_flow = 0;
        // loop will run while there is a path from source to target
        // using bfs
        while (bfs(residualGraph, source, target, parent)) {

            //store the maximum possible value for any integer variable
            int path_flow = Integer.MAX_VALUE;
            //v becomes ending node (node to node not source to target)
            for (int v = target; v != source; v = parent[v]) {
                //starting node (node to node)
                int u = parent[v];
                //calculating the path flow by getting the edge with the minimum capacity in the path
                path_flow = Math.min(path_flow, residualGraph[u][v]);
            }
            //v becomes ending node (node to node not source to target)
            for (int v = target; v != source; v = parent[v]) {
                //starting node (node to node)
                int u = parent[v];
                //updating the forward capacity of the edges in the residual graph
                residualGraph[u][v] -= path_flow;
                //updating the backward capacity of the edges in the residual graph
                residualGraph[v][u] += path_flow;
            }
            //adding the path flow to the max flow
            max_flow += path_flow;
        }
        //when loop ends meaning that there are no more paths from source to target
        // return calculated max flow
        return max_flow;
    }
}
