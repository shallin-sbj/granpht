package com.scl.online.granpht.demo;

import org.apache.commons.lang3.StringUtils;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.Iterator;

/**
 * 参考：https://jgrapht.org/guide/UserOverview
 * https://github.com/jgrapht/jgrapht/tree/master/jgrapht-demo/src/main/java/org/jgrapht/demo
 */
public class JgraphtExample {

    public static void main(String[] args) {
        String a = "A";
        String b = "B";
        String c = "C";
        String d = "D";
        String e = "E";
        String f = "F";

        // 构建图
        Graph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        // 添加顶点
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);
        // 添加边
        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(b, d);
        graph.addEdge(c, e);
        graph.addEdge(d, f);
        graph.addEdge(e, f);

        // 度
        System.out.println(graph.degreeOf(a)); // 出度 output: 2
        System.out.println(graph.inDegreeOf(a)); // 入度 output: 0
        System.out.println(graph.outDegreeOf(a)); // 入度 output: 2

        // Graphs 工具类
        // 当前顶点的后续节点列表 output: [B, C]
        System.out.println(Graphs.successorListOf(graph, a));
        // 当前顶点的前驱 节点列表 output: [D, E]
        System.out.println(Graphs.predecessorListOf(graph, f));

        // 图的遍历
        // --深度优先遍历
        Iterator<String> depthFirstIterator = new DepthFirstIterator<>(graph, a);
        while (depthFirstIterator.hasNext()) {
            String p = depthFirstIterator.next();
            System.out.println(p);
            /*
             * <pre>
             * A
             * C
             * E
             * F
             * B
             * D
             * </pre>
             */
        }
        System.out.println(StringUtils.center("split", 100, "=")); // 分割线
        // --广度优先遍历
        Iterator<String> breadthFirstIterator = new BreadthFirstIterator<>(graph, a);
        while (breadthFirstIterator.hasNext()) {
            String p = breadthFirstIterator.next();
            System.out.println(p);
            /*
             * <pre>
             * A
             * B
             * C
             * D
             * E
             * F
             * </pre>
             */
        }

        // 图中“环”的操作
        CycleDetector<String, DefaultEdge> cycleDetector = new CycleDetector<>(graph);
        // 探测图中是否有环 output: false
        System.out.println(" 探测图中是否有环 :" + cycleDetector.detectCycles());
        // 找出图中的环 output: []
        System.out.println(" 找出图中的环 :" + cycleDetector.findCycles());

        // 最短路径
        GraphPath<String, DefaultEdge> path = DijkstraShortestPath.findPathBetween(graph, a, f);
        // [A, B, D, F]
        System.out.println("a-f 在两个顶点之间找到一条路径:"+ path.getVertexList());


        DijkstraShortestPath<String, DefaultEdge> dijkstraAlg = new DijkstraShortestPath<>(graph);
        // [A, B, D, F]
        GraphPath<String, DefaultEdge> path1 = dijkstraAlg.getPath(a, f);
        System.out.println(path1.getEdgeList());
        System.out.println(path1.getWeight());
        System.out.println(path1.getVertexList());

    }
}
