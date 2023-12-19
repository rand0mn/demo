package com.daa.demo.mvp;


/* TODO: generic presenter<View, Model> */
public abstract class BasePresenter<V extends View, M extends Model> implements Presenter {
    protected V view;
    protected M model;
    protected BasePresenter(V view, M model) {
        this.view = view;
        this.model = model;
        view.setPosition(model.getPosition());
    }
}
