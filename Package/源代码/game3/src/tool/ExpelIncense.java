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
import com.userView.GameViewFour;
import com.userView.GameViewOne;
import com.userView.GameViewSix;
import com.userView.GameViewThree;
import com.userView.GameViewTwo;

public class ExpelIncense {

	/**
	 * @��������
	 */	
	GameViewThree gameview3;
	GameViewFour gameview4;
	GameViewFive gameview5;
	GameViewSix gameview6;
	
	public int level; //�ȼ�
	private int i;//���õ����ھ������
	private int j;//���õ����ھ������
	private Bitmap[] levelOneBitmap = new Bitmap[4];//һ��������ͼƬ
	private Bitmap[] levelTwoBitmap = new Bitmap[4];//����������ͼƬ
	public int state = 0;
	/*
	 * ���췽��
	 */
	public ExpelIncense(GameViewThree gameview3,int level,int pressX,int pressY){
		
		this.gameview3 = gameview3;
		this.level = level;
		 j = pressX/Constant.G_SIZE;		
		 i = pressY/Constant.G_SIZE;
		 initBitmap();
	}
    public ExpelIncense(GameViewFour gameview4,int level,int pressX,int pressY){
		
		this.gameview4 = gameview4;
		this.level = level;
		 j = pressX/Constant.G_SIZE;		
		 i = pressY/Constant.G_SIZE;
		 initBitmap();
	}

 public ExpelIncense(GameViewFive gameview5,int level,int pressX,int pressY){
		
		this.gameview5 = gameview5;
		this.level = level;
		 j = pressX/Constant.G_SIZE;		
		 i = pressY/Constant.G_SIZE;
		 initBitmap();
	}

   
	public ExpelIncense(GameViewSix gameview6, int level2, int pressX,int pressY) {

		this.gameview6 = gameview6;
		this.level = level;
		 j = pressX/Constant.G_SIZE;		
		 i = pressY/Constant.G_SIZE;
		 initBitmap();}
	
	//�������õķ��������ر仯��ĵ�ͼ
	public int[][] effect(int[][] map){
		
        if(map[i][j] == 1)
			map[i][j] = 0;
        
		return map;
		
	}

	//��������ʧ�ķ���
	public int[][] reMap(int[][] map){
		
		//����������״̬
		 if(map[i][j] == 0)
				map[i][j] = 1;
		return map;
	}
	
	    //�����Լ�
		public void drawSelf(Canvas canvas,Paint paint){
			
			int onX =j*40;
			int onY = i*40;
			canvas.drawBitmap(levelOneBitmap[state ], onX, onY, paint);
		
			
		}
		//�жϹ��ߵķ���λ���Ƿ���Ч
		public boolean haveRight3(int[][]map){
			
			if(gameview3!=null){
  				int count = gameview3.Wlist.size();
  				int[][] storeMosLocat = new int[count][count];
  				for(int i=0;i<count;i++){
  					if(gameview3.Wlist.get(i)!=null){
  				storeMosLocat[i][0] = gameview3.Wlist.get(i).locY/8;
  				storeMosLocat[i][1] = gameview3.Wlist.get(i).locX/8;
  					}
  				}
               	
  		        //������������ӵ����ܣ�����false
  			    if(meetMos(i,j,storeMosLocat)){
  			    	Log.i("meet", ""+meetMos(i,j,storeMosLocat));
  				    return false;
  			    }	
  			    
            }  
			if(i<Constant.MAP1.length&&i>0&&j<Constant.MAP1[0].length&&j>13)
				 return true;
			return false;
							
		}
		
