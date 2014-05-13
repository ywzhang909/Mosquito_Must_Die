package tool;

import java.util.ArrayList;

import utility.PicScaleHelper;

import com.publicClass.Constant;
import com.publicClass.R;
import com.userView.GameViewOne;
import com.userView.GameViewTwo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Frog {
	
	GameViewOne gameViewOne;
	GameViewTwo gameViewTwo;
	
	private Bitmap[] frog= new Bitmap[4];
	private int i;//放置点所在矩阵的行
	private int j;//放置点所在矩阵的列
	public ArrayList<int[][]> MapList;
	int store0;
	int store1;
	int store2;
	int store3;
	int store4;
	
	
	public Frog(GameViewOne gameViewOne,int pressX,int pressY){
		this.gameViewOne = gameViewOne;
		i = pressY/Constant.G_SIZE;		
		j = pressX/Constant.G_SIZE;
		initBitmap();
	}
	public Frog(GameViewTwo gameViewTwo,int pressX,int pressY){
		this.gameViewTwo = gameViewTwo;
		i = pressY/Constant.G_SIZE;		
		j = pressX/Constant.G_SIZE;
		initBitmap();
	}
	//青蛙分别在五个格内时改变后的地图
	public int[][] effect(int[][] map){
     if(gameViewOne!=null){
       if(gameViewOne.step == 0||gameViewOne.step == 3||gameViewOne.step == 6||
    	  gameViewOne.step == 7||gameViewOne.step == 10||gameViewOne.step == 13) {
    	   store0=map[i][j];

    	   map[i][j]=-1;     
       }
       else if(gameViewOne.step == 1||gameViewOne.step == 2){
    	   store1=map[i-1][j];

    	   map[i-1][j]=-1;
       }
       else if(gameViewOne.step == 4||gameViewOne.step == 5){
    	   store2=map[i+1][j];

    	   map[i+1][j]=-1;
       }
       else if(gameViewOne.step == 8||gameViewOne.step == 9){
    	   store3=map[i][j-1];

    	   map[i][j-1]=-1;
       }
       else if(gameViewOne.step == 11||gameViewOne.step == 12){
    	   store4=map[i][j+1];

    	   map[i][j+1]=-1;
       }
     } 
     
     else if(gameViewTwo!=null){
         if(gameViewTwo.step == 0||gameViewTwo.step == 3||gameViewTwo.step == 6||
        		 gameViewTwo.step == 7||gameViewTwo.step == 10||gameViewTwo.step == 13) {
      	   store0=map[i][j];

      	   map[i][j]=-1;     
         }
         else if(gameViewTwo.step == 1||gameViewTwo.step == 2){
      	   store1=map[i-1][j];

      	   map[i-1][j]=-1;
         }
         else if(gameViewTwo.step == 4||gameViewTwo.step == 5){
      	   store2=map[i+1][j];

      	   map[i+1][j]=-1;
         }
         else if(gameViewTwo.step == 8||gameViewTwo.step == 9){
      	   store3=map[i][j-1];

      	   map[i][j-1]=-1;
         }
         else if(gameViewTwo.step == 11||gameViewTwo.step == 12){
      	   store4=map[i][j+1];

      	   map[i][j+1]=-1;
         }
       } 
       return map;
			
	}
	
	//恢复地图
	public int[][] reMap(int[][] map){
		if(gameViewOne!=null){
		  if(gameViewOne.step == 0||gameViewOne.step == 3||gameViewOne.step == 6||
		    	  gameViewOne.step == 7||gameViewOne.step == 10||gameViewOne.step == 13) 
		    	   map[i][j]=store0;     
		       else if(gameViewOne.step == 1||gameViewOne.step == 2) 
		    	   map[i-1][j]=store1;
		       else if(gameViewOne.step == 4||gameViewOne.step == 5) 
		    	   map[i+1][j]=store2;
		       else if(gameViewOne.step == 8||gameViewOne.step == 9) 
		    	   map[i][j-1]=store3;
		       else if(gameViewOne.step == 11||gameViewOne.step == 12) 
		    	   map[i][j+1]=store4;
		}
		else if(gameViewTwo!=null){
			       if(gameViewTwo.step == 0||gameViewTwo.step == 3||gameViewTwo.step == 6||
					gameViewTwo.step == 7||gameViewTwo.step == 10||gameViewTwo.step == 13) 
			    	   map[i][j]=store0;     
			       else if(gameViewTwo.step == 1||gameViewTwo.step == 2) 
			    	   map[i-1][j]=store1;
			       else if(gameViewTwo.step == 4||gameViewTwo.step == 5) 
			    	   map[i+1][j]=store2;
			       else if(gameViewTwo.step == 8||gameViewTwo.step == 9) 
			    	   map[i][j-1]=store3;
			       else if(gameViewTwo.step == 11||gameViewTwo.step == 12) 
			    	   map[i][j+1]=store4;
			}
		       return map;
		
	}
	//判断工具的放置位置是否有效
			public boolean haveRight1(){
				
				if(i<11&&i>0&&j<Constant.MAP1[0].length&&j>0)
				return true;
				else 
					return false;
			}
			
	//判断工具的放置位置是否有效
		    public boolean haveRight2(){
				
		    	if(i<Constant.MAP1.length&&i>0&&j<Constant.MAP1[0].length&&j>12)
					return true;
					else 
						return false;
			}
		    
	//绘制自己
		public void drawSelf(int step,Canvas canvas,Paint paint){
			
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
		
		//加载图片的方法
		public void initBitmap(){
			
			if(gameViewOne!=null){
				frog[0]=BitmapFactory.decodeResource(gameViewOne.getResources(), R.drawable.frog1);				
				frog[0]=PicScaleHelper.scaleToFit(frog[0], Constant.ssr.ratio);
				frog[1]=BitmapFactory.decodeResource(gameViewOne.getResources(), R.drawable.frog2);
				frog[1]=PicScaleHelper.scaleToFit(frog[1], Constant.ssr.ratio);
				frog[2]=BitmapFactory.decodeResource(gameViewOne.getResources(), R.drawable.frog3);
				frog[2]=PicScaleHelper.scaleToFit(frog[2], Constant.ssr.ratio);
				frog[3]=BitmapFactory.decodeResource(gameViewOne.getResources(), R.drawable.frog4);
				frog[3]=PicScaleHelper.scaleToFit(frog[3], Constant.ssr.ratio);
			}
			else if(gameViewTwo!=null){
				frog[0]=BitmapFactory.decodeResource(gameViewTwo.getResources(), R.drawable.frog1);				
				frog[0]=PicScaleHelper.scaleToFit(frog[0], Constant.ssr.ratio);
				frog[1]=BitmapFactory.decodeResource(gameViewTwo.getResources(), R.drawable.frog2);
				frog[1]=PicScaleHelper.scaleToFit(frog[1], Constant.ssr.ratio);
				frog[2]=BitmapFactory.decodeResource(gameViewTwo.getResources(), R.drawable.frog3);
				frog[2]=PicScaleHelper.scaleToFit(frog[2], Constant.ssr.ratio);
				frog[3]=BitmapFactory.decodeResource(gameViewTwo.getResources(), R.drawable.frog4);
				frog[3]=PicScaleHelper.scaleToFit(frog[3], Constant.ssr.ratio);
			}	 
		}
		
}

