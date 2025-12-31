package headfirst.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class OwnerInvocationHandler implements InvocationHandler {
    private final PersonBean person;

    public OwnerInvocationHandler(PersonBean person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            String m = method.getName();

            if (m.startsWith("get")) {
                return method.invoke(person, args);
            }
            if (m.equals("setHotOrNotRating")) {
                throw new IllegalAccessException("Owner cannot rate themselves.");
            }
            if (m.startsWith("set")) {
                return method.invoke(person, args);
            }
            return method.invoke(person, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }
}
