import java.util.concurrent.atomic.AtomicInteger;

//Esto representa un único hilo
// NO SE USA EN LA VERSION FINAL, ESTO SE USO EN LAS PRUEBAS ALPHA
// NO SE USA EN LA VERSION FINAL, ESTO SE USO EN LAS PRUEBAS ALPHA
// NO SE USA EN LA VERSION FINAL, ESTO SE USO EN LAS PRUEBAS ALPHA
// NO SE USA EN LA VERSION FINAL, ESTO SE USO EN LAS PRUEBAS ALPHA
// NO SE USA EN LA VERSION FINAL, ESTO SE USO EN LAS PRUEBAS ALPHA
// NO SE USA EN LA VERSION FINAL, ESTO SE USO EN LAS PRUEBAS ALPHA
// NO SE USA EN LA VERSION FINAL, ESTO SE USO EN LAS PRUEBAS ALPHA
// NO SE USA EN LA VERSION FINAL, ESTO SE USO EN LAS PRUEBAS ALPHA

public class Erosion extends Thread{
    private AtomicInteger[][] matrix;
    private int row_assigned; //Este hilo sólo trabajará una fila
    private int type; //Tipo se refiere a la figura o estructura a utilizar
    private int maxColumn; //Hasta dónde tiene que reocrrer
    private int actualColumn; //Donde está recorriendo actualmente

    //Valores de la estructura
    private int val1;
    private int val2;
    private int val3;
    private int val4;
    //Valor a modificar en la estructura
    private int focusVal;

    //Se utiliza para almacenar el valor menor
    private int minValue;

    private Integer originalMatrix[][];


    public Erosion(Integer[][] imageTemp, AtomicInteger[][] matrix, int actualRow, int maxColumn, int type) {
        this.type=type;
        this.row_assigned=actualRow;
        this.matrix=matrix;
        this.maxColumn=maxColumn;
        this.originalMatrix=imageTemp;
    }

    public void run(){
        if (type==0){ //Forma predeterminada (ejemplo superior)
            for (actualColumn=1;actualColumn<maxColumn-1; actualColumn++){
                focusVal=originalMatrix[row_assigned][actualColumn];
                val1=originalMatrix[row_assigned-1][actualColumn];
                val2=originalMatrix[row_assigned][actualColumn-1];
                val3=originalMatrix[row_assigned+1][actualColumn];
                val4=originalMatrix[row_assigned][actualColumn+1];

                //Se realiza el cálculo del valor menor utilizando Integer, cabe destacar
                //que estos cálculos se hacen con la matriz de Integer y no con la de AtomicInteger
                //pues esta segunda se irá modificando, con esto se evita que los valores se expandan erroneamente
                minValue = Integer.min(Integer.min(val1,val2),Integer.min(val3,val4));
                minValue = Integer.min(focusVal,minValue);

                //Una vez que se sabe cuál es el valor menor de los valores aledaños de la estructura
                //este se modifica en la Matriz de AtomicInteger
                matrix[row_assigned][actualColumn].set(minValue);

            }
        }
    }
}
