package com.sherry.demorest;

import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("aliens")
public class AlienResource {

	AlienRepository repo= new AlienRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Alien> getAliens()
	{
		System.out.println("getAliens() called");
		return repo.getAliens();
	}
	
	@GET
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien getAlien(@PathParam("id") int id)
	{
		System.out.println("getAlien() called");
		
		return repo.getAlien(id);
	}
	
	@POST
	@Path("alien")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien createAlien(Alien a1)
	{
		System.out.println(a1);
		repo.create(a1);
		return a1;
	}
	
	@PUT
	@Path("alien")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien updateAlien(Alien a1)
	{
		System.out.println(a1);
		if(repo.getAlien(a1.getId()).getId()==0)  //Entry not existent. Will add new entry.
		{
			System.out.println("[PUT request] Entry not existent. Adding new entry.");
			repo.create(a1);
		}
		else  //Entry exists
		{
			repo.update(a1);
		}
		return a1;
	}
	
	@DELETE
	@Path("alien/{id}")
	public void deleteAlien(@PathParam("id") int id)
	{
		if(repo.getAlien(id).getId()==0)  //Entry not existent.
		{
			System.out.println("[DELETE request] Entry not existent.");
		}
		else  //Entry exists
		{
			repo.delete(id);
			System.out.println("Alien with id "+id+" deleted.");
		}
	}
}
