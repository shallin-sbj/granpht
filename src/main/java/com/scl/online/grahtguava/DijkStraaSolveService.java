package com.scl.online.grahtguava;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import com.scl.online.jgraht.GrantInfo;

public class DijkStraaSolveService {

    private GrantInfo grantInfo;

    private final MutableValueGraph<String, Integer> graph;

    public MutableValueGraph<String, Integer> getGraph() {
        return graph;
    }

    public DijkStraaSolveService(GrantInfo grantInfo) {
        this.grantInfo = grantInfo;
        graph = creatMutableValueGraph();
    }

    private MutableValueGraph<String, Integer> creatMutableValueGraph() {
        MutableValueGraph<String, Integer> graph = ValueGraphBuilder.directed()
                .nodeOrder(ElementOrder.<String>natural()).expectedNodeCount(1000000).allowsSelfLoops(true).build();
        if (grantInfo!=null){
            String[] str = grantInfo.getStr();
            int[] startPoint = grantInfo.getStartPoint();
            int[] endPoint = grantInfo.getEndPoint();
            double[] weights = grantInfo.getWeights();
            for (int i = 0; i < startPoint.length; i++) {
                double weight = weights[i];
                for (int j = 0; j < endPoint.length; j++) {
                    graph.putEdgeValue(str[i],str[j],(int)weight);
                }
            }
        }
        return graph;
    }

}
