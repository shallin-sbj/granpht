package com.scl.online.grahtguava;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class demo {
    private static final int NODE_COUNT = 1000;

    public static void main(String[] args) {

        MutableGraph<Integer> graph1 = GraphBuilder.directed()       //指定为有向图
                .nodeOrder(ElementOrder.<Integer>insertion())        //节点按插入顺序输出
                        //(还可以取值无序unordered()、节点类型的自然顺序natural())
                .expectedNodeCount( NODE_COUNT) //预期节点数
                .allowsSelfLoops(true) //允许自环
                .build();
        graph1.addNode(1);
        graph1.addNode(2);

        log.info("initlized graph1: " + graph1);
    }
}
