package ch8.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

import ch8.view.MineArea;
import ch8.view.SelfMenu;
import ch8.view.ShowRecord;

public class AppWindow extends JFrame implements MenuListener,ActionListener{
    JMenuBar bar;
    JMenu fileMenu,selfMenu;
    JMenu gradeOne,gradeTwo,gradeThree;
    JMenuItem gradeOneList,gradeTwoList,gradeThreeList;
    MineArea mineArea = null;
    ShowRecord showHeroRecord=null;
    SelfMenu self=null;
    public AppWindow(){

        bar=new JMenuBar();
        fileMenu =new JMenu("MineBlock");
        selfMenu=new JMenu("selfDefine");
        gradeOne =new JMenu("begginer");
        gradeTwo=new JMenu("middle");
        gradeThree=new JMenu("advanced");
        
        gradeOneList=new JMenuItem("begginer HeroList");
        gradeTwoList=new JMenuItem("middle HeroList");
        gradeThreeList=new JMenuItem("advanced HeroList");
        gradeOne.add(gradeOneList);
        gradeTwo.add(gradeTwoList);
        gradeThree.add(gradeThreeList);
        fileMenu.add(gradeOne);
        fileMenu.add(gradeTwo);
        fileMenu.add(gradeThree);
        
        bar.add(fileMenu);
        bar.add(selfMenu);
        setJMenuBar(bar);
        gradeOne.addMenuListener(this);
        gradeTwo.addMenuListener(this);
        gradeThree.addMenuListener(this);

        gradeOneList.addActionListener(this);
        gradeTwoList.addActionListener(this);
        gradeThreeList.addActionListener(this);
        mineArea=new MineArea(9, 9, 10,gradeOne.getText());
        
        add(mineArea,BorderLayout.CENTER);
        setBounds(500, 30,400,400);
        showHeroRecord=new ShowRecord();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    public void menuSelected(MenuEvent e){
        if(e.getSource()==gradeOne){
            mineArea.initMineArea(9, 9,10,gradeOne.getText());
            setBounds(500, 30,400,400);
            validate();
        }
        else if(e.getSource()==gradeTwo){
            mineArea.initMineArea(16, 16,40,gradeTwo.getText());
            setBounds(500, 30, 600, 750);
            validate();
        }
        else if(e.getSource()==gradeThree){
            mineArea.initMineArea(22, 30,99,gradeThree.getText());
            setBounds(500, 30, 800, 800);
            validate();
        }
        else if(e.getSource()==selfMenu){
            
            
        }
    }
    public void menuCanceled(MenuEvent e){}
    public void menuDeselected(MenuEvent e){}
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==gradeOneList){
            showHeroRecord.setGrade(gradeOne.getText());
            showHeroRecord.ShowRecord();
        }
        else if(e.getSource()==gradeTwoList){
            showHeroRecord.setGrade(gradeTwo.getText());
            showHeroRecord.ShowRecord();
        }
        else if(e.getSource()==gradeThreeList){
            showHeroRecord.setGrade(gradeThree.getText());
            showHeroRecord.ShowRecord();
        }
        
    }
    public static void main(String[] args) {
        new AppWindow();
    }
}
