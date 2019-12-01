package com.scl.online.grahtguava;

import com.google.common.graph.*;
import com.scl.online.jgraht.GrahtBuider;
import com.scl.online.jgraht.GrantInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 参考
 * https://www.jianshu.com/p/78786a4f2cf1
 */
@Slf4j
class DijkStraaSolveServiceTest {

    @Test
    void getGraph() {
        GrantInfo grantInfo = GrahtBuider.create(2000);
        if (null != grantInfo) {
            long start = System.currentTimeMillis();
            DijkStraaSolveService dijkStraaSolveService = new DijkStraaSolveService(grantInfo);
            MutableValueGraph<String, Integer> graph = dijkStraaSolveService.getGraph();
            log.info("构造图耗费时间 ：" + (System.currentTimeMillis() - start));
            log.info("graph 信息 ：" + graph);
            log.info("nodes: " + graph.asGraph().nodes().size());
            log.info("Edge： "+ graph.asGraph().edges().size());

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
            log.info("判断是否存在环: " + Graphs.hasCycle(graph.asGraph()));
            log.info("耗时 :" + (System.currentTimeMillis() - start8));

            log.info("获取仅包含指定节点的生成子图:");
            long start9 = System.currentTimeMillis();
            Set<String> subNodes = new HashSet<>();
            subNodes.add(node);
            subNodes.add(node2);
            MutableGraph<String> stringMutableGraph = Graphs.inducedSubgraph(graph.asGraph(), subNodes);
            log.info("子图信息：" + stringMutableGraph);
            log.info("耗时 :" + (System.currentTimeMillis() - start9));

            log.info("总耗时：" + (System.currentTimeMillis() - start));
        }
    }
}