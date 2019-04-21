package com.zitab.utils;

import android.text.TextUtils;
import android.util.Log;

import com.zitab.BuildConfig;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxUtils {
	private static final String TAG = RxUtils.class.getName();
	
	public static void safeDispose(Disposable disposable) {
		if (disposable != null && !disposable.isDisposed()) {
			disposable.dispose();
		}
	}
	
	public static Consumer<Object> getEmptyDataConsumer() {
		return o -> {
		};
	}
	
	public static Consumer<Throwable> getEmptyErrorConsumer(String className, String methodName) {
		return throwable -> {
			if (BuildConfig.DEBUG) {
				String tag = TextUtils.isEmpty(className) ? TAG : "ClassName : " + className;
				String errorMsg = (TextUtils.isEmpty(methodName) ? "" : "MethodName : " + methodName + "\n") +
						"Error : " +
						throwable.toString();
				Log.e(tag, errorMsg);
			}
		};
	}
}