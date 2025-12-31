package headfirst.designpatterns.proxy;

import java.lang.reflect.Proxy;

public class MatchMakingTestDrive {

    public static void main(String[] args) {
        PersonBean joe = new PersonBeanImpl();
        joe.setName("Joe Javaman");
        joe.setInterests("Java, Design Patterns, Espresso");

        System.out.println("== Owner proxy ==");
        PersonBean owner = getOwnerProxy(joe);
        System.out.println("Name: " + owner.getName());
        owner.setInterests("Java, Kubernetes");
        System.out.println("Interests: " + owner.getInterests());
        try {
            owner.setHotOrNotRating(10);
        } catch (Exception e) {
            System.out.println("Blocked: " + e.getMessage());
        }

        System.out.println("\n== Non-owner proxy ==");
        PersonBean nonOwner = getNonOwnerProxy(joe);
        System.out.println("Name: " + nonOwner.getName());
        try {
            nonOwner.setInterests("Hacking");
        } catch (Exception e) {
            System.out.println("Blocked: " + e.getMessage());
        }
        nonOwner.setHotOrNotRating(7);
        nonOwner.setHotOrNotRating(9);
        System.out.println("Rating: " + nonOwner.getHotOrNotRating());
    }

    private static PersonBean getOwnerProxy(PersonBean person) {
        return (PersonBean) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new OwnerInvocationHandler(person)
        );
    }

    private static PersonBean getNonOwnerProxy(PersonBean person) {
        return (PersonBean) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new NonOwnerInvocationHandler(person)
        );
    }
}
