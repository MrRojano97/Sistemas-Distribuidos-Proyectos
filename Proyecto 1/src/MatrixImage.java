import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Clase que representa la matriz creada por la imagen pgm entrante.
 */
public class MatrixImage {

    private AtomicInteger[][] matrix;
    private int rows;
    private int columns;

    /**
     * Constructor de clase, que recibe cantidad de filas y columnas
     * @param f filas
     * @param c columnas
     */
    public MatrixImage(int f, int c) {
        rows = f;
        columns = c;
        matrix = new AtomicInteger[rows][columns];
    }

    /**
     * Constructor de clase que crea una matriz de ejemplo.
     */
    public MatrixImage(){
        matrixExample();
    }

    /**
     * Rellenar la matriz, con datos de la imagen de tipo P5
     * @param dis
     */
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

    /**
     * Rellenar la matriz, con datos de la imagen tipo P2
     * @param fb
     * @throws IOException
     */
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

    /**
     * Metodo que crea una matriz de 10x10 con fines de prueba. Se rellena con datos predefinidos
     */
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

    /**
     * Metodo que imprime la matriz.
     */
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
