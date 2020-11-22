package ex1.tests;

import ex1.src.WGraph_DS;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WGraph_DSTest {

    /** WGRAPH TESTs **/

    /** checking if 2 nodes are connected with the right weight **/
    @Test
    void connect() {
        WGraph_DS g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.connect(1,2, 5.66);
        assertEquals(g.getEdge(1,2),5.66);
    }


    /** removing node and his edges **/
    @Test
    void removeNodeAndEdges(){
        WGraph_DS g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(5);
        g.connect(1,2, 1);
        g.connect(1,3, 1);
        g.connect(1,4, 1);
        g.connect(1,5, 1);
        g.connect(1,2, 1);
        g.removeNode(1);
        assertEquals(0,g.edgeSize());

    }
}
