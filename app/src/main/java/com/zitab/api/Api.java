package com.zitab.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zitab.BuildConfig;
import com.zitab.api.requests.ApiAnimals;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
	private static final String SERVER_URL = "http://kot3.com";
	public static final String BASE_PATH = "/xim/api.php";
	
	private static final int CONNECTION_TIMEOUT = 200;
	
	private static Api instance;
	private Retrofit retrofit;
	private Gson gson;
	
	private Api() { }
	
	public synchronized static Api getInst() {
		if (instance == null) {
			instance = new Api();
			instance.build();
		}
		return instance;
	}
	
	private void build() {
		OkHttpClient.Builder clientBuilder = provideOkHttpClientBuilder();
		clientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
		clientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
		
		OkHttpClient okHttpClient = clientBuilder.build();
		retrofit = new Retrofit.Builder()
				.baseUrl(SERVER_URL)
				.client(okHttpClient)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create(getGson()))
				.build();
	}
	
	public ApiAnimals animals() {
		return retrofit.create(ApiAnimals.class);
	}
	
	private synchronized Gson getGson() {
		if (gson == null) {
			gson = new GsonBuilder().create();
		}
		return gson;
	}
	
	private OkHttpClient.Builder provideOkHttpClientBuilder() {
		final OkHttpClient.Builder okHttpBuilder = new OkHttpClient()
				.newBuilder();
		if(BuildConfig.DEBUG) {
			okHttpBuilder.addInterceptor(provideHttpLoggingInterceptor());
		}
		return okHttpBuilder;
	}
	
	private HttpLoggingInterceptor provideHttpLoggingInterceptor() {
		return new HttpLoggingInterceptor()
				.setLevel(HttpLoggingInterceptor.Level.BODY);
	}
}