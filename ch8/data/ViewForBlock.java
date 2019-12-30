package ch8.data;
public interface ViewForBlock{
    public void acceptBlock(Block block);//确定为哪个方块提供视图；
    public void setDataOnView();//设置视图上需要显示的数据；
    public void seeBlockNameOrIcon();//显示方块上的名字或图标；
    public void seeBlockCover();//显示视图上的遮挡组件；
    public Object getBlockCover();//得到视图上的遮挡组件；
}