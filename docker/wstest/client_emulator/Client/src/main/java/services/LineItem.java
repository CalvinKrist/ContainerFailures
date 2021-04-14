package services;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LineItem
{

    private int orderId;
    private int itemId;
    private int productId;
    private String productDescription;
    private int orderQuantity;
    private float unitPrice;

    public int getOrderId()
    {
	return orderId;
    }

    public void setOrderId(int orderId)
    {
	this.orderId = orderId;
    }

    public int getItemId()
    {
	return itemId;
    }

    public void setItemId(int itemId)
    {
	this.itemId = itemId;
    }

    public int getProductId()
    {
	return productId;
    }

    public void setProductId(int productId)
    {
	this.productId = productId;
    }

    public String getProductDescription()
    {
	return productDescription;
    }

    public void setProductDescription(String string)
    {
	this.productDescription = string;
    }

    public int getOrderQuantity()
    {
	return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity)
    {
	this.orderQuantity = orderQuantity;
    }

    public float getUnitPrice()
    {
	return unitPrice;
    }

    public void setUnitPrice(float f)
    {
	this.unitPrice = f;
    }

}
