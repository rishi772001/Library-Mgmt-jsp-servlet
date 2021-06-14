package com.example.library_mgmt.api;

import com.example.library_mgmt.models.Student;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

//@Path("/students")
//public class GetStudentDetails {
//    @GET
//    @Produces("text/plain")
//    public String hello() {
//        List<Student> list = new LinkedList<>();
//        list.add(new Student(100, "rishi", "12345678"));
//        list.add(new Student(101, "swathyy", "12345678"));
//        return(new Student(101, "swathyy", "12345678"));
//        return list;
//        return "Rishi";
//    }
//}

@Path("/students")
public class GetStudentDetails {
    @GET
    @Produces("application/xml")
    public Response hello() {
        return Response.ok("<name>rishi</name>").build();
    }
}