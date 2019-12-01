package com.scl.online.controller;

import com.google.common.graph.*;
import com.scl.online.grahtguava.DijkStraaSolveService;
import com.scl.online.jgraht.CalGrahtService;
import com.scl.online.jgraht.GrahtBuider;
import com.scl.online.jgraht.GrantInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jgrapht.Graphs;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
public class GraghtController {

    @PostMapping("guava")
    public Map<String, String> creatGrate(int num) {
        Map<String, String> resultMap = new HashMap<>();
        GrantInfo grantInfo = GrahtBuider.create(num);
        if (null != grantInfo) {
            long start = System.currentTimeMillis();
            DijkStraaSolveService dijkStraaSolveService = new DijkStraaSolveService(grantInfo);
            MutableValueGraph<String, Integer> graph = dijkStraaSolveService.getGraph();
            log.info("构造图耗费时间 ：" + (System.currentTimeMillis() - start));
            log.info("graph 信息 ：" + graph);
            log.info("nodes: " + graph.asGraph().nodes().size());
            log.info("Edge： " + graph.asGraph().edges().size());

            Set<EndpointPair<String>> edges = graph.edges();
            String node = "u2";
            String node2 = "u12";
            // 度
            long start2 = System.currentTimeMillis();
            log.info("度：" + graph.degree(node)); // 出度 output: 2
            log.info("入度：" + graph.inDegree(node)); // 入度 output: 0
            log.info("出度：" + graph.outDegree(node)); // 入度 output: 2
            log.info("查询三个度耗时：" + (System.currentTimeMillis() - start2));
            // Graphs 工具类
            long start3 = System.currentTimeMillis();
            log.info("当前顶点的后续节点列表：" + graph.successors(node));
            log.info("耗时：" + (System.currentTimeMillis() - start3));
            // 当前顶点的前驱 节点列表 output: [D, E]
            long start4 = System.currentTimeMillis();
            log.info("当前顶点的前驱 节点列表：" + graph.predecessors(node));
            log.info("耗时：" + (System.currentTimeMillis() - start4));
            // 邻接点列表
            long start5 = System.currentTimeMillis();
            log.info("当前顶点的前驱 节点列表：" + graph.adjacentNodes(node));
            log.info("耗时：" + (System.currentTimeMillis() - start5));

            long start6 = System.currentTimeMillis();
            log.info("判别连通性：" + graph.hasEdgeConnecting(node, node2));
            log.info("耗时：" + (System.currentTimeMillis() - start6));

            log.info("转换成不可变graph:");
            long start7 = System.currentTimeMillis();
            ImmutableGraph<String> immutableGraph = ImmutableGraph.copyOf(graph.asGraph());
            Set<String> nodes = immutableGraph.nodes();//返回图中所有的节点(顺序依赖nodeOrder)
            log.info("nodes :" + nodes.size() + "  耗时：" + (System.currentTimeMillis() - start7));
            long start8 = System.currentTimeMillis();
            log.info("判断是否存在环: " + com.google.common.graph.Graphs.hasCycle(graph.asGraph()));
            log.info("耗时 :" + (System.currentTimeMillis() - start8));

            log.info("获取仅包含指定节点的生成子图:");
            long start9 = System.currentTimeMillis();
            Set<String> subNodes = new HashSet<>();
            subNodes.add(node);
            subNodes.add(node2);
            MutableGraph<String> stringMutableGraph = com.google.common.graph.Graphs.inducedSubgraph(graph.asGraph(), subNodes);
            log.info("子图信息：" + stringMutableGraph);
            log.info("耗时 :" + (System.currentTimeMillis() - start9));

            log.info("总耗时：" + (System.currentTimeMillis() - start));

            graph = null;
            grantInfo = null;
        }
        return resultMap;
    }

    @PostMapping("jgrate")
    public Map<String, String> creatJGrate(int num) {
        Map<String, String> resultMap = new HashMap<>();
        GrantInfo grantInfo = GrahtBuider.create(num);
        if (null != grantInfo) {
            long start = System.currentTimeMillis();
            CalGrahtService calGrahtService = new CalGrahtService(grantInfo);
            log.info("构造图耗费时间 ：" + (System.currentTimeMillis() - start));

            String vertex = "u1";
            String vertex2 = "u1000";
            log.info("当前节点是：" + vertex);

            SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> simpleDirectedWeightedGraph = calGrahtService.getSimpleDirectedWeightedGraph();
            log.info("vertex: " + simpleDirectedWeightedGraph.vertexSet().size());
            log.info("Edge： " + simpleDirectedWeightedGraph.edgeSet().size());
            // 度
            long start2 = System.currentTimeMillis();
            log.info("出度：" + simpleDirectedWeightedGraph.degreeOf(vertex)); // 出度 output: 2
            log.info("入度：" + simpleDirectedWeightedGraph.inDegreeOf(vertex)); // 入度 output: 0
            log.info("入度：" + simpleDirectedWeightedGraph.outDegreeOf(vertex)); // 入度 output: 2
            log.info("查询三个度耗时：" + (System.currentTimeMillis() - start2));
            // Graphs 工具类
            // 当前顶点的后续节点列表 output: [B, C]
            long start3 = System.currentTimeMillis();
            log.info("当前顶点的后续节点列表：" + Graphs.successorListOf(simpleDirectedWeightedGraph, vertex));
            log.info("耗时：" + (System.currentTimeMillis() - start3));
            // 当前顶点的前驱 节点列表 output: [D, E]
            long start4 = System.currentTimeMillis();
            log.info("当前顶点的前驱 节点列表：" + Graphs.predecessorListOf(simpleDirectedWeightedGraph, vertex));
            log.info("耗时：" + (System.currentTimeMillis() - start4));
            // 图的遍历
            // --深度优先遍历
            log.info("深度优先遍历:" + vertex);
            long start5 = System.currentTimeMillis();
            Iterator<String> depthFirstIterator = new DepthFirstIterator<>(simpleDirectedWeightedGraph, vertex);

            log.info("耗时：" + (System.currentTimeMillis() - start5));
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
            log.info("耗时：" + (System.currentTimeMillis() - start6));
//            while (breadthFirstIterator.hasNext()) {
//                String p = breadthFirstIterator.next();
//                System.out.println(p);
//            }

            // 图中“环”的操作
            long start7 = System.currentTimeMillis();
            CycleDetector<String, DefaultWeightedEdge> cycleDetector = new CycleDetector<String, DefaultWeightedEdge>(simpleDirectedWeightedGraph);
            // 探测图中是否有环 output: false
            log.info(" 探测图中是否有环 :" + cycleDetector.detectCycles());
            log.info("耗时：" + (System.currentTimeMillis() - start7));
            // 找出图中的环 output: []
            long start8 = System.currentTimeMillis();
            log.info(" 找出图中的环 :" + cycleDetector.findCycles());
            log.info("耗时：" + (System.currentTimeMillis() - start8));

            // 最短路径
//            GraphPath<String, DefaultEdge> path = DijkstraShortestPath.findPathBetween(simpleDirectedWeightedGraph, vertex, vertex2);
            // [A, B, D, F]
//            System.out.println("a-f 在两个顶点之间找到一条路径:"+ path.getVertexList());


//            DijkstraShortestPath<String, DefaultEdge> dijkstraAlg = new DijkstraShortestPath<>(graph);

            log.info("总耗时：" + (System.currentTimeMillis() - start));
            simpleDirectedWeightedGraph = null;
            grantInfo = null;

        }
        return resultMap;
    }
}
