package ex1.src;

import java.util.*;

public class WGraph_DS implements weighted_graph {

    private int MC;
    private int edges;
    private HashMap<Integer, node_info> nodes; // hashmap for the nodes
    private HashSet<Integer> usedKeys;


    public WGraph_DS() { // default constructor
        this.nodes = new HashMap<Integer, node_info>();
        this.usedKeys = new HashSet<Integer>();
        edges = 0;
        MC = 0;
    }

    public WGraph_DS (WGraph_DS graf){
        if(graf == null){
            this.nodes = new HashMap<Integer, node_info>();
            this.usedKeys = new HashSet<Integer>();
            edges = 0;
            MC = 0;
        }
        else{
            this.nodes = new HashMap<Integer, node_info>();
            this.usedKeys = new HashSet<Integer>();

            for(node_info nod : graf.nodes.values()){ // adding the nodes to the copy
                NodeInfo neighbor = new NodeInfo(nod.getKey(), nod.getTag(), nod.getInfo());
                nodes.put(neighbor.getKey(),nod);
            }

            for(node_info nod : graf.nodes.values()){
                NodeInfo n = (NodeInfo) nod;
                NodeInfo nodTemp = (NodeInfo) nodes.get(n.getKey());
                for(node_info Ni : n.getNi()){
                    NodeInfo temp = (NodeInfo) nodes.get(Ni.getKey());
                    if(nodTemp.hasNi(temp.getKey()) == false)
                        nodTemp.addNi(temp);
                        nodTemp.setWeight(temp.getKey(), n.getWeight(temp.getKey()));
                }
            }

            edges = graf.edges;
            MC = graf.MC;
            usedKeys = graf.usedKeys;

        }

    }

    /** returning node **/
    @Override
    public node_info getNode(int key) { // returning the specific node from hashmap
        return this.nodes.get(key);
    }

    /** checking if 2 nodes have edge **/
    @Override
    public boolean hasEdge(int node1, int node2) { // checking if 2 nodes has an edge
        NodeInfo n = (NodeInfo) nodes.get(node1); // getting the hashmap of node 1 neighbors
        return n.hasNi(node2); // checking if node2 is n neighbor

    }

    /** get edge between 2 nodes **/
    @Override
    public double getEdge(int node1, int node2) {
        if (nodes.get(node1) == null || nodes.get(node2) == null)
            return -1;
        NodeInfo n1 = (NodeInfo) nodes.get(node1);
        if (hasEdge(node1, node2))
            return n1.getWeight(node2);
        return -1;
    }

    /** add node to the graph **/
    @Override
    public void addNode(int key) {
        if(usedKeys.contains(key)) // checking if we used this key
            return;
        if(nodes.containsKey(key) == true) // checking if we already have this key
            return;
        usedKeys.add(key);
        NodeInfo n = new NodeInfo(key);
        nodes.put(key, n); // put the node into the hashmap
        MC++; // +1 to number of actions

    }

    /** connect 2 nodes **/
    @Override
    public void connect(int node1, int node2, double w) {
        if(w < 0)
            return;
        if (nodes.get(node1) == null || nodes.get(node2) == null) {
            return;
        }
        if (node1 == node2) {
            return;
        }

        NodeInfo n1 = (NodeInfo) nodes.get(node1);
        NodeInfo n2 = (NodeInfo) nodes.get(node2);

        if (n1.hasNi(node2) == false || n2.hasNi(node1) == false) {
            //System.out.println("success");
            n1.addNi(nodes.get(node2)); // creating an edge
            n1.setWeight(node2, w);
            n2.setWeight(node1, w);
            edges++; // add one to edges because we created an edge
            MC++; // +1 to number of actions
        }
        else{
            n1.setWeight(node2, w);
            n2.setWeight(node1, w);
        }

    }

    /** returning all the nodes from graph **/
    @Override
    public Collection<node_info> getV() { // returning copy of nodes collection
        return this.nodes.values(); //
    }

    /** returning copy of neighbors collection **/
    @Override
    public Collection<node_info> getV(int node_id) { // returning copy of neighbors collection
        NodeInfo n = (NodeInfo) nodes.get(node_id);
        return n.getNi();
    }

    /** remove node from graph **/
    @Override
    public node_info removeNode(int key) { // delete node and all his edges

        if(nodes.containsKey(key) == false){
            return null;}

        node_info temp = nodes.get(key);
        nodes.remove(key); // remove the node from hashmap
        if(temp != null){
            NodeInfo nod = (NodeInfo) temp;
            int edgesRemoved = nod.getNi().size();
            System.out.println(edgesRemoved);
            nod.disconnectEdges(); // disconnecting the edges from temp
            edges = edges - edgesRemoved; // removing the number of deleted edges
            MC = MC + edgesRemoved + 1; // 1 is for the node removing
        }
        return temp;
    }

