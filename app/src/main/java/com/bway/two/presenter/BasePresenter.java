package com.bway.two.presenter;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class BasePresenter<T> {
    private T t;
    public void attatch(T t)
    {
        this.t=t;
    }
    public T getMvp()
    {
        return t;
    }
    public void deattatch()
    {
        t=null;
    }

}
