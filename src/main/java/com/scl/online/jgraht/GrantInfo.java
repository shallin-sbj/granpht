package com.scl.online.jgraht;

import lombok.Data;

@Data
public class GrantInfo {
    private String[] str;
    private int[] startPoint;
    private int[] endPoint;
    private double[] weights;
    /**
     * 任意两点之间的最短距离
     */
    private Double[][] Dij;

    public GrantInfo() {
    }

    public GrantInfo(String[] str, int[] startPoint, int[] endPoint, double[] weights) {
        this.str = str;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.weights = weights;
    }
}
