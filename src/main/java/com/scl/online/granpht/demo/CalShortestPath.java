package com.scl.online.granpht.demo;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class CalShortestPath {
    //    private  String[] str={"u0","u1","u2","u3","u4","u5","u6","u7"};
//    private int[] startPoint={0,0,0,1,1,3,3,3,3,2,4,5,5,4,6};
//    private int[] endPoint={1,3,2,4,3,4,5,6,2,6,5,6,7,7,7};
//    private double[] weights={2,8,1,1,6,5,1,2,7,9,3,4,6,9,3};
    private String[] str;
    private int[] startPoint;
    private int[] endPoint;
    private double[] weights;
    private Double[][] Dij;

    /**
     * @param str        顶点集合
     *                   用三个一维数据保存图Graph的信息
     * @param startPoint 边的起点
     * @param endPoint   边的终点
     * @param weights    对应边的权值
     * @param Dij        任意两点之间的最短距离
     */
    public CalShortestPath(String[] str, int[] startPoint, int[] endPoint, double[] weights) {
        super();
        this.str = str;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.weights = weights;
        this.Dij = new Double[str.length][str.length];
    }

    /**
     * 主方法
     *
     * @param args
     */
    public static void main(String[] args) {
        String[] str = {"u0", "u1", "u2", "u3", "u4", "u5", "u6", "u7"};
        int[] startPoint = {0, 0, 0, 1, 1, 3, 3, 3, 3, 2, 4, 5, 5, 4, 6};
        int[] endPoint = {1, 3, 2, 4, 3, 4, 5, 6, 2, 6, 5, 6, 7, 7, 7};
        double[] weights = {2, 8, 1, 1, 6, 5, 1, 2, 7, 9, 3, 4, 6, 9, 3};
        CalShortestPath calShortestPath = new CalShortestPath(str, startPoint, endPoint, weights);
        calShortestPath.getShortestPath();
        Double[][] dij2 = calShortestPath.getDij();
        for (int i = 0; i < dij2.length; i++) {
            System.out.print("[");
            for (int j = 0; j < dij2.length; j++) {
                System.out.print(Integer.parseInt(dij2[i][j].toString().substring(0, dij2[i][j].toString().indexOf("."))) + ",");
            }
            System.out.println("]\n");
        }
    }

    /**
     * 得到一个有向带权图，本来是做无向图的还没写好，这个每队相邻顶点之间用两条方向相反的边表示
     *
     * @return 返回一个带权有向图
     */
    public SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> genSimpleDirectedWeightedGraph() {
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> directedGraph =
                new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        for (int i = 0; i < str.length; i++) {
            directedGraph.addVertex(str[i]);
        }
        DefaultWeightedEdge[] addEdgeUp = new DefaultWeightedEdge[startPoint.length];
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

        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str.length; j++) {
                GraphPath<String, DefaultWeightedEdge> path = dijkstraAlg.getPath(str[i], str[j]);
//                System.out.println(path + ":" + path.getWeight());
                Dij[i][j] = path.getWeight();
            }
        }
//        FloydWarshallShortestPaths<String, DefaultWeightedEdge> floydWarshallShortestPaths = new FloydWarshallShortestPaths<>(directedGraph);
//        System.out.println(floydWarshallShortestPaths.getPath("u0","u7"));
    }

    public Double[][] getDij() {
        return Dij;
    }

}