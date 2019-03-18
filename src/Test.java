/**
 * @program: HUA
 * @description:
 * @author: LiuYing
 * @create: 2019-03-18 16:18
 **/
public class Test {

    public static void main(String[] args){
        int[][] map=new int[][]{// 地图数组
                {1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,0,1,1,1,1,1}
        };
        Astar aStar=new Astar(map, 6, 10);
        int flag=aStar.search(4, 0, 3, 8);
        if(flag==-1){
            System.out.println("传输数据有误！");
        }else if(flag==0){
            System.out.println("没找到！");
        }else{
            for(int x=0;x<6;x++){
                for(int y=0;y<10;y++){
                    if(map[x][y]==1){
                        System.out.print("　");
                    }else if(map[x][y]==0){
                        System.out.print("〓");
                    }else if(map[x][y]==-1){
                        System.out.print("※");
                    }
                }
                System.out.println();
            }
        }
    }
}