package com.lrony.mvpframe.mvp;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lrony.mvpframe.base.BaseFragment;
import com.lrony.mvpframe.util.ToastUtil;

/**
 * Created by Lrony on 18-4-10.
 */
public abstract class MvpFragment<P extends MvpPresenter> extends BaseFragment implements MvpView {

    private P mPresenter;

    @Override
    public void showToast(String message) {
        ToastUtil.showToast(message);
    }

    @Override
    public void showToast(int id) {
        super.showToast(id);
        ToastUtil.showToast(id);
    }

    @Override
    public Context provideContext() {
        return this.getContext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }
    }

    @NonNull
    public abstract P createPresenter();

    /**
     * Subclass can get the bound presenter by calling this method.
     *
     * 子类通过调用该方法，获得绑定的presenter
     *
     * @return Bound presenter
     */
    protected P getPresenter() {
        if (mPresenter == null) {
            mPresenter = createPresenter();
            mPresenter.attachView(this);
        }
        return mPresenter;
    }
}
