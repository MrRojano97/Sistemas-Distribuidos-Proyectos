import java.util.SortedMap;

public class ImageProcessor {

    private MatrixImage image;
    private String tech_selected;
    private int nstruct_selected;
    private final Integer[][] structs = {{0,0,0,1,1,0}, {1,0,0,0,1,1},{1,0,0,0,2,0},
            {0,0,0,1},{1,1,0,0,0,2,2,0,2,2}};
    private ConcurrentProcessImage[] concurrentProcess;
    private int numThreads;


    public ImageProcessor(String tech_selected, int struct_selected, MatrixImage imagen) {
        System.out.print("Iniciando procesador de imagenes..");
        this.tech_selected = tech_selected;
        this.nstruct_selected = struct_selected-1;
        image = imagen;

        System.out.println("done!");

        runTech();

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
