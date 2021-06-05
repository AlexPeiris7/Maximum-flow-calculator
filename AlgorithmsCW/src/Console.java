//Name: K.A.R. Peiris
//
//IIT ID: 2018110
//
//UoW ID: w1761873

import java.util.ArrayList;
import java.util.Scanner;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

public class Console {

    private static DataReader dataReader = new DataReader();
    private static MaxFlow maxFlow = new MaxFlow();
    private static String fileName = "bridge_1.txt";

    public static void main(String[] args) {
        while (true){
            selectedOption();
        }
    }

    public static String menu(){
        System.out.println("\nWelcome to the Max Flow calculator\n" +
                "Please choose one of the options from below: \n" +
                "1 - To calculate the max flow of the given graph\n" +
                "2 - To get the number of nodes/vertices the given graph\n" +
                "3 - To calculate the num of edges in the given graph\n" +
                "4 - To check if there is an edge between two nodes/vertices\n" +
                "5 - To get the degree of a node/vertex\n" +
                "6 - To get the indegree of a node/vertex\n" +
                "7 - To get the outdegree of a node/vertex\n" +
                "8 - To print the graph\n" +
                "9 - To get the details of the used algorithm\n" +
                "10 - To exit the Max Flow calculator");
        Scanner sc1 = new Scanner(System.in);
        String userChoice = toLowerCase(sc1.nextLine());
        return userChoice;
    }

    public static void selectedOption(){
        switch (menu()){
            case "1":
                maxFlow();
                break;

            case "2":
                vertices();
                break;

            case "3":
                edges();
                break;

            case "4":
                existEdge();
                break;

            case "5":
                degree();
                break;

            case "6":
                indegree();
                break;

            case "7":
                outdegree();
                break;

            case "8":
                printGraph();
                break;

            case "9":
                details();
                break;

            case "10":
                System.exit(0);
                break;

        }
    }

    public static void maxFlow(){
        Graph graph = createGraph();
        //getting adjacency matrix from graph created using file input
        int[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        // source 0 as source should be lowest node
        int source = 0;
        //number of nodes as target should be the highest node
        int target = graph.getAdjacencyMatrix().length-1;
        System.out.println("Source = "+source);
        System.out.println("Target = "+target);
        long startTime = System.nanoTime();
        long start = System.currentTimeMillis();
        System.out.println("The maximum possible flow of "+fileName+" is "
                + maxFlow.fordFulkerson(adjacencyMatrix, source, target));
        long now = System.currentTimeMillis();
        long endTime = System.nanoTime();
        double elapsed = (now - start)/1000;
        long duration = (endTime - startTime);
        System.out.println("Time taken in seconds: " + elapsed);
        System.out.println("Time taken in nanoseconds: " + duration);

    }

    public static void printGraph(){
        //printing the graph
        Graph graph = createGraph();
        System.out.println(graph.toString());
    }

    public static Graph createGraph(){
        try {
            dataReader.readData("./res/benchmarks/"+fileName);
            Graph graph = new Graph(dataReader.getNumberOfNodes());
            ArrayList<int[]> edges = dataReader.getEdges();
            for(int[] arr : edges){
                //System.out.println(arr[0] + " " +arr[1] + " " +arr[2] + " "  );
                //setting the nodes(source, destination)
                Graph.Node source = graph.new Node(arr[0]);
                Graph.Node destination = graph.new Node(arr[1]);
                //setting weight
                int edgeWeight = arr[2];
                //creating an edge object
                Graph.Edge edge = graph.new Edge(source,destination,edgeWeight);
                //adding edge to graph
                graph.putEdge(edge);
            }
            return graph;
        }catch (Exception e){
            //System.out.println(e.getMessage());
            return null;
        }
    }

    //method which prints number of vertices in the graph
    public static void vertices(){
        System.out.println("The number of vertices is : "+createGraph().vertices());
    }

    //method which prints number of edges in the graph
    public static void edges(){
        System.out.println("The number of edges is : "+createGraph().edges());
    }

    //method which prints if an edge exists in the graph
    public static void existEdge(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter source node: ");
        int source = scanner.nextInt();
        System.out.println("Enter destination node: ");
        int destination = scanner.nextInt();
        if(!checkIfNodeExist(source)||!checkIfNodeExist(destination)){
            return;
        }
        boolean edgeExist = createGraph().existsEdge(source,destination);
        if(edgeExist){
            int [][] adjMatrix = createGraph().getAdjacencyMatrix();
            int weight = adjMatrix[source][destination];
            System.out.println("There is an edge between node " + source +
                    " and node " + destination + " and the weight is: "+ weight+".");
        }else{
            System.out.println("There is no edge between node " + source +
                    " and node " + destination + ".");
        }
    }

    //method which prints the degree of a node in the graph
    public static void degree(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter source node: ");
        int node = scanner.nextInt();
        int vertices = createGraph().vertices();
        //if there is no node like the one the user entered return
        if(!checkIfNodeExist(node)){
            return;
        };
        System.out.println("The degree of the node "+ node + " is "+createGraph().degree(node));
    }

    //method which prints the indegree of a node in the graph
    public static void indegree(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter source node: ");
        int node = scanner.nextInt();
        int vertices = createGraph().vertices();
        //if there is no node like the one the user entered return
        if(!checkIfNodeExist(node)){
            return;
        };
        System.out.println("The indegree of the node "+ node + " is "+createGraph().inDegree(node));
    }

    //method which prints the outdegree of a node in the graph
    public static void outdegree(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter source node: ");
        int node = scanner.nextInt();
        //if there is no node like the one the user entered return
        if(!checkIfNodeExist(node)){
            return;
        };
        System.out.println("The outdegree of the node "+ node + " is "+createGraph().outDegree(node));
    }
    public static void details(){
        System.out.println("The algorithm used to find the maximum flow of a flow network was Edmunds Karp Algorithm.\n" +
                "Edmunds Karp algorithm is an implementation of the Ford-Fulkerson algorithm which uses the Breadth\n" +
                "First Search algorithm to find the augmenting paths or the path from the source to target.\n" +
                "The algorithm was first published by Yefim Dinitz in 1970, and later independently published \n" +
                "by Jack Edmonds and Richard Karp in 1972.\n" +
                "reference: https://cp-algorithms.com/graph/edmonds_karp.html");
    }

    public static boolean checkIfNodeExist(int node){
        //checking if passed node exists in  the graph
        int vertices = createGraph().vertices();
        if(node>vertices-1){
            System.out.println("The node that you entered is not in the graph! ");
            return false;
        }else{
            return true;
        }
    }

}
