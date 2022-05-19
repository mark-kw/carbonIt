
public class Pion {
    private String name;
    private Coordinate cord;
    private Direction direc;
    private String[] action;
    private int nbtresor;

    public Pion(String name, Coordinate cord, Direction direc, String[] action) {
        this.name = name;
        this.cord = cord;
        this.direc = direc;
        this.action = action;
        this.nbtresor = 0;
    }

    public void setDirectionLeft(){
        switch (this.direc){
            case N:
                this.setDirec(Direction.O);
                break;
            case E:
                this.setDirec(Direction.N);
                break;
            case O:
                this.setDirec(Direction.S);
                break;
            case S:
                this.setDirec(Direction.E);
                break;
        }
    }

    public void setDirectionRight(){
        switch (this.direc){
            case N:
                this.setDirec(Direction.E);
                break;
            case E:
                this.setDirec(Direction.S);
                break;
            case O:
                this.setDirec(Direction.N);
                break;
            case S:
                this.setDirec(Direction.O);
                break;
        }
    }

    public int getNbtresor() {
        return nbtresor;
    }

    public void setNbtresor(int nbtresor) {
        this.nbtresor = nbtresor;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCord() {
        return cord;
    }

    public void setCord(Coordinate cord) {
        this.cord = cord;
    }

    public Direction getDirec() {
        return direc;
    }

    public void setDirec(Direction direc) {
        this.direc = direc;
    }

    public String[]getAction() {
        return action;
    }

    public void setAction(String[] action) {
        this.action = action;
    }
}
