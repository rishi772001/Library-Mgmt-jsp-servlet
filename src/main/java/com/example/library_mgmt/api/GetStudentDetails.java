package com.example.library_mgmt.api;

import com.example.library_mgmt.api.resource.StudentResource;
import com.example.library_mgmt.models.Student;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


@Path("/students")
public class GetStudentDetails {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> allStudents() throws SQLException {
        return new StudentResource().getAllStudents();
    }

    @GET
    @Path("/{stud_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student hello(@PathParam("stud_id") int id) throws SQLException {
        return new StudentResource().getStudent(id);
    }
}