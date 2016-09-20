package com.huchiwei.gankessence.core.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * RxJava封装辅助类
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class RxJavaHelper {

    /**
     * 切换线程操作
     * @return Observable转换器
     */
    public static <T> Observable.Transformer<T, T> observeOnMainThread() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable
                        .subscribeOn(Schedulers.io())                       // 在io线程中请求
                        .observeOn(AndroidSchedulers.mainThread());         // 请求完成后返回主线程处理
            }
        };
    }
}
