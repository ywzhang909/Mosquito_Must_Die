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
 *  帮助界面
 *
 */
public class AboutView extends SurfaceView implements SurfaceHolder.Callback{
	GameActivity activity;//activity的引用
	Paint paint;//画笔引用
	//背景图片
	Bitmap bgBitmap;
	Bitmap backbutton;
	float bmpx;//文字位置	
	public AboutView(GameActivity activity) {
		super(activity);
		this.activity=activity;
		//获得焦点并设置为可触控
		this.requestFocus();
        this.setFocusableInTouchMode(true);
		getHolder().addCallback(this);//注册回调接口		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);  	
		//绘制背景
		canvas.drawBitmap(bgBitmap, 0, 0, paint);
		canvas.drawBitmap(backbutton, 0, 0, paint);
	}
	//重新绘制的方法
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
		paint=new Paint();//创建画笔
		paint.setAntiAlias(true);//打开抗锯齿	
		initBitmap();//初始化位图资源				
		repaint();//绘制界面
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
	//判断用户是否点击了返回键
		public boolean isActionOnbackButton(Bitmap button,float pointx,float pointy){
			
			return Constant.isPointInRect(pointx, pointy, 0, 0, button.getWidth(),button.getHeight());
		}
		//按钮监听
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
	//加载图片的方法
	public void initBitmap(){
		backbutton=BitmapFactory.decodeResource(this.getResources(), R.drawable.goback);
		bgBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.aboutbackground);
		bgBitmap=PicScaleHelper.FullScreenScale(bgBitmap, Constant.wRatio, Constant.hRatio);

		
	}	
}
