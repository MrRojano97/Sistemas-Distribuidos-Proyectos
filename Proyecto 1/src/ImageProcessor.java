import java.util.ArrayList;
import java.util.SortedMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ImageProcessor {

    private MatrixImage image;
    private AtomicInteger matrixImage[][];
    private String tech_selected;
    private int nstruct_selected;
    private final Integer[][] structs = {{0,0,0,1,1,0}, {1,0,0,0,1,1},{1,0,0,0,2,0},
            {0,0,0,1},{1,1,0,0,0,2,2,0,2,2}};
    private ConcurrentProcessImage[] concurrentProcess;
    private int numThreads;
    private Integer[][] imageTemp;

    public ImageProcessor(String tech_selected, int struct_selected, MatrixImage imagen) throws InterruptedException {
        System.out.print("Iniciando procesador de imagenes..");
        this.tech_selected = tech_selected;
        this.nstruct_selected = struct_selected-1;

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

    public void runTech(){
        int rowsMatrix = image.getRows();
        int colMatrix=  image.getColumns();
        int row_assigned, id_struct ;
        //this.numThreads = rowsMatrix;
        this.numThreads = 2; //TEMPORAL
        System.out.println("cantidad filas: "+rowsMatrix);
        System.out.println("tecnica de procesamiento elegida: "+tech_selected);
        concurrentProcess = new ConcurrentProcessImage[numThreads];
        //struct_selected.printInfoStruct();
        for (int i = 0; i < numThreads ; i++) {
            id_struct = i+1;
            Structure struct_selected = new Structure(structs[nstruct_selected],id_struct);
            row_assigned = i;
            struct_selected.setStartPosition(row_assigned);
            if(struct_selected.isStructValid(rowsMatrix,colMatrix)){
                concurrentProcess[i] = new ConcurrentProcessImage(tech_selected,image,struct_selected,row_assigned);
                concurrentProcess[i].start();
            }
        }
//        for (int i = 0; i < numThreads ; i++) {
//            try {
//                concurrentProcess[i].join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }

}
