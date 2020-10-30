import java.util.concurrent.atomic.AtomicInteger;

public class Erosion extends Thread{
    private AtomicInteger[][] matrix;
    private int row_assigned;
    private int type;
    private int maxColumn;
    private int actualColumn;
    private int val1;
    private int val2;
    private int val3;
    private int val4;
    private int focusVal;
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
                val4=originalMatrix[row_assigned+1][actualColumn+1];

                minValue = Integer.min(Integer.min(val1,val2),Integer.min(val3,val4));
                minValue = Integer.min(focusVal,minValue);

                matrix[row_assigned][actualColumn].set(minValue);

            }
        }
    }
}
