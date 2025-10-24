package com.ucc;

import java.sql.Connection;
import java.sql.SQLException;
import com.ucc.Connection.DatabaseConnection;
import com.ucc.model.Actor;
import com.ucc.repository.ActorRepository;
import com.ucc.repository.IRepository;

public class Main {
    public static void main(String[] args) throws SQLException {

        try (Connection myConn = DatabaseConnection.getInstanceConnection()) {
            IRepository actorRepository = new ActorRepository();

            Actor actor = new Actor();
            actor.setActor_id(9999);
            actor.setFirst_name("PepitoCode");
            actor.setLast_name("pepitoCode");
            actorRepository.save(actor);

            actor.setFirst_name("PepitoNuevo");
            actor.setLast_name("CodeNuevo");
            actorRepository.update(actor);

            actorRepository.delete(9999);

            actorRepository.findAll().forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
