package com.yurset.wiipm.base;

import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.widget.ImageView;
import com.yurset.wiipm.R;

public class UGifView extends ImageView {

	private Movie mMovie;
	private int duration;
	private long mMovieStart;
	private int width = 0;
	private int height = 0;
	private int resId, locX, locY;

	public UGifView(Context context, int locBF) {
		super(context);
		if (locBF == 0) {
			this.locY = 70 * 5;
			this.locX = 34 * 5;
		} else if (locBF == 1) {
			this.locY = 45 * 5;
			this.locX = 94 * 5;
		}
		resId = R.drawable.xxegg;
		loadMovie(resId);
	}

	public int getResource() {
		return resId;
	}

	public void setResource(int res) {
		this.resId = res;
		loadMovie(res);
	}

	private void loadMovie(int res) {
		InputStream is = null;
		try {
			is = getResources().openRawResource(res);
			mMovie = Movie.decodeStream(is);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		if (mMovie == null) {
			duration = 1000;
			width = 0;
			height = 0;
			return;
		}

		duration = mMovie.duration();
		if (duration <= 0) {
			duration = 1000;
		}
		width = mMovie.width();
		height = mMovie.height();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (mMovie == null) {
			return;
		}
		long now = SystemClock.uptimeMillis();
		if (mMovieStart == 0) {
			mMovieStart = now;
		}
		if (now - mMovieStart >= duration) {
			mMovieStart = now;
		}
		int relTime = (int) (now - mMovieStart);
		canvas.scale(1.6f, 1.6f);
		mMovie.setTime(relTime);
		mMovie.draw(canvas, locX - width / 2, locY - height);
		canvas.scale(0.625f, 0.625f);
		invalidate();
	}
}
