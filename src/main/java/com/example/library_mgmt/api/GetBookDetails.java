package com.example.library_mgmt.api;

import com.example.library_mgmt.api.resource.BookResource;
import com.example.library_mgmt.models.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/books")
public class GetBookDetails {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() throws SQLException {
        return new BookResource().getAllBooks();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getAllBooks(@PathParam("id") int id) throws SQLException {
        return new BookResource().getBook(id);
    }
}
