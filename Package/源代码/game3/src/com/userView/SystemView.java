package com.userView;
import utility.PicScaleHelper;

import com.publicClass.Constant;
import com.publicClass.GameActivity;
import com.publicClass.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
/**
 * 
 * 系统设置界面（音乐、音效、帮助、关于）
 *
 */
public class SystemView extends SurfaceView implements SurfaceHolder.Callback{
	GameActivity activity;//activity的引用
	
	Paint paint;//画笔引用
	//线程引用
	DrawThread drawThread;//绘制线程引用	
	//声音开关虚拟按钮图片
	Bitmap onBitmap;
	Bitmap offBitmap;
	//文字的图片	
	private Bitmap yinyueOnBitmap;
	private Bitmap yinyueOffBitmap;
	private Bitmap yinxiaoOnBitmap;
	private Bitmap yinxiaoOffBitmap;
	//主菜单上虚拟按钮对象引用
	SoundSwitchButton yinyueBtn;
	SoundSwitchButton yinxiaoBtn;	
	//帮助、关于按钮图片
	private Bitmap helpbuttonBitmap;
	private Bitmap aboutbuttonitmap;
	
	private Bitmap backButton;
	
	//背景图片
	Bitmap bgBitmap;
	private HelpView helpView;
	private AboutView aboutView;
	
	
	public SystemView(GameActivity activity) {
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
		canvas.drawColor(Color.GRAY);
		canvas.drawBitmap(bgBitmap, 0, 0, paint);
		//绘制虚拟按钮
		yinyueBtn.drawSelf(canvas, paint);
		yinxiaoBtn.drawSelf(canvas, paint);
		canvas.drawBitmap(helpbuttonBitmap, Constant.SCREEN_WIDTH/2-helpbuttonBitmap.getWidth()/2, Constant.SCREEN_HEIGHT/5*3, paint);//绘制文字
		canvas.drawBitmap(aboutbuttonitmap, Constant.SCREEN_WIDTH/2-helpbuttonBitmap.getWidth()/2,  Constant.SCREEN_HEIGHT/4*3, paint);//绘制虚拟按钮
		canvas.drawBitmap(backButton, 0,  0, paint);//绘制虚拟按钮

	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();		
    	switch(event.getAction())
    	{
    	case MotionEvent.ACTION_DOWN:
    		//点击在音乐按钮上
    		if(yinyueBtn.isActionOnButtonOn(x, y))
    		{
    			yinyueBtn.switchOn();
    			activity.setBackGroundMusicOn(true);
    		}
    		else if(yinyueBtn.isActionOnButtonOff(x, y))
    		{
    			yinyueBtn.switchOff();
    			activity.setBackGroundMusicOn(false);
    		}
    		//点击在音效按钮上
    		else if(yinxiaoBtn.isActionOnButtonOn(x, y))
    		{
    			yinxiaoBtn.switchOn();
    			activity.setSoundOn(true);
    		}
    		else if(yinxiaoBtn.isActionOnButtonOff(x, y))
    		{
    			yinxiaoBtn.switchOff();
    			activity.setSoundOn(false);
    		}    	
    		else if(isActionOnhelpButton(helpbuttonBitmap,x, y))
    		{		
    			activity.sendMessage(Constant.GOTO_HELP_VIEW);
    		}    	
    		else if(isActionOnaboutButton(aboutbuttonitmap,x, y))
    		{		
    			activity.sendMessage(Constant.GOTO_ABOUT_VIEW);    		
    			} 
    		else if(isActionOnBackButton(backButton,x, y))
    		{		
    			activity.sendMessage(Constant.GOTO_MAIN_MENU_VIEW);    		
    			}    
    		break;	
    	}
    	
		return true;
	}
	
	public boolean isActionOnhelpButton(Bitmap button,float pointx,float pointy){
	
		return Constant.isPointInRect(pointx, pointy, Constant.SCREEN_WIDTH/2-helpbuttonBitmap.getWidth()/2, Constant.SCREEN_HEIGHT/5*3, button.getWidth(),button.getHeight());
	}
	
	public boolean isActionOnaboutButton(Bitmap button,float pointx,float pointy){

		return Constant.isPointInRect(pointx, pointy, Constant.SCREEN_WIDTH/2-helpbuttonBitmap.getWidth()/2,Constant.SCREEN_HEIGHT/4*3,button.getWidth(),button.getHeight());
	}
	
