package ch8.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch8.view.MineArea;
import ch8.view.SelfMenu;
import ch8.view.ShowRecord;

public class SelfMenu extends JDialog implements ActionListener {
    int row = 0;
    int column = 0;
    int mineCount = 0;
    String grade = null;
    JTextField rowText, columnText, mineCountText, gradeText;
    JLabel rowLabel, columnLabel, mineLabel, gradeLabel;
    JButton confirm, cancel;
    MineArea mineArea = null;

    public SelfMenu() {
        setTitle("selfDefineMode");
        this.row = row;
        this.column = column;
        this.mineCount = mineCount;
        this.grade = grade;
        setBounds(200, 200, 300, 300);
        setResizable(true);
        setModal(true);
        confirm = new JButton("confirm");
        cancel = new JButton("cancel");
        rowText = new JTextField(4);
        rowText.setText("10");
        columnText = new JTextField(4);
        columnText.setText("10");
        mineCountText = new JTextField(4);
        mineCountText.setText("10");
        confirm.addActionListener(this);
        cancel.addActionListener(this);
        setLayout(new java.awt.GridLayout(5, 1));
        rowLabel = new JLabel("row");
        columnLabel = new JLabel("column:");
        mineLabel = new JLabel("mineCount:");
        gradeLabel = new JLabel("grade:");
        JPanel row1 = new JPanel();
        row1.add(rowLabel);
        row1.add(rowText);
        JPanel row2 = new JPanel();
        row2.add(columnLabel);
        row2.add(columnText);
        JPanel row3 = new JPanel();
        row3.add(mineLabel);
        row3.add(mineCountText);
        JPanel row4 = new JPanel();
        row4.add(confirm);
        row4.add(cancel);

        add(row1);
        add(row2);
        add(row3);
        add(row4);
        // add(cancel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(confirm)) {
            int row = Integer.parseInt(rowText.getText());
            int column = Integer.parseInt(columnText.getText());
            int mineCount = Integer.parseInt(mineCountText.getText());
            setRow(row);
            setColumn(column);
            setMineCount(mineCount);
            setVisible(false);
            // int test=Integer.parseInt(row)+Integer.parseInt(column);
            // System.out.println("confirm:"+test);
        } else {
            System.out.println("cancel");
            setVisible(false);
        }
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

  


  

}
