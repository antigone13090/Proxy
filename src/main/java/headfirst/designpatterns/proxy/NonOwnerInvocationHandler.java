package headfirst.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NonOwnerInvocationHandler implements InvocationHandler {
    private final PersonBean person;

    public NonOwnerInvocationHandler(PersonBean person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            String m = method.getName();

            if (m.startsWith("get")) {
                return method.invoke(person, args);
            }
            if (m.equals("setInterests") || m.equals("setName")) {
                throw new IllegalAccessException("Non-owner cannot modify personal data.");
            }
            if (m.equals("setHotOrNotRating")) {
                return method.invoke(person, args);
            }
            return method.invoke(person, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }
}
