//package com.scl.online.granpht.demo;
//
//import com.mxgraph.layout.*;
//import com.mxgraph.swing.*;
//import org.jgrapht.ListenableGraph;
//import org.jgrapht.ext.*;
//import org.jgrapht.graph.DefaultDirectedGraph;
//import org.jgrapht.graph.DefaultListenableGraph;
//import org.jgrapht.graph.DefaultWeightedEdge;
//
//import javax.swing.*;
//import java.awt.*;
//
///**
// * A demo applet that shows how to use JGraphX to visualize JGraphT graphs. Applet based on
// * JGraphAdapterDemo.
// */
//public class CustomJGraphXAdapter extends JApplet {
//    private static final long serialVersionUID = 2202072534703043194L;
//
//    private static final Dimension DEFAULT_SIZE = new Dimension(530 * 2, 320 * 2);
//
//    private JGraphXAdapter<String, DefaultWeightedEdge> jgxAdapter;
//
//    /**
//     * An alternative starting point for this demo, to also allow running this applet as an
//     * application.
//     *
//     * @param args command line arguments
//     */
//    public static void main(String[] args) {
//        CustomJGraphXAdapter applet = new CustomJGraphXAdapter();
//        applet.init();
//
//        JFrame frame = new JFrame();
//        frame.getContentPane().add(applet);
//        frame.setTitle("JGraphT Adapter to JGraphX Demo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    @Override
//    public void init() {
//        // create a JGraphT graph
//        ListenableGraph<String, DefaultWeightedEdge> directedGraph =
//                new DefaultListenableGraph<>(new DefaultDirectedGraph<>(DefaultWeightedEdge.class));
//
//        // create a visualization using JGraph, via an adapter
//        jgxAdapter = new JGraphXAdapter<>(directedGraph);
//
//        setPreferredSize(DEFAULT_SIZE);
//        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
//        component.setConnectable(false);
//        component.getGraph().setAllowDanglingEdges(false);
//        getContentPane().add(component);
//        resize(DEFAULT_SIZE);
//
//        String[] str = {"u0", "u1", "u2", "u3", "u4", "u5", "u6", "u7"};
//        int[] startPoint = {0, 0, 0, 1, 1, 3, 3, 3, 3, 2, 4, 5, 5, 4, 6};
//        int[] endPoint = {1, 3, 2, 4, 3, 4, 5, 6, 2, 6, 5, 6, 7, 7, 7};
//        double[] weights = {2, 8, 1, 1, 6, 5, 1, 2, 7, 9, 3, 4, 6, 9, 3};
//        for (int i = 0; i < str.length; i++) {
//            directedGraph.addVertex(str[i]);
//        }
//        DefaultWeightedEdge[] addEdgeUp = new DefaultWeightedEdge[startPoint.length];
//        DefaultWeightedEdge[] addEdgeDown = new DefaultWeightedEdge[startPoint.length];
//        for (int i = 0; i < endPoint.length; i++) {
//            addEdgeUp[i] = directedGraph.addEdge(str[startPoint[i]], str[endPoint[i]]);
//            addEdgeDown[i] = directedGraph.addEdge(str[endPoint[i]], str[startPoint[i]]);
//        }
//
//        // positioning via jgraphx layouts
//        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
//
//        // center the circle
//        int radius = 300;
//        layout.setX0((DEFAULT_SIZE.width) / 2 - 250);
//        layout.setY0((DEFAULT_SIZE.height) / 2 - 310);
//        layout.setRadius(radius);
//        layout.setMoveCircle(true);
//
//        layout.execute(jgxAdapter.getDefaultParent());
//        // that's all there is to it!...
//    }
//}
