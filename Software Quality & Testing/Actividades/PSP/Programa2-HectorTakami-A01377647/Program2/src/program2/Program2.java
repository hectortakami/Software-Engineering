/******************************************************************/
/* Program Assignment:             2                                                         
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
/*    ArrayList lines = new ArrayList();
/*        try {
/*            Scanner sc = new Scanner(new FileReader(nameFile));
/*            while(sc.hasNext()){
/*                String line = sc.nextLine(); //Get the line read
/*                lines.add(line);
/*                
/*            }
/*        } catch (FileNotFoundException ex) {
/*            Logger.getLogger(Program2.class.getName()).log(Level.SEVERE, null, ex);
/*        }
/*     return lines;   
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

package program2;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program2 { 
    
/****************************************************************************************/
/* This section process all program data counting physical lines and dividing it into parts
/****************************************************************************************/  
    private static int linesInClass = 0;
    private static int methodsInClass(int index,ArrayList<String> program) {
        int numberMethods = 0;
        for (int i = index; i < program.size(); i++) {
            String line = program.get(i);
            if(line.startsWith("}")){
                break;
            }else{
                if(line.startsWith("    private static")&&line.endsWith("{")){
                    numberMethods++;     
/*                    System.out.println(line);*/
                    for (int j = index; j < program.size(); j++) {
                        String lineInMethod = program.get(j);
                        if(lineInMethod.startsWith("    }")){                            
                        }else{
                            linesInClass++;
                        }
                        
                    }
                }
            }
        }
        return numberMethods;
    }
    
    private static void processProgram(ArrayList<String> program, int programNumber) {
        
        System.out.println("***************************************************************************************");
        System.out.println("Program Number      Part Name       Number of Methods       Part Size       Total Size");
        int numClasses = 0;
        int numDeclarations = 0;
        int numDirectives = 0;
        int totalLinesInProgram = 0;
        System.out.println("      " + programNumber);
        
        for (int i = 0; i < program.size(); i++) {
            String line = program.get(i);
            if(line.startsWith("package")||line.startsWith("import")){
                numDirectives++;
                System.out.println("\t\t    C.D no." + numDirectives + "\t\t   " + '-' + "\t\t       " + 1);
                totalLinesInProgram++;
            }          
        }
        System.out.println("");
        for (int i = 0; i < program.size(); i++) {
            String line = program.get(i);
            if((line.startsWith("    private static")||line.startsWith("    public static"))&&line.endsWith(";")){
                numDeclarations++;
                System.out.println("\t\t    D no." + numDeclarations + "\t\t   " + '-' + "\t\t       " + 1);
                totalLinesInProgram++;
            }          
        }
        System.out.println("");
        for (int i = 0; i < program.size(); i++) {
            String line = program.get(i);
            if(line.startsWith("public class")){
                numClasses++;
                int numberMethods = methodsInClass(i,program);
                System.out.println("\t\t    C no." + numClasses + "\t\t   " + numberMethods + "\t\t       " + linesInClass);
                totalLinesInProgram += linesInClass;
                linesInClass = 0;
            }          
        }
        System.out.println("\t\t\t\t\t\t\t\t\t       " + totalLinesInProgram);
        System.out.println("***************************************************************************************");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("COUNTING STD: PHYSICAL      C.D = Compiler Directives, D = Declaration, C = Class");
        System.out.println("---------------------------------------------------------------------------------------\n\n");

    }
    

/****************************************************************************************/
/* This section reads all the program and filter the comments and blank spaces
/****************************************************************************************/
    
    private static ArrayList<String> readFile(String nameFile) {
        ArrayList<String> lines = new ArrayList();
        ArrayList<String> filteredLines = new ArrayList();
        try {
            Scanner sc = new Scanner(new FileReader(nameFile));
            while(sc.hasNext()){
                String line = sc.nextLine(); //Get the line read
                lines.add(line);
                
            }
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if(line.startsWith("/*")||line.isEmpty()){                    
                }else{
                    filteredLines.add(line);
                }  
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Program2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return filteredLines;    
    }  

/****************************************************************************************/
/* This section tests and proves all the classes and methods
/****************************************************************************************/
    
    public static void main(String[] args) {
        
        ArrayList<String> program1 = readFile("src/program2/program1.txt");     
        processProgram(program1,1);  
        
        ArrayList<String> program2 = readFile("src/program2/program2.txt");     
        processProgram(program2,2);
    }    
}