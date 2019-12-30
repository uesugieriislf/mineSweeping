package ch8.view;
import javax.swing.*;
import java.awt.*;
import ch8.data.*;
public class BlockView extends JPanel implements ViewForBlock{
    JLabel blockNameOrIcon;//use to display the name,number and mineIcon of Block
    JButton blockCover;//use to cover blockNameOrIcon
    CardLayout card;//card layout
    Block block;//the displayed block
    BlockView(){
        card =new CardLayout();
        setLayout(card);
        blockNameOrIcon=new JLabel("",JLabel.CENTER);
        blockNameOrIcon.setHorizontalTextPosition(AbstractButton.CENTER);
        blockNameOrIcon.setVerticalTextPosition(AbstractButton.CENTER);
        blockCover=new JButton();
        add("cover",blockCover);
        add("view",blockNameOrIcon);

    }
    public void acceptBlock(Block block){
        this.block=block;
    }
    public void setDataOnView(){
        if(block.isMine()){
            blockNameOrIcon.setText(block.getName());
            blockNameOrIcon.setIcon(block.getMineIcon());

        }else{
            int n=block.getAroundMineNumber();
            if(n>=1)
                blockNameOrIcon.setText(""+n);
            else
                blockNameOrIcon.setText(" ");

        }
    }
    public void seeBlockNameOrIcon(){
        card.show(this, "view");
        validate();
    }
    public void seeBlockCover(){
        card.show(this, "cover");
        validate();
    }
    public JButton getBlockCover(){
        return blockCover;
    }

}