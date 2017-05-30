package test;


/**
 * Created by Milo on 2017/3/31.
 * TranscodeQueue 转码队列
 */
public enum SourceTypeEnum  {

    THREAD(0, "帖子"),
    TOPIC(1, "小话题"),
    WEIWEN(2, "微文"),
    FORWAED(3, "转发");

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
