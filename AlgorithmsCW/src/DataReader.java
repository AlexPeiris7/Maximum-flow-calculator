//Name: K.A.R. Peiris
//
//IIT ID: 2018110
//
//UoW ID: w1761873

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {

    //number of nodes in the graph
    private static int numberOfNodes;

    // arraylist which store information about edges
    // arrays contain the following
    // first element = source node
    // second element = destination node
    // third element = weight of the edge
    // number of arrays in the array is = number of nodes
    private ArrayList<int[]> edges = new ArrayList<>();

    public void readData(String filePath){

        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            while (line != null) {
                String[] lineParts = line.split(" ");
                //if line contains more than one splitted part = not first line
                // if line contains only one line(catches exception) = first line
                try{
                    int[] edge = new int[3];
                    //source
                    edge[0]=Integer.parseInt(lineParts[0]);
                    //destination
                    edge[1]=Integer.parseInt(lineParts[1]);
                    //weight
                    edge[2]=Integer.parseInt(lineParts[2]);
                    edges.add(edge);
                    //System.out.println(line);
                }
                catch (ArrayIndexOutOfBoundsException e){
                    //if try block threw an exception, it means that that line didnt have 3 values
                    //hence meaning that it was the first line in which the number of nodes
                    //is denoted
                    numberOfNodes = Integer.parseInt(lineParts[0]);
                    //e.printStackTrace();
                    //System.out.println(line);
                }catch (Exception e){
                    e.printStackTrace();
                }
                //next line
                line = br.readLine();
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public ArrayList<int[]> getEdges() {
        return edges;
    }
}
