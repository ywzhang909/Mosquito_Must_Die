package com.userView;


import java.util.HashMap;

import utility.PicScaleHelper;


import com.publicClass.Constant;

import com.publicClass.*;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
/**
 * 
 * @author 山东大学android实验室刘昭呈
 *
 */
public class WelcomeView extends SurfaceView 
implements SurfaceHolder.Callback  //实现生命周期回调接口
{
	
	
	//首先需要把GameActivity的activity传过来
	GameActivity activity;
	
	//绘制相关
	Paint paint;//画笔
	
	//动画相关
	int currentAlpha=0;//当前的不透明值
	int sleepSpan=50;//动画的时延ms
	
	//屏幕参数
	int screenWidth=Constant.SCREEN_WIDTH;//屏幕宽度
	int screenHeight=Constant.SCREEN_HEIGHT;//屏幕高度
	
	//动画源图片
	Bitmap[] logos=new Bitmap[2];//logo图片数组
	Bitmap currentLogo;//当前logo图片引用
	
	//坐标参数相关
	int currentX;
	int currentY;
	
	//音乐参数
	SoundPool Soundpool;
	HashMap<Integer, Integer> SoundMap;
	
	//构造器
	public WelcomeView(GameActivity activity) {
		super(activity);
		this.activity=activity;
		
		//设置回调接口
		this.getHolder().addCallback(this);

		paint = new Paint();//创建画笔
		paint.setAntiAlias(true);//打开抗锯齿
		
		//加载图片
				logos[0]=BitmapFactory.decodeResource(activity.getResources(), R.drawable.welcome22); 
				logos[1]=BitmapFactory.decodeResource(activity.getResources(), R.drawable.welcome44);		
				for(int i=0;i<logos.length;i++){
					//将每一张图片进行适当的缩放
					logos[i]=PicScaleHelper.FullScreenScale(logos[i], Constant.wRatio,Constant.hRatio);
				}
	}
    
	//绘画方法
	public void onDraw(Canvas canvas){	
		
		
		//绘制黑填充矩形清背景
		paint.setColor(Color.BLACK);//设置画笔颜色
		paint.setAlpha(255);
		canvas.drawRect(0, 0, screenWidth, screenHeight, paint);
		
		//进行平面贴图
		if(currentLogo==null)return;
		paint.setAlpha(currentAlpha);		
		canvas.drawBitmap(currentLogo, currentX, currentY, paint);	
	}
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	public void surfaceCreated(SurfaceHolder holder) {
		new Thread()
		{
			public void run()
			{
				for(Bitmap bm:logos)
				{
					currentLogo=bm;
					//计算图片位置，将图片放置在屏幕的中间
					currentX=screenWidth/2-bm.getWidth()/2;
					currentY=screenHeight/2-bm.getHeight()/2;
					//播放音乐，注意：初始化音乐与播放音乐之间需要间隙
				
					for(int i=255;i>-10;i=i-10)
					{//动态更改图片的透明度值并不断重绘	
						currentAlpha=i;
						if(currentAlpha<0)
						{
							currentAlpha=0;
						}
						SurfaceHolder myholder=WelcomeView.this.getHolder();//获得容器
						Canvas canvas = myholder.lockCanvas();//获取画布
						try{
							synchronized(myholder)//锁定容器对象
							{
								onDraw(canvas);//进行绘制
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}
						finally{
							if(canvas != null){
								myholder.unlockCanvasAndPost(canvas);//释放画布
							}
						}						
						try
						{
							if(i==255)
							{//若是新图片，多等待一会
								Thread.sleep(1000);
							}
							Thread.sleep(sleepSpan);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				}
				//动画播放完毕后，去主菜单界面
				activity.sendMessage(Constant.GOTO_MAIN_MENU_VIEW);
			}
		}.start();
	}

		

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
}
