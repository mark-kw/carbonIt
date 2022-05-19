public class Main {
     public static void main(String[] args) {
         try {
             GetArgs param = new GetArgs(args);
             Coordinate size = param.sizeMap();
             Pion adv = param.getAdv();
             Map map = new Map(size.getX(),size.getY());
             map.createMap(param.getPathfile(), adv);
             map.playGame(adv);
             map.outPut(map.toString());
         } catch (Exception e){
             e.printStackTrace();
         }
    }

}
