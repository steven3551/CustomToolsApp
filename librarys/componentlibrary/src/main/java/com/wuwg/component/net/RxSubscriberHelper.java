package com.wuwg.component.net;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.wuwg.component.ComponentLibrary;
import com.wuwg.component.R;
import com.wuwg.component.log.MyLog;
import com.wuwg.component.net.except.ApiException;
import com.wuwg.component.net.except.ExceptionEngine;
import com.wuwg.component.net.view.DialogHelper;
import com.wuwg.component.net.view.IBaseView;

import java.lang.ref.WeakReference;

import rx.Subscriber;

/**
 * Created by wuwengao on 2017/6/15.
 */
public abstract class RxSubscriberHelper<T> extends Subscriber<T> {

    public static class Builder<E> {

        boolean isShowMessage = true;
        boolean isCheckPermission = true;
        boolean isShowLoad = false;
        IBaseView view;

        public void setView(IBaseView view) {
            this.view = view;
        }

        public Builder setShowMessage(boolean showMessage) {
            isShowMessage = showMessage;
            return this;
        }

        public Builder setCheckPermission(boolean checkPermission) {
            isCheckPermission = checkPermission;
            return this;
        }

        public Builder setShowLoad(boolean showLoad) {
            isShowLoad = showLoad;
            return this;
        }
    }

    /**
     * 错误是否弹出toast
     */
    protected boolean isShowMessage = true;

    /**
     * 是否自动拦截权限类错误
     */
    protected boolean isCheckPermission = true;

    /**
     * 是否显示加载框
     */
    protected boolean isShowLoad = false;

    private WeakReference<Context> context;

    protected IBaseView view;

    public RxSubscriberHelper() {
        context = null;
    }

    /**
     * @param context
     * @param isShowLoad 是否显示请求加载框
     */
    public RxSubscriberHelper(Context context, boolean isShowLoad) {
        this.context = new WeakReference<Context>(context);
        this.isShowLoad = isShowLoad;
    }

    /**
     * @param context
     * @param builder 配置Builder
     */
    public RxSubscriberHelper(Context context, Builder builder) {
        this.context = new WeakReference<Context>(context);
        if (builder != null) {
            this.isShowMessage = builder.isShowMessage;
            this.isShowLoad = builder.isShowLoad;
            this.isCheckPermission = builder.isCheckPermission;
            this.view = builder.view;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isShowLoad) {
            onShowLoading();
        }
    }

    @Override
    public void onCompleted() {
        if (isShowLoad) {
            onDissLoad();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        if (throwable != null) {
            MyLog.e(throwable.getMessage(), throwable.getClass());
        }
        ApiException apiException = ExceptionEngine.handleException(throwable);
        if (isCheckPermission && apiException.code == ExceptionEngine.ERROR.PERMISSION_ERROR) {
            onPermissionError(apiException);
        } else {
            if(isShowMessage){
                onShowMessage(apiException);
            }
            _onError(apiException);
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    public abstract void _onNext(T t);

    protected void onDissLoad() {

    }

    public void _onError(ApiException error) {
        if(isShowLoad) {
            onDissLoad();
        }
        if(view != null) {
            view.onLoadedError(error);
        }
    }

    /**
     * 显示加载loading
     */
    protected void onShowLoading() {
        if(context != null) {
            DialogHelper.getInstance().showLodingDialog(context.get());
        }
    }

    /**
     * 权限失效会自动登出, V1版本权限失效主要依据为 401 {@link ExceptionEngine#handleException(Throwable)}
     * 所有需要验证权限的接口，Authorization token 缺失或校验失败都将触发401 {@link }
     *
     * @param apiException
     */
    protected void onPermissionError(ApiException apiException) {
    }

    /**
     * 任何类型的错误都会弹出Toast,重写方式修改
     *
     * @param apiException
     */
    protected void onShowMessage(ApiException apiException) {
        if (apiException != null && !TextUtils.isEmpty(apiException.message)) {
            Toast.makeText(ComponentLibrary.mContext, apiException.message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ComponentLibrary.mContext, ComponentLibrary.mContext.getString(R.string.common_net_error_default), Toast.LENGTH_SHORT).show();
        }
    }

}
