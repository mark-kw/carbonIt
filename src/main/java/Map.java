import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Map {
    private int x;
    private int y;

    private Cell[][] map;
    private String showMap;

    public Map(int x, int y) {
        this.x = x;
        this.y = y;
        this.map = new Cell[x][y];
        this.showMap = "";
    }

    public Cell[][] emptyMap(int x, int y){
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                this.map[i][j] = new Cell(i,j);
            }
        }
        return this.map;
    }


    public Cell[][] createMap (String pathFile, Pion adv) throws Exception{
        try {
            FileReader filereader = new FileReader(pathFile);
            BufferedReader br = new BufferedReader(filereader);
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("\\s","");
                String[] part = line.split("-");
                for (int j = 0; j < part.length ; j ++){
                    if(part[j].equals("C")){
                        this.map = emptyMap(Integer.parseInt(part[j+2]),Integer.parseInt(part[j+1]));
                    }
                    if(part[j].equals("M") && Integer.parseInt(part[j+2]) < x && Integer.parseInt(part[j+1]) < y ){
                        this.map[Integer.parseInt(part[j+2])][Integer.parseInt(part[j+1])].setValue("M");
                    }

                    if(part[j].equals("T") && Integer.parseInt(part[j+2]) < x && Integer.parseInt(part[j+1]) < y){
                        this.map[Integer.parseInt(part[j+2])][Integer.parseInt(part[j+1])].setValue("T("+Integer.parseInt(part[j+3])+")");
                        this.map[Integer.parseInt(part[j+2])][Integer.parseInt(part[j+1])].setNbtresor(Integer.parseInt(part[j+3]));
                    }

                    if(part[j].equals("A")){
                        if (this.map[adv.getCord().getX()][adv.getCord().getY()].getValue().equals(".")) {
                            this.map[adv.getCord().getX()][adv.getCord().getY()].setPion(adv);
                            this.map[adv.getCord().getX()][adv.getCord().getY()].setValue("A");
                        }else
                            throw new Exception("Attention le joueur se situe au meme endroit qu'un tresor ou qu'une montagne");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.map;
    }
    public void playGame(Pion adv){
        for(int i = 0; i < adv.getAction().length; i++){
            String action = adv.getAction()[i];
            if(action.equals("A")){
                advMove(adv);
            }
            if(action.equals("D")){
                adv.setDirectionRight();
            }
            if(action.equals("G")){
                adv.setDirectionLeft();
            }
        }
    }
    public void advMove(Pion adv){
        int tresor = this.map[adv.getCord().getX()][adv.getCord().getY()].getNbtresor();
        Coordinate cordTre = new Coordinate(adv.getCord().getX(),adv.getCord().getY());
        String val = ".";
        if (tresor > 0) {
            val = "T(" + (tresor - 1) + ")";
            adv.setNbtresor(adv.getNbtresor() + 1);
            this.map[cordTre.getX()][cordTre.getY()].setNbtresor(tresor - 1);
        }
        this.map[adv.getCord().getX()][adv.getCord().getY()].setValue(val);
        this.map[adv.getCord().getX()][adv.getCord().getY()].setPion(null);
        switch (adv.getDirec()){
            case S:
                if (adv.getCord().getX()+1 <= this.y && !(this.map[adv.getCord().getX() + 1][adv.getCord().getY()].getValue().equals("M"))) {
                    Coordinate cord = new Coordinate(adv.getCord().getX() + 1, adv.getCord().getY());
                    adv.setCord(cord);
                }
                break;
            case O:
                if(adv.getCord().getY()-1 >= 0 && !(this.map[adv.getCord().getX()][adv.getCord().getY() - 1].getValue().equals("M"))) {
                    Coordinate cord2 = new Coordinate(adv.getCord().getX(), adv.getCord().getY()-1);
                    adv.setCord(cord2);
                }
                break;
            case E:
                if (adv.getCord().getY()+1 <= this.x && !(this.map[adv.getCord().getX()][adv.getCord().getY() + 1].getValue().equals("M"))) {
                    Coordinate cord3 = new Coordinate(adv.getCord().getX(), adv.getCord().getY() + 1);
                    adv.setCord(cord3);
                }
                break;
            case N:
                if (adv.getCord().getX()-1 >= 0 && !(this.map[adv.getCord().getX() - 1][adv.getCord().getY()].getValue().equals("M"))) {
                    Coordinate cord4 = new Coordinate(adv.getCord().getX() - 1, adv.getCord().getY());
                    adv.setCord(cord4);
                }
                break;
        }
        this.map[adv.getCord().getX()][adv.getCord().getY()].setValue("A");
        this.map[adv.getCord().getX()][adv.getCord().getY()].setPion(adv);
    }
    public void outPut(String res){
        try {
            FileWriter myWriter = new FileWriter("out.txt");
            myWriter.write(res);
            myWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        showMap = "";
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                if (this.map[i][j].getValue().length() == 4)
                    showMap += this.map[i][j].getValue()+"\t ";
                else if (this.map[i][j].getPion() != null){
                    showMap += this.map[i][j].getValue()+"("+this.map[i][j].getPion().getName()+")  ";
                }
                else
                    showMap += this.map[i][j].getValue()+"\t"+"\t ";
            }
            showMap += "\n";
        }
        return showMap;
    }

    public Cell[][] getMap() {
        return map;
    }

    public void setMap(Cell[][] map) {
        this.map = map;
    }

    public String getShowMap() {
        return showMap;
    }

    public void setShowMap(String showMap) {
        this.showMap = showMap;
    }
}
