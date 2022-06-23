package com.company.dao;

import com.company.model.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOH2 implements IDao<Estudiante>{

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/db_estudiantes;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";
    private static final String SQL_SELECT = "SELECT * FROM estudiantes WHERE id_estudiante = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM estudiantes";

    private static final String SQL_INSERT = "INSERT INTO estudiantes(id_estudiante, nombre, apellido) VALUES (?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM estudiantes WHERE id_estudiante = ?";
    @Override
    public Estudiante guardar(Estudiante estudiante) {
        Connection conn = null;
        PreparedStatement stmt= null;
        //1 Levantar el driver y conectarnos
        try {
            Class.forName(DB_JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setLong(1, estudiante.getId());
            stmt.setString(2, estudiante.getNombre());
            stmt.setString(3, estudiante.getApellido());

            //3 Ejecutar la sentencia
            stmt.executeUpdate();

            //4 Cerrar la conexión
            stmt.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return estudiante;

    }

    @Override
    public void eliminar(Long id) {
        Connection conn = null;
        PreparedStatement stmt= null;
        //1 Levantar el driver y conectarnos
        try {
            Class.forName(DB_JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setLong(1, id);

            //3 Ejecutar la sentencia
            stmt.executeUpdate();

            //4 Cerrar la conexión
            stmt.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Estudiante buscar(Long id) {
        Connection conn = null;
        PreparedStatement stmt= null;
        Estudiante estudiante = null;
        //1 Levantar el driver y conectarnos
        try {
            Class.forName(DB_JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setLong(1, id);

            //3 Ejecutar la sentencia
            ResultSet rs = stmt.executeQuery();

            //4 Evaluar los resultados
            while (rs.next()){
                Long idEstudiante = rs.getLong("id_estudiante");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                estudiante = new Estudiante();
                estudiante.setId(idEstudiante);
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);

            }

            //5 Cerrar la conexión
            stmt.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return estudiante;
    }

    @Override
    public List<Estudiante> buscarTodos() {
        Connection conn = null;
        PreparedStatement stmt= null;
        List<Estudiante> estudiantes = new ArrayList<>();

        //1 Levantar el driver y conectarnos
        try {
            Class.forName(DB_JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            stmt = conn.prepareStatement(SQL_SELECT_ALL);

            //3 Ejecutar la sentencia
            ResultSet rs = stmt.executeQuery();

            //4 Evaluar los resultados
            while (rs.next()){
                Long idEstudiante = rs.getLong("id_estudiante");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                Estudiante estudiante = new Estudiante();
                estudiante.setId(idEstudiante);
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);

                estudiantes.add(estudiante);

            }

            //5 Cerrar la conexión
            stmt.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return estudiantes;
    }
}
