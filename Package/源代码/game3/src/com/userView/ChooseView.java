package com.userView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.publicClass.Constant;
import com.publicClass.GameActivity;
import com.publicClass.R;

public class ChooseView extends SurfaceView implements SurfaceHolder.Callback {
	GameActivity activity;// activity的引用
	Paint paint;// 画笔引用
	// 背景图片
	Bitmap bgBitmap;
	Bitmap backbutton;
	Bitmap[] numberBitmaps;
	int[][] loc;
	int ableChoose = 1;

	public ChooseView(GameActivity activity) {
		super(activity);
		this.activity = activity;
		// 获得焦点并设置为可触控
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		getHolder().addCallback(this);// 注册回调接口+
	}

	private void locate() {
		int w = Constant.SCREEN_WIDTH / 4;
		int h = Constant.SCREEN_HEIGHT / 3;
		loc = new int[][] {
				{ w - numberBitmaps[0].getWidth() / 2,
						h - numberBitmaps[0].getHeight() },
				{ 2 * w - numberBitmaps[1].getWidth() / 2,
						h - numberBitmaps[1].getHeight() },
				{ 3 * w - numberBitmaps[2].getWidth() / 2,
						h - numberBitmaps[2].getHeight() },
				{ w - numberBitmaps[3].getWidth() / 2,
						3 * h / 2 - numberBitmaps[3].getHeight() / 2 },
				{ 2 * w - numberBitmaps[4].getWidth() / 2,
						3 * h / 2 - numberBitmaps[4].getHeight() / 2 },
				{ 3 * w - numberBitmaps[5].getWidth() / 2,
						3 * h / 2 - numberBitmaps[5].getHeight() / 2 },
//				{ w - numberBitmaps[6].getWidth() / 2, 2 * h },
//				{ 2 * w - numberBitmaps[7].getWidth() / 2, 2 * h },
//				{ 3 * w - numberBitmaps[8].getWidth() / 2, 2 * h }, 
				};
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 绘制背景
		canvas.drawBitmap(bgBitmap, 0, 0, paint);
		canvas.drawBitmap(backbutton, 0, 0, paint);
		ableChoose=activity.getAchievement();
//		Log.e("achievement",activity.getAchievement()+"");
		Log.e("ableChoose", ableChoose+"");
		for (int i = 0; i < ableChoose; i++) {
			canvas.drawBitmap(numberBitmaps[i], loc[i][0], loc[i][1], paint);
		}
	}

	// 重新绘制的方法
	public void repaint() {
		Canvas canvas = this.getHolder().lockCanvas();
		try {
			synchronized (canvas) {
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

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		paint = new Paint();// 创建画笔
		paint.setColor(Color.GRAY);
		paint.setAntiAlias(true);// 打开抗锯齿
		initBitmap();// 初始化位图资源
		locate();
		repaint();// 绘制界面
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	// 判断用户是否点击了返回键
	public boolean isActionOnbackButton(Bitmap button, float pointx,
			float pointy) {

		return Constant.isPointInRect(pointx, pointy, 0, 0, button.getWidth(),
				button.getHeight());
	}

	// 按钮监听
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (isActionOnbackButton(backbutton, x, y)) {
				activity.sendMessage(Constant.GOTO_START_GAME_VIEW);
			}
			if (Constant.isPointInRect(x, y, loc[0][0], loc[0][1], loc[0][0]
					+ numberBitmaps[0].getWidth(),
					loc[0][1] + numberBitmaps[0].getHeight()))
				activity.sendMessage(Constant.GOTO_1);
			if (Constant.isPointInRect(x, y, loc[1][0], loc[1][1], loc[1][0]
					+ numberBitmaps[1].getWidth(),
					loc[1][1] + numberBitmaps[1].getHeight())
					&& ableChoose >= 2)
				activity.sendMessage(Constant.GOTO_2);
			if (Constant.isPointInRect(x, y, loc[2][0], loc[2][1], loc[2][0]
					+ numberBitmaps[2].getWidth(),
					loc[2][1] + numberBitmaps[2].getHeight())
					&& ableChoose >= 3)
				activity.sendMessage(Constant.GOTO_3);
			if (Constant.isPointInRect(x, y, loc[3][0], loc[3][1], loc[3][0]
					+ numberBitmaps[3].getWidth(),
					loc[3][1] + numberBitmaps[3].getHeight())
					&& ableChoose >= 4)
				activity.sendMessage(Constant.GOTO_4);
			if (Constant.isPointInRect(x, y, loc[4][0], loc[4][1], loc[4][0]
					+ numberBitmaps[4].getWidth(),
					loc[4][1] + numberBitmaps[4].getHeight())
					&& ableChoose >= 5)
				activity.sendMessage(Constant.GOTO_5);
			if (Constant.isPointInRect(x, y, loc[5][0], loc[5][1], loc[5][0]
					+ numberBitmaps[5].getWidth(),
					loc[5][1] + numberBitmaps[5].getHeight())
					&& ableChoose >= 6)
				activity.sendMessage(Constant.GOTO_6);
//			if (Constant.isPointInRect(x, y, loc[6][0], loc[6][1], loc[6][0]
//					+ numberBitmaps[6].getWidth(),
//					loc[6][1] + numberBitmaps[6].getHeight())
//					&& ableChoose >= 7)
//				activity.sendMessage(Constant.GOTO_7);
//			if (Constant.isPointInRect(x, y, loc[7][0], loc[7][1], loc[7][0]
//					+ numberBitmaps[7].getWidth(),
//					loc[7][1] + numberBitmaps[7].getHeight())
//					&& ableChoose >= 8)
//				activity.sendMessage(Constant.GOTO_8);

		}
		return true;
	}

	// 加载图片的方法
	public void initBitmap() {
		bgBitmap = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.viewbackground);
		backbutton = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.goback);
		numberBitmaps = new Bitmap[] {
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number1),
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number2),
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number3),
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number4),
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number5),
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number6),
				
		};

	}
}
