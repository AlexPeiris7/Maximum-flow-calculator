//Name: K.A.R. Peiris
//
//IIT ID: 2018110
//
//UoW ID: w1761873

public class Graph implements GraphInterface {

    private int vertices;
    private int edges;
    private int[][] adjacencyMatrix;

    public class Edge{

        Node source;
        Node destination;
        int weight;

        public Edge(Node source, Node destination, int weight){
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public class Node{
        int node;
        public Node(int node){
            this.node = node;
        }
    }

    public Graph(int vertices){
        this.vertices = vertices;
        adjacencyMatrix = new int [vertices][vertices];
    }

    @Override
    public int vertices(){return vertices;}
    @Override
    public int edges(){
        for(int x = 0; x<vertices; x++) {
            for (int y = 0; y < vertices; y++) {
                if(adjacencyMatrix[x][y]!=0){
                    edges++;
                }
            }
        }
        return edges;
    }
    @Override
    public boolean existsEdge(int source, int destination){
        if(adjacencyMatrix[source][destination]>0){
            return true;
        }else return false;
    }
    @Override
    //edge.source.node - edge is edge obj, source is a node obj and .node is the node
    public void putEdge(Edge edge){
        adjacencyMatrix[edge.source.node][edge.destination.node] = edge.weight;
    }
    @Override
    public void removeEdge(int source, int destination){
        adjacencyMatrix[source][destination] = 0;
    }
    @Override
    public int degree(int vertex){
        int degree = inDegree(vertex) + outDegree(vertex);
        return degree;
    }
    @Override
    public int inDegree(int vertex){
        int degree = 0;
        for (int i = 0; i < vertices; i++) {
            if (adjacencyMatrix[vertex][i] != 0)
                degree++;
        }
        return degree;
    }
    @Override
    public int outDegree(int vertex){
        int degree = 0;
        for (int i = 0; i < vertices; i++) {
            if (adjacencyMatrix[i][vertex] != 0)
                degree++;
        }
        return degree;
    }
    public int[][] getAdjacencyMatrix(){
        return adjacencyMatrix;
    }

    public void setAdjacencyMatrix(int[][] adjacencyMatrix){
        this.adjacencyMatrix = adjacencyMatrix;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int x = 0; x<vertices; x++){
            for(int y = 0; y<vertices; y++){
                //to make the graph look proper
                if(adjacencyMatrix[x][y]>9){
                    sb.append(adjacencyMatrix[x][y]);
                }else{
                    sb.append(adjacencyMatrix[x][y] + " ");
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
