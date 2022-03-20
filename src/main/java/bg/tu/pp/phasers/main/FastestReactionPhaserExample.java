package bg.tu.pp.phasers.main;

public class FastestReactionPhaserExample {

    private static final String[] PLAYERS = {"Petko","Stiliyan","Anna","Boris","Petya"};

    public static void main(String[] args) {
        PhaserRunner phaserTest = new PhaserRunner(PLAYERS);
        phaserTest.run();
    }

}
