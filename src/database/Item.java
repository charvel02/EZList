package database;

public class Item
{
	private long itemId;
	private String itemName;
	/**
	 * @return the id
	 */
	public long getId()
	{
		return itemId;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id)
	{
		this.itemId = id;
	}
	/**
	 * @return the name
	 */
	public String getName()
	{
		return itemName;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.itemName = name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString()
    {
	    return itemName;
    }
	
	public void saveItem(){
		
	}
	
}
