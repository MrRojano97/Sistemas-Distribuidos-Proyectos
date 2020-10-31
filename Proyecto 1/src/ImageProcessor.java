import java.util.ArrayList;
import java.util.SortedMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que controla la ejecucion del programa, contiene toda las opciones del usuario y ejecuta
 * los algoritmos de procesamiento segun estas opciones.
 */
public class ImageProcessor {

    private MatrixImage image;              //Matriz generada desde la imagen
    private AtomicInteger matrixImage[][];  //Matriz de imagen generada
    private String tech_selected;           //tecnica seleccionada por el usuario
    private int nstruct_selected;           //estructura seleccionada por el usuario
    /*Arreglo de estructuras disponibles:
    - Son los valores de ubicacion de la esctructura dentro de la matriz, en el formato (i1,j1,i2,j2,i3,j3,...).
    - Por ej para la estructura {0,0,0,1,1,0} se tienen 6 valores, por lo que es una estructura de 3 pixeles (3 pares).
        - pixel principal siempre es el primer par: (0,0) en este caso y es el pixel que se compara con el resto
        - segundo pixel (0,1), tercer pixel (1,0)
        - este crea la figura: |_|_|
                               |_|
    - El arreglo tiene 5 listas correspondientes a las 5 estructuras requeridas, para mas informacion consulte el archivo
    de estructuras.png
    */
    private final Integer[][] structs = {{0,0,0,1,1,0}, {1,0,0,0,1,1},{1,0,0,0,2,0},
            {0,0,0,1},{1,1,0,0,0,2,2,0,2,2}};
    // Estructura base usada en el ejemplo
    private final Integer[] structBase = {1,1,0,1,1,0,1,2,2,1};
    //Objeto para ejecutar un proceso concurrente (dilatacion o erosion)
    private ConcurrentProcessImage[] concurrentProcess;
    private int numThreads;                 //numero de threads que se crean
    private Integer[][] imageTemp;          //Matriz copia auxiliar

    public ImageProcessor(String tech_selected, int struct_selected, MatrixImage imagen) throws InterruptedException {
        System.out.print("Iniciando procesador de imagenes..");
        this.tech_selected = tech_selected;
        this.nstruct_selected = struct_selected;
        this.image = imagen;
        matrixImage = imagen.getMatrix();

        if (this.tech_selected.equals("erosion")){
            ArrayList<Erosion> thread = new ArrayList<Erosion>();
            int totalRows = imagen.getRows();
            int totalColumns=imagen.getColumns();

            //Pasando Matriz a una temporal

            imageTemp=new Integer[totalRows][totalColumns];

            for (int i = 0; i < totalRows; i++) {
                for (int j = 0; j < totalColumns; j++) {
                    imageTemp[i][j]=matrixImage[i][j].get();
                }
            }

            for (int actualRow=1; actualRow<totalRows-1; actualRow++){
                thread.add(new Erosion(imageTemp, matrixImage,actualRow,totalColumns,0));
            }
            int actualThread=0;

            while (actualThread<thread.size()){
                thread.get(actualThread).run();
                actualThread++;
            }
        }

        else {
            System.out.println("done!");

            runTech();

        }


    }

    /**
     * Metodo que ejecuta una tecnica de procesamiento, en un principio esta implementado solo la dilatacion
     */
    public void runTech(){
        int rowsMatrix = image.getRows();       //cantidad de filas de la matriz
        int colMatrix=  image.getColumns();     //cantidad de columnas de la matriz
        int row_assigned;                       //fila asignada a cada estructura para recorrerla
        int id_struct;                          //id de cada estructura para diferenciarlas
        int num_invalidStructs=0;
        
        this.numThreads = rowsMatrix;         //NUmero de hilos a ejecutar segun la cantidad de filas
        //this.numThreads = 2;                    // cantidad de hilos de prueba, eliminar o comentar en entrega definitiva
        System.out.println("cantidad filas: "+rowsMatrix);
        System.out.println("tecnica de procesamiento elegida: "+tech_selected);
        concurrentProcess = new ConcurrentProcessImage[numThreads]; //Crea una instancia de ejecucion paralela
        //struct_selected.printInfoStruct();

        for (int i = 0; i < numThreads ; i++) {//Crear hilos
            id_struct = i+1;
            Structure struct_selected;
            if(nstruct_selected==0){
                //Crear estructura base QUITAR EN IMPLENTACION FINAL, USAR LA DE ARRIBA
                struct_selected = new Structure(structBase,id_struct);
            }
            else{
                //Crear una escructura con las forma de la elegida por el usuario
                struct_selected = new Structure(structs[nstruct_selected-1],id_struct);
            }

            //fila asignada para la estructura.
            row_assigned = i;

            struct_selected.setStartPosition(row_assigned);// ubica la estructura en la fila requerida.

            // si la estructura tiene coordenadas validas, entonces crea un hilo de ejecucion
            if(struct_selected.isStructValid(rowsMatrix,colMatrix)){
                concurrentProcess[i] = new ConcurrentProcessImage(tech_selected,image,struct_selected,row_assigned);
                concurrentProcess[i].start();   //iniciar hilo
            }
            else{
                num_invalidStructs ++;
            }
        }
        //Join del hilo principal
        for (int i = 0; i < numThreads - num_invalidStructs ; i++) {
            try {
                concurrentProcess[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}