/******************************************************************/
/* Program Assignment:             3                                                      
/* Name:                           Hector Manuel Takami Flores                                   
/* Date:                           25/01/18                
/* Description:                    Counts the number of lines in a program      
/******************************************************************/

/**********************************************************/
/* Listing Contents:                                                                                                   
/*    Reuse instructions                                                                                              
/*    Modification instructions                                                                                   
/*    Compilation instructions                                                                                    
/*    Packages and Imports                                                                                                               
/*    Class declarations:     
/*    Methods
/*    Main                                                                                   
/**********************************************************/

/**************************************************************************/
/*    Reuse instructions                                                                                                        */
/*    File file = new File(fileName);
/*		ArrayList<Double> calList = new ArrayList<Double>();
/*		try {
/*			FileReader fReader = new FileReader(file);
/*			BufferedReader bReader = new BufferedReader(fReader);
/*			String lineString;
/*			try {
/*				while ((lineString = bReader.readLine())!= null){
/*					calList.add(Double.parseDouble(lineString));
/*				}
/*			} catch (IOException e) {
/*				e.printStackTrace();
/*			}
/*		} catch (FileNotFoundException e) {
/*			e.printStackTrace();
/*		}		
/*		return calList; 
/***********************************************************/

/**************************************************************************/
/*    Modification instructions                                                                                                        */
/*    ArrayList lines = new ArrayList();
/*        try {
/*            Scanner sc = new Scanner(new FileReader(nameFile));
/*            while(sc.hasNext()){
/*                String line = sc.nextLine(); //Get the line read
/*                lines.add(line);
/*                
/*            }
/**************************************************************************/
/**************************************************************************/
/*            for (int i = 0; i < lines.size(); i++) { 
/*                String line = lines.get(i);
/*                if(line.startsWith("/*")||line.isEmpty()){                    
/*                }else{
/*                    filteredLines.add(line);
/*                }  
/*            }
/**************************************************************************/
/**************************************************************************/
/*        } catch (FileNotFoundException ex) {
/*            Logger.getLogger(Program2.class.getName()).log(Level.SEVERE, null, ex);
/*        }
/**************************************************************************/
/**************************************************************************/
/*     return filteredLines;   
/**************************************************************************/
/**************************************************************************/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Program3 {
    
	double beta1;
	double beta0;
	double r;
	double y;
/****************************************************************************************/
/* This section process all program data and read the files
/****************************************************************************************/         
	public ArrayList<Double> readData(String fileName){
		File file = new File(fileName);
		ArrayList<Double> calList = new ArrayList<Double>();
		try {
			FileReader fReader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(fReader);
			String lineString;
			try {
				while ((lineString = bReader.readLine())!= null){
					calList.add(Double.parseDouble(lineString));
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
	public void test1(){
		//Test 1
		ArrayList<Double> xArrayList = new ArrayList<Double>();
		xArrayList = readData("src/estimatedProxy.txt");
		ArrayList<Double> yArrayList = new ArrayList<Double>();
		yArrayList = readData("src/actualAdded.txt");	
		double xAverage = getAverage(xArrayList);
		double yAverage = getAverage(yArrayList);
		
		beta1 = calBeta1(xArrayList,yArrayList,xAverage,yAverage);
		beta0 = calBeta0(beta1,xAverage,yAverage);
		r = calR(xArrayList,yArrayList);
		
		double x = 386;
		y = beta0 + beta1 * x;
		
                System.out.println("************* Test 1 *************");
		System.out.printf("\t xAverage: %.1f\n" ,xAverage);
		System.out.printf("\t yAverage: %.1f\n\n" ,yAverage);
		System.out.printf("* beta0: %.3f\n",beta0);
		System.out.printf("* beta1: %.4f\n",beta1);
		System.out.printf("* r: %.4f\n",r);
		System.out.printf("* r^2: %.4f\n",r*r);
		System.out.printf("* y: %.4f\n",y);
                System.out.println("**********************************\n\n");
		
	}
        
        public void test2(){
                //Test 2
                ArrayList<Double> xArrayList2 = new ArrayList<Double>();
		xArrayList2 = readData("src/estimatedProxy.txt");
		ArrayList<Double> yArrayList2 = new ArrayList<Double>();
		yArrayList2 = readData("src/actualDevelopment.txt");	
		double xAverage2 = getAverage(xArrayList2);
		double yAverage2 = getAverage(yArrayList2);
		
		beta1 = calBeta1(xArrayList2,yArrayList2,xAverage2,yAverage2);
		beta0 = calBeta0(beta1,xAverage2,yAverage2);
		r = calR(xArrayList2,yArrayList2);
		
                double x = 386;
		y = beta0 + beta1 * x;
		
                System.out.println("************* Test 2 *************");
		System.out.printf("\t xAverage: %.1f\n" ,xAverage2);
		System.out.printf("\t yAverage: %.1f\n\n" ,yAverage2);
		System.out.printf("* beta0: %.3f\n",beta0);
		System.out.printf("* beta1: %.4f\n",beta1);
		System.out.printf("* r: %.4f\n",r);
		System.out.printf("* r^2: %.4f\n",r*r);
		System.out.printf("* y: %.4f\n",y);
                System.out.println("**********************************");
        }
        
        public void test3(){
                //Test 3
                ArrayList<Double> xArrayList2 = new ArrayList<Double>();
		xArrayList2 = readData("src/planAdded.txt");
		ArrayList<Double> yArrayList2 = new ArrayList<Double>();
		yArrayList2 = readData("src/actualAdded.txt");	
		double xAverage2 = getAverage(xArrayList2);
		double yAverage2 = getAverage(yArrayList2);
		
		beta1 = calBeta1(xArrayList2,yArrayList2,xAverage2,yAverage2);
		beta0 = calBeta0(beta1,xAverage2,yAverage2);
		r = calR(xArrayList2,yArrayList2);
		
                double x = 386;
		y = beta0 + beta1 * x;
		
                System.out.println("************* Test 3 *************");
		System.out.printf("\t xAverage: %.1f\n" ,xAverage2);
		System.out.printf("\t yAverage: %.1f\n\n" ,yAverage2);
		System.out.printf("* beta0: %.3f\n",beta0);
		System.out.printf("* beta1: %.4f\n",beta1);
		System.out.printf("* r: %.4f\n",r);
		System.out.printf("* r^2: %.4f\n",r*r);
		System.out.printf("* y: %.4f\n",y);
                System.out.println("**********************************");
        }
        
        public void test4(){
                //Test 4
                ArrayList<Double> xArrayList2 = new ArrayList<Double>();
		xArrayList2 = readData("src/planAdded.txt");
		ArrayList<Double> yArrayList2 = new ArrayList<Double>();
		yArrayList2 = readData("src/actualDevelopment.txt");	
		double xAverage2 = getAverage(xArrayList2);
		double yAverage2 = getAverage(yArrayList2);
		
		beta1 = calBeta1(xArrayList2,yArrayList2,xAverage2,yAverage2);
		beta0 = calBeta0(beta1,xAverage2,yAverage2);
		r = calR(xArrayList2,yArrayList2);
		
                double x = 386;
		y = beta0 + beta1 * x;
		
                System.out.println("************* Test 4 *************");
		System.out.printf("\t xAverage: %.1f\n" ,xAverage2);
		System.out.printf("\t yAverage: %.1f\n\n" ,yAverage2);
		System.out.printf("* beta0: %.3f\n",beta0);
		System.out.printf("* beta1: %.4f\n",beta1);
		System.out.printf("* r: %.4f\n",r);
		System.out.printf("* r^2: %.4f\n",r*r);
		System.out.printf("* y: %.4f\n",y);
                System.out.println("**********************************");
        }
/****************************************************************************************/
/* This section calculates average and values based on the equations
/****************************************************************************************/          
	public double getAverage(ArrayList<Double> cal){
		double sumFloat = 0;
		double averageFloat = 0;
		for (int i = 0; i < cal.size(); i++){
			sumFloat += cal.get(i);
		}
		averageFloat = sumFloat / cal.size();
		return averageFloat;
	}
	public double calBeta0(double beta1, double xAverage, double yAverage){
		double cal = yAverage - beta1*xAverage;
		return cal;
	}
	public double calBeta1(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList, double xAverage, double yAverage){
		double cal = 0;
		cal = multiple(xArrayList, yArrayList)-(xArrayList.size()*xAverage*yAverage);
		cal = cal / (multiple(xArrayList, xArrayList) - xArrayList.size()*xAverage*xAverage);
		return cal;
	}
	public double calR(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList){
		double cal = 0;
		cal = xArrayList.size()*multiple(xArrayList, yArrayList)-multiple(xArrayList)*multiple(yArrayList);
		double minus = 0;
		minus = xArrayList.size()*multiple(xArrayList, xArrayList)-multiple(xArrayList)*multiple(xArrayList);
		minus *= yArrayList.size()*multiple(yArrayList, yArrayList)-multiple(yArrayList)*multiple(yArrayList);
		cal = cal / Math.sqrt(minus);
		return cal;
	}
	public double multiple(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList){
		double cal = 0;
		for (int i = 0; i < xArrayList.size(); i++){
			cal += xArrayList.get(i)*yArrayList.get(i);
		}
		return cal;
	}
	public double multiple(ArrayList<Double> xArrayList){
		double cal = 0;
		for (int i = 0; i < xArrayList.size(); i++){
			cal += xArrayList.get(i);
		}
		return cal;
	}

/****************************************************************************************/
/* This section tests and proves all the classes and methods
/****************************************************************************************/        
    public static void main(String[] args){
		Program3 calc = new Program3();
		//calc.test1();
                //calc.test2();
                //calc.test3();
                calc.test4();
	}
}
