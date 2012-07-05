package com.chatman.mad;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class WordService extends Service {
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onStart(Intent i, int startId) {
		super.onStart(i, startId);
		String[] words = RhymeWithMad.getWordArray(getResources(), getAssets());
		Toast.makeText(this, String.format("Y U SO %s", words[new Random().nextInt(words.length)].toUpperCase()), 
				Toast.LENGTH_SHORT).show();
		stopSelf();
	}

}