		//�жϹ��ߵķ���λ���Ƿ���Ч
				public boolean haveRight4(int[][]map){
					 if(gameview4!=null){
		            	int count = gameview4.Wlist.size();
		  				int[][] storeMosLocat = new int[count][count];
		  				for(int i=0;i<count;i++){
		  					if(gameview4.Wlist.get(i)!=null){
		  				storeMosLocat[i][0] = gameview4.Wlist.get(i).locY/8;
		  				storeMosLocat[i][1] = gameview4.Wlist.get(i).locX/8;
		  					}
		  				}
		               	
		  		        //������������ӵ����ܣ�����false
		  			    if(meetMos(i,j,storeMosLocat)){
		  			    	Log.i("meet", ""+meetMos(i,j,storeMosLocat));
		  				    return false;
		  			    }	
		            }else if(gameview5!=null){
		            	int count = gameview5.Wlist.size();
		  				int[][] storeMosLocat = new int[count][count];
		  				for(int i=0;i<count;i++){
		  					if(gameview5.Wlist.get(i)!=null){
		  				storeMosLocat[i][0] = gameview5.Wlist.get(i).locY/8;
		  				storeMosLocat[i][1] = gameview5.Wlist.get(i).locX/8;
		  					}
		  				}
		               	
		  		        //������������ӵ����ܣ�����false
		  			    if(meetMos(i,j,storeMosLocat)){
		  			    	Log.i("meet", ""+meetMos(i,j,storeMosLocat));
		  				    return false;
		  			    }	
		            }
		            else if(gameview6!=null){
		            	int count = gameview6.Wlist.size();
		  				int[][] storeMosLocat = new int[count][count];
		  				for(int i=0;i<count;i++){
		  					if(gameview6.Wlist.get(i)!=null){
		  				storeMosLocat[i][0] = gameview6.Wlist.get(i).locY/8;
		  				storeMosLocat[i][1] = gameview6.Wlist.get(i).locX/8;
		  					}
		  				}
		               	
		  		        //������������ӵ����ܣ�����false
		  			    if(meetMos(i,j,storeMosLocat)){
		  			    	Log.i("meet", ""+meetMos(i,j,storeMosLocat));
		  				    return false;
		  			    }	
		            }
					
					if(i<Constant.MAP1.length&&i>0&&j<Constant.MAP1[0].length&&j>0)
						 return true;
					return false;
				}
	//�жϴ����Ƿ�����������
	public boolean meetMos(int i,int j,int[][] MosLocat){
		for(int n=0;n<MosLocat.length;n++){
		
			if((i==MosLocat[n][0]&&j==MosLocat[n][1])||(i==MosLocat[n][0]-1&&j==MosLocat[n][1])||
					(i==MosLocat[n][0]+1&&j==MosLocat[n][1])||(i==MosLocat[n][0]&&j==MosLocat[n][1]-1)
					||(i==MosLocat[n][0]&&j==MosLocat[n][1]+1))
				return true;
		}
		return false;
		
}
	//����ͼƬ�ķ���
	public void initBitmap(){
		if(gameview3!=null){
			levelOneBitmap[0]=BitmapFactory.decodeResource(gameview3.getResources(), R.drawable.incense1);				
			levelOneBitmap[0]=PicScaleHelper.scaleToFit(levelOneBitmap[0], Constant.ssr.ratio);
			levelOneBitmap[1]=BitmapFactory.decodeResource(gameview3.getResources(), R.drawable.incense2);
			levelOneBitmap[1]=PicScaleHelper.scaleToFit(levelOneBitmap[1], Constant.ssr.ratio);
			levelOneBitmap[2]=BitmapFactory.decodeResource(gameview3.getResources(), R.drawable.incense3);
			levelOneBitmap[2]=PicScaleHelper.scaleToFit(levelOneBitmap[2], Constant.ssr.ratio);
			levelOneBitmap[3]=BitmapFactory.decodeResource(gameview3.getResources(), R.drawable.incense4);
			levelOneBitmap[3]=PicScaleHelper.scaleToFit(levelOneBitmap[3], Constant.ssr.ratio);
		}else if(gameview4!=null){
			levelOneBitmap[0]=BitmapFactory.decodeResource(gameview4.getResources(), R.drawable.incense1);				
			levelOneBitmap[0]=PicScaleHelper.scaleToFit(levelOneBitmap[0], Constant.ssr.ratio);
			levelOneBitmap[1]=BitmapFactory.decodeResource(gameview4.getResources(), R.drawable.incense2);
			levelOneBitmap[1]=PicScaleHelper.scaleToFit(levelOneBitmap[1], Constant.ssr.ratio);
			levelOneBitmap[2]=BitmapFactory.decodeResource(gameview4.getResources(), R.drawable.incense3);
			levelOneBitmap[2]=PicScaleHelper.scaleToFit(levelOneBitmap[2], Constant.ssr.ratio);
			levelOneBitmap[3]=BitmapFactory.decodeResource(gameview4.getResources(), R.drawable.incense4);
			levelOneBitmap[3]=PicScaleHelper.scaleToFit(levelOneBitmap[3], Constant.ssr.ratio);
		
		}else if(gameview5!=null){
			levelOneBitmap[0]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.incense1);				
			levelOneBitmap[0]=PicScaleHelper.scaleToFit(levelOneBitmap[0], Constant.ssr.ratio);
			levelOneBitmap[1]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.incense2);
			levelOneBitmap[1]=PicScaleHelper.scaleToFit(levelOneBitmap[1], Constant.ssr.ratio);
			levelOneBitmap[2]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.incense3);
			levelOneBitmap[2]=PicScaleHelper.scaleToFit(levelOneBitmap[2], Constant.ssr.ratio);
			levelOneBitmap[3]=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.incense4);
			levelOneBitmap[3]=PicScaleHelper.scaleToFit(levelOneBitmap[3], Constant.ssr.ratio);
		
		}else if(gameview6!=null){
			levelOneBitmap[0]=BitmapFactory.decodeResource(gameview6.getResources(), R.drawable.incense1);				
			levelOneBitmap[0]=PicScaleHelper.scaleToFit(levelOneBitmap[0], Constant.ssr.ratio);
			levelOneBitmap[1]=BitmapFactory.decodeResource(gameview6.getResources(), R.drawable.incense2);
			levelOneBitmap[1]=PicScaleHelper.scaleToFit(levelOneBitmap[1], Constant.ssr.ratio);
			levelOneBitmap[2]=BitmapFactory.decodeResource(gameview6.getResources(), R.drawable.incense3);
			levelOneBitmap[2]=PicScaleHelper.scaleToFit(levelOneBitmap[2], Constant.ssr.ratio);
			levelOneBitmap[3]=BitmapFactory.decodeResource(gameview6.getResources(), R.drawable.incense4);
			levelOneBitmap[3]=PicScaleHelper.scaleToFit(levelOneBitmap[3], Constant.ssr.ratio);
		
		}
			
			

	   
	}
				
	
}
