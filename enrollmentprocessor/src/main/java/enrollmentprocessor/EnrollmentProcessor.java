package enrollmentprocessor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import enrollmentprocessor.Enrollment;
import java.util.ArrayList;
import java.util.Arrays;

/** 
 *  Process enrollments from csv file.
 * 
 * @author Stephen Hartman
 * @version 3/9/17
 */
public class EnrollmentProcessor 
{
    private String filename;
    private List<Enrollment> enrollmentList;
    
	public EnrollmentProcessor(String filename)
	{
        this.filename = filename;
		enrollmentList = new ArrayList<Enrollment>();
	}
	
    protected void process() {
        readFile(filename);
		Collections.sort(enrollmentList,
            Comparator.comparing(Enrollment::getCompanyName)
			.thenComparing(Enrollment::getVersion).reversed()
			.thenComparing(Enrollment::getId));
		HashSet<Object> seen = new HashSet<>();
		enrollmentList.removeIf(e -> !seen.add(Arrays.asList(e.getCompanyName(), e.getId())));
        Collections.sort(enrollmentList,
            Comparator.comparing(Enrollment::getCompanyName)
            .thenComparing(Enrollment::getSortName));
        writeFiles();
    }
    
    /**
     * Read the csv, export them into Enrollment beans
     * @param fileName
     */
    private void readFile(String fileName)
    {
    	try {          
    		enrollmentList = new CsvToBeanBuilder<Enrollment>(new FileReader(filename))
    			       .withType(Enrollment.class).build().parse();
    	} catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            System.exit(1);
        } catch(Exception O_o) {
            O_o.printStackTrace();
            System.exit(1);
        }
    }
    
	private void writeFiles() {
		while (!enrollmentList.isEmpty()) {
			boolean companyFlag = true;
			String company = enrollmentList.get(0).getCompanyName();
			String temp = company;
			while(companyFlag) {
				try (Writer writer = new FileWriter("resources/" + company.replaceAll("\\s","_") + "_enrollment.csv");) {
					CSVWriter csvWriter = new CSVWriter(writer);
					Iterator<Enrollment> iterator = enrollmentList.iterator();
					while(iterator.hasNext()) {
						Enrollment tempEnroll = iterator.next();
						temp = tempEnroll.getCompanyName();
						if (!temp.equalsIgnoreCase(company)) {
							companyFlag = false;
							csvWriter.close();
							writer.close();
							break;
						}
						writeRow(csvWriter, tempEnroll);
					}
					enrollmentList.removeIf(el -> el.getCompanyName().contentEquals(company));
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} catch (Exception O_o) {
					O_o.printStackTrace();
				} 
			}
		}
    }
	
	private void writeRow(CSVWriter csvWriter, Enrollment enrollment) {
		String[] writeArray = new String[4];
		writeArray[0] = enrollment.getId();
		writeArray[1] = enrollment.getName();
		writeArray[2] = enrollment.getCompanyName();
		writeArray[3] = Integer.toString(enrollment.getVersion());
		csvWriter.writeNext(writeArray);
	}
}