package com.userView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.publicClass.Constant;
import com.publicClass.GameActivity;
import com.publicClass.R;

public class ChooseView extends SurfaceView implements SurfaceHolder.Callback,
		Runnable {
	GameActivity activity;// activity的引用
	private int Width, Height;
	private int Score[];
	private Bitmap levelBackground[];
	private Point location[];
	private Bitmap background, btn_back;
	private Paint paint;
	private boolean isRunnig;

	public ChooseView(GameActivity activity) {
		super(activity);
		this.activity = activity;
		// 获得焦点并设置为可触控
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		getHolder().addCallback(this);// 注册回调接口
		Width = Constant.SCREEN_WIDTH;
		Height = Constant.SCREEN_HEIGHT;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(background, 0, 0, paint);
		canvas.drawBitmap(btn_back, 0, 0, paint);
		for (int i = 0; i < tool.getUnlockedLevel(); i++) {
			canvas.drawBitmap(levelBackground[i], location[i].x, location[i].y,
					paint);
			canvas.drawText(Score[i] + "", location[i].x, location[i].y
					+ levelBackground[0].getHeight(), paint);
		}
		for (int i = tool.getUnlockedLevel(); i < tool.getLevel(); i++) {
			// TODO change pictures which present the unlocked ones
			canvas.drawBitmap(levelBackground[i], location[i].x, location[i].y,
					paint);
			canvas.drawText(Score[i] + "", location[i].x, location[i].y
					+ levelBackground[0].getHeight(), paint);
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
		repaint();// 绘制界面
		new Thread(this).start();
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
		float downX = 0, downY = 0, dX, dY;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			downX = event.getX();
			downY = event.getY();
			isRunnig = true;
			break;
		}
		case MotionEvent.ACTION_MOVE: {
			dX = event.getX() - downX;
			for (int i = 0; i < tool.getLevel(); i++) {
				location[i].x += dX;
			}
			break;
		}
		case MotionEvent.ACTION_UP: {
			isRunnig = false;
			dX = event.getX() - downX;
			dY = event.getY() - downY;
			if (dX < 5 && dY < 5) {
				int backWidth = btn_back.getWidth();
				if (downX < backWidth && downY < btn_back.getHeight()) {
					Log.i("Touch Event", "back");
					break;
				} else if (downX > backWidth + 10 && downY > 10
						&& downY < 10 + levelBackground[0].getHeight()) {
					int celection = (int) ((downX - location[0].x) / (10 + levelBackground[0]
							.getWidth()));
					switch (celection) {
					case 1:
						activity.sendMessage(Constant.GOTO_1);
						break;
					case 2:
						activity.sendMessage(Constant.GOTO_2);
						break;
					case 3:
						activity.sendMessage(Constant.GOTO_3);
						break;
					case 4:
						activity.sendMessage(Constant.GOTO_4);
						break;
					case 5:
						activity.sendMessage(Constant.GOTO_5);
						break;
					}
				}
			}
			break;
		}
		default: {
			isRunnig = false;
		}
		}
		return true;
	}

	private Bitmap zoom(Bitmap bitmap, float width, float height) {
		if (bitmap == null) {
			return null;
		}
		int w = Width;
		int h = Height;
		Matrix matrix = new Matrix();
		float scaleWidth = (width / w);
		float scaleHeight = (height / h);
		matrix.postScale(scaleWidth, scaleHeight);
		return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
	}

	// 加载图片的方法
	public void initBitmap() {
		int gap = 10;
		float pic_height = Height / 2;
		float pic_width = pic_height * 4 / 3;
		levelBackground[0] = zoom(BitmapFactory.decodeResource(getResources(),
				R.drawable.playground1), pic_width, pic_height);
		levelBackground[1] = BitmapFactory.decodeResource(getResources(),
				R.drawable.playground2);
		levelBackground[2] = BitmapFactory.decodeResource(getResources(),
				R.drawable.playground3);
		levelBackground[3] = BitmapFactory.decodeResource(getResources(),
				R.drawable.playground4);
		levelBackground[4] = BitmapFactory.decodeResource(getResources(),
				R.drawable.playground5);

		background = BitmapFactory.decodeResource(getResources(),
				R.drawable.viewbackground);
		btn_back = BitmapFactory.decodeResource(getResources(),
				R.drawable.previous);
		// set the location of the level picture
		int temp = gap + btn_back.getWidth();
		for (int i = 0; i < tool.getLevel(); i++) {
			location[i] = new Point(temp, gap);
			temp += (gap + pic_width);
		}
		// TODO loading the scores
		for (int i = 0; i < tool.getLevel(); i++) {
			Score[i] = 0;
		}
	}

	@Override
	public void run() {
		while (isRunnig) {
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				Log.e("Sleep Error", "");
			}
		}

	}
}
