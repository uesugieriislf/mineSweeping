package ch8.data;
import java.util.LinkedList;
import javax.swing.ImageIcon;
public class LayMines{
    ImageIcon mineIcon;     //方块上雷的图标
    public LayMines(){
        mineIcon=new ImageIcon("static/img/mine.jpg");//设置图标的地址
    }
     //初始化雷区
     public void initBlock(Block [][] block){
         for(int i=0;i<block.length;i++){
             for(int j=0;j<block[i].length;j++){
                 block[i][j].setIsMine(false);
             }
         }
     }
     //在雷区布置mineCount个雷
     public void layMinesForBlock(Block [][] block,int mineCount){
         initBlock(block); //将方块设置成不是雷
         int row=block.length;
         int colum=block[0].length;
         LinkedList<Block> list=new LinkedList<Block>();//创建空链表

        for(int i=0;i<row;i++){
            for(int j=0;j<colum;j++){
                list.add(block[i][j]);
            }
        }
         while(mineCount>0){    //开始布雷
            int size=list.size();//list返回节点个数
            int randomIndex=(int)(Math.random()*size);
            Block b=list.get(randomIndex);
            b.setIsMine(true);
            b.setName("雷");//设置方块是雷
            b.setMineIcon(mineIcon);
            list.remove(randomIndex);//list删除索引值为randomIndex的节点
            mineCount--;
         }

         for(int i=0;i<row;i++){
             for(int j=0;j<colum;j++){
                 if(block[i][j].isMine()){
                     block[i][j].setIsOpen(false);
                     block[i][j].setIsMark(false);

                 }
                 else{
                     int mineNumber=0;//周围雷的设置
                     for(int k=Math.max(i-1,0);k<=Math.min(i+1,row-1);k++){
                         for(int t=Math.max(j-1,0);t<=Math.min(j+1,colum-1);t++){
                             if(block[k][t].isMine()){
                                 mineNumber++;
                             }
                         }
                     }
                     block[i][j].setIsOpen(false);
                     block[i][j].setIsMark(false);
                     block[i][j].setName(""+mineNumber);
                     block[i][j].setAroundMineNumber(mineNumber);
                 }
             }
         }
     }
}