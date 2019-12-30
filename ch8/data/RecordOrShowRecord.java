package ch8.data;

import java.sql.*;

public class RecordOrShowRecord {
    Connection con;
    String tableName;
    int heroNumber = 3;// the max displayed number of heroList

    /**
     * 构造函数，在该对象创建时便完成数据库驱动的加载
     */
    public RecordOrShowRecord() {
        try {
            // load driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (Exception e) {

        }
    }

    /**
     * 创建表格，如果表格已存在则抛出异常，停止创建表格
     * 传入的参数为表格名，实际创建的表格名要加上前缀“t_”
     * 
     * @param str
     */
    public void setTable(String str) {
        tableName = "t_" + str;
        connectDatabse();// connect to database
        try {
            Statement sta = con.createStatement();
            // use sql to create a table
            String SQL = "create table " + tableName + "(p_name varchar(50) ,p_time int)";
            sta.executeUpdate(SQL);// excute
            con.close();
        } catch (Exception e) {
           
            // if the table is exsited, it will throw exception and stop creating table
        }
    }

    /**
     * 使用预处理语句添加记录，返回值是成功与否
     * 
     * @param name
     * @param time
     * @return
     */
    public Boolean addRecord(String name, int time) {
        boolean ok = true;
        if (tableName == null) {
            ok = false;
        }
        // check if time has reached the standard(to be in the top 3)
        int amount = verifyScore(time);
        if (amount >= heroNumber) {
            ok = false;
        } else {
            connectDatabse();
            try {
                // 使用预处理语句
                String SQL = "insert into " + tableName + " values(?,?)";
                PreparedStatement sta = con.prepareStatement(SQL);
                sta.setString(1, name);
                sta.setInt(2,time);
                sta.executeUpdate();
                con.close();
                ok = true;
            } catch (Exception e) {
               
                ok = false;
            }
        }
        return ok;
    }

    /**
     * 查询表中记录
     * 
     * @return
     */
    public String[][] queryRecord() {
        if (tableName == null)
            return null;
        String[][] record = null;
        Statement sql;
        ResultSet rs;
        try {
            // 获取一个可滚动的结果集，结果集的游标可以上下移动，当数据库变化时，当前结果集不变；
            // 不能用结果集更新数据库中的表
            sql = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String str = "select * from " + tableName + " order by p_time ";
            rs = sql.executeQuery(str);
            // 将游标移到结果集的最后一行
            boolean boo = rs.last();
            if (boo == false) {
                return null;
            }
            int recordAmount = rs.getRow();
            record = new String[recordAmount][2];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                record[i][0] = rs.getString(1);
                record[i][1] = rs.getString(2);
                i++;
            }
            con.close();
        } catch (Exception e) {
           
        }
        return record;
    }
    
    /**
     * 连接数据库，如果不存在就创建
     */
    private void connectDatabse(){
        try {
            String uri = "jdbc:derby:record;create = true";
            con = DriverManager.getConnection(uri);
        } catch (Exception e) {
            
        }
    }

    private int verifyScore(int time){
        if(tableName == null){
            return Integer.MAX_VALUE;

        }
        connectDatabse();
        Statement sql;
        ResultSet rs;
        int amount = 0;
        String str ="select * from "+tableName+" where p_time < "+time;
        try {
            sql = con.createStatement();
            rs = sql.executeQuery(str);
            while(rs.next()){
                amount++;
            }
        } catch (Exception e) {
            
        }
        return amount;
    }

}