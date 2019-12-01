package com.scl.online.jgraht;

import lombok.extern.slf4j.Slf4j;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.List;

@Slf4j
public class CalGrahtService {

    private GrantInfo grantInfo;

    public SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> getSimpleDirectedWeightedGraph() {
        return simpleDirectedWeightedGraph;
    }

    private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> simpleDirectedWeightedGraph;

    /**
     * @param str        顶点集合
     *                   用三个一维数据保存图Graph的信息
     * @param startPoint 边的起点
     * @param endPoint   边的终点
     * @param weights    对应边的权值
     *                   param Dij        任意两点之间的最短距离
     */
    public CalGrahtService(String[] str, int[] startPoint, int[] endPoint, double[] weights) {
        super();
        grantInfo = new GrantInfo(str, startPoint, endPoint, weights);
        grantInfo.setDij(new Double[str.length][str.length]);
    }

    public CalGrahtService(GrantInfo grantInfo) {
        super();
        this.grantInfo = grantInfo;
        int length = grantInfo.getStr().length;
        grantInfo.setDij(new Double[length][length]);
        simpleDirectedWeightedGraph = genSimpleDirectedWeightedGraph();
//        simpleDirectedWeightedGraph = creatMutableValueGraph();
    }

    /**
     * 得到一个有向带权图，这个每队相邻顶点之间用两条方向相反的边表示
     *
     * @return 返回一个带权有向图
     */
    public SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> genSimpleDirectedWeightedGraph() {
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> directedGraph =
                new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        String[] str = grantInfo.getStr();
        int[] startPoint = grantInfo.getStartPoint();
        int[] endPoint = grantInfo.getEndPoint();
        double[] weights = grantInfo.getWeights();

        for (int i = 0; i < str.length; i++) {
            directedGraph.addVertex(str[i]);   // 加顶点
        }
        DefaultWeightedEdge[] addEdgeUp = new DefaultWeightedEdge[startPoint.length];   // 边
        DefaultWeightedEdge[] addEdgeDown = new DefaultWeightedEdge[startPoint.length];
        for (int i = 0; i < endPoint.length; i++) {
            try {
                String targetVertex = str[endPoint[i]];
                String sourceVertex = str[startPoint[i]];
                if (targetVertex.equals(sourceVertex)) {
                    continue;
                }
                addEdgeUp[i] = directedGraph.addEdge(sourceVertex, targetVertex);
                addEdgeDown[i] = directedGraph.addEdge(targetVertex, sourceVertex);
                double weight = weights[i];

                if (addEdgeUp[i] != null) {
                    directedGraph.setEdgeWeight(addEdgeUp[i], weight);
                }
                if (addEdgeDown[i] != null) {
                    directedGraph.setEdgeWeight(addEdgeDown[i], weight);
                }
            } catch (Exception e) {
                log.info("构造图错误", e.getCause());
                log.info("i :" + i);
                log.info("endPoint[i]: " + endPoint[i]);
                log.info("startPoint[i]: " + startPoint[i]);
                log.info("targetVertex: " + str[endPoint[i]]);
                log.info("sourceVertex: " + str[startPoint[i]]);
                e.printStackTrace();
            }
        }
        return directedGraph;
    }

    /**
     * 得到一个有向方向权图（闭环），
     *
     * @return 返回一个带权无向图
     */
    public DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> getDefaultDirectedWeightedGraph() {
        DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> directedGraph =
                new DefaultDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);

        String[] str = grantInfo.getStr();
        int[] startPoint = grantInfo.getStartPoint();
        int[] endPoint = grantInfo.getEndPoint();
        double[] weights = grantInfo.getWeights();
        for (int i = 0; i < str.length; i++) {
            directedGraph.addVertex(str[i]);   // 加顶点
        }
        DefaultWeightedEdge[] addEdgeUp = new DefaultWeightedEdge[startPoint.length];   // 边
        DefaultWeightedEdge[] addEdgeDown = new DefaultWeightedEdge[startPoint.length];
        for (int i = 0; i < endPoint.length; i++) {
            addEdgeUp[i] = directedGraph.addEdge(str[startPoint[i]], str[endPoint[i]]);
            addEdgeDown[i] = directedGraph.addEdge(str[endPoint[i]], str[startPoint[i]]);
            directedGraph.setEdgeWeight(addEdgeUp[i], weights[i]);
            directedGraph.setEdgeWeight(addEdgeDown[i], weights[i]);
        }
        return directedGraph;
    }

    /**
     * 根据得到的带权有向图，Dijkstra计算最短路径，并保存他们的路径权值之和到数组Dij中
     */
    public void getShortestPath() {
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> directedGraph = genSimpleDirectedWeightedGraph();

//        System.out.println("Shortest path from u0 to u7:");
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraAlg =
                new DijkstraShortestPath<>(directedGraph);
//        SingleSourcePaths<String, DefaultWeightedEdge> iPaths = dijkstraAlg.getPaths("u0");
//        System.out.println(iPaths.getPath("u7") + "\n");

//        String[] str = grantInfo.getStr();
//        Double[][] dij = grantInfo.getDij();
//        for (int i = 0; i < str.length; i++) {
//            for (int j = 0; j < str.length; j++) {
//                GraphPath<String, DefaultWeightedEdge> path = dijkstraAlg.getPath(str[i], str[j]);
////                System.out.println(path + ":" + path.getWeight());
//                Dij[i][j] = path.getWeight();
//            }
//        }
//        FloydWarshallShortestPaths<String, DefaultWeightedEdge> floydWarshallShortestPaths = new FloydWarshallShortestPaths<>(directedGraph);
//        System.out.println(floydWarshallShortestPaths.getPath("FloydWarshallShortestPaths"+ "u0","u7"));
    }

    public Double[][] getDij() {
        return grantInfo.getDij();
    }

    /**
     * 当前顶点的后续节点列表
     */
    public List<String> getNextVertex(String vertex) {
        DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> defaultDirectedWeightedGraph = getDefaultDirectedWeightedGraph();
        List<String> strings = Graphs.successorListOf(defaultDirectedWeightedGraph, vertex);
        return strings;
    }

    /**
     * 当前顶点的前驱
     */
    public List<String> getBeforeVertex(String vertex) {
//        DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> defaultDirectedWeightedGraph = getDefaultDirectedWeightedGraph();
//        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> simpleDirectedWeightedGraph = genSimpleDirectedWeightedGraph();
        List<String> strings = Graphs.predecessorListOf(simpleDirectedWeightedGraph, vertex);
        return strings;
    }


    public SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> creatMutableValueGraph() {
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph =
                new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        if (grantInfo != null) {
            String[] str = grantInfo.getStr();
            int[] startPoint = grantInfo.getStartPoint();
            int[] endPoint = grantInfo.getEndPoint();
            double[] weights = grantInfo.getWeights();
            for (int i = 0; i < startPoint.length; i++) {
                double weight = weights[i];
                for (int j = 0; j < endPoint.length; j++) {
                    if (str[i].equals(str[j])) {
                        continue;
                    }
                    graph.setEdgeWeight(str[i], str[j], (int) weight);
                }
            }
        }
        return graph;
    }

}