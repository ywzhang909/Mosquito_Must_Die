package com.userView;
import utility.PicScaleHelper;

import com.publicClass.Constant;
import com.publicClass.GameActivity;
import com.publicClass.R;

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
public class ExitView extends SurfaceView implements SurfaceHolder.Callback{
	GameActivity activity;//activity的引用
	Paint paint;//画笔引用
	//背景图片
	Bitmap bgBitmap;
	//绘制Yes与No按钮
//	Bitmap yesbutton;
//	Bitmap nobutton;
//	Bitmap bmp;//文字的图片
	float bmpx;//文字位置	
	public ExitView(GameActivity activity) {
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
//		canvas.drawBitmap(yesbutton,( Constant.SCREEN_WIDTH/2-yesbutton.getWidth())/2, Constant.SCREEN_HEIGHT/2, paint);
//		canvas.drawBitmap(nobutton,Constant.SCREEN_WIDTH/2+( Constant.SCREEN_WIDTH/2-yesbutton.getWidth())/2, Constant.SCREEN_HEIGHT/2, paint);
//		canvas.drawBitmap(bmp, bmpx+Constant.X_OFFSET, Constant.SCREEN_HEIGHT/4, paint);
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
		//初始化图片的位置
//		bmpx=(Constant.SCREEN_WIDTH-bmp.getWidth())/2;
		
		repaint();//绘制界面
	}
	//判断用户是否点击了YES键
		public boolean isActionOnYesButton(float pointx,float pointy){
			
			return Constant.isPointInRect(pointx, pointy,Constant.SCREEN_WIDTH/4,Constant.SCREEN_HEIGHT/2, Constant.SCREEN_WIDTH/4,Constant.SCREEN_WIDTH/4);
		}
		//判断用户是否点击了NO键
				public boolean isActionOnNoButton(float pointx,float pointy){
					
					return Constant.isPointInRect(pointx, pointy,Constant.SCREEN_WIDTH/2,Constant.SCREEN_HEIGHT/2, Constant.SCREEN_WIDTH/4,Constant.SCREEN_WIDTH/4);
				}
		//按钮监听
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			int x = (int) event.getX();
			int y = (int) event.getY();		
	    	switch(event.getAction())
	    	{
	    	case MotionEvent.ACTION_DOWN:
	 		if(isActionOnYesButton( x, y)){
	 			activity.sendMessage(Constant.EXIT);
	 		}
	 		if(isActionOnNoButton( x, y)){
	 			activity.sendMessage(Constant.GOTO_MAIN_MENU_VIEW);
	 		}
	    	}
	    	return true;
		}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
	//加载图片的方法
	public void initBitmap(){
//		yesbutton=BitmapFactory.decodeResource(this.getResources(), R.drawable.yesbutton);
//		nobutton=BitmapFactory.decodeResource(this.getResources(), R.drawable.nobutton);
		bgBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.existbackground);
		bgBitmap=PicScaleHelper.FullScreenScale(bgBitmap, Constant.wRatio, Constant.hRatio);
//		bmp=PicScaleHelper.scaleToFit(bmp, Constant.ssr.ratio);
		
	}	
}
