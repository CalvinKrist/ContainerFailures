package services;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Location
{
    private String id;
    private String description;
    private String Address;

    public String getId()
    {
	return id;
    }

    public void setId(String id)
    {
	this.id = id;
    }

    public String getDescription()
    {
	return description;
    }

    public void setDescription(String description)
    {
	this.description = description;
    }

    public String getAddress()
    {
	return Address;
    }

    public void setAddress(String address)
    {
	Address = address;
    }

}
