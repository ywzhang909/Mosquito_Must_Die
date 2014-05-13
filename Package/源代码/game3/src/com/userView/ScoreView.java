package com.userView;

import com.publicClass.Constant;
import com.publicClass.GameActivity;
import com.publicClass.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class ScoreView extends SurfaceView implements Callback {
	GameActivity activity;// activity的引用
	Paint paint;// 画笔引用
	// 背景图片
	Bitmap bgBitmap, back;
	Bitmap number[];

	float fFrontH;
	float width = Constant.SCREEN_WIDTH, height = Constant.SCREEN_HEIGHT;
    //积分榜前五位
	int[] Top5;
	public ScoreView(GameActivity activity) {
		super(activity);
		this.activity = activity;
		// 获得焦点并设置为可触控
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		getHolder().addCallback(this);// 注册回调接口
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Top5=activity.currentGrade();
		paint = new Paint();
		paint.setAntiAlias(true);// 打开抗锯齿
		paint.setTextSize(40);
		paint.setTextAlign(Align.LEFT);
		fFrontH = (float) (Math.ceil(paint.getFontMetrics().descent
				- paint.getFontMetrics().ascent));
		initBitmap();
		repaint();
	}

	private void repaint() {
		Canvas canvas=this.getHolder().lockCanvas();
		try {
			synchronized (canvas) {
				Log.e("start paint", "");
				onDraw(canvas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (canvas != null) {
				this.getHolder().unlockCanvasAndPost(canvas);
			}
		}
	}

	private void initBitmap() {
		bgBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.jifenbackground);
		back = BitmapFactory.decodeResource(getResources(), R.drawable.goback);
		
		number = new Bitmap[] {
				BitmapFactory
						.decodeResource(getResources(), R.drawable.number1),
				BitmapFactory
						.decodeResource(getResources(), R.drawable.number2),
				BitmapFactory
						.decodeResource(getResources(), R.drawable.number3),
				BitmapFactory
						.decodeResource(getResources(), R.drawable.number4),
				BitmapFactory
						.decodeResource(getResources(), R.drawable.number5), };
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float downX = event.getX();
		float downY = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (Constant.isPointInRect(downX, downY, 0,0, back.getWidth(),
					back.getHeight()))
				activity.sendMessage(Constant.GOTO_MAIN_MENU_VIEW);
			break;
		}
		return super.onTouchEvent(event);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.e("number", "");
		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(bgBitmap, 0, 0, paint);
		canvas.drawBitmap(back,0,0, paint);
		
//		canvas.drawBitmap(title, width / 2 - title.getWidth() / 2, 30, paint);

		int h = 75 + 100;
		
		int jianju=150;
		int numW=number[0].getWidth();
		Log.e("number", ""+h);
		canvas.drawBitmap(number[0], 75, h-fFrontH+10,paint);
		canvas.drawText(Top5[0]+"", jianju + numW, h, paint);

		canvas.drawBitmap(number[1], 75, h+10 , paint);
		canvas.drawText(Top5[1]+"", jianju + numW, h + fFrontH, paint);

		canvas.drawBitmap(number[2], 75, h + fFrontH+10, paint);
		canvas.drawText(Top5[2]+"", jianju + numW, h + 2 * fFrontH, paint);

		canvas.drawBitmap(number[3], 75, h + 2 * fFrontH+10, paint);
		canvas.drawText(Top5[3]+"", jianju + numW, h + 3 * fFrontH, paint);

		canvas.drawBitmap(number[4], 75, h + 3 * fFrontH+10, paint);
		canvas.drawText(Top5[4]+"", jianju + numW, h + 4 * fFrontH, paint);
	}

}
