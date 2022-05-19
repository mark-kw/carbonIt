public class Cell {
    private int x;
    private int y;
    private int nbCellTresor;
    private Pion pion;
    private String value;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.value = ".";
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNbtresor() {
        return nbCellTresor;
    }

    public void setNbtresor(int nbTresor) {
        this.nbCellTresor = nbTresor;
    }

    public Pion getPion() {
        return pion;
    }

    public void setPion(Pion pion) {
        this.pion = pion;
    }


}
