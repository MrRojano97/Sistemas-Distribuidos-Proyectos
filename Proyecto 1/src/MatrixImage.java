import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class MatrixImage {

    private AtomicInteger[][] matrix;
    private int rows;
    private int columns;

    public MatrixImage(int f, int c) {
        rows = f;
        columns = c;
        matrix = new AtomicInteger[rows][columns];
    }
    public MatrixImage(){
        matrixExample();
    }

    public void fillMatrixP5(DataInputStream dis){
        System.out.print("Transformando a P2 y llenando matriz...");
        for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    try {
                        matrix[i][j] = new AtomicInteger(dis.readUnsignedByte());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
        System.out.println("done!");
    }
    public void fillMatrixP2(BufferedReader fb) throws IOException {
        System.out.print("Llenando matriz P2..");
        Scanner actualLine = new Scanner(fb.readLine());
        for (int i = 0; i< rows; i++) {
            for (int j = 0; j< columns; j++) {
                if (!actualLine.hasNext()){
                    actualLine = new Scanner(fb.readLine());
                }
                matrix[i][j] = new AtomicInteger(actualLine.nextInt());
            }
        }
        System.out.println("done!");

    }
    public AtomicInteger getAtomicInt(int i, int j){
        return matrix[i][j];
    }

    public AtomicInteger[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(AtomicInteger[][] matrix) {
        this.matrix = matrix;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getValue(int i, int j){
        return getAtomicInt(i,j).get();
    }
    public void setValue(int i, int j, int v){
        AtomicInteger value = new AtomicInteger(v);
        matrix[i][j] = value;
    }
    public void matrixExample(){
        System.out.print("Creando matriz ejemplo..");
        rows = 10;
        columns= 10;
        int desfase = 10;
        matrix = new AtomicInteger[rows][columns];
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < columns; j++) {
                AtomicInteger aux = new AtomicInteger(j+desfase);
                matrix[i][j] = aux;

            }
            desfase+=2;
        }
        System.out.println("done!");

    }
    public void printMatrix(){
        for (int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                //System.out.print(image[i][j] + " ");
                System.out.print(getAtomicInt(i,j) + " ");
            }
            System.out.println();
        }
    }

}
