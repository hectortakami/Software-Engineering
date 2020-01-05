/******************************************************************/
/* Program Assignment:             5                                                 
/* Name:                           Hector Manuel Takami Flores                                   
/* Date:                           25/01/18                   
/******************************************************************/

import java.io.*;
import java.util.regex.*;

public class Program5 {
  //&i
  public static void main(String []args){

    //Se crea el objeto de la clase BufferedReader para leer el input del usuario
    try (BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in))){

    	//Se crea la variable para hacer el calculo e impresion de P con precision de E
    	CalculadorX ceCalculadorX; //&m

    	//Se crea el flotante para leer el input de X del usuario
    	double dP = 0; //&m

    	//Se crea el entero para leer los DOF del usuario
    	int iDof = 0;

    	//Se crea la variable para recibir los inputs en forma de string
    	String sCurrentLine;

        //Pido el ingreso de la P
        System.out.println("Dame la P:"); //&m
        //Recibe la X de entrada
        sCurrentLine = brInput.readLine();
        //Revisa que lo que haya recibido cumpla con el formato de un flotante
        if(Pattern.matches("\\d+(\\.\\d+)?", sCurrentLine)){
        	//Si estos caracteres representan un flotante mayor o igual a 0
        	if(Double.parseDouble(sCurrentLine) >= 0 && Double.parseDouble(sCurrentLine) <=  0.5){ //&m
        		//Asignalo a la variable que maneja la entrada de X
        		dP = Double.parseDouble(sCurrentLine);//&m
        	}
        	//Si no
        	else{
        		//Error en la entrada, salgamos
        		System.out.println("P incorrecta");//&m
        		System.exit(0);
        	}
        }
        //Si no
        else{
        	//Error en la entrada, salgamos
        	System.out.println("P incorrecta");//&m
    		System.exit(0);
        }

      //Pido el ingreso de los Dof
        System.out.println("Dame los DOF:");
        //Recibe los Dof de entrada
        sCurrentLine = brInput.readLine();
        //Revisa que lo que haya recibido cumpla con el formato de un entero
        if(Pattern.matches("\\d+", sCurrentLine)){
        	//Si estos caracteres representan un entero mayor a 0
        	if(Integer.parseInt(sCurrentLine)>0){
        		//Asignalo a la variable que maneja los dof
        		iDof = Integer.parseInt(sCurrentLine);
        	}
        	//Si no
        	else{
        		//Error en la entrada, salgamos
        		System.out.println("DOF incorrectos");
        		System.exit(0);
        	}
        }
        //Si no
        else{
        	//Error en la entrada, salgamos
        	System.out.println("DOF incorrectos");
    		System.exit(0);
        }

        //Creamos el objeto de CalculadoraE
        ceCalculadorX = new CalculadorX(dP,iDof);//&m

        //Mandamos a imprimir
        ceCalculadorX.print();//&m

    }catch(IOException e){
      System.out.println("Couldn't open BufferReader"+e);
    }
  }
}
