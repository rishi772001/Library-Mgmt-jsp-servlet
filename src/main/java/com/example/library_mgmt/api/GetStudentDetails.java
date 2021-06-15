package com.example.library_mgmt.api;

import com.example.library_mgmt.api.resource.StudentResource;
import com.example.library_mgmt.models.Student;

import javax.ws.rs.*;
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
    public Student getStudent(@PathParam("stud_id") int id) throws SQLException {
        return new StudentResource().getStudent(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(Student student) throws SQLException {
        if(new StudentResource().addStudent(student))
            return Response.ok(student).build();
        else
            return Response.notModified().build();
    }

    @PUT
    @Path("/{stud_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("stud_id") int id, Student student) {
        if(new StudentResource().updateStudent(id, student))
            return Response.ok(student).build();
        else
            return Response.notModified().build();
    }

    @DELETE
    @Path("/{stud_id}")
    public Response update(@PathParam("stud_id") int id) {
        if(new StudentResource().deleteStudent(id))
            return Response.ok().build();
        else
            return Response.notModified().build();
    }
}