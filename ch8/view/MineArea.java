package ch8.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ch8.data.*;

import java.util.Stack;

public class MineArea extends JPanel implements ActionListener, MouseListener {
    JButton reStart;
    Block[][] block;// block of mine area
    BlockView[][] blockView;// view of block
    LayMines lay;// in charge of lay mine
    PeopleScoutMine peopleScoutMine;// in charge of scout mine
    int row, column, mineCount, markMount;

    ImageIcon mark;
    String grade;// the difficulty level of game
    JPanel pCenter, pNorth;
    JTextField showTime, showMarkedMineCount;

    Timer time;
    int spendTime = 0;
    Record record;
    PlayMusic playMusic,relaxMusic,tipMusic;

    public MineArea(int row, int column, int mineCount, String grade) {
        record = new Record();
        reStart = new JButton("restart");
        mark = new ImageIcon("static/img/mark.png");
        time = new Timer(1000, this);
        showTime = new JTextField(5);
        showMarkedMineCount = new JTextField(5);
        showTime.setHorizontalAlignment(JTextField.CENTER);
        showMarkedMineCount.setHorizontalAlignment(JTextField.CENTER);
        showMarkedMineCount.setFont(new Font("Arial", Font.BOLD, 16));
        showTime.setFont(new Font("Arial", Font.BOLD, 16));
        pCenter = new JPanel();
        pNorth=new JPanel();
        lay = new LayMines();
        peopleScoutMine = new PeopleScoutMine();
        initMineArea(row, column, mineCount, grade);

        reStart.addActionListener(this);
        pNorth.add(new JLabel("left mines:"));
        pNorth.add(showMarkedMineCount);
        pNorth.add(reStart);
        pNorth.add(new JLabel("used Time:"));
        pNorth.add(showTime);
        setLayout(new BorderLayout());
        add(pNorth, BorderLayout.NORTH);
        add(pCenter, BorderLayout.CENTER);
        playMusic = new PlayMusic();
        playMusic.setClipFile("static/sound/mine.wav");
        relaxMusic=new PlayMusic();
        relaxMusic.setClipFile("static/sound/mine.wav");
        tipMusic=new PlayMusic();
        tipMusic.setClipFile("static/sound/mine.wav");
    }

    public void initMineArea(int row, int column, int mineCount, String grade) {
        pCenter.removeAll();
        spendTime = 0;
        markMount = mineCount;
        this.row = row;
        this.column = column;
        this.mineCount = mineCount;
        this.grade = grade;
        block = new Block[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                block[i][j] = new Block();
            }
        }
        lay.layMinesForBlock(block, mineCount);
        peopleScoutMine.setBlock(block, mineCount);
        blockView = new BlockView[row][column];
        pCenter.setLayout(new GridLayout(row, column));
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                blockView[i][j] = new BlockView();
                block[i][j].setBlockView(blockView[i][j]);
                blockView[i][j].setDataOnView();
                pCenter.add(blockView[i][j]);
                blockView[i][j].getBlockCover().addActionListener(this);
                blockView[i][j].getBlockCover().addMouseListener(this);
                blockView[i][j].seeBlockCover();
                blockView[i][j].getBlockCover().setEnabled(true);
                blockView[i][j].getBlockCover().setIcon(null);

            }
        }
        showMarkedMineCount.setText("" + markMount);
        repaint();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getMineCount() {
        return mineCount;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != reStart && e.getSource() != time) {
            time.start();
            int m = -1, n = -1;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (e.getSource() == blockView[i][j].getBlockCover()) {
                        m = i;
                        n = j;
                        break;
                    }
                }
            }
            if (block[m][n].isMine()) {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        blockView[i][j].getBlockCover().setEnabled(false);
                        if (block[i][j].isMine()) {
                            blockView[i][j].seeBlockNameOrIcon();
                        }
                    }
                }
                time.stop();
                spendTime = 0;
                markMount = mineCount;
                playMusic.playMusic();// play Boom sound
            } else {
                Stack<Block> notMineBlocks = peopleScoutMine.getNoMineAroundBlock(block[m][n]);
                while (!notMineBlocks.empty()) {
                    Block bk = notMineBlocks.pop();
                    ViewForBlock viewForBlock = bk.getBlockView();
                    viewForBlock.seeBlockNameOrIcon();// view display the data of block
                    System.out.println("ookk");
                }
                relaxMusic.playMusic();
            }
        }
        if (e.getSource() == reStart)

        {
            initMineArea(row, column, mineCount, grade);
            repaint();
            validate();
        }
        if (e.getSource() == time) {
            spendTime++;
            showTime.setText("" + spendTime);
        }
        if (peopleScoutMine.verifyWin()) {
            time.stop();
            record.setGrade(grade);
            record.setTime(spendTime);
            record.setVisible(true);
        }

    }
    public void mousePressed(MouseEvent e){
        JButton source =(JButton)e.getSource();
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(e.getModifiers()==InputEvent.BUTTON3_MASK&&source==blockView[i][j].getBlockCover()){
                    if(block[i][j].getIsMark()){
                        source.setIcon(null);
                        block[i][j].setIsMark(false);
                        markMount=markMount+1;
                        showMarkedMineCount.setText(""+markMount);
                        tipMusic.playMusic();

                    }else{
                        source.setIcon(mark);
                        block[i][j].setIsMark(true);
                        markMount=markMount-1;
                        showMarkedMineCount.setText(""+markMount);
                        tipMusic.playMusic();
                    }
                }
            }
        }
    }
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
}