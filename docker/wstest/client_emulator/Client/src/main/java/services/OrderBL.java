/*
 * Copyright (c) 2004 by Sun Microsystems, Inc. All Rights Reserved.
 * This software is the proprietary information of Sun Microsystems, Inc.
 * Use is subject to license terms.
 *
 *
 */

package services;

import java.util.*;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.DatatypeConstants;


public class OrderBL {
    
    static DatatypeFactory df;
    
    public OrderBL() {
    }
    
    public Order GetOrder(int orderId, int customerId) {
        int id = 1;
        
        Address ship = new Address();
        ship.setFirstName("Ship FirstName "+ id);
        ship.setLastName("Ship LastName " + id);
        ship.setAddress1( "Ship StreetAddres " + id);
        ship.setAddress2("Street Address Line 2 " + id);
        ship.setCity( "City " + id);
        ship.setState( "State " + id);
        ship.setZip( "95052");
        
        Address bill = new Address();
        bill.setFirstName("Bill FirstName "+ id);
        bill.setLastName("Bill LastName " + id);
        bill.setAddress1( "Bill StreetAddres " + id);
        bill.setAddress2("Street Address Line 2 " + id);
        bill.setCity( "City " + id);
        bill.setState( "State " + id);
        bill.setZip( "95052");        
        
        Customer customer = new Customer();
        customer.setCustomerId(customerId) ;
        customer.setContactFirstName("FirstName " + id);
        customer.setContactLastName( "LastName " + id);
        customer.setContactPhone(Integer.toString(id));
        
        try {
           if( df == null )
            df = DatatypeFactory.newInstance();
        }
        catch(javax.xml.datatype.DatatypeConfigurationException ex) {
        }
        
//        XMLGregorianCalendar date = df.newXMLGregorianCalendar();
//        date.setYear(2007);
//        date.setMonth(DatatypeConstants.MARCH);
//        date.setDay(1);
//        date.setTime(11,11,11);
        Date date = new Date(System.currentTimeMillis());
        customer.setLastActivityDate(date) ;
        customer.setCreditCardNumber(""+id);
        customer.setCreditCardExpirationDate( ""+id) ;
        customer.setBillingAddress(bill) ;
        customer.setShippingAddress(ship) ;       
        
        int numberLineItems = 50;
        //ArrayOfLineItem linearray = new ArrayOfLineItem();
//        List<LineItem> lineItemList = linearray.getLineItem();
        List<LineItem> lineItemList = new ArrayList<LineItem>();
        
        
        for(int i = 0; i < numberLineItems; i++) {
            LineItem lineItem = new LineItem();
            lineItem.setOrderId(orderId);
            lineItem.setItemId(i+1);
            lineItem.setProductId(i);
            lineItem.setProductDescription("Test Product " +i);
            lineItem.setOrderQuantity(1);
            lineItem.setUnitPrice((float) 1.00);
            
            lineItemList.add(lineItem);
        }
        
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderStatus( 1);
        order.setOrderDate(date);
        order.setOrderTotalAmount((float) 50);
        order.setCustomer(customer);
        order.setLineItems(lineItemList);        
        return order;
    }
}

