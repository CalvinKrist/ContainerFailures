package services;

import java.io.*;

public class CSV {
    private FileWriter fileWriter;
    private String fileName;

    public CSV(String fileName){
        this.fileName = fileName;
        try {
            fileWriter = new FileWriter (new File(fileName),true);
            String columnNamesList = "TestCase,ServerFail,Request Time,Response Time,Time Taken, First Response Time Error\n";
            fileWriter.write(columnNamesList);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String testCase, boolean d, long startRequest, long endRequest, long firstResponseError){
        String delimiter = ",";
        try {
            fileWriter = new FileWriter(fileName,true);
            String builder = testCase +
                    delimiter +
                    d +
                    delimiter +
                    startRequest +
                    delimiter +
                    endRequest +
                    delimiter +
                    (endRequest - startRequest) +
                    delimiter +
                    firstResponseError +
                    '\n';
            fileWriter.write(builder);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeResult(long nRequests, long startRequests, long endRequests, double throughput){
        try {
            fileWriter = new FileWriter (new File("Results_" + fileName),true);
            String testResultCol = "Number of requests,Start Time,End Time,Throughput\n";
            fileWriter.write(testResultCol);
            String testResult = nRequests + "," + startRequests + "," + endRequests + "," + throughput;
            fileWriter.write(testResult);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
