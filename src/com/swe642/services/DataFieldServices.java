package com.swe642.services;
import com.swe642.beans.DataBean;

/**
 * DataFieldServices calculates the mean and standard deviation and also sets them in the DataBean.
 */
public class DataFieldServices 
{
	public int[] dataService(String data)
	{
		String[] processData = data.split(",");
		int[] dataArray = new int[processData.length];
		
		for(int i = 0; i < processData.length ; i++)
		{
			dataArray[i] = Integer.parseInt(processData[i]);
		}
		System.out.println("Length of Integer Array for Mean and Deviation----->"+dataArray.length);
		return dataArray;
	}
	
	public float Mean(String dataArray)
	{
		float mean = 0;
		float sum = 0;
		
		try
		{
			int[] data = dataService(dataArray);
			for(int i = 0; i < data.length; i++)
			{
				sum = sum + data[i];
			}
			mean = sum/data.length;
			System.out.println("----Sum in Mean method----"+sum+"----Mean in Mean method----"+mean);
		}
		catch(Exception e)
		{
			System.out.println("------Exception in Mean Method------");
			e.printStackTrace();
		}
		return mean;
	}
	
	public float standardDeviation(String dataArray,Float mean)
	{
		float standardDev = 0;
		float sum = 0;
		try
		{
			int[] data = dataService(dataArray);
			
			for(int i = 0; i < data.length; i++)
			{
				sum = (float) (sum + Math.pow((data[i] - mean), 2));
			}
			standardDev = (float) Math.sqrt(sum / (data.length));
			System.out.println("---Sum in Standard Deviation method---"+sum+"-----Standard Deviation---"+standardDev);
		}
		catch(Exception e)
		{
			System.out.println("------Exception in Standard Deviation Method------");
			e.printStackTrace();
		}
		return standardDev;
	}
	
	public DataBean setDataBean(String dataArray)
	{
		DataBean databean = new DataBean();
		try
		{
			float mean = Mean(dataArray);
			float standardDeviation = standardDeviation(dataArray, mean);
			databean.setMean(mean);
			databean.setStandardDeviation(standardDeviation);
		}
		catch(Exception e)
		{
			System.out.println("---Exception in setDataBean---");
			e.printStackTrace();
		}
		return databean;
	}
	
	/*public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}*/

}
