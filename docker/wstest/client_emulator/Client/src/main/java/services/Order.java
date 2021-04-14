package services;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class Order
{

    private int orderId;
    private int orderStatus;
    private Date orderDate;
    private float orderTotalAmount;
    private Customer customer;
    private List<LineItem> lineItems;

    public int getOrderId()
    {
	return orderId;
    }

    public void setOrderId(int orderId)
    {
	this.orderId = orderId;
    }

    public int getOrderStatus()
    {
	return orderStatus;
    }

    public void setOrderStatus(int orderStatus)
    {
	this.orderStatus = orderStatus;
    }

    public Date getOrderDate()
    {
	return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
	this.orderDate = orderDate;
    }

    public float getOrderTotalAmount()
    {
	return orderTotalAmount;
    }

    public void setOrderTotalAmount(float orderTotalAmount)
    {
	this.orderTotalAmount = orderTotalAmount;
    }

    public Customer getCustomer()
    {
	return customer;
    }

    public void setCustomer(Customer customer)
    {
	this.customer = customer;
    }

    public List<LineItem> getLineItems()
    {
	return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems)
    {
	this.lineItems = lineItems;
    }

}
