package services;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Customer
{

    private int customerId;
    private String contactFirstName;
    private String contactLastName;
    private String contactPhone;
    private Date lastActivityDate;
    private String creditCardNumber;
    private String creditCardExpirationDate;
    private Address billingAddress;
    private Address shippingAddress;

    public int getCustomerId()
    {
	return customerId;
    }

    public void setCustomerId(int customerId)
    {
	this.customerId = customerId;
    }

    public String getContactFirstName()
    {
	return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName)
    {
	this.contactFirstName = contactFirstName;
    }

    public String getContactLastName()
    {
	return contactLastName;
    }

    public void setContactLastName(String contactLastName)
    {
	this.contactLastName = contactLastName;
    }

    public String getContactPhone()
    {
	return contactPhone;
    }

    public void setContactPhone(String contactPhone)
    {
	this.contactPhone = contactPhone;
    }

    public String getCreditCardNumber()
    {
	return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber)
    {
	this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardExpirationDate()
    {
	return creditCardExpirationDate;
    }

    public void setCreditCardExpirationDate(String creditCardExpirationDate)
    {
	this.creditCardExpirationDate = creditCardExpirationDate;
    }

    public Address getBillingAddress()
    {
	return billingAddress;
    }

    public void setBillingAddress(Address billingAddress)
    {
	this.billingAddress = billingAddress;
    }

    public Address getShippingAddress()
    {
	return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress)
    {
	this.shippingAddress = shippingAddress;
    }

    public Date getLastActivityDate()
    {
	return lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate)
    {
	this.lastActivityDate = lastActivityDate;
    }

}
