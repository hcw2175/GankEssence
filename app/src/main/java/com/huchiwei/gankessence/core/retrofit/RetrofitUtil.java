package com.huchiwei.gankessence.core.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.huchiwei.gankessence.BuildConfig;
import com.huchiwei.gankessence.R;
import com.huchiwei.gankessence.core.util.AppUtil;
import com.huchiwei.gankessence.core.util.ResourceUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit2工具类
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class RetrofitUtil {

    private static Retrofit retrofitInstance = null;

    /**
     * 创建Retrofit请求Api
     * @param clazz   Retrofit Api接口
     * @return api实例
     */
    public static <T> T createApi(Class<T> clazz){
        return getInstance().create(clazz);
    }


    // ===============================================================
    // private methods =================================================
    /**
     * 获取Retrofit实例
     * @return Retrofit
     */
    private static Retrofit getInstance(){
        if(null == retrofitInstance){
            synchronized (Retrofit.class){
                if(null == retrofitInstance){ // 双重检验锁,仅第一次调用时实例化
                    retrofitInstance = new Retrofit.Builder()
                            // baseUrl总是以/结束，@URL不要以/开头
                            .baseUrl(BuildConfig.API_SERVER_URL)
                            // 使用OkHttp Client
                            .client(buildOKHttpClient())
                            // 集成RxJava处理
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            // 集成Gson转换器
                            .addConverterFactory(buildGsonConverterFactory())
                            .build();
                }
            }
        }
        return retrofitInstance;
    }

    /**
     * 构建OkHttpClient
     * @return OkHttpClient
     */
    private static OkHttpClient buildOKHttpClient(){
        // 添加日志拦截器，非debug模式不打印任何日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(AppUtil.isDev() ? HttpLoggingInterceptor.Level.HEADERS : HttpLoggingInterceptor.Level.NONE) ;

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)                       // 添加日志拦截器
                //.addInterceptor(buildTokenInterceptor())                // 添加token拦截器
                .addNetworkInterceptor(buildCacheInterceptor())           // 添加网络缓存拦截器
                .cache(getCache())                                        // 设置缓存文件
                .retryOnConnectionFailure(true)                           // 自动重连
                .connectTimeout(15, TimeUnit.SECONDS)                     // 15秒连接超时
                .readTimeout(20, TimeUnit.SECONDS)                        // 20秒读取超时
                .writeTimeout(20, TimeUnit.SECONDS)                       // 20秒写入超时
                .build();
    }

    /**
     * 获取缓存对象
     * @return Cache
     */
    private static Cache getCache(){
        // 获取缓存目标,SD卡
        File cacheFile = new File(AppUtil.getContext().getCacheDir(), ResourceUtil.getString(R.string.app_name_en));
        // 创建缓存对象,最大缓存50m
        return new Cache(cacheFile, 1024*1024*20);
    }

    /**
     * 构建缓存拦截器
     * @return Interceptor
     */
    private static Interceptor buildCacheInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                // 无网络连接时请求从缓存中读取
                if (!AppUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                // 响应内容处理
                // 在线时缓存5分钟
                // 离线时缓存4周
                Response response = chain.proceed(request);
                if (AppUtil.isNetworkConnected()) {
                    int maxAge = 300;
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                }else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
    }

    /**
     * 构建GSON转换器
     * @return GsonConverterFactory
     */
    private static GsonConverterFactory buildGsonConverterFactory(){
        GsonBuilder builder = new GsonBuilder();
        builder.setLenient();

        // 注册类型转换适配器
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return null == json ? null : new Date(json.getAsLong());
            }
        });

        Gson gson = builder.create();
        return GsonConverterFactory.create(gson);
    }
}
