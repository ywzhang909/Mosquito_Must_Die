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
 * @author ɽ����ѧandroidʵ�������ѳ�
 *
 */
public class WelcomeView extends SurfaceView 
implements SurfaceHolder.Callback  //ʵ���������ڻص��ӿ�
{
	
	
	//������Ҫ��GameActivity��activity������
	GameActivity activity;
	
	//�������
	Paint paint;//����
	
	//�������
	int currentAlpha=0;//��ǰ�Ĳ�͸��ֵ
	int sleepSpan=50;//������ʱ��ms
	
	//��Ļ����
	int screenWidth=Constant.SCREEN_WIDTH;//��Ļ���
	int screenHeight=Constant.SCREEN_HEIGHT;//��Ļ�߶�
	
	//����ԴͼƬ
	Bitmap[] logos=new Bitmap[2];//logoͼƬ����
	Bitmap currentLogo;//��ǰlogoͼƬ����
	
	//����������
	int currentX;
	int currentY;
	
	//���ֲ���
	SoundPool Soundpool;
	HashMap<Integer, Integer> SoundMap;
	
	//������
	public WelcomeView(GameActivity activity) {
		super(activity);
		this.activity=activity;
		
		//���ûص��ӿ�
		this.getHolder().addCallback(this);

		paint = new Paint();//��������
		paint.setAntiAlias(true);//�򿪿����
		
		//����ͼƬ
				logos[0]=BitmapFactory.decodeResource(activity.getResources(), R.drawable.welcome22); 
				logos[1]=BitmapFactory.decodeResource(activity.getResources(), R.drawable.welcome44);		
				for(int i=0;i<logos.length;i++){
					//��ÿһ��ͼƬ�����ʵ�������
					logos[i]=PicScaleHelper.FullScreenScale(logos[i], Constant.wRatio,Constant.hRatio);
				}
	}
    
	//�滭����
	public void onDraw(Canvas canvas){	
		
		
		//���ƺ��������屳��
		paint.setColor(Color.BLACK);//���û�����ɫ
		paint.setAlpha(255);
		canvas.drawRect(0, 0, screenWidth, screenHeight, paint);
		
		//����ƽ����ͼ
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
					//����ͼƬλ�ã���ͼƬ��������Ļ���м�
					currentX=screenWidth/2-bm.getWidth()/2;
					currentY=screenHeight/2-bm.getHeight()/2;
					//�������֣�ע�⣺��ʼ�������벥������֮����Ҫ��϶
				
					for(int i=255;i>-10;i=i-10)
					{//��̬����ͼƬ��͸����ֵ�������ػ�	
						currentAlpha=i;
						if(currentAlpha<0)
						{
							currentAlpha=0;
						}
						SurfaceHolder myholder=WelcomeView.this.getHolder();//�������
						Canvas canvas = myholder.lockCanvas();//��ȡ����
						try{
							synchronized(myholder)//������������
							{
								onDraw(canvas);//���л���
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}
						finally{
							if(canvas != null){
								myholder.unlockCanvasAndPost(canvas);//�ͷŻ���
							}
						}						
						try
						{
							if(i==255)
							{//������ͼƬ����ȴ�һ��
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
				//����������Ϻ�ȥ���˵�����
				activity.sendMessage(Constant.GOTO_MAIN_MENU_VIEW);
			}
		}.start();
	}

		

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
}
