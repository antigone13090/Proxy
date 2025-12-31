package headfirst.designpatterns.proxy;

public class PersonBeanImpl implements PersonBean {
    private String name;
    private String interests;
    private int ratingTotal = 0;
    private int ratingCount = 0;

    @Override public String getName() { return name; }
    @Override public void setName(String name) { this.name = name; }

    @Override public String getInterests() { return interests; }
    @Override public void setInterests(String interests) { this.interests = interests; }

    @Override public int getHotOrNotRating() {
        if (ratingCount == 0) return 0;
        return ratingTotal / ratingCount;
    }

    @Override public void setHotOrNotRating(int rating) {
        ratingTotal += rating;
        ratingCount++;
    }
}
