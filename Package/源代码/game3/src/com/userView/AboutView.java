package com.userView;
import utility.PicScaleHelper;

import com.publicClass.Constant;
import com.publicClass.GameActivity;
import com.publicClass.R;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 
 *  ��������
 *
 */
public class AboutView extends SurfaceView implements SurfaceHolder.Callback{
	GameActivity activity;//activity������
	Paint paint;//��������
	//����ͼƬ
	Bitmap bgBitmap;
	Bitmap backbutton;
	float bmpx;//����λ��	
	public AboutView(GameActivity activity) {
		super(activity);
		this.activity=activity;
		//��ý��㲢����Ϊ�ɴ���
		this.requestFocus();
        this.setFocusableInTouchMode(true);
		getHolder().addCallback(this);//ע��ص��ӿ�		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);  	
		//���Ʊ���
		canvas.drawBitmap(bgBitmap, 0, 0, paint);
		canvas.drawBitmap(backbutton, 0, 0, paint);
	}
	//���»��Ƶķ���
    @SuppressLint("WrongCall") public void repaint()
	{
		Canvas canvas=this.getHolder().lockCanvas();
		try
		{
			synchronized(canvas)
			{
				onDraw(canvas);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(canvas!=null)
			{
				this.getHolder().unlockCanvasAndPost(canvas);
			}
		}
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder){
		paint=new Paint();//��������
		paint.setAntiAlias(true);//�򿪿����	
		initBitmap();//��ʼ��λͼ��Դ				
		repaint();//���ƽ���
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
	//�ж��û��Ƿ����˷��ؼ�
		public boolean isActionOnbackButton(Bitmap button,float pointx,float pointy){
			
			return Constant.isPointInRect(pointx, pointy, 0, 0, button.getWidth(),button.getHeight());
		}
		//��ť����
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			int x = (int) event.getX();
			int y = (int) event.getY();		
	    	switch(event.getAction())
	    	{
	    	case MotionEvent.ACTION_DOWN:
	 		if(isActionOnbackButton(backbutton, x, y)){
	 			activity.sendMessage(Constant.GOTO_SYSTEM_VIEW);
	 		}
	    	}
			return true;
		}
	//����ͼƬ�ķ���
	public void initBitmap(){
		backbutton=BitmapFactory.decodeResource(this.getResources(), R.drawable.goback);
		bgBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.aboutbackground);
		bgBitmap=PicScaleHelper.FullScreenScale(bgBitmap, Constant.wRatio, Constant.hRatio);

		
	}	
}
