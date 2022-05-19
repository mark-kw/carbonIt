import java.io.*;

public class GetArgs {
    private String pathFile;
    private String result;
    private Coordinate sizeMap;

    public GetArgs(String[] args) throws Exception {
        if(args.length == 1) {
            pathFile = args[0];
            this.sizeMap = new Coordinate(0, 0);
        }else{
            throw new Exception("Trop ou pas assez d'arguments");
        }
    }

    public Coordinate sizeMap() throws Exception {
        try {
            FileReader filereader = new FileReader(pathFile);
            BufferedReader br = new BufferedReader(filereader);
            String line = "";
            try {
                line = br.readLine();
                line = line.replaceAll("\\s", "");
                String[] part = line.split("-");
                if (part[0].equals("C")) {
                    this.sizeMap.setX(Integer.parseInt(part[2]));
                    this.sizeMap.setY(Integer.parseInt(part[1]));
                }
                else
                    throw new Exception("Veuillez indiquer la taille de la carte (ex: C-2-3)");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return this.sizeMap;
    }

    public Pion getAdv() throws Exception{
        Pion adv = null;

            FileReader filereader = new FileReader(pathFile);
            BufferedReader br = new BufferedReader(filereader);
            String line = "";
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("\\s", "");
                String[] part = line.split("-");
                if (part[0].equals("A")) {
                    Coordinate coord = new Coordinate(Integer.parseInt(part[3]), Integer.parseInt(part[2]));
                    String[] mouv = part[5].split("");
                    adv = new Pion(part[1], coord, Direction.valueOf(part[4]), mouv);
                    break;
                }
            }
            if (adv == null)
                throw new Exception("Veuillez définir un joueur dans le fichier (ex: A - Lara - 1 - 1 - S - AADADAGGA)");

        return adv;
    }

    public String getPathfile() {
        return pathFile;
    }

    public void setPathfile(String pathfile) {
        this.pathFile = pathfile;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
