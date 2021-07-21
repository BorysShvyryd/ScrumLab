package pl.coderslab.dao;

public class AdminDao {

    private static final String CREAT_ADMIN_QUERY = "INSERT INTO admins(id, first_name, last_name, email, password, superadmin, enable) VALUES (0, ?, ?, ?, ?, 0, 0);";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins WHERE id = ?;";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins;";
    private static final String READ_ADMIN_QUERY = "SELECT * FROM admins WHERE id = ?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name = ?, last_name = ?, email = ?, password = ?, superadmin = ?, enable = ? WHERE id = ?;";



}
