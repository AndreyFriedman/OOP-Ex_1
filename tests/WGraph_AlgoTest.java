package ex1.tests;

import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.node_info;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WGraph_AlgoTest {

    /** WGRAPH_ALGO TESTs **/

    /** checking if all the nodes connected to each other **/
    @Test
    void isConnected(){
        WGraph_DS g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.connect(1,2, 5.66);
        WGraph_Algo gl = new WGraph_Algo();
        gl.init(g);
        assertTrue(gl.isConnected());
        g.addNode(3); // adding node that doesnt connected to any other node
        assertFalse(gl.isConnected());
    }
    /** Testing the shortest path distance **/
    @Test
    void ShortestDist(){
        WGraph_DS g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.connect(1,2, 5.66);
        g.addNode(3);
        g.addNode(4);
        g.connect(4,3, 1);
        g.connect(1,4, 12);
        g.connect(1,3, 9.33);
        g.addNode(5);
        g.connect(4,5, 1.66);
        g.connect(5,3, 3);
        WGraph_Algo gl = new WGraph_Algo();
        gl.init(g);
        assertEquals(gl.shortestPathDist(1,5), 11.99 );
    }

    /** testing the shortest path that returning list **/
    @Test
    void ShortestPath(){
        WGraph_DS g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.connect(1,2, 5.66);
        g.addNode(3);
        g.addNode(4);
        g.connect(4,3, 1);
        g.connect(1,4, 12);
        g.connect(1,3, 9.33);
        g.addNode(5);
        g.connect(4,5, 1.66);
        g.connect(5,3, 3);
        WGraph_Algo gl = new WGraph_Algo();
        gl.init(g);
        List<node_info> path = gl.shortestPath(1,5);
        System.out.println( path.size());
        for (int i = 0; i< 4; i++){

        }
        //assertEquals(gl.shortestPathDist(1,5), 11.99 );
    }


}
