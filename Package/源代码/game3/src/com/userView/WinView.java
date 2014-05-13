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
 *  胜利界面
 *
 */
public class WinView extends SurfaceView implements SurfaceHolder.Callback{
	GameActivity activity;//activity的引用
	Paint paint;//画笔引用
	
	Bitmap bgBitmap;//背景图片
	
	public WinView(GameActivity activity) {
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
	//判断用户是否点击了进入下一关按钮
		public boolean isActionOnEnterNextButton(float pointx,float pointy){
			
			return Constant.isPointInRect(pointx, pointy, Constant.SCREEN_WIDTH/8,Constant.SCREEN_HEIGHT/4, Constant.SCREEN_WIDTH/5,Constant.SCREEN_HEIGHT/8);
		}
	//判断用户是否点击了返回主菜单按钮
		public boolean isActionOnReturnManuButton(float pointx,float pointy){
					
			return Constant.isPointInRect(pointx, pointy,Constant.SCREEN_WIDTH/8,Constant.SCREEN_HEIGHT/2-Constant.SCREEN_HEIGHT/16, Constant.SCREEN_WIDTH/5,Constant.SCREEN_HEIGHT/8);
		}
	//判断用户是否点击了重玩按钮
		public boolean isActionOnReplayButton(float pointx,float pointy){
					
			return Constant.isPointInRect(pointx, pointy,Constant.SCREEN_WIDTH/8,Constant.SCREEN_HEIGHT/2+Constant.SCREEN_HEIGHT/8, Constant.SCREEN_WIDTH/5,Constant.SCREEN_HEIGHT/8);
		}
		//按钮监听
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			int x = (int) event.getX();
			int y = (int) event.getY();		
	    	switch(event.getAction())
	    	{
	    	case MotionEvent.ACTION_DOWN:
	 		if(isActionOnEnterNextButton( x, y)){
	 			if(activity.achievement==2)
		 			activity.sendMessage(Constant.GOTO_2);
	 			if(activity.achievement==3)
		 			activity.sendMessage(Constant.GOTO_3);
	 			if(activity.achievement==4)
		 			activity.sendMessage(Constant.GOTO_4);
	 			if(activity.achievement==5)
		 			activity.sendMessage(Constant.GOTO_5);
	 			if(activity.achievement==6)
		 			activity.sendMessage(Constant.GOTO_6);
//	 			if(activity.achievement==7)
//		 			activity.sendMessage(Constant.GOTO_7);
//	 			if(activity.achievement==8)
//		 			activity.sendMessage(Constant.GOTO_8);
//	 			if(activity.achievement==9)
//		 			activity.sendMessage(Constant.GOTO_9);
	 		}
	 		if(isActionOnReturnManuButton( x, y)){
	 			activity.sendMessage(Constant.GOTO_MAIN_MENU_VIEW);
	 		}
	 		if(isActionOnReplayButton( x, y)){
	 			if(activity.achievement==2){
		 			activity.sendMessage(Constant. GOTO_1);
		 			activity.achievement--;
	 			}
	 			if(activity.achievement==3){
		 			activity.sendMessage(Constant.GOTO_2);
		 			activity.achievement--;
	 			}
	 			if(activity.achievement==4){
		 			activity.sendMessage(Constant.GOTO_3);
		 			activity.achievement--;

	 			}	 			
	 			if(activity.achievement==5){
		 			activity.sendMessage(Constant.GOTO_4);
		 			activity.achievement--;

	 			}
	 			if(activity.achievement==6){
		 			activity.sendMessage(Constant.GOTO_5);
		 			activity.achievement--;

	 			}
	 			
	 		}
	    	}
	    	return true;
		}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
	//加载图片的方法
	public void initBitmap(){
		
		bgBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.winbackground);
	    bgBitmap=PicScaleHelper.FullScreenScale(bgBitmap, Constant.wRatio, Constant.hRatio);
		
	}	
}
