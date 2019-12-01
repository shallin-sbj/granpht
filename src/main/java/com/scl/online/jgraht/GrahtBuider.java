package com.scl.online.jgraht;

import java.util.ArrayList;

/**
 * 图生产器
 */
public class GrahtBuider {

    /**
     * 创建指定边的个数
     *
     * @param vertexNum 边的个数
     * @return
     */
    public static GrantInfo create(int vertexNum) {
        if (vertexNum != 0) {

            String[] str = new String[vertexNum];
            int[] startPoint = new int[vertexNum];
            int[] endPoint = new int[vertexNum];
            double[] weights = new double[vertexNum];

            for (int i = 0; i < vertexNum; i++) {
                str[i] = "u" + i;
//                startPoint[i] = getRoundNum(vertexNum-1);
//                endPoint[i] = getRoundNum(vertexNum-1);
//                weights[i] = getRoundNum(vertexNum-1);

                startPoint[i] = getRoundNum(vertexNum - 1, 0);
                endPoint[i] = getRoundNum(vertexNum - 1, 0);
                weights[i] = getRoundNum(vertexNum - 1, 0);
            }
            return new GrantInfo(str, startPoint, endPoint, weights);
        }
        return null;
    }

    private static int getRoundNum(int vertexNum, int min) {
//        long randomNum = System.currentTimeMillis();
//        return (int) Math.round(Math.random() * vertexNum);
        return min + (int) (Math.random() * (vertexNum - min + 1));
    }

//    /**
//     * @param num
//     * @param count
//     * @return
//     */
//    public static double[] fn(int num, int count) {
//        ArrayList<Integer> numbers = new ArrayList();
//        double[] rtnumbers = new double[count];
//        for (int i = 0; i < num; i++) { //初始化数组
//            numbers.add(i + 1);
//        }
//        for (int j = 0; j < count; j++) {
//            int raNum = (int) (Math.random() * numbers.size());
//            rtnumbers[j] = numbers.get(raNum);
//            numbers.remove(raNum);
//        }
//        return rtnumbers;
//    }


//
//    public int[] fn(int n,int t)
//    {
//        ArrayList<Integer> numbers=new ArrayList();
//        int[] rtnumbers=new int[t];
//        for(int i=0;i<n;i++){ //初始化数组
//            numbers.add(i+1);
//        }
//        for(int j=0;j<t;j++){
//            int raNum=(int)(Math.random()*numbers.size());
//            rtnumbers[j]=numbers.get(raNum);
//            numbers.remove(raNum);
//        }
//        return rtnumbers;
//    }

    /**
     * 创建指定边的个数
     *
     * @param vertexNum 边的个数
     * @return
     */
    public static GrantInfo createForGuava(int vertexNum) {
        if (vertexNum != 0) {
            String[] str = new String[vertexNum];
            int[] startPoint = new int[vertexNum];
            int[] endPoint = new int[vertexNum];
            double[] weights = new double[vertexNum];

            for (int i = 0; i < vertexNum; i++) {
                str[i] = "u" + i;
            }
            return new GrantInfo(str, startPoint, endPoint, weights);
        }
        return null;
    }

}
