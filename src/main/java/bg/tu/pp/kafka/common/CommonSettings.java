package bg.tu.pp.kafka.common;

public interface CommonSettings {
    String EXIT_CMD = "exit";
    String MESSAGE_CMD = "msg:";
    int CUT_OFF_OFFSET = MESSAGE_CMD.length();

    String TOPIC_NAME = "test-part1";//"test-partitioning";
}
