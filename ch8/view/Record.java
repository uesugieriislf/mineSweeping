package ch8.view;

import javax.swing.*;
import java.awt.event.*;
import ch8.data.RecordOrShowRecord;

public class Record extends JDialog implements ActionListener{
    int time=0;
    String grade=null;
    String key =null;
    String message=null;
    JTextField textName;
    JLabel label=null;
    JButton confirm,cancel;

    public Record(){
        setTitle("record your score:");
        this.time=time;
        this.grade=grade;
        setBounds(100,100,240,160);
        setResizable(false);
        setModal(true);
        confirm =new JButton("confirm");
        cancel=new JButton("cancel");
        textName=new JTextField(8);
        textName.setText("unknown");
        
        confirm.addActionListener(this);
        cancel.addActionListener(this);
        setLayout(new java.awt.GridLayout(2,1));
        label=new JLabel("enter your name to check if you can reach the list");
        add(label);
        JPanel p=new JPanel();
        p.add(textName);
        p.add(confirm);
        p.add(cancel);
        add(p);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    public void setGrade(String grade){
        this.grade=grade;
    }
    public void setTime(int time){
        this.time=time;
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==confirm){
            String name=textName.getText();
            writeRecord(name,time);
            setVisible(false);
        }
        if(e.getSource()==cancel){
            setVisible(false);
        }

    }
    public void writeRecord(String name,int time){
        RecordOrShowRecord rd =new RecordOrShowRecord();
        rd.setTable(grade);
        boolean boo=rd.addRecord(name, time);
        if(boo){
            JOptionPane.showMessageDialog(null,"Congratulations!","Dialog",JOptionPane.WARNING_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null,"sorry","Dialog",JOptionPane.WARNING_MESSAGE);
        }
    }
}
