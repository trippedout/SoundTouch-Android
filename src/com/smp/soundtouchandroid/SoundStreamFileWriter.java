package com.smp.soundtouchandroid;

import java.io.IOException;

import android.util.Log;

public class SoundStreamFileWriter extends SoundStreamRunnable
{
	private long start, end;
	private AACFileAudioSink file;
	private String fileNameOut;

	public SoundStreamFileWriter(int id, String fileNameIn, String fileNameOut,
			float tempo, float pitchSemi) throws IOException
	{
		super(id, fileNameIn, tempo, pitchSemi);
		this.fileNameOut = fileNameOut;
		file.setFileOutputName(fileNameOut);
	}

	@Override
	protected AudioSink initAudioSink() throws IOException
	{
		file = new AACFileAudioSink(fileNameOut);
		return file;
	}

	@Override
	protected void onStart()
	{
		start = System.nanoTime();
	}

	@Override
	protected void onPause()
	{
		// TODO Auto-generated method stub
	}

	@Override
	protected void onStop()
	{
		try
		{
			file.finishWriting();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		end = System.nanoTime();
		long elapsedTime = end - start;
		double seconds = (double) elapsedTime / 1000000000.0;
		//Log.i("ENCODE", "SECONDS: " + String.valueOf(seconds));
	}
}
