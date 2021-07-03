package com.tanmey.Duke.SolvingProblemsWithSoftware;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
import java.io.*;

public class Week4projBabyName
{
    void totalBirths(FileResource fr)
    {
		int totalBirths = 0;
		int totalGirls = 0;
		int totalBoys = 0;
		CSVParser parser = fr.getCSVParser(false);

		for(CSVRecord record : parser) {
			int numBorn = Integer.parseInt(record.get(2));
			String gender = record.get(1);
			totalBirths += numBorn;
			if(gender.equals("M")) {
				totalBoys += numBorn;
			} else {
				totalGirls += numBorn;
			}
		}

		System.out.println("Total: " + totalBirths);
		System.out.println("Boys: " + totalBoys);
		System.out.println("Girls: " + totalGirls);
	}
   
    int getRank(int year,String name,String gender)
    {
        String fileName="C:\\Users\\tanme\\git\\repository\\Misc\\courswera\\us_babynames\\us_babynames_by_year\\yob"+year+".csv";
        FileResource fr=new FileResource(fileName);
        int rank=0,flag=0;
        for(CSVRecord rec:fr.getCSVParser(false))
        {
            if(gender.equalsIgnoreCase(rec.get(1)))
                rank++;
            if((rec.get(0)).equalsIgnoreCase(name) && gender.equalsIgnoreCase(rec.get(1)))
            {
                flag=1;
                break;
            }
        }
        if(flag==0)
            return -1;
        return rank;
    }
    void testGetRank()
    {
        int rank=getRank(1960,"Emily","F");
        System.out.println("The rank of the name is "+rank);
    }
    String getName(int year,int rank,String gender)
    {
        int counter=0;
        String name="No Name";
        String fileName="C:\\\\Users\\\\tanme\\\\git\\\\repository\\\\Misc\\\\courswera\\\\us_babynames\\\\us_babynames_by_year\\\\yob"+year+".csv";
        FileResource fr=new FileResource(fileName);
        for(CSVRecord rec:fr.getCSVParser(false))
        {
            if(gender.equalsIgnoreCase(rec.get(1)))
                counter++;
            if(counter==rank)
                name=rec.get(0);
        }
        return name;
    }
    void testGetName()
    {
        String name=getName(1982,450,"M");
        System.out.println("The name at the said position is "+name);
    }
    void whatIsNameInYear(String name,int year,int newYear,String gender)
    {
        int rank=getRank(year,name,gender);
        String newName=getName(newYear,rank,gender);
        System.out.println(name+" born in "+year+" would be "+newName+" if she was born in "+newYear);
    }
    int yearOfHighestRank(String name,String gender)
    {
        int year=1880,minYear=9999999,minRank=9999999;
        while(year!=2015)
        {
            int rank=getRank(year,name,gender);
            if(rank!=-1 && rank<minRank)
                {
                    minYear=year;
                    minRank=rank;
                }
            year++;
        }
        return minYear;//return minRank;
    }
    int getTotalBirthsRankedHigher(int year,String name,String gender)
    {
        int rank=getRank(year,name,gender);
        String fileName="C:\\\\Users\\\\tanme\\\\git\\\\repository\\\\Misc\\\\courswera\\\\us_babynames\\\\us_babynames_by_year\\\\yob"+year+".csv";
        FileResource fr=new FileResource(fileName);
        int counter=1,births=0,totalBirths=0;
        for(CSVRecord rec:fr.getCSVParser(false))
        {
            if(counter<rank && gender.equalsIgnoreCase(rec.get(1)))
            {
                counter++;
                births=Integer.parseInt(rec.get(2));
                totalBirths+=births;
            }
        }
        System.out.println("Total births higher than the given name is "+totalBirths);
        return totalBirths;
    }
    void testGetTotalBirthsRankedHigher()
    {
        int totalBirths=getTotalBirthsRankedHigher(2012,"Ethan","M");
        System.out.println("Total births higher than the given name is "+totalBirths);
    }
    double getAverageRank(String name,String gender)
    {
        int year=1880,counter=0,totalRank=0,rank;
        while(year!=2015)
        {
            rank=getRank(year,name,gender);
            if(rank!=-1 )
                {
                    totalRank+=rank;
                    counter++;
                }
            year++;
        }
        if(totalRank==0)
            return -1;
        return ((double)totalRank)/counter;
    }
	public int yearOfHighestRanks(String name, String gender) {
		long highestRank = 0;
		int yearOfHighestRank = -1;
		String fileName = "";
		DirectoryResource dr = new DirectoryResource();
		
		// Iterate through all files
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser(false);
			
			// Iterate through all records in file
			for(CSVRecord record : parser) {
				String currName = record.get(0);
				String currGender = record.get(1);

				if(currName.equals(name) && currGender.equals(gender)) {
					long currRank = record.getRecordNumber();
					
					if(highestRank == 0) {
						highestRank = currRank;
						fileName = f.getName();
					} 
					else {
						if(highestRank > currRank) {
							highestRank = currRank;
							fileName = f.getName();
						}
					}
				}
			}
		}

