package com.vswamy.mt.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import javax.ws.rs.QueryParam;
import javax.ws.rs.FormParam;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;

@Path("/cache")
@Produces(MediaType.APPLICATION_JSON)
public class CacheHandler
{
    final static Logger logger = LoggerFactory
            .getLogger(CacheHandler.class);
    private final static HashMap<String, String> map = new HashMap<String, String>();

    @GET
    @Timed
    public HashMap<String, String> getAll()
    {
        return map;
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public synchronized HashMap<String, String> addObject(
            @FormParam("id") String id, @FormParam("value") String value)
    {
        map.put(id, value);
        return map;
    }

    @DELETE
    @Timed
    public synchronized HashMap<String, String> deleteObject(
            @QueryParam("id") String id)
    {
        map.remove(id);
        return map;
    }
}
