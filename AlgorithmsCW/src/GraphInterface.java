//Name: K.A.R. Peiris
//
//IIT ID: 2018110
//
//UoW ID: w1761873

public interface GraphInterface {
    public abstract int vertices();
    public abstract int edges();
    public abstract boolean existsEdge(int source, int destination);
    public abstract void putEdge(Graph.Edge edge);
    public abstract void removeEdge(int source, int destination);
    public abstract int degree(int vertex);
    public abstract int inDegree(int vertex);
    public abstract int outDegree(int vertex);
}
