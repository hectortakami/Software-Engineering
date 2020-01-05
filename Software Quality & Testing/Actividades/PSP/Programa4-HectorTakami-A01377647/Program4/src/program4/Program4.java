/******************************************************************/
/* Program Assignment:             3                                                      
/* Name:                           Hector Manuel Takami Flores                                   
/* Date:                           25/01/18                
/* Description:                    Counts the number of lines in a program      
/******************************************************************/
package program4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program4 {

/****************************************************************************************/
/* This section process all program data and read the files
/****************************************************************************************/         
	public ArrayList<String> readData(String fileName){
		File file = new File(fileName);
		ArrayList<String> calList = new ArrayList<String>();
		try {
			FileReader fReader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(fReader);
			String lineString;
			try {
				while ((lineString = bReader.readLine())!= null){
					calList.add(lineString);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		return calList;
	}
/****************************************************************************************/
/* This section do all the test according to the instruction between files
/****************************************************************************************/  
        private void calculateValues(double avg, double strDer) {
            double lnVS = avg - (2*strDer);
            double lnS = avg - (strDer);
            double lnM = avg ;
            double lnL = avg + (strDer);
            double lnVL = avg + (2*strDer);
            
            double VS = Math.exp(lnVS);
            System.out.printf("\t    VS: %.4f\n" ,VS);
            double S = Math.exp(lnS);
            System.out.printf("\t    S: %.4f\n" ,S);
            double M = Math.exp(lnM);
            System.out.printf("\t    M: %.4f\n" ,M);
            double L = Math.exp(lnL);
            System.out.printf("\t    L: %.4f\n" ,L);
            double VL = Math.exp(lnVL);
            System.out.printf("\t    VL: %.4f\n" ,VL);
            System.out.println("**********************************");
    }
        
	public void test1(){
		//Test 1
		ArrayList<String> read = new ArrayList<String>();
		read = readData("src/program4/table1.txt");
                ArrayList<Integer> classLOC = new ArrayList<Integer>();
                ArrayList<Integer> noMethods = new ArrayList<Integer>();
                for (int i = 0; i < read.size(); i++) {
                    String line = read.get(i);
                    String[] values = line.split(",");
                    classLOC.add(Integer.parseInt(values[0]));
                    noMethods.add(Integer.parseInt(values[1]));                
                }
                ArrayList<Double> LOC_method = new ArrayList<Double>();
                for (int i = 0; i < noMethods.size(); i++) {
                    double val = (double)classLOC.get(i)/(double)noMethods.get(i);
                    LOC_method.add(val);                
                }
                ArrayList<Double> lnXi = new ArrayList<Double>();
                for (int i = 0; i < LOC_method.size(); i++) {
                    lnXi.add(Math.log(LOC_method.get(i)));
                }
                double sumlnXi = 0;
                for (int i = 0; i < LOC_method.size(); i++) {
                    lnXi.add(Math.log(LOC_method.get(i)));
                    sumlnXi+=Math.log(LOC_method.get(i));
                }
                double avg = sumlnXi/noMethods.size();
                
                double varSum = 0;
                for (int i = 0; i < LOC_method.size(); i++) {
                    double lnxi = lnXi.get(i)-avg;
                    varSum += (lnxi*lnxi);
                }
                double var = varSum/13;
                double strDer = Math.sqrt(var);
                
                System.out.println("************* Test 1 *************");
                calculateValues(avg,strDer);
                
            }
        private void test2() {
        	//Test 2
		ArrayList<String> read = new ArrayList<String>();
		read = readData("src/program4/table2.txt");
		ArrayList<Double> values = new ArrayList<Double>();
                for (int i = 0; i < read.size(); i++) {
                    String line = read.get(i);
                    values.add(Double.parseDouble(line));
                }                            
                ArrayList<Double> lnXi = new ArrayList<Double>();
                for (int i = 0; i < values.size(); i++) {
                    lnXi.add(Math.log(values.get(i)));
                }
                double sumlnXi = 0;
                for (int i = 0; i < values.size(); i++) {
                    lnXi.add(Math.log(values.get(i)));
                    sumlnXi+=Math.log(values.get(i));
                }
                double avg = sumlnXi/values.size();
                
                double varSum = 0;
                for (int i = 0; i < values.size(); i++) {
                    double lnxi = lnXi.get(i)-avg;
                    varSum += (lnxi*lnxi);
                }
                double var = varSum/13;
                double strDer = Math.sqrt(var);
                
                System.out.println("************* Test 2 *************");
                calculateValues(avg,strDer);

    }
    
    public static void main(String[] args) {
        Program4 program = new Program4();
        program.test1();
        program.test2();
    }   
}
