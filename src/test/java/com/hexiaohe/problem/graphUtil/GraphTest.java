package com.hexiaohe.problem.graphUtil;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GraphTest {
    private Graph g;

    @Before
    public void init() {
        g = new Graph();
        g.addVertex('A', Arrays.asList(new Vertex('B', 5), new Vertex('D', 5), new Vertex('E', 7)));
        g.addVertex('B', Arrays.asList(new Vertex('C', 4)));
        g.addVertex('C', Arrays.asList(new Vertex('D', 8), new Vertex('E', 2)));
        g.addVertex('D', Arrays.asList(new Vertex('C', 8), new Vertex('E', 6)));
        g.addVertex('E', Arrays.asList(new Vertex('B', 3)));
    }

    @Test
    public void getShortestPathDistanceTest() {
        Integer shortestPathDistance1 = g.getShortestPathDistance('A', 'C');
        Integer shortestPathDistance2 = g.getShortestPathDistance('C', 'C');
        System.out.println("shortestPathDistance A-C: " + shortestPathDistance1);
        System.out.println("shortestPathDistance A-C: " + shortestPathDistance2);
    }

    @Test
    public void getDistanceOfPathTest() {
        Integer distanceOfPath1 = g.getDistanceOfPath('A', 'E', 'B', 'C', 'D');
        Integer distanceOfPath2 = g.getDistanceOfPath('A', 'E', 'D');
        System.out.println("distanceOfPath A-E-B-C-D:" + distanceOfPath1);
        System.out.println("distanceOfPath A-E-D:" + distanceOfPath2);
    }

    @Test
    public void DFSByCertainDepthTest() {
        List<String> strings1 = g.DFSByCertainDepth(3, 'C', 'C');
        List<String> strings2 = g.DFSByCertainDepth(4, 'A', 'C');
        System.out.println("DFSByCertainDepth C-C:" + strings1);
        System.out.println("DFSByCertainDepth A-C:" + strings2);
    }
}
