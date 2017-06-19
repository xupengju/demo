package test;


/**
 * Created by Milo on 2017/3/31.
 * TranscodeQueue ת�����
 */
public enum SourceTypeEnum  {

    THREAD(0, "11"),
    TOPIC(1, "22"),
    WEIWEN(2, "33"),
    FORWAED(3, "44");

    private int index;
    private String name;

    SourceTypeEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return this.index;
    }

    public static SourceTypeEnum valueOf(int index) {
        switch (index) {

            case 0:
                return THREAD;
            case 1:
                return TOPIC;
            case 2:
                return WEIWEN;
            case 3:
                return FORWAED;
            default:
                return null;
        }
    }

    public String getName() {
        return this.name;
    }
}