		// Remove all non-numeric characters from the filename
		fileName = fileName.replaceAll("[^\\d]", "");
		
		// Convert String fileName to Integer
		yearOfHighestRank = Integer.parseInt(fileName);

		return yearOfHighestRank;
	}
	/*
	* This method returns the average rank of a name in multiple files
	*/
	public double getAverageRanks(String name, String gender) {
		// Initialize a DirectoryResource
		DirectoryResource dr = new DirectoryResource();
		// Define rankTotal, howMany
		double rankTotal = 0.0;
		int howMany = 0;
		// For every file the directory add name rank to agvRank
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser(false);
			for(CSVRecord record : parser) {
				String currName = record.get(0);
				String currGender = record.get(1);
				if(currName.equals(name) && currGender.equals(gender)){
					long currRank = record.getRecordNumber();
					rankTotal += (double)currRank;
					howMany += 1;
				}
			}
		}
		// Define avgRank = rankTotal / howMany
		double avgRank = rankTotal / (double)howMany;
		return avgRank;
	}
	public void newtotalBirths(FileResource fr) {
        int totalBirths = 0;
        int boyTotalBirths = 0;
        int girlTotalBirths = 0;
        int boyCount = 0;
        int girlCount = 0;
        
        for (CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            
            if (record.get(1).equals("M")) {
                boyTotalBirths += numBorn;
                boyCount++;
            } else {
                girlTotalBirths += numBorn;
                girlCount++;
            }
        }
        
        System.out.println("Total data = " + (boyCount + girlCount));
        System.out.println("Total births = " + totalBirths);
        
        System.out.println("Total girls = " + girlCount);
        System.out.println("Total girls births = " + girlTotalBirths);
        
        System.out.println("Total boys = " + boyCount);
        System.out.println("Total boys births = " + boyTotalBirths);
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        newtotalBirths(fr);
    }

    public void tester()
    {
        //FileResource fr = new FileResource();
        //CSVParser parser = fr.getCSVParser();
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // parser = fr.getCSVParser(); //... Each time you want to use the parser with another method,
        ///////////////////////////// .. you will need to reset the parser by calling fr.getCSVParser() again to get a new parser.
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	testTotalBirths();
    	//testGetName();      // testGetRank();
    	//whatIsNameInYear("Owen" ,1974,2014,"M");
    	//getTotalBirthsRankedHigher(1990,"Emily","F");
    	//System.out.println(yearOfHighestRanks("Mich", "M"));
    	//System.out.println(getAverageRanks("Robert", "M"));
   //  System.out.println(getAverageRank("Susan", "F"));//173.51111111111112
    // System.out.println(getAverageRank("Robert", "M"));//10.755555555555556

    }


    public static void main (String[] args)
    {
        Week4projBabyName CSV = new Week4projBabyName();
        CSV.tester();
    }
}