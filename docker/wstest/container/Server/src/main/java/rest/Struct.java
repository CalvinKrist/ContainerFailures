package rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Struct
{
    private int varInt;

    private float varFloat;

    private String varString;

    public int getVarInt()
    {
	return varInt;
    }

    public void setVarInt(int varInt)
    {
	this.varInt = varInt;
    }

    public float getVarFloat()
    {
	return varFloat;
    }

    public void setVarFloat(float varFloat)
    {
	this.varFloat = varFloat;
    }

    public String getVarString()
    {
	return varString;
    }

    public void setVarString(String varString)
    {
	this.varString = varString;
    }

}
