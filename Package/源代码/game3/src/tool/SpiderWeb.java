package tool;

import utility.PicScaleHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.publicClass.Constant;
import com.publicClass.R;
import com.userView.GameViewFive;
import com.userView.GameViewOne;

public class SpiderWeb {


	/**
	 * @֩������
	 */
	GameViewFive gameview5;

	public int level; //�ȼ�
	private int lasttime = 3000;  //����ʱ��
	private boolean live = true;
	private int i;//���õ����ھ������
	private int j;//���õ����ھ������
	private Bitmap[] levelOneBitmap = new Bitmap[4];//һ�����ü�ͼƬ
	private Bitmap[] levelTwoBitmap = new Bitmap[4];//�������ü�ͼƬ
	public int state = 0;

	/*
	 * ���췽��
	 */
	public SpiderWeb(	GameViewFive gameview5,int level,int pressX,int pressY){
		this.level = level;
		this.gameview5 = gameview5;
		i = pressY/Constant.G_SIZE;		
		j = pressX/Constant.G_SIZE;
		initBitmap();

	}
	
    /*
     * �������õķ��������ر仯��ĵ�ͼ
     */
	public int[][] effect(int[][] map){
		

		if(level==1){
			
			for(int f=i-1;f<=i;f++){	
				if(f>=0&&f<map.length)
				for(int s=j-1;s<=j;s++){
					if(s>=0&&s<map[f].length&&map[f][s]==1)
					map[f][s]=-1;
				}						
			}
		}
		//������õƵȼ�Ϊ2�����÷�ΧΪ2
		else if(level==2){
			
			for(int f=i-1;f<=i+1;f++){	
				if(f>=0&&f<map.length)
				for(int s=j-1;s<=j+1;s++){
					if(s>=0&&s<map[f].length&&map[f][s]==1)
					map[f][s]=-1;
				}						
				
			}

		}
			
		return map;
	}
	
	//��ԭ��ͼ�ķ���
	public int[][] reMap(int[][] map){
		
		if(level==1){
			for(int f=i-1;f<=i;f++){	
				if(f>=0&&f<map.length)
				for(int s=j-1;s<=j;s++){
				    if(s>=0&&s<map[f].length&&map[f][s]==-1)
						map[f][s]=1;
				}						
			}
			
		}
		else if(level==2){
					
			for(int f=i-1;f<=i+1;f++){	
				if(f>=0&&f<map.length)
				for(int s=j-1;s<=j+1;s++){
				    if(s>=0&&s<map[f].length&&map[f][s]==-1)
						map[f][s]=1;
				}						
			}
		}
					
		return map;
	}
	//�����Լ�
	public void drawSelf(Canvas canvas,Paint paint){
		
		if(level==1){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY = i*Constant.SCREEN_HEIGHT/12;
		    canvas.drawBitmap(levelOneBitmap[state], onX, onY, paint);
	    	

			}else if(level==2){
				int onX =j*Constant.SCREEN_WIDTH/20;
				int onY = i*Constant.SCREEN_HEIGHT/12;
			    canvas.drawBitmap(levelTwoBitmap[state], onX, onY, paint);
				
			}
	}
	//����ͼƬ�ķ���
			public void initBitmap(){
				if(gameview5!=null){
					if(level==1){
						levelOneBitmap[0]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.web11);
						levelOneBitmap[0]=PicScaleHelper.scaleToFit(levelOneBitmap[0], Constant.ssr.ratio);
						levelOneBitmap[1]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.web12);
						levelOneBitmap[1]=PicScaleHelper.scaleToFit(levelOneBitmap[1], Constant.ssr.ratio);
						levelOneBitmap[2]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.web13);
						levelOneBitmap[2]=PicScaleHelper.scaleToFit(levelOneBitmap[2], Constant.ssr.ratio);
						levelOneBitmap[3]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.web14);
						levelOneBitmap[3]=PicScaleHelper.scaleToFit(levelOneBitmap[2], Constant.ssr.ratio);
							
					}else{
						levelTwoBitmap[0]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.web21);
						levelTwoBitmap[0]=PicScaleHelper.scaleToFit(levelTwoBitmap[0], Constant.ssr.ratio);
						levelTwoBitmap[1]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.web22);
						levelTwoBitmap[1]=PicScaleHelper.scaleToFit(levelTwoBitmap[1], Constant.ssr.ratio);
						levelTwoBitmap[2]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.web23);
						levelTwoBitmap[2]=PicScaleHelper.scaleToFit(levelTwoBitmap[2], Constant.ssr.ratio);
						levelTwoBitmap[3]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.web24);
						levelTwoBitmap[3]=PicScaleHelper.scaleToFit(levelTwoBitmap[2], Constant.ssr.ratio);
						
					}
				}
			}

			public boolean haveRight5() {
				if(i>=4&&i<=6&&j>=13&&j<=15)					
				return false;
				
				return true;
			}
    
}
