package ch8.test;

import ch8.data.*;
import java.util.Stack;

public class AppTest {
    public static void main(String[] args) {
        // 创建雷区
        Block block[][] = new Block[10][10];
        // 初始化雷区
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[i].length; j++) {
                block[i][j] = new Block();
            }
        }
        // 布雷者
        LayMines layMines = new LayMines();
        // 扫雷者
        PeopleScoutMine peopleScoutMine = new PeopleScoutMine();
        // 在雷区布雷，布雷10个
        layMines.layMinesForBlock(block, 10);
        System.out.println("雷区情况：");
        // self define method，显示布雷结果
        inputShow(block);
        // prepare to scout mine
        peopleScoutMine.setBlock(block, 10);
        // 泛型,创建堆栈对象
        Stack<Block>stack = peopleScoutMine.getNoMineAroundBlock(block[0][0]);
        // 模拟扫雷，点击区域[0][0]
        if(block[0][0].isMine()){
            System.out.println("Oh my God! You Boomed!");
            return;
        }
        System.out.println("扫雷情况：");
        inputProcess(block,stack);
        System.out.println("成功了吗："+peopleScoutMine.verifyWin());
// 模拟扫雷，点击区域[3][3]
        if(block[3][3].isMine()){
            System.out.println("Oh my God! You Boomed!");
            return;
        }
        stack = peopleScoutMine.getNoMineAroundBlock(block[3][3]);
        System.out.println("扫雷情况：");
        inputProcess(block,stack);
        System.out.println("成功了吗："+peopleScoutMine.verifyWin());
    }
    static void inputProcess(Block [][] block,Stack<Block> stack){
        // 这个k是干嘛的？
        // int k = 0;
        for(int i=0;i<block.length;i++){
            for(int j=0;j<block[i].length;j++){
                if(!stack.contains(block[i][j])&&block[i][j].getIsOpen()==false){
                    System.out.printf("%2s","■ ");
                }else{
                    // 显示周围雷的数量
                    int m= block[i][j].getAroundMineNumber();
                    System.out.printf("%2s","□ "+m);
                }
            }
            System.out.println();
        }
    }

    static void inputShow(Block [][] block){
        // 这个k是干嘛的？
        // int k = 0;
        for(int i=0;i<block.length;i++){
            for(int j=0;j<block[i].length;j++){
                if(block[i][j].isMine()){
                    System.out.printf("%2s","#");
                }else{
                    // 显示周围雷的数量
                    int m= block[i][j].getAroundMineNumber();
                    System.out.printf("%2s",m);
                }
            }
            System.out.println();
        }
    }

}