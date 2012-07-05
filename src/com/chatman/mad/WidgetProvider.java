package com.chatman.mad;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		for (int i : appWidgetIds) {
			RemoteViews v = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			Intent showWord = new Intent(context, WordService.class);
			v.setOnClickPendingIntent(R.id.trollface, PendingIntent.getService(context, 1, 
					showWord, PendingIntent.FLAG_UPDATE_CURRENT));
			appWidgetManager.updateAppWidget(i, v);
		}
	}
}
