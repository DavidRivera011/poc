package com.inap.crud.api.model;

import com.inap.crud.api.config.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public List<Empleado> obtenerEmpleados() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Ejecutando consulta SQL: " + sql);

            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId_empleado(rs.getInt("id_empleado"));
                e.setNombre_empleado(rs.getString("nombre_empleado"));
                e.setEdad_empleado(rs.getInt("edad_empleado"));
                e.setTelefono_empleado(rs.getString("telefono_empleado"));
                e.setGenero_empleado(rs.getString("genero_empleado"));
                e.setFecha_nac_empleado(rs.getString("fecha_nac_empleado"));

                System.out.println("Empleado encontrado: " + e.getNombre_empleado());

                lista.add(e);
            }

            System.out.println("Total de empleados encontrados: " + lista.size());

        } catch (SQLException e) {
            System.out.println("Error al obtener empleados:");
            e.printStackTrace();
        }
        return lista;
    }



    public boolean agregarEmpleado(Empleado e) {
        String sql = "INSERT INTO empleado (nombre_empleado, edad_empleado, telefono_empleado, genero_empleado, fecha_nac_empleado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getNombre_empleado());
            stmt.setInt(2, e.getEdad_empleado());
            stmt.setString(3, e.getTelefono_empleado());
            stmt.setString(4, e.getGenero_empleado());
            stmt.setString(5, e.getFecha_nac_empleado());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean actualizarEmpleado(Empleado e) {
        String sql = "UPDATE empleado SET nombre_empleado=?, edad_empleado=?, telefono_empleado=?, genero_empleado=?, fecha_nac_empleado=? WHERE id_empleado=?";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getNombre_empleado());
            stmt.setInt(2, e.getEdad_empleado());
            stmt.setString(3, e.getTelefono_empleado());
            stmt.setString(4, e.getGenero_empleado());
            stmt.setString(5, e.getFecha_nac_empleado());
            stmt.setInt(6, e.getId_empleado());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminarEmpleado(int id) {
        String sql = "DELETE FROM empleado WHERE id_empleado=?";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
