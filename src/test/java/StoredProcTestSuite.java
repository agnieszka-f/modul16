import com.kodilla.jdbc.DbManager;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StoredProcTestSuite {
    @Test
    public void testUpdateBestsellers() throws SQLException {
        //Given
        DbManager dbManager = DbManager.getInstance();
        String sqlUpdateBooks = "update books set bestseller = false";
        Statement statement = dbManager.getConnection().createStatement();
        statement.executeUpdate(sqlUpdateBooks);
        //When
        String sqlCallUpdateBestsellers = "call UpdateBestsellers()";
        statement.execute(sqlCallUpdateBestsellers);
        //Then
        String sqlCheckTable = "select * from books where bestseller!=false";
        ResultSet rs = statement.executeQuery(sqlCheckTable);
        int i=0;
        while(rs.next()){
            System.out.println("Title: " + rs.getString("title"));
            i++;
        }
        rs.close();
        statement.close();
        Assert.assertEquals(1,i);
    }
}
