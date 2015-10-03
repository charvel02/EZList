package com.example.EZList;
class Item
{
	private String itemId;
	private String itemName = "";
	private String listId;
	private String checked = "0";

	/**
	 * @param listId - list Id the item belongs to
	 * @param itemId - the item id
	 * @param itemName - the item name
	 */
	public Item(String listId, String itemId, String itemName, String checked)
	{
		this.listId = listId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.checked = checked;
	}

	/**
	 * @return the checked
	 */
	public String getChecked()
	{
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(String checked)
	{
		this.checked = checked;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId()
	{
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName()
	{
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	/**
	 * @return the listId
	 */
	public String getListId()
	{
		return listId;
	}


	/**
	 * @param listId the listId to set
	 */
	public void setListId(String listId)
	{
		this.listId = listId;
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
}