package com.hi_depok.hi_depok.Sikepok_RS;

import android.content.Context;
import android.content.res.Resources;

public class PixelUtil {

	public static int dp2px(float value, Context context) {
		final float scale = context.getResources().getDisplayMetrics().densityDpi;
		return (int) (value * (scale / 160) + 0.5f);
	}

	public static int px2dp(float value, Context context) {
		final float scale = context.getResources().getDisplayMetrics().densityDpi;
		return (int) ((value * 160) / scale + 0.5f);
	}

	public static int sp2px(float value, Context context) {
		Resources r;
		if (context == null) {
			r = Resources.getSystem();
		} else {
			r = context.getResources();
		}
		float spvalue = value * r.getDisplayMetrics().scaledDensity;
		return (int) (spvalue + 0.5f);
	}

	public static int px2sp(float value, Context context) {
		final float scale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (value / scale + 0.5f);
	}

}
