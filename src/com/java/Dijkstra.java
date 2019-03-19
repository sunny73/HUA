package com.java;

/*
 *Dijkstra�����·���㷨
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
        n = map.length;       //����ĸ���
        shortest = new int[n];  //��Ŵ�start�������ڵ�����·��
        //��Ŵ�start�������ڵ�����·��
        visited = new boolean[n]; //��ǵ�ǰ�ö�������·���Ƿ��Ѿ������true��ʾ�Ѿ����

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

        int orig = 0; //��ʼ��
        //Ѱ�����·��
        int[] shortPath = a.dijkstra_alg(orig,2);

        if (shortPath == null) {
            return;
        }


            System.out.println(Arrays.toString(shortPath));

    }*/

    int[] dijkstra_alg(int orig, int end) {
        int[] shorpath;
        // TODO Auto-generated method stub
        // ��ʼ������һ���������
        shortest[orig] = 0;
        visited[orig] = true;


        //��Ŵ�start���������ڵ�����·��
        String[] path = new String[n];
        for (int i = 0; i < n; i++) {
            path[i] = new String(orig + "--->" + i);


        }
        for (int count = 0; count != n - 1; count++) {
            //ѡ��һ�������ʼ���������Ϊ��Ƕ���
            int k = M;
            int min = M;
            //ÿ�ζ�ѡ��ԭ������ĵ�
            for (int i = 0; i < n; i++)//����ÿһ������
            {
//                �ɴ�ĵ��Ƕ������ʹ�,û�����ʹ��ģ�ȫ�������ɴ�
                if (!visited[i] && map[orig][i] != M) //����ö���δ������������orig����
                {
                    if (min == -1 || min > map[orig][i]) //�ҵ���orig����ĵ�
                    {
                        min = map[orig][i];
                        k = i;
                    }
                }
            }


            //��ȷ��ͼ���ɵľ��󲻿��ܳ���K== M�����,�����ڹ�·������� ��������Ǵ��ڵ� ��Ϊ��������ͨ�İ�
            if (k == M) {
                System.out.println("���ͼ������ͨͼ");

                break;
            }
            shortest[k] = min;
            visited[k] = true; //��ʵ������Ϳ��� ��ֹѰ����
            //��kΪ���ĵ㣬����oirg��δ���ʵ�ľ���
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
