package enrollmentprocessor;

import com.opencsv.bean.CsvBindByName;

/**
 * Enrollment files from various benefits management and enrollment solutions 
 * (I.e. HR platforms, payroll platforms).
 * Enrollment POJOs are converted from csv records (OpenCSV 4).
 * @author Stephen Hartman
 * @version 04/07/19
 */
public class Enrollment {
	
	@CsvBindByName
    private String name;
	
	@CsvBindByName
    private String id;
	
	@CsvBindByName
    private String companyName;
	
	@CsvBindByName
    private int version;
  
    public Enrollment(String[] enrollmentData) {
        this.id = enrollmentData[0];
        this.name = enrollmentData[1];
        this.companyName = enrollmentData[2];
        this.version = Integer.parseInt(enrollmentData[3]);
    }

    public Enrollment() {
    	
    }
    
    public String getName() {
      return name;
    }
  
    public void setName(String Name) {
      this.name = Name;
    }

    public String getId() {
      return id;
    }
  
    public void setId(String id) {
      this.id = id;
    }

    public String getCompanyName() {
      return companyName;
    }
  
    public void setCompanyName(String companyName) {
      this.companyName= companyName;
    }

    public int getVersion() {
      return version;
    }
  
    public void setVersion(int version) {
      this.version = version;
    }

    /*
     * As per sort by last name then first name requirement
     */
    public String getSortName() {
    	String[] names = name.split(" ");
        return (names[1] + " " + names[0]).toUpperCase();
    }

    @Override
    public String toString() {
        return "{ id : " + id + ", name : " + name + ", version : " + version + ", companyName : " + companyName + " }";
    }
  } 