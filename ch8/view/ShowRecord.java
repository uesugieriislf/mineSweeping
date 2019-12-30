package ch8.view;
import java.awt.*;
import javax.swing.*;
import ch8.data.RecordOrShowRecord;
public class ShowRecord extends JDialog{
    String [][] record;
    JTextArea showMess;
    RecordOrShowRecord rd;//query from database
    public ShowRecord(){
        rd =new RecordOrShowRecord();
        showMess=new JTextArea();
        showMess.setFont(new Font("楷体",Font.BOLD,15));
        add(new JScrollPane(showMess));
        setTitle("show heroList");
        setBounds(400,200,400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    public void setGrade(String grade){
        rd.setTable(grade);
        
    }
    public void setRecord(String [][] record){
        this.record=record;
    }
    public void ShowRecord(){
        showMess.setText(null);
        record=rd.queryRecord();
        if(record==null){
            JOptionPane.showMessageDialog(null, "nobody reach the List","Dialog",JOptionPane.WARNING_MESSAGE);

        }else{
            for(int i=0;i<record.length;i++){
                int m=i+1;
                showMess.append("\nhero"+m+":"+record[i][0]+"score:"+record[i][1]);
                showMess.append("\n---------------------");
            }
        }
        setVisible(true); 
    }

}