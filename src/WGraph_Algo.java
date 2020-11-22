package ex1.src;

import ex1.src.*;

import java.io.*;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms {




    public weighted_graph graf;

    public WGraph_Algo() {
        this.graf = new WGraph_DS();
    }


    @Override
    public void init(weighted_graph g) {
        this.graf = g;
    }

    @Override
    public weighted_graph getGraph() {
        return graf;
    }

    /**  making a deep copy of the graph **/
    @Override
    public weighted_graph copy() {
        return new WGraph_DS((WGraph_DS) graf);
    }

    /** checking if all the nodes connected to each other **/
    @Override
    public boolean isConnected() {
        if(this.graf.nodeSize() == 0 || this.graf.nodeSize() == 1) // if the graph has only 0 or 1 node
            return true;
        if(this.graf.nodeSize()-1 > this.graf.edgeSize()) // graph cant have 2 unconnected nodes
            return false;


        for (node_info n:graf.getV()){ //the tags marked by 1 will tell us that we already saw that node
            n.setTag(0);
        }
        Iterator<node_info> temp = graf.getV().iterator();
        node_info nod;
        if (temp.hasNext() == false)
            return true;
        nod=temp.next();


        connection(nod); // checking the connection

        while(temp.hasNext()){ //going through all the nodes and checking if all of them market with 1
            nod = temp.next();
            if(nod.getTag() == 0) // if there is node that doesnt marked with 1 that means that this node isnt connected
                return false;
        }
        return true;
    }

    /** starting in src node at going through all the graph and checking all the nodes connected to each other **/
    private void connection(node_info src) { // starts at node src and going through all the graph

        src.setTag(1);
        LinkedList<node_info> queue = new LinkedList<>();
        queue.add(src);
        while (queue.isEmpty() == false) { // loop going through the queue
            node_info nod = queue.poll();
            WGraph_DS.NodeInfo nod2 = (WGraph_DS.NodeInfo) nod;

            for (node_info n: nod2.getNi()) { // going through the neighbors
                if(n.getTag() == 0){ // if doesnt seen
                    n.setTag(1);
                    queue.add(n);
                }
            }
        }
        return;
    }

    /** returning the shortest distance in number from src to dest **/
    @Override
    public double shortestPathDist(int src, int dest) { // returning the size of the shortest path between src and dist
        for (node_info nod : graf.getV()) { // setting all the tags to 0
            nod.setTag(0);
        }
        HashMap<node_info,Double> data = DijkstraAlgo(graf.getNode(src),graf.getNode(dest));

        if (data.containsKey(graf.getNode(dest)))
            return data.get(graf.getNode(dest));
        return -1;
    }

    /** returning HashMap where every nodes key has double that represent this nodes shortest patch to the src **/
    private HashMap<node_info, Double> DijkstraAlgo(node_info node, node_info node1) { // calculate the shortest distance from node to node 1
        LinkedList<node_info> queue = new LinkedList<>();
        queue.add(node);
        HashMap<node_info,Double> disMap = new HashMap<>();
        disMap.put(node,0.0);
        node.setTag(1);


        while (queue.isEmpty() == false ){ // going through the queue
            WGraph_DS.NodeInfo nod = (WGraph_DS.NodeInfo) queue.poll();
            for (node_info n : nod.getNi() ){ // going through the neighbors
                double distance = graf.getEdge(n.getKey(), nod.getKey()); // distance from node to its neigbor
                double distanceFromStart = disMap.get(nod) + distance; // distance to the neigbor from the start
                if (disMap.containsKey(n) == false || distanceFromStart < disMap.get(n)) {
                    disMap.put(n, distanceFromStart); // putting the distance from the start if it is smaller the the previous distance that saved there or if there is no distance saved at all
                    queue.add(n);
                }
            }
        }
        return disMap;
    }

    /** returning the shortest list of nodes from src to dest **/
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        for (node_info nod : graf.getV()) { // setting all the tags to 0
            nod.setTag(0);
        }
        HashMap<node_info,ArrayList<node_info>> data = DijkstraList(graf.getNode(src),graf.getNode(dest));

        if (data.containsKey(graf.getNode(dest)))
            return data.get(graf.getNode(dest));
        return null;
    }

    /** returning HashMap where every nodes key has list that represent this nodes shortest patch to the src bt list of nodes **/
    private HashMap<node_info, ArrayList<node_info>> DijkstraList(node_info node, node_info node1) { // calculate the shortest distance from node to node 1
        LinkedList<node_info> queue = new LinkedList<>();
        queue.add(node);
        HashMap<node_info,Double> disMap = new HashMap<>(); // hashMap with the distances in numbers
        HashMap<node_info, ArrayList<node_info>> disList = new HashMap<>(); // hashMap with the distances in array list
        ArrayList<node_info> list123 = new ArrayList<>();
        list123.add(node);
        disList.put(node,list123);
        //System.out.println(disList.get(node));
        disMap.put(node,0.0);
        node.setTag(0);


        while (queue.isEmpty() == false ){ // going through the queue
            WGraph_DS.NodeInfo nod = (WGraph_DS.NodeInfo) queue.poll();
            for (node_info n : nod.getNi() ){ // going through the neighbors
                double distance = graf.getEdge(n.getKey(), nod.getKey()); // distance from node to its neigbor
                double distanceFromStart = disMap.get(nod) + distance; // distance to the neigbor from the start
                ArrayList<node_info> list = new ArrayList<>();
                if (disMap.containsKey(n) == false || distanceFromStart < disMap.get(n)) {

                    n.setTag(distanceFromStart);

                    list = disList.get(nod);
                    ArrayList<node_info> newList = new ArrayList<>(list);
                    newList.add(n);

                    disList.put(n, newList);
                    System.out.println(disList.get(n).toString());
                    disMap.put(n, distanceFromStart); // putting the distance from the start if it is smaller the the previous distance that saved there or if there is no distance saved at all
                    queue.add(n);
                }
            }
        }
        return disList;
    }



    /** saving the graph **/
    @Override
    public boolean save(String file) {
        try {
            FileOutputStream saveFile = new FileOutputStream(file);
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(this.graf);
            save.close();
        }
        catch(Exception exc){
            exc.printStackTrace();
            return false;
        }
        return true;


        /*try{
            FileOutputStream FileOS = new FileOutputStream(file);
            ObjectOutputStream ObjOS = new ObjectOutputStream(FileOS);
            ObjOS.writeObject(this.graf);
            FileOS.close();
            ObjOS.close();
            return true;
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }*/
    }
    /** loading the graph **/
    @Override
    public boolean load(String file) {

        try{
            FileInputStream saveFile = new FileInputStream(file);
            ObjectInputStream restore = new ObjectInputStream(saveFile);
            Object obj = restore.readObject();
            this.graf = (WGraph_DS) restore.readObject();
            restore.close();
        }
        catch(Exception exc){
            exc.printStackTrace(); // If there was an error, print the info.
        }


        return true;
        /*try{
            FileInputStream FileOS = new FileInputStream(file);
            ObjectInputStream ObjOS = new ObjectInputStream(FileOS);
            this.graf = (WGraph_DS) ObjOS.readObject();
            FileOS.close();
            ObjOS.close();
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }*/
    }
}
