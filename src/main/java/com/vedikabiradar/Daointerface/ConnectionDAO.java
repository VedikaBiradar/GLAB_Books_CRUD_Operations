package com.vedikabiradar.Daointerface;

import model.Books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ConnectionDAO  {
    static Connection con = null;
    protected PreparedStatement ps = null;
    protected ResultSet rs = null;
    public static Connection getConnection() throws ClassNotFoundException
    {
        final String  DBURL  = "jdbc:mysql://localhost:3306/library";
        final String DBUSERNAME = "root";
        final String  DBPASSWORD = "root";
        try {
            con = DriverManager.getConnection(DBURL, DBUSERNAME, DBPASSWORD);
            System.out.println("Connected Database Successfully");
        }
        catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return con;
    }

    public void disconnect()
    {
        try {
            if(rs != null)
            {
                rs.close();
            }
            if(ps != null)
            {
                ps.close();
            }
            if(con != null)
            {
                con.close();
            }

        }catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static interface BookDao {

        /** This is the method to be used to list down all the records from the books table.*/
        List<Books> getAllBooks() throws ClassNotFoundException, SQLException;

        /** This method to be used to create a record in the books table. */
        void saveBook(List<Books> BookList);

        /** This is the method to be used to delete a record from the Student table corresponding to a passed books id. */
        boolean deleteBook(int id);

        /** This is the method to be used to update a record into the books table. */
        boolean updateBook(Books book, int id);
    }
}
