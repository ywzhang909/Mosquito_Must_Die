package com.userView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import utility.PicScaleHelper;

import com.publicClass.Constant;
import com.publicClass.GameActivity;

import com.publicClass.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.style.LineHeightSpan.WithDensity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

/**
 * 
 * @author ɽ����ѧ�Ա��������ء����������ں������
 * 
 */
public class MainMenuView extends SurfaceView implements View.OnTouchListener,
		SurfaceHolder.Callback {
	public Paint paint;// ����

	// �û����¶�����X,Y����
	int downX = 0;
	int downY = 0;
	// �û��뿪��Ļ��X,Y����
	int upX = 0;
	int upY = 0;
	// ��Ļ�ߴ�
	int width;
	int height;
	// �ж���ת
	int x;
	int y;
	// ת���־λ
	int front = 0;

	// ͼ����Ϣ
	int arrowAgr = 0;// ��ͷ�Ƕ�
	int smokeLocX;
	int smokeLocY;// ����λ��
	int smokeSizeX = 0;
	int smokeSizeY = 0;// �����С

	Bitmap arrow;
	Bitmap smoke;
	Bitmap background;
	// �ٶ�
	int rotateSpeed = 4;
	int moveSpeed = 10;
	int zoomSpeed = 15;//px

	GameActivity activity;
	// DrawThread thread = new DrawThread();
	SurfaceHolder holder;

	public MainMenuView(GameActivity activity) {
		super(activity);
		this.activity = activity;
		// TODO Auto-generated constructor stub
		Log.e("ontouch", "���˵�");
		this.setOnTouchListener(this);
		background = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.mainmenuback);
		background = PicScaleHelper.FullScreenScale(background, Constant.wRatio, Constant.hRatio);
		arrow = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.arrow);
		arrow = rotate(arrow, 90);
		arrow = zoom(arrow,100,100);
		smoke = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.smoke);
		holder = this.getHolder();
		holder.addCallback(this);
		width = Constant.SCREEN_WIDTH;
		height = Constant.SCREEN_HEIGHT;
	}

	public boolean onTouch(View v, MotionEvent event) {
		// �ж��û�����

		switch (event.getAction()) {
		// ��ȡ�û����µ�����

		case MotionEvent.ACTION_DOWN:
			downX = (int) event.getX();
			downY = (int) event.getY();

			break;
		// ��ȡ�û�̧�������
		case MotionEvent.ACTION_UP:
			upX = (int) event.getX();
			upY = (int) event.getY();
			break;
		}

		if (upX != 0 && upY != 0) {
			// ǰ������if�������ж��Ƿ�����Ч����
			if ((downX > (width / 2 - 40)) && (downX < (width / 2 + 40))) {
				if ((downY > (height / 2 - 40)) && (downY < (height / 2 + 40))) {
					x = upX - Constant.SCREEN_WIDTH / 2;
					y = upY - Constant.SCREEN_HEIGHT / 2;
					whereTogo(x, y);
					return true;
				}
			}
		}
		return true;

	}

	// �жϵķ���
	private void whereTogo(int x, int y) {
		if (x < 0) {
			if (y == 0 || y > 0) {
				front = 3;
				// ��������
				dodraw();
				activity.sendMessage(Constant.GOTO_SCORE_VIEW);

			}
			if (y < 0) {
				front = 2;
				// �ڶ�����
				dodraw();
				activity.sendMessage(Constant.GOTO_SYSTEM_VIEW);

			}
		}
		// ������ұ�
		if (x >= 0) {
			if (y == 0 || y > 0) {
				front = 4;
				// ��������
				dodraw();
				activity.sendMessage(Constant.GOTO_EXIT_VIEW);

			}
			if (y < 0) {
				front = 1;
				// ��һ����
				dodraw();
				activity.sendMessage(Constant.GOTO_START_GAME_VIEW);

			}
		}
		upX = 0;
		upY = 0;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Canvas canvas = holder.lockCanvas();
		onDrawArrow(canvas);
		holder.unlockCanvasAndPost(canvas);
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

/**
 * ��������ָ��
 * @param canvas
 */
	public void onDrawArrow(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(background,0,0,paint);
		canvas.drawBitmap(rotate(arrow, -arrowAgr),
				width / 2 - arrow.getWidth() / 2,
				height / 2 - arrow.getHeight() / 2, null);
	}
/**
 * ��������
 * @param canvas
 */
	public void onDrawSmoke(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(background,0,0,paint);
		canvas.drawBitmap(rotate(arrow, -arrowAgr),
				width / 2 - arrow.getWidth() / 2,
				height / 2 - arrow.getHeight() / 2, null);
		canvas.drawBitmap(zoom(smoke, smokeSizeX, smokeSizeY), smokeLocX,
				smokeLocY, null);
	}

	/**
	 * ��תͼ��
	 * 
	 * @param b
	 * @param degree
	 */
	public static Bitmap rotate(Bitmap b, int degree) {
		if (degree == 0 || b == null)
			return b;
		Matrix m = new Matrix();
		m.setRotate(degree, b.getWidth() / 2, b.getHeight() / 2);
		return Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m,
				true);
	}

	/**
	 * ����ͼ��
	 * 
	 * @param bitmap
	 * @param width
	 * @param height
	 */
	public static Bitmap zoom(Bitmap bitmap, int width, int height) {
		if (bitmap == null) {
			return null;
		}
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / w);
		float scaleHeight = ((float) height / h);
		matrix.postScale(scaleWidth, scaleHeight);
		return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
	}
