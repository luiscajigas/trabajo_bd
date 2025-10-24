package com.ucc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ucc.Connection.DatabaseConnection;
import com.ucc.model.Actor;

public class ActorRepository implements IRepository{

    private Connection getConnection() throws SQLException{
        return DatabaseConnection.getInstanceConnection();
    }


    @Override
    public List<Actor> findAll() throws SQLException{
        List<Actor> actors = new ArrayList<>();
        try (Statement myStat = getConnection().createStatement();
            ResultSet myRes= myStat.executeQuery("SELECT * FROM actor");) {
            while (myRes.next()) {
                Actor newActor = new Actor();
                newActor.setActor_id(myRes.getInt("actor_id"));
                newActor.setFirst_name(myRes.getString("first_name"));
                newActor.setLast_name(myRes.getString("last_name"));
                actors.add(newActor);
            }
        } 
        return actors;
    }

    @Override
    public Actor save(Actor actor) throws SQLException {
        String sql = "INSERT INTO actor(actor_id, first_name, last_name) VALUES (?,?,?)";
        try (PreparedStatement myPrepare = getConnection().prepareStatement(sql);) {
            myPrepare.setInt(1, actor.getActor_id());
            myPrepare.setString(2, actor.getFirst_name());
            myPrepare.setString(3, actor.getLast_name());
            myPrepare.executeUpdate();
        }
        return actor;
    }
    
        @Override
    public Actor update(Actor actor) throws SQLException {
        String sql = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
        try (PreparedStatement myPrepare = getConnection().prepareStatement(sql)) {
            myPrepare.setString(1, actor.getFirst_name());
            myPrepare.setString(2, actor.getLast_name());
            myPrepare.setInt(3, actor.getActor_id());

            int rowsAffected = myPrepare.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No se encontró el actor con el ID: " + actor.getActor_id());
            } else {
                System.out.println("el actor se actualizo correctamente: " + actor);
            }
        }
        return actor;
    }

        @Override
        public boolean delete(int actorId) throws SQLException {
            String sql = "DELETE FROM actor WHERE actor_id = ?";
            try (PreparedStatement myPrepare = getConnection().prepareStatement(sql)) {
                myPrepare.setInt(1, actorId);
                int rowsAffected = myPrepare.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("No se encontró el actor con el ID: " + actorId);
                    return false;
                } else {
                    System.out.println("el actor se elimino correctamente (ID " + actorId + ")");
                    return true;
                }
            }
        }
}
