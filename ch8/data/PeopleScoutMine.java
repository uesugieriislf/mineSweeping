package ch8.data;
import java.util.Stack;

public class PeopleScoutMine{
    public Block [][] block;//雷区的全部方块
    Stack<Block> notMineBlock;//存放一个方块周围区域内不是雷的方块
    int m,n;//方块的索引下标
    int row,colum;//雷区的行和列
    int mineCount;//雷的数目
    // 这里容易出错，一开始报错空指针，因为数组没有分配元素；后来则是索引越界
    //这里写100是为了防止索引越界，其实不必写那么大
    boolean vis[][]=new boolean[100][100];//dfs标记已扫过得方块
    int dr[]={-1,-1,-1,0,0,1,1,1,0};//dfs方向
    int dc[]={-1,0,1,-1,1,-1,0,1,0};
    public PeopleScoutMine(){
        notMineBlock=new Stack<Block>();
    }
    public void setBlock(Block[][] block,int mineCount){
        this.block=block;
        this.mineCount=mineCount;
        row=block.length;
        colum=block[0].length;
    }
    //得到方块bk区域不是雷的方块
    public Stack<Block> getNoMineAroundBlock(Block bk){
        notMineBlock.clear();
        for(int i=0;i<row;i++){     //寻找bk在雷区block中的索引
            for(int j=0;j<colum;j++){
                if(bk==block[i][j]){
                    m=i;
                    n=j;
                    break;
                }
            }
        }
        if(!bk.isMine()){   //方块不是雷
         //   if(block[m][n].getAroundMineNumber()!=0)//这个初始的方块不是雷并且周围有雷则入栈
                notMineBlock.push(block[m][n]);
    
            block[m][n].setIsOpen(true);
            for(int i=0;i<100;i++)
            {
                for(int j=0;j<100;j++)
                {
                    vis[i][j]=false;
                }
            }
            dfs(m,n);
        }
        return notMineBlock;
    }
    //深度优先搜索
    public void dfs(int x,int y){
        this.vis[x][y]=true;
        for(int i=0;i<9;i++){
            int dx=x+dr[i];
            int dy=y+dc[i];
            if(dx>=0&&dx<row&&dy>=0&&dy<colum&&!block[dx][dy].isMine()&&!vis[dx][dy]){
                if(block[dx][dy].getAroundMineNumber()==0){
                    vis[dx][dy]=true;
                    notMineBlock.push(block[dx][dy]);
                    block[dx][dy].setIsOpen(true);
                    dfs(dx,dy);
                }
                else{
                    block[dx][dy].setIsOpen(true);
                    vis[dx][dy]=true;
                    notMineBlock.push(block[dx][dy]);//不是雷的并且周围有雷的方块入栈
                    
                }
            }
        }
        // if(block[m][n].getAroundMineNumber()>0&&block[m][n].getIsOpen()==false){
        //     block[m][n].setIsOpen(true);
        //     notMineBlock.push(block[m][n]);
        //     return;
        // }
        // else if(block[m][n].getAroundMineNumber()==0&&block[m][n].getIsOpen()==false){
        //     block[m][n].setIsOpen(true);
        //     notMineBlock.push(block[m][n]);
        //     for(int k=Math.max(m-1, 0);k<=Math.min(m+1, row-1);k++){
        //         for(int t=Math.max(n-1,0);t<=Math.min(n+1,colum-1);t++){
        //             dfs(k,t);
        //         }
        //     }
        // }
    }
    public boolean verifyWin(){
        boolean isOK=false;
        int number=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<colum;j++){
                if(block[i][j].getIsOpen()==false){
                    number++;
                }
            }
        }
        if(number==mineCount){
            isOK=true;
        }
        return isOK;
    }
    
}