/**
 * ���Ʒ���
 */
	public void dodraw() {
		// rotate process
		boolean flag = true;// trueʱΪ��ת��falseʱΪ�����ƶ��Ŵ�

		while (flag) {
			Canvas canvas = null;
			try {
				switch (front) {
				case 1: {
					if (arrowAgr < 135)
						arrowAgr += rotateSpeed;
					else
						flag = false;
					break;
				}
				case 2: {
					if (arrowAgr > -135)
						arrowAgr -= rotateSpeed;
					else
						flag = false;
					break;
				}
				case 3: {
					if (arrowAgr > -45)
						arrowAgr -= rotateSpeed;
					else
						flag = false;
					break;
				}
				case 4: {
					if (arrowAgr < 45)
						arrowAgr += rotateSpeed;
					else
						flag = false;
					break;
				}
				}
				canvas = holder.lockCanvas(); // �������� ����ȡcanvas
				onDrawArrow(canvas);// ����onDraw ��Ⱦ����Ļ
				holder.unlockCanvasAndPost(canvas);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// move process
		smokeSizeX = 50;
		smokeSizeY = 50;
		smokeLocX = width / 2 - smokeSizeX / 2;
		smokeLocY = height / 2 - smokeSizeY / 2;

		boolean flag1 = true;
		while (flag1) {
			Canvas canvas = null;
			try {
				switch (front) {
				case 1: {
					if (smokeLocX + smokeSizeX < width) {
						
						smokeLocX += moveSpeed * width / height;
						smokeLocY -= moveSpeed;
					} else
						flag1 = false;
					break;
				}
				case 2: {
					if (smokeLocX > 0) {
						smokeLocX -= moveSpeed * width / height;
						smokeLocY -= moveSpeed;
					} else
						flag1 = false;
					break;
				}
				case 3: {
					if (smokeLocX > 0) {
						smokeLocX -= moveSpeed * width / height;
						smokeLocY += moveSpeed;
					} else
						flag1 = false;
					break;
				}
				case 4: {
					if (smokeLocX + smokeSizeX < width) {
						smokeLocX += moveSpeed * width / height;
						smokeLocY += moveSpeed;
					} else
						flag1 = false;
					break;
				}
				}
				canvas = holder.lockCanvas(); // �������� ����ȡcanvas
				onDrawSmoke(canvas);// ����onDraw ��Ⱦ����Ļ
				holder.unlockCanvasAndPost(canvas);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// zoom process
		// boolean flag2=true;
		while (smokeSizeX < width && smokeSizeY < height) {
			Canvas canvas = null;
			try {
				switch (front) {
				case 1: {
					smokeLocX -= zoomSpeed * width / height;
					smokeSizeX += zoomSpeed * width / height;
					smokeSizeY += zoomSpeed;
					break;
				}
				case 2: {
					smokeSizeX += zoomSpeed * width / height;
					smokeSizeY += zoomSpeed;
					break;
				}
				case 3: {
					smokeLocY -= zoomSpeed;
					smokeSizeX += zoomSpeed * width / height;
					smokeSizeY += zoomSpeed;
					break;
				}
				case 4: {
					smokeLocX -= zoomSpeed * width / height;
					smokeLocY -= zoomSpeed;
					smokeSizeX += zoomSpeed * width / height;
					smokeSizeY += zoomSpeed;
					break;
				}
				}
				canvas = holder.lockCanvas(); // �������� ����ȡcanvas
				onDrawSmoke(canvas);// ����onDraw ��Ⱦ����Ļ
				holder.unlockCanvasAndPost(canvas);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
