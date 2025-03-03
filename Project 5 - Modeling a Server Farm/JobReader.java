package Lab05;

/**
* Author: Daniel Yu
* 
* To take jobs from a file and turn them into an array of Jobs
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Arrays;

public class JobReader {
    public static Queue<Job> readJobFile(String filename) {

        try {
            // assign to a variable of type FileReader a new FileReader object, passing
            // filename to the constructor
            FileReader fr = new FileReader(filename);
            // assign to a variable of type BufferedReader a new BufferedReader, passing fr
            // to the constructor
            BufferedReader br = new BufferedReader(fr);
            // assign to a variable of type Queue<Job> a new LinkedList.
            Queue<Job> jobSequence = new LinkedList<Job>();

            // assign to a variable of type String line the result of calling the readLine
            // method of the BufferedReader.
            String line = br.readLine();
            // everytime we call br.readLine(), we advance to the next line of the file we
            // are reading.
            // Since the first line of the job files are just the headers,
            // let's skip the first line by calling br.readLine again:
            // line = br.readLine();
            // start a while loop that loops while line isn't null
            while (line != null) {
                // assign to an array of type String the result of calling split on the line
                // with the argument ","
                String[] arr = line.split(",");
                // let's see what this array holds:
                // System.out.println(Arrays.toString(arr));
                // Create a new job by parsing the arrival time and processing time out of the
                // String array
                Job temp = new Job(Double.parseDouble(arr[0]), Double.parseDouble(arr[1]));
                // Offer it to jobSequence
                jobSequence.offer(temp);
                // Read the next line of the file
                line = br.readLine();
            }
            // call the close method of the BufferedReader:
            br.close();
            return jobSequence;
        } catch (FileNotFoundException ex) {
            System.out.println("JobReader.read():: unable to open file " + filename + ": file not found");
        } catch (IOException ex) {
            System.out.println("JobReader.read():: error reading file " + filename);
        }

        return null;
    }
}
