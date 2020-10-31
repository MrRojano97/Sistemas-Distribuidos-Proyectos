/**
 * Clase que representa una Coordenada (i,j) con su respectivo valor.
 */

public class Coordinate {

    private int i;
    private int j;
    private int value=0;

    public Coordinate(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public String toString(){
        return "("+i+","+j+") value= "+value;
    }
}
