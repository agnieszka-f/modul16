import com.kodilla.jdbc.DbManager;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbManagerTestSuite {
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
                                + ", LASTNAME: " + resultSet.getString("LASTNAME"));
            i++;
        }
        //Then
        Assert.assertEquals(2, i);
    }

}
