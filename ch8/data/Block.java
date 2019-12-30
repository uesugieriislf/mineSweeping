package ch8.data;
import javax.swing.ImageIcon;
public class Block{
    String name;//？
    int aroundMineNumber;//如果不是雷，此数据为周围的雷数
    ImageIcon mineIcon;//雷的图标
    public boolean isMine=false;//是否是雷
    boolean isMark=false;//是否被标记
    boolean isOpen=false;//是否被挖开
    ViewForBlock blockView;//方块的视图
    public void setName(String name){
        this.name=name;
    }

    public void setNumber(int n){
        aroundMineNumber=n;
    }

    public int getAroundMineNumber(){
        return aroundMineNumber;
    }

    public String getName(){
        return name;
    }

    public boolean isMine(){
        return isMine;
    }

    public void setIsMine(boolean b){
        isMine=b;
    }

    public ImageIcon getMineicon()
    {
        return mineIcon;
    }

    public boolean getIsOpen(){
        return isOpen;
    }

    public void setIsOpen(boolean p) {
        isOpen=p;
    }

    public boolean getIsMark(){
        return isMark;
    }

    public void setIsMark(boolean m){
        isMark=m;
    }

    public void setBlockView(ViewForBlock view){
        blockView=view;
        blockView.acceptBlock(this);
    }

    public ViewForBlock getBlockView(){
        return blockView;
    }

    public void setAroundMineNumber(int aroundMineNumber) {
        this.aroundMineNumber = aroundMineNumber;
    }

    public ImageIcon getMineIcon() {
        return mineIcon;
    }

    public void setMineIcon(ImageIcon mineIcon) {
        this.mineIcon = mineIcon;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public void setMark(boolean isMark) {
        this.isMark = isMark;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}