	public boolean isActionOnBackButton(Bitmap button,float pointx,float pointy){

		return Constant.isPointInRect(pointx, pointy, 0,0,button.getWidth(),button.getHeight());
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder){
		paint=new Paint();//创建画笔
		paint.setAntiAlias(true);//打开抗锯齿	
		createAllThreads();//创建所有线程
		initBitmap();//初始化位图资源	
		//创建虚拟按钮对象
		int btnX=(Constant.SCREEN_WIDTH-onBitmap.getWidth()-yinyueOnBitmap.getWidth())/2;
		yinyueBtn=new SoundSwitchButton(yinyueOnBitmap,yinyueOffBitmap,onBitmap,offBitmap,btnX,Constant.SOUND_BTN_Y1,activity.isBackGroundMusicOn());
		yinxiaoBtn=new SoundSwitchButton(yinxiaoOnBitmap,yinxiaoOffBitmap,onBitmap,offBitmap,btnX,Constant.SOUND_BTN_Y2,activity.isSoundOn());
		startAllThreads();//开启所有线程
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		  boolean retry = true;
	        stopAllThreads();
	        while (retry) {
	            try {
	            	drawThread.join();
	                retry = false;
	            } 
	            catch (InterruptedException e) {e.printStackTrace();}//不断地循环，直到刷帧线程结束
	        }
	}
	//加载图片的方法
	public void initBitmap(){
		onBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.on);
		offBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.off);
		yinyueOnBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.yinyuekai);
		yinyueOffBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.yinyueguan);
		yinxiaoOnBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.yinxiaokai);
		yinxiaoOffBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.yinxiaoguan);	
		bgBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.viewbackground);
		helpbuttonBitmap= BitmapFactory.decodeResource(this.getResources(), R.drawable.helpbutton);	
		aboutbuttonitmap= BitmapFactory.decodeResource(this.getResources(), R.drawable.aboutbutton);		
		backButton = BitmapFactory.decodeResource(this.getResources(), R.drawable.goback);		
				
		onBitmap=PicScaleHelper.scaleToFit(onBitmap, Constant.ssr.ratio);
		offBitmap=PicScaleHelper.scaleToFit(offBitmap, Constant.ssr.ratio);
		yinyueOnBitmap=PicScaleHelper.scaleToFit(yinyueOnBitmap, Constant.ssr.ratio);		
		yinyueOffBitmap=PicScaleHelper.scaleToFit(yinyueOffBitmap, Constant.ssr.ratio);
		yinxiaoOnBitmap=PicScaleHelper.scaleToFit(yinxiaoOnBitmap, Constant.ssr.ratio);		
		yinxiaoOffBitmap=PicScaleHelper.scaleToFit(yinxiaoOffBitmap, Constant.ssr.ratio);
		bgBitmap=PicScaleHelper.FullScreenScale(bgBitmap, Constant.wRatio, Constant.hRatio);
		helpbuttonBitmap=PicScaleHelper.scaleToFit(helpbuttonBitmap, Constant.ssr.ratio);
		backButton=PicScaleHelper.scaleToFit(backButton, Constant.ssr.ratio);
		aboutbuttonitmap=PicScaleHelper.scaleToFit(aboutbuttonitmap, Constant.ssr.ratio);
	}
	void createAllThreads()
	{
		drawThread=new DrawThread(this);//创建绘制线程		
	}
	void startAllThreads()
	{
		drawThread.setFlag(true);     
		drawThread.start();
	}
	void stopAllThreads()
	{
		drawThread.setFlag(false);       
	}
	private class DrawThread extends Thread{
		private boolean flag = true;	
		private int sleepSpan = 100;
		SystemView fatherView;
		SurfaceHolder surfaceHolder;
		public DrawThread(SystemView fatherView){
			this.fatherView = fatherView;
			this.surfaceHolder = fatherView.getHolder();
		}
		public void run(){
			Canvas c;
	        while (this.flag) {
	            c = null;
	            try {
	            	// 锁定整个画布
	                c = this.surfaceHolder.lockCanvas(null);
	                synchronized (this.surfaceHolder) {
	                	fatherView.onDraw(c);//绘制
	                }
	            } finally {
	                if (c != null) {
	                	//并释放锁
	                    this.surfaceHolder.unlockCanvasAndPost(c);
	                }
	            }
	            try{
	            	Thread.sleep(sleepSpan);//睡眠指定毫秒数
	            }
	            catch(Exception e){
	            	e.printStackTrace();//打印堆栈信息
	            }
	        }
		}
		public void setFlag(boolean flag) {
			this.flag = flag;
		}
	}
}
