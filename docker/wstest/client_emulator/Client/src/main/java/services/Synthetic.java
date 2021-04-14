package services;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Synthetic
{
    private String str;
    private Struct s;
    private byte[] bytes;
    public String getStr()
    {
        return str;
    }
    public void setStr(String str)
    {
        this.str = str;
    }
    public Struct getS()
    {
        return s;
    }
    public void setS(Struct s)
    {
        this.s = s;
    }
    public byte[] getBytes()
    {
        return bytes;
    }
    public void setBytes(byte[] bytes)
    {
        this.bytes = bytes;
    }
}
