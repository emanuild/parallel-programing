package bg.tu.pp.phasers.main;

import bg.tu.pp.phasers.dto.Player;
import bg.tu.pp.phasers.threads.ReactionThread;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
public class PhaserRunner {

    private Phaser phaser;
    private ExecutorService executorService;
    private Map<String,Player> players;
    private Player slowestPlayer;

    public PhaserRunner(String[] playerNames) {
        executorService = Executors.newCachedThreadPool();
        phaser = new Phaser();
        this.players = getPlayersList(playerNames);
        this.slowestPlayer = new Player("noone");
        phaser.register();
    }

    public void run() {
        System.out.println("Phase is "+phaser.getPhase());

        while (players.size() > 1) {
            for (Map.Entry<String,Player> e : players.entrySet()) {
                executorService.submit(new ReactionThread(e.getValue(), phaser, slowestPlayer));
            }

            phaser.arriveAndAwaitAdvance();

            System.out.println("Phase is "+phaser.getPhase());

            String slowestPlayerName = slowestPlayer.getName();
            System.out.println("Slowest is "+slowestPlayerName);

            players.remove(slowestPlayerName);
        }

        Optional<Map.Entry<String, Player>> e = players.entrySet().stream().findFirst();
        String winnerName = e.get().getKey();

        System.out.println("And the winner is "+winnerName);

        phaser.arriveAndDeregister();
        System.out.println("Main thread deregistered!!!");
        System.exit(0);
    }

    private Map<String,Player> getPlayersList(String[] playerNames) {
        Map<String,Player> m = new HashMap<>(playerNames.length);

        for (String pn : playerNames) {
            m.put(pn, new Player(pn));
        }

        return m;
    }

}
