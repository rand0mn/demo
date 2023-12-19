package com.daa.demo.mvp;

import java.lang.reflect.InvocationTargetException;

public class ViewFactory {
    private ViewFactory() {

    }

    public static <V extends View, P extends Presenter> View create(
        Class<V> viewType,
        Class<P> presenterType,
        Model model
    ) {
        try {
            var view = viewType.getDeclaredConstructor().newInstance();
            presenterType
                .getDeclaredConstructors()[0]
                .newInstance(view, model);

            return view;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
