import java.util.ArrayList;

public class Structure {

    private ArrayList<Coordinate> structure;
    private int id;

    public Structure(Integer[] listacoordenadas,int id ) {
        System.out.print("Creando estructura id "+id+"..");
        this.id = id;
        structure = new ArrayList<>();
        for (int i = 0; i < listacoordenadas.length; i++) {
            if(i+1<listacoordenadas.length){
                Coordinate cord = new Coordinate(listacoordenadas[i],listacoordenadas[i+1] );
                structure.add(cord);
                i++;
            }
        }
        System.out.println("done!");
    }

    public Structure(ArrayList<Coordinate> structure){
        this.structure=structure;
    }

    public boolean canMoveRightStruct(int i, int j){
        for (i=0; i<structure.size(); i++){

        }
        return false;
    }
    public void printInfoStruct(){
        System.out.println("Info Estructura "+id+" size: "+ structure.size());
        System.out.println("Pixel Estudio: "+getCoord(0).toString());
        for (int i = 1; i < structure.size() ; i++) {
            System.out.println("Pixel "+i+": "+getCoord(i).toString());
        }

    }
    public void moveStruct(int increase_column, int limit){
        int j;
        for (Coordinate coord: getStruct()) {
            j = coord.getJ()+ increase_column;
            if(j<limit){
                coord.setJ(j);
            }

        }
    }
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
    public void setStartPosition(int row){
        for (Coordinate cord :structure) {
            int i_old = cord.getI();
            cord.setI(i_old + row);
        }
    }
    public boolean isStructValid(int limit1, int limit2){
        System.out.print("Analizando struct "+id+" limites "+(limit1-1)+" "+(limit2-1)+"...");
        for (Coordinate cord :structure) {
            //System.out.println(cord.getI()+"<"+limit1+" && "+cord.getJ()+"<"+limit2);
            //System.out.println((cord.getI()<limit1 && cord.getJ()<limit2));
           if(!(cord.getI()<limit1 && cord.getJ()<limit2)){
               System.out.println(" es invalida!:");
               printInvalidStruct(limit1, limit2);
               return false;
           }
        }
        System.out.println(" es valida!");
        return true;
    }

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
