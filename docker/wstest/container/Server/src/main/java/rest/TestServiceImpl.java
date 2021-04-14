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
    @POST
    @Path("/echoVoid")
    public void echoVoid()
    {
    }

    @POST
    @Path("/echoStruct")
    public Struct echoStruct(Struct parameters)
    {
        return parameters;
    }

    @POST
    @Path("/echoSynthetic")
    public Synthetic echoSynthetic(Synthetic s)
    {
	return s;
    }

    @POST
    @Path("/echoArray")
    public Item[] echoArray(Item[] itemArray)
    {
	return itemArray;
    }

    @POST
    @Path("/echoFloat")
    public float echoFloat(float parameters)
    {
	return parameters;
    }

    @POST
    @Path("/echoDate")
    public Date echoDate(Date parameters)
    {
	return parameters;
    }

    @POST
    @Path("/echoInteger")
    public int echoInteger(int params)
    {
	return params;
    }

    @POST
    @Path("/echoString")
    public String echoString(String parameters)
    {
	return parameters;
    }

    @GET
    @Path("/getOrder/{orderId}/{customerId}")
    public Order getOrder(@PathParam("orderId") int orderId, @PathParam("customerId") int customerId)
    {
        OrderBL bl = new OrderBL();
        return bl.GetOrder(orderId, customerId);
    }

    @POST
    @Path("/echoOrder")
    public Order echoOrder(Order order)
    {
        return order;
    }

}
