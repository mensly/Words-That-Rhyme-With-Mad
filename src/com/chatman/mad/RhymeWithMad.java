package com.chatman.mad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class RhymeWithMad extends Activity {
	private AlertDialog.Builder mBuilder;
	private Resources mRes; 
	private String[] mWordArray;
	private Random mRand;
	private int mPrevMsg;
	private int mCurrMsg;

	public void readWords() {
		mWordArray = getWordArray(mRes, this.getAssets());
	}
	public static String[] getWordArray(Resources mRes, AssetManager assets)
	{
		ArrayList<String> wordList = new ArrayList<String>();
		BufferedReader br = null;
		InputStream inStream = null;
		String word;
		String madfile = mRes.getString(R.string.madfile);
		try
		{
			String state = Environment.getExternalStorageState();
			if (state.equals(Environment.MEDIA_MOUNTED)
					|| state.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
			{
				File mad = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
						+ File.separator + madfile);

				if (mad.exists())
				{
					inStream = new FileInputStream(mad);
				}
				else
				{
					inStream = assets.open(madfile);
				}
			}
			else
			{
				inStream = assets.open(madfile);

			}
			br = new BufferedReader(new InputStreamReader(inStream));
			while ( (word = br.readLine()) != null)
			{
				wordList.add(word);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				br.close();
				inStream.close();
			}
			catch (IOException e2)
			{
				e2.printStackTrace();
			}
		}
		if (wordList.isEmpty())
			wordList.add("mad");

		return wordList.toArray(new String[0]);
	}
	
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		mRes = getResources();
		mRand = new Random();
		mPrevMsg = -1;
		
		readWords();
		
		mBuilder = new AlertDialog.Builder(this);
		mBuilder.setCancelable(false)
			.setTitle(R.string.dialogTitle)
			.setPositiveButton(mRes.getString(R.string.closeBox), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
		
		
		Button b = (Button)findViewById(R.id.yuso);
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		        
				do
				{
					mCurrMsg = mRand.nextInt(mWordArray.length);
				} while (mPrevMsg == mCurrMsg);
		        mBuilder.setMessage(mWordArray[mCurrMsg].toUpperCase());
				mBuilder.create().show();
				if (mWordArray.length > 1)
					mPrevMsg = mCurrMsg;
				
			}
		});
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch (item.getItemId())
    	{
    	case R.id.help:
    		startActivity(new Intent(this,HelpScreen.class)); 
    		break;
    	}
    	return true;
    }
}


