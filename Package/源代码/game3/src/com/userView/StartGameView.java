package com.userView;
import utility.PicScaleHelper;

import com.publicClass.Constant;
import com.publicClass.GameActivity;
import com.publicClass.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 
 *  帮助界面
 *
 */
public class StartGameView extends SurfaceView implements SurfaceHolder.Callback{
	GameActivity activity;//activity的引用
	Paint paint;//画笔引用
	//背景图片
	Bitmap bgBitmap;
	//绘制单人模式与双人模式按钮
	Bitmap solobutton;
	Bitmap doublebutton;
	Bitmap backbutton;
	public StartGameView(GameActivity activity) {
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
//		canvas.drawBitmap(solobutton,( Constant.SCREEN_WIDTH/2-solobutton.getWidth())*2/3, Constant.SCREEN_HEIGHT/2, paint);
//		canvas.drawBitmap(doublebutton,Constant.SCREEN_WIDTH/2+doublebutton.getWidth()/2, Constant.SCREEN_HEIGHT/2, paint);
		canvas.drawBitmap(backbutton, 0,0, paint);
	}
	//重新绘制的方法
    public void repaint()
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
	//加载图片的方法
	public void initBitmap(){
//		solobutton=BitmapFactory.decodeResource(this.getResources(), R.drawable.buttonsolo);
//		doublebutton=BitmapFactory.decodeResource(this.getResources(), R.drawable.buttondouble);
		bgBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.choosestyle);
		backbutton=BitmapFactory.decodeResource(this.getResources(), R.drawable.goback);	
		bgBitmap=PicScaleHelper.FullScreenScale(bgBitmap, Constant.wRatio, Constant.hRatio);
		backbutton=PicScaleHelper.scaleToFit(backbutton, Constant.ssr.ratio);
//		solobutton=PicScaleHelper.scaleToFit(solobutton, Constant.ssr.ratio);
//		doublebutton=PicScaleHelper.scaleToFit(doublebutton, Constant.ssr.ratio);

	}	
	//判断用户是否点击了返回键
	public boolean isActionOnbackButton(Bitmap button,float pointx,float pointy){
		
		return Constant.isPointInRect(pointx, pointy, 0, 0, button.getWidth(),button.getHeight());
	}
	//判断用户是否点击了单人模式
		public boolean isActionOnSingleButton(Bitmap button,float pointx,float pointy){
			
			return Constant.isPointInRect(pointx, pointy, Constant.SCREEN_WIDTH/4,Constant.SCREEN_HEIGHT/2, Constant.SCREEN_WIDTH/4,Constant.SCREEN_WIDTH/4);
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
 			activity.sendMessage(Constant.GOTO_MAIN_MENU_VIEW);
 		}
 		//下面是选择单人模式和多人模式的监听
	else if(isActionOnSingleButton(solobutton,x,y)){
			activity.sendMessage(Constant.GOTO_CHOOSE_VIEW);
		}
//选择了多人模式
	else if(Constant.isPointInRect(x, y, Constant.SCREEN_WIDTH/2, Constant.SCREEN_HEIGHT/2, Constant.SCREEN_WIDTH/4,Constant.SCREEN_WIDTH/4)){
		activity.sendMessage(Constant.GOTO_COOP_VIEW);
	}
 		}
		return true;
		
	}
}
