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

public class Swatter {


	/**
	 * @��������
	 */
	GameViewFive gameview5;

	public int level; //�ȼ�
	private int lasttime = 3000;  //����ʱ��
	private boolean live = true;
	private int i;//���õ����ھ������
	private int j;//���õ����ھ������
	private Bitmap levelOneBitmap ;
	private Bitmap levelTwoBitmap ;
	public int state = 0;

	/*
	 * ���췽��
	 */
	public Swatter(	GameViewFive gameview5,int level,int pressX,int pressY){
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
			int onX =j*Constant.SCREEN_WIDTH/20-20;
			int onY = i*Constant.SCREEN_HEIGHT/12-20;
		    canvas.drawBitmap(levelOneBitmap, onX, onY, paint);
	    	Log.e("lamp","drew");

			}else if(level==2){
				int onX =j*Constant.SCREEN_WIDTH/20;
				int onY = i*Constant.SCREEN_HEIGHT/12;
			    canvas.drawBitmap(levelTwoBitmap, onX, onY, paint);
				
			}
	}
	//����ͼƬ�ķ���
			public void initBitmap(){
				if(gameview5!=null){
					if(level==1){
						levelOneBitmap=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.swatter1);
								
					}else{
						levelTwoBitmap=BitmapFactory.decodeResource(gameview5.getResources(), R.drawable.swatter2);
						
					}
				}
			}

			public boolean haveRight5() {
				if(i>=4&&i<=6&&j>=13&&j<=15)					
				return false;
				
				return true;
			}
    
}
