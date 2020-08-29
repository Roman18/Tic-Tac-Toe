public class Play {
    static {
        System.out.println("Welcome!!!");
    }
    private  Player player;
    public Play(){
        player=new Player();
player.play();
    }

    public static void main(String[] args) {
        Play play=new Play();
    }
}
