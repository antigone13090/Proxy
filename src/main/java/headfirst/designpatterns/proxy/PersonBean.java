package headfirst.designpatterns.proxy;

public interface PersonBean {
    String getName();
    void setName(String name);

    String getInterests();
    void setInterests(String interests);

    int getHotOrNotRating();
    void setHotOrNotRating(int rating);
}
