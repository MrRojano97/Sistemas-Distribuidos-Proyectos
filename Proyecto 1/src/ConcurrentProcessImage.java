/**
 * Clase que corresponde a la ejecucion paralela de los 2 algoritmos de procesamiento Dilatacion y erosion
 */

public class ConcurrentProcessImage extends Thread{

    private MatrixImage image;              //Matriz de la imagen
    private final String tech_selected;     //tecnica seleccionada por usuario
    private Structure struct_selected;      //Esctructura seleccionada por usuario
    private int row_assigned;               //fila de la matriz asignada a la estructura
    private int increase_column = 1;        //valor de avance horizontal de la estructura

    /**
     * Constructor de la clase
     * @param tech tecnica seleccionada por usuario
     * @param imagen Matriz de la imagen
     * @param struct Esctructura seleccionada por usuario
     * @param row fila de la matriz asignada a la estructura
     */
    public ConcurrentProcessImage(String tech, MatrixImage imagen, Structure struct, int row) {
        tech_selected  = tech;
        struct_selected = struct;
        row_assigned  = row;
        image = imagen;
    }

    /**
     * Metodo run de la clase Thread, inicia la ejecucion de un hilo
     */
    @Override
    public void run() {
        System.out.print("run..");
        if(tech_selected.equals("erosion")){
            //runErosion();
        }
        else if(tech_selected.equals("dilatation")){
            System.out.println("dilatation..");
            runDilatation();
        }
        else{
            System.out.println("Error de numero de tecnica");
        }
        System.out.println("----------- Thread "+(row_assigned+1)+" Terminado -----------");
    }

    /**
     * Algoritmo de procesamiento erosion
     */
    private void runErosion(){

    }

    /**
     * Algoritmo de procesamiento de dilatacion
     */
    private void runDilatation(){
        boolean end = false;            //bandera de termino de la ejecucion de un hilo
        //struct_selected.setStartPosition(row_assigned);   //
        setValuesStruct();              //Asigna a cada casillero de la estructura el valor en la matriz
        int pixel_objetivo = 0;         //indice del pixel objetivo dentro del arreglo de pixeles.
        int i_pos;                      //valor de posicion i del pixel objetivo.
        int j_pos;                      //valor de posicion j del pixel objetivo.

        //Comienza el recorrido de la estructura por una fila
        while (!end) {
            //System.out.println("Dilatacion hilo "+row_assigned);
            //truct_selected.printInfoStruct();
            //valor numero del pixel objetivo para comparar.
            int valorPixelObjetivo = struct_selected.getValueCoord(pixel_objetivo);
            //Recorrer la esctructura para comparar valores y asignar el mayor.
            for (Coordinate coord: struct_selected.getStruct()) {
                //Si el valor objetivo es menor, se cambia por el mayor.
                if(coord.getValue()>valorPixelObjetivo ){
                    //System.out.println("Mayor encontrado! "+coord.getValue()+ ">"+valorPixelObjetivo );
                    valorPixelObjetivo  = coord.getValue();
                }
            }
            //Cambiar el valor encontrado en la matriz
            i_pos = struct_selected.getIPixelObj();
            j_pos = struct_selected.getJPixelObj();
            //System.out.println("cambiando "+i_pos+","+j_pos);
            image.setValue(i_pos,j_pos,valorPixelObjetivo );
            //image.printMatrix();

            //Mover estructura hacia la derecha para comparar nuevamente
            if(struct_selected.canMoveStruct(increase_column, image.getColumns())){
                struct_selected.moveStruct(increase_column, image.getColumns());
                setValuesStruct();
            }
            //Si no es posible mover, quiere decir que se llego al final
            else{
                end = true;
            }

        }

    }

    /**
     * Obtener los valores de la matriz para cada casillero de la estructura.
     */
    public void setValuesStruct(){
        int i,j;
        for (Coordinate coord: struct_selected.getStruct()) {
            i = coord.getI();
            j = coord.getJ();
            coord.setValue(image.getValue(i,j));
        }
    }

}
