package com.userView;


import com.publicClass.Constant;
import com.publicClass.GameActivity;
import com.publicClass.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 
 *  帮助界面
 *
 */
public class testView extends SurfaceView implements SurfaceHolder.Callback{
	GameActivity activity;//activity的引用
	Paint paint;//画笔引用
	private Bitmap[] frog;
	int bmpx;
	int i=5;
	int j=8;
	private int step=0;
	public testView(GameActivity activity) {
		
		super(activity);
		Log.i("gameView", "enter");
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
		//青蛙初始化位置，面朝上
		if(step==0){	   
		int onX =j*Constant.SCREEN_WIDTH/20;
		int onY =i*Constant.SCREEN_HEIGHT/12;
			canvas.drawBitmap(frog[0], onX, onY, paint);
		}
		//向上走一步
		if(step==1){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY = (i-1)*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[0], onX, onY, paint);
			}
		//转身向下
		if(step==2){
			int onX = j*Constant.SCREEN_WIDTH/20;
			int onY = (i-1)*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[1], onX, onY, paint);
				
			}
		//向下走一步
		if(step==3){
			int onX = j*Constant.SCREEN_WIDTH/20;
			int onY = i*Constant.SCREEN_HEIGHT/12;
		
				canvas.drawBitmap(frog[1], onX, onY, paint);
			}
		//向下走一步
		if(step==4){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY =(i+1)*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[1], onX, onY, paint);
			}
		//转身向上
		if(step==5){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY =(i+1)*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[0], onX, onY, paint);
			}
		//向上走一步
		if(step==6){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[0], onX, onY, paint);
			}
		//转身向左
		if(step==7){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[2], onX, onY, paint);
			}
		//向左走一步
		if(step==8){
			int onX =(j-1)*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[2], onX, onY, paint);
			}
		//转身向右
		if(step==9){
			int onX =(j-1)*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[3], onX, onY, paint);
			}
		//向右走一步
		if(step==10){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[3], onX, onY, paint);
			}
		//向右走一步
		if(step==11){
			int onX =(j+1)*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[3], onX, onY, paint);
			}
		//转身向左
		if(step==12){
			int onX =(j+1)*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[2], onX, onY, paint);
			}
		//向右走一步
		if(step==13){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[3], onX, onY, paint);
			}
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
				step++;
				Thread.sleep(500);
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
		bmpx=200;		
		initBitmap();
		repaint();//绘制界面
	}
		//按钮监听
		
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
	//加载图片的方法
	public void initBitmap(){

		frog[0]=BitmapFactory.decodeResource(this.getResources(), R.drawable.frog1);
		frog[1]=BitmapFactory.decodeResource(this.getResources(), R.drawable.frog2);
		frog[2]=BitmapFactory.decodeResource(this.getResources(), R.drawable.frog3);
		frog[3]=BitmapFactory.decodeResource(this.getResources(), R.drawable.frog4);
	   	
	}	
}
