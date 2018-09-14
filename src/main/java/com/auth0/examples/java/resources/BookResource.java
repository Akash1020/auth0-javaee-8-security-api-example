package com.auth0.examples.java.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.auth0.examples.java.entities.Book;

@Stateless
@Path("/")
public class BookResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks() {
    	Book firstBook = new Book();
    	firstBook.setAuthor("William Smile");
    	firstBook.setTitle("Securing your API with Auth0");
    	firstBook.setDescription("Learn how to secure your APIs with Auth0.");
    	firstBook.setDate("01/03/2017");
    	
    	Book secondBook = new Book();
    	secondBook.setAuthor("George Keen");
    	secondBook.setTitle("Understanding JSON Web Tokens");
    	secondBook.setDescription("Secure is everywhere and JWT plays a big game in API security.");
    	secondBook.setDate("08/09/2018");
    	
    	List<Book> books = new ArrayList<Book>();
    	books.add(firstBook);
    	books.add(secondBook);
    	
        return books;
    }
}
