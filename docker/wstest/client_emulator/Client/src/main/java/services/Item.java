package services;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Item
{
    private String id;
    private String description;
    private float price;
    private int inventory;
    private Location location;
    private Date creationdate;

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

    public float getPrice()
    {
	return price;
    }

    public void setPrice(float price)
    {
	this.price = price;
    }

    public int getInventory()
    {
	return inventory;
    }

    public void setInventory(int inventory)
    {
	this.inventory = inventory;
    }

    public Location getLocation()
    {
	return location;
    }

    public void setLocation(Location location)
    {
	this.location = location;
    }

    public Date getCreationdate()
    {
        return creationdate;
    }

    public void setCreationdate(Date creationdate)
    {
        this.creationdate = creationdate;
    }



}
