package tool;

import utility.PicScaleHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.publicClass.Constant;
import com.publicClass.R;
import com.userView.GameViewFive;
import com.userView.GameViewOne;
import com.userView.GameViewThree;
import com.userView.GameViewTwo;

public class killspray {


	/**
	 * @���ü���
	 */
	GameViewTwo gameview2;
	GameViewThree gameview3;
	GameViewFive gameview5;
	
	public int level; //�ȼ�
	private int i;//���õ����ھ������
	private int j;//���õ����ھ������
	private Bitmap[] levelOneBitmap = new Bitmap[3];//һ�����ü�ͼƬ
	private Bitmap[] levelTwoBitmap = new Bitmap[3];//�������ü�ͼƬ
    public int state=0;
	/*
	 * ���췽��
	 */
	public killspray(GameViewTwo gameview2,int level,int pressX,int pressY){
		this.level = level;
		this.gameview2 = gameview2;
		i = pressY/Constant.G_SIZE;		
		j = pressX/Constant.G_SIZE;
	    initBitmap();
	}
	public killspray(GameViewThree gameview3,int level,int pressX,int pressY){
		this.level = level;
		this.gameview3 = gameview3;
		i = pressY/Constant.G_SIZE;		
		j = pressX/Constant.G_SIZE;
	    initBitmap();
	}
	
    public killspray(GameViewFive gameview5, int level, int pressX,int pressY) {
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
		
		//������õƵȼ�Ϊ1�����÷�ΧΪ4��
		if(level==1){
			
			for(int f=i-1;f<=i;f++){	
				if(f>=0&&f<map.length)
				for(int s=j-1;s<=j;s++){
					if(s>=0&&s<map[f].length&&map[f][s]==1)
					map[f][s]=-1;
				}						
			}
		}
		//������õƵȼ�Ϊ2�����÷�ΧΪ9��
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
		
		//��ԭ��ͼ
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
	
	
	//�жϹ��ߵķ���λ���Ƿ���Ч
		public boolean haveRight2(){
			if(i<10&&i>0&&j<8&&j>=0)
				return true;
				else 
					return false;
			
		}
		
	    //�жϹ��ߵķ���λ���Ƿ���Ч
		public boolean haveRight3(){
			
			if(i<Constant.MAP1.length-1&&i>0&&j<Constant.MAP1[0].length&&j>13)
			return true;
			else 
				return false;
		}	
		//�жϹ��ߵķ���λ���Ƿ���Ч
		public boolean haveRight5(){
					
			if(i<Constant.MAP1.length-1&&i>0&&j<Constant.MAP1[0].length&&j>0)
				
			return true;
			else 
				return false;
		}	
				
	//�����Լ�
	public void drawSelf(Canvas canvas,Paint paint){
		int onX =j*Constant.SCREEN_WIDTH/20;
		int onY = i*Constant.SCREEN_HEIGHT/12;
		if(level == 1)
			canvas.drawBitmap(levelOneBitmap[state], onX, onY, paint);
		if(level == 2)
			canvas.drawBitmap(levelTwoBitmap[state], onX, onY, paint);
	}

	//����ͼƬ�ķ���
			public void initBitmap(){
				if(gameview2!=null){
				if(level==1){
					levelOneBitmap[0]=BitmapFactory.decodeResource(gameview2.getResources(), R.drawable.spray1);
					levelOneBitmap[0]=PicScaleHelper.scaleToFit(levelOneBitmap[0], Constant.ssr.ratio);
					levelOneBitmap[1]=BitmapFactory.decodeResource(gameview2.getResources(), R.drawable.spray2);
					levelOneBitmap[1]=PicScaleHelper.scaleToFit(levelOneBitmap[1], Constant.ssr.ratio);
					levelOneBitmap[2]=BitmapFactory.decodeResource(gameview2.getResources(), R.drawable.spray3);
					levelOneBitmap[2]=PicScaleHelper.scaleToFit(levelOneBitmap[2], Constant.ssr.ratio);
					}else{
					levelTwoBitmap[0]=BitmapFactory.decodeResource(gameview2.getResources(), R.drawable.spray11);
					levelTwoBitmap[0]=PicScaleHelper.scaleToFit(levelTwoBitmap[0], Constant.ssr.ratio);
					levelTwoBitmap[1]=BitmapFactory.decodeResource(gameview2.getResources(), R.drawable.spray22);
					levelTwoBitmap[1]=PicScaleHelper.scaleToFit(levelTwoBitmap[1], Constant.ssr.ratio);
					levelTwoBitmap[2]=BitmapFactory.decodeResource(gameview2.getResources(), R.drawable.spray33);
					levelTwoBitmap[2]=PicScaleHelper.scaleToFit(levelTwoBitmap[2], Constant.ssr.ratio);
					}
				}else if(gameview3!=null){
					if(level==1){
						levelOneBitmap[0]=BitmapFactory.decodeResource(gameview3.getResources(), R.drawable.spray1);
						levelOneBitmap[0]=PicScaleHelper.scaleToFit(levelOneBitmap[0], Constant.ssr.ratio);
						levelOneBitmap[1]=BitmapFactory.decodeResource(gameview3.getResources(), R.drawable.spray2);
						levelOneBitmap[1]=PicScaleHelper.scaleToFit(levelOneBitmap[1], Constant.ssr.ratio);
						levelOneBitmap[2]=BitmapFactory.decodeResource(gameview3.getResources(), R.drawable.spray3);
						levelOneBitmap[2]=PicScaleHelper.scaleToFit(levelOneBitmap[2], Constant.ssr.ratio);
						}else{
						levelTwoBitmap[0]=BitmapFactory.decodeResource(gameview3.getResources(), R.drawable.spray11);
						levelTwoBitmap[0]=PicScaleHelper.scaleToFit(levelTwoBitmap[0], Constant.ssr.ratio);
						levelTwoBitmap[1]=BitmapFactory.decodeResource(gameview3.getResources(), R.drawable.spray22);
						levelTwoBitmap[1]=PicScaleHelper.scaleToFit(levelTwoBitmap[1], Constant.ssr.ratio);
						levelTwoBitmap[2]=BitmapFactory.decodeResource(gameview3.getResources(), R.drawable.spray33);
						levelTwoBitmap[2]=PicScaleHelper.scaleToFit(levelTwoBitmap[2], Constant.ssr.ratio);
						}
				}else if(gameview5!=null){
					if(level==1){
						levelOneBitmap[0]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.spray1);
						levelOneBitmap[0]=PicScaleHelper.scaleToFit(levelOneBitmap[0], Constant.ssr.ratio);
						levelOneBitmap[1]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.spray2);
						levelOneBitmap[1]=PicScaleHelper.scaleToFit(levelOneBitmap[1], Constant.ssr.ratio);
						levelOneBitmap[2]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.spray3);
						levelOneBitmap[2]=PicScaleHelper.scaleToFit(levelOneBitmap[2], Constant.ssr.ratio);
						}else{
						levelTwoBitmap[0]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.spray11);
						levelTwoBitmap[0]=PicScaleHelper.scaleToFit(levelTwoBitmap[0], Constant.ssr.ratio);
						levelTwoBitmap[1]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.spray22);
						levelTwoBitmap[1]=PicScaleHelper.scaleToFit(levelTwoBitmap[1], Constant.ssr.ratio);
						levelTwoBitmap[2]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.spray33);
						levelTwoBitmap[2]=PicScaleHelper.scaleToFit(levelTwoBitmap[2], Constant.ssr.ratio);
						}
				}
			}
}
