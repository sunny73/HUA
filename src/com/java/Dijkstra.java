package com.java;

/*
 *Dijkstra，最短路径算法
 */

import java.util.Arrays;

public class Dijkstra {
    public final int M = -1;
    int n;
    int[][] map;
    int[] shortest;

    boolean[] visited;




    public Dijkstra(int[][] map) {

        this.map = map;
        n = map.length;       //顶点的个数
        shortest = new int[n];  //存放从start到其他节点的最短路径
        //存放从start到其他节点的最短路径
        visited = new boolean[n]; //标记当前该顶点的最短路径是否已经求出，true表示已经求出

    }


   /* public static void main(String[] args) {
        int[][] map = {
                { 0, 8, 1, 2, M },
                { 8, 0, M, 3, M },
                { 1, M, 0, 2, 3 },
                { 2, 3, 2, 0, M },
                { M, M, 3, M, 0 }
        };
        Dijkstra a=new Dijkstra(map);

        int orig = 0; //起始点
        //寻找最短路径
        int[] shortPath = a.dijkstra_alg(orig,2);

        if (shortPath == null) {
            return;
        }


            System.out.println(Arrays.toString(shortPath));

    }*/

    int[] dijkstra_alg(int orig, int end) {
        int[] shorpath;
        // TODO Auto-generated method stub
        // 初始化，第一个顶点求出
        shortest[orig] = 0;
        visited[orig] = true;


        //存放从start到其他各节点的最短路径
        String[] path = new String[n];
        for (int i = 0; i < n; i++) {
            path[i] = new String(orig + "--->" + i);


        }
        for (int count = 0; count != n - 1; count++) {
            //选出一个距离初始顶点最近的为标记顶点
            int k = M;
            int min = M;
            //每次都选与原点最近的点
            for (int i = 0; i < n; i++)//遍历每一个顶点
            {
//                可达的但是都被访问过,没被访问过的，全部都不可达
                if (!visited[i] && map[orig][i] != M) //如果该顶点未被遍历过且与orig相连
                {
                    if (min == -1 || min > map[orig][i]) //找到与orig最近的点
                    {
                        min = map[orig][i];
                        k = i;
                    }
                }
            }


            //正确的图生成的矩阵不可能出现K== M的情况,但是在公路的情况下 这种情况是存在的 因为并不是连通的啊
            if (k == M) {
                System.out.println("这个图不是连通图");

                break;
            }
            shortest[k] = min;
            visited[k] = true; //其实在这里就可以 终止寻找了
            //以k为中心点，更新oirg到未访问点的距离
            for (int i = 0; i < n; i++) {
                if (!visited[i] && map[k][i] != M) {
                    int callen = min + map[k][i];
                    if (map[orig][i] == M || map[orig][i] > callen) {
                        map[orig][i] = callen;
                        path[i] = path[k] + "--->" + i;
                    }
                }
            }
        }


        String arr[] = path[end].split("--->");
        shorpath = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            shorpath[i] = Integer.parseInt(arr[i]);
        System.out.println(Arrays.toString(shorpath));
        System.out.println("=====================================");

        return shorpath;
    }
}
