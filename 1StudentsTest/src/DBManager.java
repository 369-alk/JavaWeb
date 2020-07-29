import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBManager {
	
	//���ݿ��ַ
    private static final String url = "jdbc:mysql://localhost:3306/students";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "ROOT";
    
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    
    private DBManager(String sql){
        try{
            Class.forName(name);
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement(sql);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void close(){
        try{
            this.connection.close();
            this.preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		String sql = "SELECT * FROM students";
        DBManager dbManager = new DBManager(sql);  //ʵ����

        String id, name;

        try{
            ResultSet result = dbManager.preparedStatement.executeQuery();
            while(result.next()){                  //�������ݣ������
                id = result.getString(1);
                name = result.getString(2);
                //��ʾ��ÿһ������
                System.out.println(id + "  " + name);
            }
            result.close();
            dbManager.close();
            
        }catch (Exception e){
            e.printStackTrace();
        }

	}

}
