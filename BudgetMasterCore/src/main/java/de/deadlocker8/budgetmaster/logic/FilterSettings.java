package de.deadlocker8.budgetmaster.logic;

import java.util.ArrayList;

public class FilterSettings
{
	private boolean isIncomeAllowed;
	private boolean isPaymentAllowed;
	private boolean isNoRepeatingAllowed;
	private boolean isMonthlyRepeatingAllowed;
	private boolean isRepeatingEveryXDaysAllowed;
	private ArrayList<Integer> allowedCategoryIDs;
	private ArrayList<Integer> allowedTagIDs;
	private String name;

	public FilterSettings(boolean isIncomeAllowed, boolean isPaymentAllowed, boolean isNoRepeatingAllowed, boolean isMonthlyRepeatingAllowed, boolean isRepeatingEveryXDaysAllowed, ArrayList<Integer> allowedCategoryIDs, ArrayList<Integer> allowedTagIDs, String name)
	{
		this.isIncomeAllowed = isIncomeAllowed;
		this.isPaymentAllowed = isPaymentAllowed;
		this.isNoRepeatingAllowed = isNoRepeatingAllowed;
		this.isMonthlyRepeatingAllowed = isMonthlyRepeatingAllowed;
		this.isRepeatingEveryXDaysAllowed = isRepeatingEveryXDaysAllowed;
		this.allowedCategoryIDs = allowedCategoryIDs;
		this.allowedTagIDs = allowedTagIDs;
		this.name = name;
	}

	public FilterSettings()
	{
		this.isIncomeAllowed = true;
		this.isPaymentAllowed = true;
		this.isNoRepeatingAllowed = true;
		this.isMonthlyRepeatingAllowed = true;
		this.isRepeatingEveryXDaysAllowed = true;
		this.allowedCategoryIDs = null;
		this.allowedTagIDs = null;
		this.name = null;
	}

	public boolean isIncomeAllowed()
	{
		return isIncomeAllowed;
	}

	public void setIncomeAllowed(boolean isIncomeAllowed)
	{
		this.isIncomeAllowed = isIncomeAllowed;
	}

	public boolean isPaymentAllowed()
	{
		return isPaymentAllowed;
	}

	public void setPaymentAllowed(boolean isPaymentAllowed)
	{
		this.isPaymentAllowed = isPaymentAllowed;
	}

	public boolean isNoRepeatingAllowed()
	{
		return isNoRepeatingAllowed;
	}

	public void setNoRepeatingAllowed(boolean isNoRepeatingAllowed)
	{
		this.isNoRepeatingAllowed = isNoRepeatingAllowed;
	}

	public boolean isMonthlyRepeatingAllowed()
	{
		return isMonthlyRepeatingAllowed;
	}

	public void setMonthlyRepeatingAllowed(boolean isMonthlyRepeatingAllowed)
	{
		this.isMonthlyRepeatingAllowed = isMonthlyRepeatingAllowed;
	}

	public boolean isRepeatingEveryXDaysAllowed()
	{
		return isRepeatingEveryXDaysAllowed;
	}

	public void setRepeatingEveryXDaysAllowed(boolean isRepeatingEveryXDaysAllowed)
	{
		this.isRepeatingEveryXDaysAllowed = isRepeatingEveryXDaysAllowed;
	}

	public ArrayList<Integer> getAllowedCategoryIDs()
	{
		return allowedCategoryIDs;
	}

	public void setAllowedCategoryIDs(ArrayList<Integer> allowedCategoryIDs)
	{
		this.allowedCategoryIDs = allowedCategoryIDs;
	}
	
	public ArrayList<Integer> getAllowedTagIDs()
	{
		return allowedTagIDs;
	}

	public void setAllowedTagIDs(ArrayList<Integer> allowedTagIDs)
	{
		this.allowedTagIDs = allowedTagIDs;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}	

	@Override
	public String toString()
	{
		return "FilterSettings [isIncomeAllowed=" + isIncomeAllowed + ", isPaymentAllowed=" + isPaymentAllowed + ", isNoRepeatingAllowed=" + isNoRepeatingAllowed + ", isMonthlyRepeatingAllowed=" + isMonthlyRepeatingAllowed + ", isRepeatingEveryXDaysAllowed=" + isRepeatingEveryXDaysAllowed
				+ ", allowedCategoryIDs=" + allowedCategoryIDs + ", allowedTagIDs=" + allowedTagIDs + ", name=" + name + "]";
	}

	public boolean equals(Object other)
	{
		if(other == null) return false;
		if(other == this) return true;
		if(!(other instanceof FilterSettings)) return false;
		FilterSettings otherSettings = (FilterSettings)other;
		if(isIncomeAllowed == otherSettings.isIncomeAllowed() &&
			isPaymentAllowed == otherSettings.isPaymentAllowed &&
			isNoRepeatingAllowed == otherSettings.isNoRepeatingAllowed &&
			isMonthlyRepeatingAllowed == otherSettings.isMonthlyRepeatingAllowed &&
			isRepeatingEveryXDaysAllowed == otherSettings.isRepeatingEveryXDaysAllowed)
		{			
			if(name == null)
			{
				if(otherSettings.getName() != null)
				{					
					return false;
				}
			}
			else 
			{				
				if(otherSettings.getName() == null) 
				{
					return false;
				}	
				else 
				{
					if(!name.equals(otherSettings.getName()))	return false;
				}
			}
			
			
			if(allowedCategoryIDs == null)
			{
				if(otherSettings.getAllowedCategoryIDs() != null)
				{
					return false;				
				}
				else
				{
					return isEqualTagIDs(otherSettings);
				}
			}
			else 
			{
				if(otherSettings.getAllowedCategoryIDs() == null)
				{
					return false;
				}
				else
				{					
					if(allowedCategoryIDs.equals(otherSettings.getAllowedCategoryIDs())) 
					{
						return isEqualTagIDs(otherSettings);		
					}
				}				
			}		
		}			
			
		return false;
	}
	
	private boolean isEqualTagIDs(FilterSettings otherSettings)
	{
		if(allowedTagIDs == null)	
		{
			if(otherSettings.getAllowedTagIDs() != null)
			{
				return false;				
			}
			else
			{
				return true;
			}
		}
		else 
		{
			if(otherSettings.getAllowedTagIDs() == null)
			{
				return false;
			}
			else
			{					
				if(allowedTagIDs.equals(otherSettings.getAllowedTagIDs())) 
				{
					return true;		
				}
			}
		}
		
		return false;
	}
}