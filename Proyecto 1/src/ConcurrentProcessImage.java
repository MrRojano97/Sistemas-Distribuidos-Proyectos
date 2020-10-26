public class ConcurrentProcessImage extends Thread{

    private MatrixImage image;
    private final String tech_selected;
    private Structure struct_selected;
    private int row_assigned;
    private int increase_column = 1;
    private Structure save;
    public ConcurrentProcessImage(String tech, MatrixImage imagen, Structure struct, int row) {
        tech_selected  = tech;
        struct_selected = struct;
        row_assigned  = row;
        image = imagen;
    }

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

    private void runErosion(Structure structure){

    }
    private void runDilatation(){
        boolean end = false;
        //struct_selected.setStartPosition(row_assigned);
        setValuesStruct();
        int pixel_objetivo = 0,i_pos,j_pos;
        while (!end) {
            System.out.println("Dilatacion hilo "+row_assigned);
            struct_selected.printInfoStruct();
            int mayor=struct_selected.getValueCoord(pixel_objetivo);
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Coordinate coord: struct_selected.getStruct()) {
                if(coord.getValue()>mayor){
                    System.out.println("Mayor encontrado! "+coord.getValue()+ ">"+mayor);
                    mayor = coord.getValue();
                }
            }

            i_pos = struct_selected.getIPixelObj();
            j_pos = struct_selected.getJPixelObj();
            System.out.println("cambiando "+i_pos+","+j_pos);
            image.setValue(i_pos,j_pos,mayor);
            image.printMatrix();

            if(struct_selected.canMoveStruct(increase_column, image.getColumns())){
                struct_selected.moveStruct(increase_column, image.getColumns());
                setValuesStruct();
            }
            else{
                end = true;
            }

        }

    }
    public void setValuesStruct(){
        int i,j;
        for (Coordinate coord: struct_selected.getStruct()) {
            i = coord.getI();
            j = coord.getJ();
            coord.setValue(image.getValue(i,j));
        }
    }

}
