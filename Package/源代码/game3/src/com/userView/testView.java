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
 *  ��������
 *
 */
public class testView extends SurfaceView implements SurfaceHolder.Callback{
	GameActivity activity;//activity������
	Paint paint;//��������
	private Bitmap[] frog;
	int bmpx;
	int i=5;
	int j=8;
	private int step=0;
	public testView(GameActivity activity) {
		
		super(activity);
		Log.i("gameView", "enter");
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
		//���ܳ�ʼ��λ�ã��泯��
		if(step==0){	   
		int onX =j*Constant.SCREEN_WIDTH/20;
		int onY =i*Constant.SCREEN_HEIGHT/12;
			canvas.drawBitmap(frog[0], onX, onY, paint);
		}
		//������һ��
		if(step==1){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY = (i-1)*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[0], onX, onY, paint);
			}
		//ת������
		if(step==2){
			int onX = j*Constant.SCREEN_WIDTH/20;
			int onY = (i-1)*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[1], onX, onY, paint);
				
			}
		//������һ��
		if(step==3){
			int onX = j*Constant.SCREEN_WIDTH/20;
			int onY = i*Constant.SCREEN_HEIGHT/12;
		
				canvas.drawBitmap(frog[1], onX, onY, paint);
			}
		//������һ��
		if(step==4){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY =(i+1)*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[1], onX, onY, paint);
			}
		//ת������
		if(step==5){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY =(i+1)*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[0], onX, onY, paint);
			}
		//������һ��
		if(step==6){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[0], onX, onY, paint);
			}
		//ת������
		if(step==7){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[2], onX, onY, paint);
			}
		//������һ��
		if(step==8){
			int onX =(j-1)*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[2], onX, onY, paint);
			}
		//ת������
		if(step==9){
			int onX =(j-1)*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[3], onX, onY, paint);
			}
		//������һ��
		if(step==10){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[3], onX, onY, paint);
			}
		//������һ��
		if(step==11){
			int onX =(j+1)*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[3], onX, onY, paint);
			}
		//ת������
		if(step==12){
			int onX =(j+1)*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[2], onX, onY, paint);
			}
		//������һ��
		if(step==13){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY =i*Constant.SCREEN_HEIGHT/12;
				canvas.drawBitmap(frog[3], onX, onY, paint);
			}
		}
	//���»��Ƶķ���
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
		paint=new Paint();//��������
		paint.setAntiAlias(true);//�򿪿����	
		initBitmap();//��ʼ��λͼ��Դ		
		//��ʼ��ͼƬ��λ��
		bmpx=200;		
		initBitmap();
		repaint();//���ƽ���
	}
		//��ť����
		
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
	//����ͼƬ�ķ���
	public void initBitmap(){

		frog[0]=BitmapFactory.decodeResource(this.getResources(), R.drawable.frog1);
		frog[1]=BitmapFactory.decodeResource(this.getResources(), R.drawable.frog2);
		frog[2]=BitmapFactory.decodeResource(this.getResources(), R.drawable.frog3);
		frog[3]=BitmapFactory.decodeResource(this.getResources(), R.drawable.frog4);
	   	
	}	
}
