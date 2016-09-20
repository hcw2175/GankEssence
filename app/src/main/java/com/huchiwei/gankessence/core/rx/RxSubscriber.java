package com.huchiwei.gankessence.core.rx;

import com.huchiwei.gankessence.R;
import com.huchiwei.gankessence.core.response.ResponseData;
import com.huchiwei.gankessence.core.utils.LogUtil;
import com.huchiwei.gankessence.core.utils.ResourceUtil;
import com.huchiwei.gankessence.core.utils.ToastUtil;

import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * RxJava订阅者Subscriber封装扩展
 *
 * @author huchiwei
 * @version 1.0.0
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        // 忽略操作，需要可覆写该方法
    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof HttpException){
            String errNetwork = ResourceUtil.getString(R.string.err_network);
            LogUtil.error(e, "onError: " + errNetwork);
            ToastUtil.show(errNetwork);
        }

        // TODO: 处理其它通用错误
        // 覆写此方法自行处理异常，通用异常使用super.onError(e)保留
        e.printStackTrace();
    }

    @Override
    public void onNext(T t) {
        if(t instanceof ResponseData){
            ResponseData response = (ResponseData) t;
            // 判断是否请求错误，出错直接转到onError()
            if(response.isError()){
                Throwable e = new Throwable(ResourceUtil.getString(R.string.err_default));
                this.onError(e);
                return;
            }
        }
        onSuccess(t);
    }

    /**
     * 请求成功回调
     * @param t 最终响应结果
     */
     public abstract void onSuccess(T t);
}
