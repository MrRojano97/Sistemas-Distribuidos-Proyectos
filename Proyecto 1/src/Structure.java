import java.util.ArrayList;


/**
 * Clase que representa una estructura disponible para recorrer una fila de la matriz, obteniendo los valores en
 * cada posicion del pixel desde la matriz.
 */
public class Structure {
    //Arreglo de coordenadas tipo (i,j), cada una representa un pixel o casillero.
    private ArrayList<Coordinate> structure;
    //id de la matriz para identificarla.
    private int id;

    /**
     * Constructur de la clase
     * @param listacoordenadas lista de coordenadas de una estructura para transformarla a Coordinates
     * @param id id de la estructura
     */
    public Structure(Integer[] listacoordenadas,int id ) {
        //System.out.print("Creando estructura id "+id+"..");
        this.id = id;
        structure = new ArrayList<>();
        for (int i = 0; i < listacoordenadas.length; i++) {
            if(i+1<listacoordenadas.length){
                Coordinate cord = new Coordinate(listacoordenadas[i],listacoordenadas[i+1] );
                structure.add(cord);
                i++;
            }
        }
        //System.out.println("done!");
    }

    /**
     * Imprime por consola informacion general de la esctuctura, como su id, su tamaño, su pixel de estudio, y sus
     * demas pixeles con sus respectivos valores.
     */
    public void printInfoStruct(){
        System.out.println("Info Estructura "+id+" size: "+ structure.size());
        System.out.println("Pixel Estudio: "+getCoord(0).toString());
        for (int i = 1; i < structure.size() ; i++) {
            System.out.println("Pixel "+i+": "+getCoord(i).toString());
        }

    }

    /**
     * Metodo que mueve la estructura y le suma valores a la coordenada J para emular movimiento horizontal
     * @param increase_column factor de incremento horizontal
     * @param limit limite maximo la coordenada J en la matriz
     */
    public void moveStruct(int increase_column, int limit){
        int j;
        for (Coordinate coord: getStruct()) {
            j = coord.getJ()+ increase_column;
            if(j<limit){
                coord.setJ(j);
            }

        }
    }

    /**
     * Metodo que se analiza si es posible realizar un movimiento horizontal y retorna si es o no posible.
     * @param increase_column factor de incremento horizontal.
     * @param limit limite maximo de la coordenada J en la matriz.
     * @return true o false, segun es posible o no mover la estructura horizontalmente.
     */
    public boolean canMoveStruct(int increase_column, int limit){
        int j;
        for (Coordinate coord: getStruct()) {
            j = coord.getJ()+ increase_column;
            if(j>=limit){
                return false;
            }
        }
        return true;
    }

    /**
     * Ubica una estructura en una fila especifica de la matriz.
     * @param row fila en donde se desea ubicar la estructura.
     */
    public void setStartPosition(int row){
        for (Coordinate cord :structure) {
            int i_old = cord.getI();
            cord.setI(i_old + row);
        }
    }

    /**
     * Analiza si la estructura es valida segun los limites de la matriz, esto quiere decir que si cualquier coordenada
     * no esta dentro de los limites se considera una coordenada invalida, por lo tanto invalida a la estructura
     * @param limit1 limite vertical (filas)
     * @param limit2 limite horizontal (columnas)
     * @return true o false. Es true si la estructura es valida o false si no lo es.
     */
    public boolean isStructValid(int limit1, int limit2){
        //System.out.print("Analizando struct "+id+" limites "+(limit1-1)+" "+(limit2-1)+"...");
        for (Coordinate cord :structure) {
           if(!(cord.getI()<limit1 && cord.getJ()<limit2)){
               //System.out.println(" es invalida!:");
               //printInvalidStruct(limit1, limit2);
               return false;
           }
        }
        //System.out.println(" es valida!");
        return true;
    }

    /**
     * Al igual que printInfoStruct, este metodo imprime una estructura pero en este caso lo hace cuando la estructura
     * es invalida. Asigna un color rojo a la coordenada que es invalida.
     * @param limit1 limite vertical
     * @param limit2 limite horizontal
     */
    public void printInvalidStruct(int limit1, int limit2 ){
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        int i=0;
        for(Coordinate cord:structure){
            if(cord.getI()<limit1 && cord.getJ()<limit2 ){
                System.out.println("Pixel "+i+": "+getCoord(i).toString());
            }
            else{
                System.out.println(ANSI_RED+"Pixel "+i+": "+getCoord(i).toString()+ANSI_RESET);
            }
            i++;
        }

    }

    //getters y setters
    public ArrayList<Coordinate> getStruct() {
        return structure;
    }
    public void setStruct(ArrayList<Coordinate> struct) {
        this.structure = struct;
    }
    public Coordinate getCoord(int i){
        return structure.get(i);
    }
    public int getValueCoord(int i){
        return structure.get(i).getValue();
    }
    public void setValuePixelObj(int value){
        structure.get(0).setValue(value);
    }
    public int getIPixelObj(){
        return structure.get(0).getI();
    }
    public int getJPixelObj(){
        return structure.get(0).getJ();
    }
    public int getValuePixelObj(){
        return structure.get(0).getValue();
    }
}
