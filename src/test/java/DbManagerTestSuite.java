import com.kodilla.jdbc.DbManager;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbManagerTestSuite {
    @Test
    public void testUpdateVipLevel() throws SQLException {
        //Given
        DbManager dbManager = DbManager.getInstance();
        String sqlUpdate = "update readers  set vip_level=\"Not set\"";
        Statement statement = dbManager.getConnection().createStatement();
        statement.executeUpdate(sqlUpdate);
        //When
        String sqlProcedureCall = "call UpdateVipLevels()";
        statement.execute(sqlProcedureCall);
        //Then
        String sqlCheckTable = "select count(*) as how_many from readers where vip_level = \"Not set\"";
        ResultSet rs = statement.executeQuery(sqlCheckTable);
        int howMany = -1;
        if(rs.next()){
            howMany = rs.getInt("how_many");
        }
        rs.close();
        statement.close();
        Assert.assertEquals(0,howMany);
    }
    @Test
    public void testSelectUsersAndPosts() throws SQLException {
        //Given
        DbManager dbManager = DbManager.getInstance();
        //When
        String query = "SELECT U.FIRSTNAME, U.LASTANEM FROM POSTS P\n" +
                "JOIN USERS U\n" +
                "ON U.ID = P.USER_ID\n" +
                "GROUP BY P.USER_ID\n" +
                "HAVING COUNT(*) >=2;";
        Statement statement = dbManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        //Then
        int i = 0;
        while(resultSet.next()){
            System.out.println("FIRSTNAME: " + resultSet.getString("FIRSTNAME")
                                + ", LASTNAME: " + resultSet.getString("LASTANEM"));
            i++;
        }
        //Then
        resultSet.close();
        statement.close();
        Assert.assertEquals(2, i);
    }

}
