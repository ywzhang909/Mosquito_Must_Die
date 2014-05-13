package tool;

import utility.PicScaleHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.publicClass.Constant;
import com.publicClass.R;
import com.userView.GameViewFour;


public class KillLamp {


	/**
	 * @灭蚊灯类
	 */
	GameViewFour gameview4;
	private int level; //等级
	private int i;//放置点所在矩阵的行
	private int j;//放置点所在矩阵的列
	private Bitmap levelOneBitmap;//一级灭蚊灯图片
	private Bitmap levelTwoBitmap;//二级灭蚊灯图片

	/*
	 * 构造方法
	 */
	public KillLamp(GameViewFour gameview4,int level,int pressX,int pressY){
		this.gameview4 = gameview4;
		this.level = level;
		i = pressY/Constant.G_SIZE;		
		j = pressX/Constant.G_SIZE;
		initBitmap();
	}
	
    /*
     * 发挥作用的方法，返回变化后的地图
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
		//如果灭蚊灯等级为2，作用范围为2
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
	
	//还原地图的方法
	public int[][] reMap(int[][] map){
		
		//还原地图
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
	//绘制自己
	public void drawSelf(Canvas canvas,Paint paint){
		if(level==1){
		int onX =j*Constant.SCREEN_WIDTH/20-20;
		int onY = i*Constant.SCREEN_HEIGHT/12-20;
	    canvas.drawBitmap(levelOneBitmap, onX, onY, paint);

		}else if(level==2){
			int onX =j*Constant.SCREEN_WIDTH/20;
			int onY = i*Constant.SCREEN_HEIGHT/12;
		    canvas.drawBitmap(levelTwoBitmap, onX, onY, paint);
			
		}
	}

	public boolean haveRight() {
		if(i>1&&i<Constant.MAP1.length-2&&j>1&&j<Constant.MAP1[0].length-4)
			return true;
		return false;
	}
	
	//加载图片的方法
		public void initBitmap(){
			if(level==1){
			levelOneBitmap=BitmapFactory.decodeResource(gameview4.getResources(), R.drawable.killlamp1);
			levelOneBitmap=PicScaleHelper.scaleToFit(levelOneBitmap, Constant.ssr.ratio);
			}else if(level==2){
			levelTwoBitmap=BitmapFactory.decodeResource(gameview4.getResources(), R.drawable.killlamp2);
			levelTwoBitmap=PicScaleHelper.scaleToFit(levelTwoBitmap, Constant.ssr.ratio);
			}
		}

    
}