    /** remove edge from graph **/
    @Override
    public void removeEdge(int node1, int node2) { // removing edge between to nodes
        if (node1 == node2)
            return;
        if (nodes.get(node1) == null || nodes.get(node2) == null)
            return;
        NodeInfo n1 = (NodeInfo) nodes.get(node1);
        NodeInfo n2 = (NodeInfo) nodes.get(node2);
        if (n1.hasNi(node2)) { // check if the nodes have an edge
            n1.removeNode(nodes.get(node2)); // remove node2
            n1.setWeight(node2, 0);
            n2.setWeight(node1, 0);
            edges--; // update the num edges
            MC++;
        }
    }

    /** returning the amount of nodes **/
    @Override
    public int nodeSize() { // returning the amount of nodes
        return this.nodes.size(); // returning thr amount of nodes
    }

    /** returning the amount of edges **/
    @Override
    public int edgeSize() { // return the edge size
        return edges;
    }

    /** returning the mc **/
    @Override
    public int getMC() { // return the MC
        return MC;
    }


    public class NodeInfo implements node_info {
        int key;
        double tag;
        String info;
        private HashMap<Integer, node_info> neigh; //hashmap of neighbors
        private HashMap<Integer, Double> weights; //weight for every neighbor

        public NodeInfo(int key) {
            this.key = key;
            tag = 0;
            info = "";
            this.neigh = new HashMap<Integer, node_info>(); // create a new HashMap to store all the node neighbors
            this.weights = new HashMap<Integer, Double>(); // create a new HashMap to store all the weights for the neighbors
        }

        public NodeInfo(int key, double tag, String info) {
            this.key = key;
            this.tag = tag;
            this.info = info;
            this.neigh = new HashMap<Integer, node_info>(); // create a new HashMap to store all the node neighbors
            this.weights = new HashMap<Integer, Double>(); // create a new HashMap to store all the weights for the neighbors
        }

        public HashMap<Integer, node_info> gettable() {
            return this.neigh;
        }

        /** getting nodes key **/
        @Override
        public int getKey() {
            return key;
        }

        /** getting info from node **/
        @Override
        public String getInfo() {
            return info;
        }

        /** setting info for node **/
        @Override
        public void setInfo(String s) {
            this.info = s;
        }

        /** getting tag from node **/
        @Override
        public double getTag() {
            return tag;
        }

        /** setting tag for node **/
        @Override
        public void setTag(double t) {
            this.tag = t;
        }

        /** return the copy of the collection of all the neighbors of node **/
        public Collection<node_info> getNi() { // return the copy of the collection of all the neighbors of node
            return this.neigh.values();
        }

        /** checking if there is a neighbor **/
        public boolean hasNi(int key) { // checking if there is a neighbor
            return this.neigh.containsKey(key); // using the containsKey function of Hashmap
        }

        /** adds a neighbor to given node **/
        public void addNi(node_info t) { //adds a neighbor to given node
            if (this.equals(t) == false && this.hasNi(t.getKey()) == false) // check if it doesn't add itself and that it doesn't already have itself as a neighbor
            {
                NodeInfo n = (NodeInfo) t;
                this.neigh.put(t.getKey(), t);
                n.neigh.put(getKey(), this);
            }
        }

        /** Remove a node from the neighbor hashmap **/
        public void removeNode(node_info node) { //Remove a node from the neighbor hashmap
            NodeInfo n = (NodeInfo) node;
            this.neigh.remove(node.getKey()); //removing from the neighbor hashmap
            n.neigh.remove(getKey()); // removing the neighbor from hashmap
        }

        /** returning the weight from this.node to another node **/
        public double getWeight(int node) { // returning the weight from this.node to another node
            return this.weights.get(node);
        }

        /** setting weight for given neighbor **/
        public void setWeight(int key, double weight) { // setting the weight from this.node to another node
            if(this.getKey() == key){
                return;
            }
            if(this.hasNi(key)) {
                this.weights.put(key, weight);
            }
            return;
        }

        /** disconecting all the edges from given node **/
        public void disconnectEdges() { // disconnect from all neighbors
            node_info temp1;
            Iterator<node_info> k = this.neigh.values().iterator(); // create an iterator
            while (k.hasNext()) // loop through the iterator with the condition that there is a next neighbor.
            {
                temp1 = k.next(); // place the neighbor into temp1
                k.remove(); //
                removeNode(temp1); //remove the neighbr
                this.setWeight(temp1.getKey(), 0);
            }
        }
    }
}







