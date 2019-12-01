package com.scl.online.jgraht;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Slf4j
class CalShortestPathTest {


    /**
     * 有向带权图（无闭环）
     */
    @Test
    void simpleDirectedWeightedGraph() {
        String[] str = {"u0", "u1", "u2", "u3", "u4", "u5", "u6", "u7"};
        int[] startPoint = {0, 0, 0, 1, 1, 3, 3, 3, 3, 2, 4, 5, 5, 4, 6};
        int[] endPoint = {1, 3, 2, 4, 3, 4, 5, 6, 2, 6, 5, 6, 7, 7, 7};
        double[] weights = {2, 8, 1, 1, 6, 5, 1, 2, 7, 9, 3, 4, 6, 9, 3};
        CalGrahtService calGrahtService = new CalGrahtService(str, startPoint, endPoint, weights);
        calGrahtService.getShortestPath();
        Double[][] dij2 = calGrahtService.getDij();
        for (int i = 0; i < dij2.length; i++) {
            System.out.print("[");
            for (int j = 0; j < dij2.length; j++) {
                System.out.print(Integer.parseInt(dij2[i][j].toString().substring(0, dij2[i][j].toString().indexOf("."))) + ",");
            }
            System.out.println("]\n");
        }
    }

    /**
     * 有像带权图有闭环
     */
    @Test
    void defaultDirectedWeightedGraph() {
        String[] str = {"u0", "u1", "u2", "u3", "u4", "u5", "u6", "u7"};
        int[] startPoint = {0, 0, 0, 1, 1, 3, 3, 3, 3, 2, 4, 5, 5, 4, 6};
        int[] endPoint = {1, 3, 2, 4, 3, 4, 5, 6, 2, 6, 5, 6, 7, 7, 7};
        double[] weights = {2, 8, 1, 1, 6, 5, 1, 2, 7, 9, 3, 4, 6, 9, 3};
        CalGrahtService calGrahtService = new CalGrahtService(str, startPoint, endPoint, weights);
        calGrahtService.getShortestPath();
        Double[][] dij2 = calGrahtService.getDij();
        for (int i = 0; i < dij2.length; i++) {
            System.out.print("[");
            for (int j = 0; j < dij2.length; j++) {
                System.out.print(Integer.parseInt(dij2[i][j].toString().substring(0, dij2[i][j].toString().indexOf("."))) + ",");
            }
            System.out.println("]\n");
        }
    }

    @Test
    void getNextVertex() {
        String[] str = {"u0", "u1", "u2", "u3", "u4", "u5", "u6", "u7"};
        int[] startPoint = {0, 0, 0, 1, 1, 3, 3, 3, 3, 2, 4, 5, 5, 4, 6};
        int[] endPoint = {1, 3, 2, 4, 3, 4, 5, 6, 2, 6, 5, 6, 7, 7, 7};
        double[] weights = {2, 8, 1, 1, 6, 5, 1, 2, 7, 9, 3, 4, 6, 9, 3};
        CalGrahtService calGrahtService = new CalGrahtService(str, startPoint, endPoint, weights);
        calGrahtService.getShortestPath();
        Double[][] dij2 = calGrahtService.getDij();
        for (int i = 0; i < dij2.length; i++) {
            System.out.print("[");
            for (int j = 0; j < dij2.length; j++) {
                System.out.print(Integer.parseInt(dij2[i][j].toString().substring(0, dij2[i][j].toString().indexOf("."))) + ",");
            }
            System.out.println("]\n");
        }
    }

    @Test
    void getBeforeVertex() {
        GrantInfo grantInfo = GrahtBuider.create(10000);
        if (null != grantInfo) {
            long start = System.currentTimeMillis();
            CalGrahtService calGrahtService = new CalGrahtService(grantInfo);
            log.info("构造图耗费时间 ：" + (System.currentTimeMillis() - start));

            String vertex = "u1";
            String vertex2 = "u1000";
            log.info("当前节点是：" + vertex);

            SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> simpleDirectedWeightedGraph = calGrahtService.getSimpleDirectedWeightedGraph();
            log.info("vertex: " + simpleDirectedWeightedGraph.vertexSet().size());
            log.info("Edge： "+ simpleDirectedWeightedGraph.edgeSet().size());
            // 度
            long start2 = System.currentTimeMillis();
            log.info("出度：" + simpleDirectedWeightedGraph.degreeOf(vertex)); // 出度 output: 2
            log.info("入度：" + simpleDirectedWeightedGraph.inDegreeOf(vertex)); // 入度 output: 0
            log.info("入度：" + simpleDirectedWeightedGraph.outDegreeOf(vertex)); // 入度 output: 2
            log.info("查询三个度耗时："+ (System.currentTimeMillis() - start2));
            // Graphs 工具类
            // 当前顶点的后续节点列表 output: [B, C]
            long start3 = System.currentTimeMillis();
            log.info("当前顶点的后续节点列表：" + Graphs.successorListOf(simpleDirectedWeightedGraph, vertex));
            log.info("耗时："+ (System.currentTimeMillis() - start3));
            // 当前顶点的前驱 节点列表 output: [D, E]
            long start4 = System.currentTimeMillis();
            log.info("当前顶点的前驱 节点列表：" + Graphs.predecessorListOf(simpleDirectedWeightedGraph, vertex));
            log.info("耗时："+ (System.currentTimeMillis() - start4));
            // 图的遍历
            // --深度优先遍历
            log.info("深度优先遍历:" + vertex);
            long start5 = System.currentTimeMillis();
            Iterator<String> depthFirstIterator = new DepthFirstIterator<>(simpleDirectedWeightedGraph, vertex);
            log.info("耗时："+ (System.currentTimeMillis() - start5));
//            while (depthFirstIterator.hasNext()) {
//                String p = depthFirstIterator.next();
//                System.out.println(p);
//
//            }

            log.info(StringUtils.center("split", 100, "=")); // 分割线
            log.info("广度优先遍历:" + vertex);
            // --广度优先遍历
            long start6 = System.currentTimeMillis();
            Iterator<String> breadthFirstIterator = new BreadthFirstIterator<>(simpleDirectedWeightedGraph, vertex);
            log.info("耗时："+ (System.currentTimeMillis() - start6));
//            while (breadthFirstIterator.hasNext()) {
//                String p = breadthFirstIterator.next();
//                System.out.println(p);
//            }

            // 图中“环”的操作
            long start7 = System.currentTimeMillis();
            CycleDetector<String, DefaultWeightedEdge> cycleDetector = new CycleDetector<String, DefaultWeightedEdge>(simpleDirectedWeightedGraph);
            // 探测图中是否有环 output: false
            log.info(" 探测图中是否有环 :" + cycleDetector.detectCycles());
            log.info("耗时："+ (System.currentTimeMillis() - start7));
            // 找出图中的环 output: []
            long start8 = System.currentTimeMillis();
            log.info(" 找出图中的环 :" + cycleDetector.findCycles());
            log.info("耗时："+ (System.currentTimeMillis() - start8));

            // 最短路径
//            GraphPath<String, DefaultEdge> path = DijkstraShortestPath.findPathBetween(simpleDirectedWeightedGraph, vertex, vertex2);
            // [A, B, D, F]
//            System.out.println("a-f 在两个顶点之间找到一条路径:"+ path.getVertexList());


//            DijkstraShortestPath<String, DefaultEdge> dijkstraAlg = new DijkstraShortestPath<>(graph);
            log.info("总耗时：" + (System.currentTimeMillis() - start));
        }
    }

}