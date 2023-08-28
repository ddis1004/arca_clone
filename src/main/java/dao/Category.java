package dao;

public class Category {
    private String id;
    private String channel;
    private String name;
    private boolean noBest;
    private boolean noBestChannel;
    private boolean noRateDown;
    private boolean noDelete;
    private int minRecommend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNoBest() {
        return noBest;
    }

    public void setNoBest(boolean noBest) {
        this.noBest = noBest;
    }

    public boolean isNoBestChannel() {
        return noBestChannel;
    }

    public void setNoBestChannel(boolean noBestChannel) {
        this.noBestChannel = noBestChannel;
    }

    public boolean isNoRateDown() {
        return noRateDown;
    }

    public void setNoRateDown(boolean noRateDown) {
        this.noRateDown = noRateDown;
    }

    public boolean isNoDelete() {
        return noDelete;
    }

    public void setNoDelete(boolean noDelete) {
        this.noDelete = noDelete;
    }

    public int getMinRecommend() {
        return minRecommend;
    }

    public void setMinRecommend(int minRecommend) {
        this.minRecommend = minRecommend;
    }
}
