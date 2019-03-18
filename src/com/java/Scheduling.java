package com.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @program: HUA
 * @description: com.java.Scheduling
 * @author: LiuYing
 * @create: 2019-03-18 16:44
 **/

public class Scheduling {

    class Cross {
        int id = -1;
        int leftRoad = -1;
        int rightRoad = -1;
        int upRoad = -1;
        int downRoad = -1;

    }

    class Road {
        int id = -1;
        int length = -1;
        int speed = -1;
        int channel = -1;
        int from = -1;
        int to = -1;
        int isDuplex = -1;
    }

    class Car {
        int id = -1;
        int from = -1;
        int to = -1;
        int speed = -1;
        int planTime = -1;
        int shortestpath[];
    }

    Dijkstra dijkstra;

    public List<Cross> cross = new ArrayList<>(); //存放所有的cross
    public List<Road> road = new ArrayList<>(); //存放所有的road
    public List<Car> car = new ArrayList<>(); //存放所有的car
    public List<Integer> mapping = new ArrayList<>(); //存放cross的对应关系
    public int Graph[][]; //这里是图的邻接矩阵
    public List<ArrayList> Path = new ArrayList<>(); //存放所有的car

    public void BuildGraph() {
        readcross("C:\\sd\\Project\\java_pro\\HUA\\src\\data\\cross.txt");
        readRoads("C:\\sd\\Project\\java_pro\\HUA\\src\\data\\road.txt");
        Cross start = new Cross();
        Cross end = new Cross();
        Graph = new int[cross.size()][cross.size()];
//        初始化地图
        for (int i = 0; i < Graph.length; i++)
            for (int j = 0; j < Graph.length; j++) {

                if (i == j) {
                    Graph[i][j] = 0;

                } else {

                    Graph[i][j] = -1;
                }

            }


//       建立地图
        int i, j;
        for (Road r : road) {

//           start=getGross(r.from);
//           end=getGross(r.to);
            i = mapping.indexOf(r.from);
            j = mapping.indexOf(r.to);

            if (r.isDuplex == 1) {
                Graph[i][j] = r.length;
            }
            Graph[i][j] = r.length;

        }

    }

    public void Findpath() {
        int path[][];
        readCar("C:\\sd\\Project\\java_pro\\HUA\\src\\data\\car.txt");
        dijkstra = new Dijkstra(Graph);


        int ii, jj;
        for (Car c : car) {

            ii = mapping.indexOf(c.from);
            jj = mapping.indexOf(c.to);
            c.shortestpath = dijkstra.dijkstra_alg(ii,jj); //这样返回的值 会不会出问题呢？

            //做一个转换
            for (int i = 0; i < c.shortestpath.length; i++) {
                c.shortestpath[i] = mapping.indexOf(c.shortestpath[i]);
            }

        }

    }

    public void ont(String name) {

        try {
            FileWriter writer = new FileWriter(name);
            writer.write("Hello Kuka:\n");

            for (Car c : car) {
                writer.write(Arrays.toString(c.shortestpath) + "\n");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Cross getGross(int id) {
        Cross a = new Cross();
        for (int i = 0; i < cross.size(); i++) {

            if (cross.get(i).id == id) {
                return cross.get(i);
            }

        }
        return a;

    }

    public void readcross(String name) {
        ArrayList<String> arrayList = new ArrayList<>();
        int[] array;
        int length = 0;
        try {
            FileReader fr = new FileReader(name);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            int index = 0;
            // 按行读取字符串  读取所有行
            bf.readLine();//去掉第一行


            while ((str = bf.readLine()) != null) {
                str = str.replace("(", "").replace(")", "");
                String arr[] = str.split(", ");
                length = arr.length;
                array = new int[length];
                for (int i = 0; i < length; i++) {
                    String s = arr[i];
                    array[i] = Integer.parseInt(s);
                }

                Cross a = new Cross();
                a.id = array[0];
                a.leftRoad = array[1];
                a.rightRoad = array[2];
                a.upRoad = array[3];
                a.downRoad = array[4];
                cross.add(a);
                mapping.add(a.id);
            }
            bf.close();
            fr.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void readCar(String name) {
        ArrayList<String> arrayList = new ArrayList<>();
        int[] array;
        int length = 0;
        try {
            FileReader fr = new FileReader(name);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            int index = 0;
            // 按行读取字符串  读取所有行
            bf.readLine();//去掉第一行


            while ((str = bf.readLine()) != null) {
                str = str.replace("(", "").replace(")", "");
                String arr[] = str.split(", ");
                length = arr.length;
                array = new int[length];
                for (int i = 0; i < length; i++) {
                    String s = arr[i];
                    array[i] = Integer.parseInt(s);
                }

                Car a = new Car();
//      #(id,from,to,speed,planTime)
                a.id = array[0];
                a.from = array[1];
                a.to = array[2];
                a.speed = array[3];
                a.planTime = array[4];
                car.add(a);

            }
            bf.close();
            fr.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void readRoads(String name) {
        ArrayList<String> arrayList = new ArrayList<>();
        int[] array;
        int length = 0;
        try {
            FileReader fr = new FileReader(name);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            int index = 0;
            // 按行读取字符串  读取所有行
            bf.readLine();//去掉第一行


            while ((str = bf.readLine()) != null) {
                str = str.replace("(", "").replace(")", "");
                String arr[] = str.split(", ");
                length = arr.length;
                array = new int[length];
                for (int i = 0; i < length; i++) {
                    String s = arr[i];
                    array[i] = Integer.parseInt(s);
                }

                Road a = new Road();
//                id,length,speed,channel,from,to,isDuplex
                a.id = array[0];
                a.length = array[1];
                a.speed = array[2];
                a.channel = array[3];
                a.from = array[4];
                a.to = array[5];
                a.isDuplex = array[6];
                road.add(a);

            }
            bf.close();
            fr.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String args[]) {
        String Outname = "C:\\sd\\Project\\java_pro\\HUA\\src\\data\\answer.txt";
        Scheduling a = new Scheduling();
        a.BuildGraph();
        a.Findpath();
        a.ont(Outname);

    }

}
