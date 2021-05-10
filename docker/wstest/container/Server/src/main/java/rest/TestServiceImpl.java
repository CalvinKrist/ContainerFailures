/*
 * Copyright (c) 2004 by Sun Microsystems, Inc. All Rights Reserved.
 * This software is the proprietary information of Sun Microsystems, Inc.
 * Use is subject to license terms.
 *
 *
 */

package rest;

import java.util.Date;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/wstest")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestServiceImpl
{
    private static final int computeSeconds = 10;
	
    private static void doComputation() {
    	try {
			Thread.sleep(computeSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
	@POST
    @Path("/echoVoid")
    public void echoVoid()
    {
		doComputation();
    }

    @POST
    @Path("/echoStruct")
    public Struct echoStruct(Struct parameters)
    {
    	doComputation();
    	return parameters;
    }

    @POST
    @Path("/echoSynthetic")
    public Synthetic echoSynthetic(Synthetic s)
    {
    	doComputation();
    	return s;
    }

    @POST
    @Path("/echoArray")
    public Item[] echoArray(Item[] itemArray)
    {
    	doComputation();
    	return itemArray;
    }

    @POST
    @Path("/echoFloat")
    public float echoFloat(float parameters)
    {
    	doComputation();
    	return parameters;
    }

    @POST
    @Path("/echoDate")
    public Date echoDate(Date parameters)
    {
    	doComputation();
    	return parameters;
    }

    @POST
    @Path("/echoInteger")
    public int echoInteger(int params)
    {
    	doComputation();
    	return params;
    }

    @POST
    @Path("/echoString")
    public String echoString(String parameters)
    {
    	doComputation();
    	return parameters;
    }

    @GET
    @Path("/getOrder/{orderId}/{customerId}")
    public Order getOrder(@PathParam("orderId") int orderId, @PathParam("customerId") int customerId)
    {
    	doComputation();
    	OrderBL bl = new OrderBL();
        return bl.GetOrder(orderId, customerId);
    }

    @POST
    @Path("/echoOrder")
    public Order echoOrder(Order order)
    {
    	doComputation();
        return order;
    }

}
