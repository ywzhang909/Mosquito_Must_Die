package utility;

import java.util.ArrayList;

import mosquito.Mosquito;

import tool.ExpelIncense;
import tool.ExpelPlant;
import tool.Frog;
import tool.killspray;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.userView.GameViewFour;
import com.userView.GameViewOne;
import com.userView.GameViewThree;
import com.userView.GameViewTwo;
/**
 * 
 * @author 山东大学android实验室刘昭呈
 *
 */
public class TimeThread4 extends Thread {
	int i=1;//计量关卡时间
	int time=0;

	int bornWenzi = 0;//第二波蚊子出现时间

	int xiangsteady1=0;//计量蚊香1持续时间
	int xiangsteady2=0;//计量蚊香2持续时间
	int xiangsteady3=0;//计量蚊香3持续时间
	int xiangsteady4=0;//计量蚊香4持续时间
	int xiangsteady5=0;//计量蚊香5持续时间
	int xiangsteady6=0;//计量蚊香6持续时间
	
	boolean ToDate=true;//关卡时间
	boolean flag=true;
	
	boolean xiangFlag1=false;//标识驱蚊蚊香1是否开始计时
	boolean xiangFlag2=false;//标识驱蚊蚊香2是否开始计时
	boolean xiangFlag3=false;//标识驱蚊蚊香3是否开始计时
	boolean xiangFlag4=false;//标识驱蚊蚊香4是否开始计时
	boolean xiangFlag5=false;//标识驱蚊蚊香5是否开始计时
	boolean xiangFlag6=false;//标识驱蚊蚊香6是否开始计时
	
	GameViewFour gameview;
	 public Handler mhandler;
	public TimeThread4(GameViewFour gameViewFour){
		this.gameview=gameViewFour;
		mhandler=new Handler(){
			public void handleMessage(Message msg){
				switch(msg.what){
				//case1,case2道具生成
				case 1:
					xiangFlag1=true;
					break;
				case 2:
					xiangFlag2=true;
					break;
				case 3:
					xiangFlag3=true;
					break;		
				case 4:
					xiangFlag4=true;
					break;
				case 5:
					xiangFlag5=true;
					break;
				case 6:
					xiangFlag6=true;
					break;		
				
				}
				
			}
		};
	}
	@Override
    public void run(){
	while(ToDate){
		
		//计时器，一秒种变化一次
		if(time==20){
			 gameview.timer.subtractTime(1);
			 time=0;
		}
		time++;

		if(bornWenzi==800){
			gameview.Wlist.add(gameview.createWenzi4(1, 1));
			gameview.Wlist.add(gameview.createWenzi5(1, 1));
		}
		
		bornWenzi++;
		
	try {
		Thread.sleep(50);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//前两只蚊子出现
	if(i==80){
		//gameview.isBornWenzi=true;
	}
	
	//第一关时间到，调用判断是否游戏胜利的方法
	if(i>3600){
		ToDate=false;
	}
	i++;

		
		//第一个蚊香道具
		if(xiangFlag1){
			ExpelIncense temp = gameview.incenseList.get(0);

			if(temp.level==1)
				xiangsteady1+=2;
				else 
				xiangsteady1+=1;

			//第二张图片
			if(xiangsteady1==30){
				temp.state = 1;
				gameview.incenseList.set(0, temp);
				}
			//第三张图片
			if(xiangsteady1==60){
				temp.state = 2;
				gameview.incenseList.set(0, temp);
				}
			//第四张图片
			if(xiangsteady1==90){
				temp.state = 3;
				gameview.incenseList.set(0, temp);
				}
			//持续时间
			if(xiangsteady1==120){
				gameview.mhandler.sendEmptyMessage(1);
				xiangFlag1=false;
			}
		}
		//第二个蚊香道具
				if(xiangFlag2){
					ExpelIncense temp = gameview.incenseList.get(1);

					if(temp.level==1)
						xiangsteady2+=2;
						else 
						xiangsteady2+=1;

					//第二张图片
					if(xiangsteady2==30){
						temp.state = 1;
						gameview.incenseList.set(1, temp);
						}
					//第三张图片
					if(xiangsteady2==60){
						temp.state = 2;
						gameview.incenseList.set(1, temp);
						}
					//第四张图片
					if(xiangsteady2==90){
						temp.state = 3;
						gameview.incenseList.set(1, temp);
						}
					//持续时间
					if(xiangsteady2==120){
						gameview.mhandler.sendEmptyMessage(2);
						xiangFlag2=false;
					}
				}
				//第三个蚊香道具
				if(xiangFlag3){
					ExpelIncense temp = gameview.incenseList.get(2);

					if(temp.level==1)
						xiangsteady3+=2;
						else 
						xiangsteady3+=1;

					//第二张图片
					if(xiangsteady3==30){
						temp.state = 1;
						gameview.incenseList.set(2, temp);
						}
					//第三张图片
					if(xiangsteady3==60){
						temp.state = 2;
						gameview.incenseList.set(2, temp);
						}
					//第四张图片
					if(xiangsteady3==90){
						temp.state = 3;
						gameview.incenseList.set(2, temp);
						}
					//持续时间
					if(xiangsteady3==120){
						gameview.mhandler.sendEmptyMessage(3);
						xiangFlag3=false;
					}
				}
				//第4个蚊香道具
				if(xiangFlag4){
					ExpelIncense temp = gameview.incenseList.get(3);

					if(temp.level==1)
						xiangsteady4+=2;
						else 
						xiangsteady4+=1;

					//第二张图片
					if(xiangsteady4==30){
						temp.state = 1;
						gameview.incenseList.set(3, temp);
						}
					//第三张图片
					if(xiangsteady4==60){
						temp.state = 2;
						gameview.incenseList.set(3, temp);
						}
					//第四张图片
					if(xiangsteady4==90){
						temp.state = 3;
						gameview.incenseList.set(3, temp);
						}
					//持续时间
					if(xiangsteady4==120){
						gameview.mhandler.sendEmptyMessage(3);
						xiangFlag4=false;
					}
				}
				//第5个蚊香道具
				if(xiangFlag5){
					ExpelIncense temp = gameview.incenseList.get(4);

					if(temp.level==1)
						xiangsteady5+=2;
						else 
						xiangsteady5+=1;

					//第二张图片
					if(xiangsteady5==30){
						temp.state = 1;
						gameview.incenseList.set(4, temp);
						}
					//第三张图片
					if(xiangsteady5==60){
						temp.state = 2;
						gameview.incenseList.set(4, temp);
						}
					//第四张图片
					if(xiangsteady5==90){
						temp.state = 3;
						gameview.incenseList.set(4, temp);
						}
					//持续时间
					if(xiangsteady5==120){
						gameview.mhandler.sendEmptyMessage(5);
						xiangFlag5=false;
					}
				}
				//第6个蚊香道具
				if(xiangFlag6){
					ExpelIncense temp = gameview.incenseList.get(5);

					if(temp.level==1)
						xiangsteady6+=2;
						else 
						xiangsteady6+=1;

					//第二张图片
					if(xiangsteady6==30){
						temp.state = 1;
						gameview.incenseList.set(5, temp);
						}
					//第三张图片
					if(xiangsteady6==60){
						temp.state = 2;
						gameview.incenseList.set(5, temp);
						}
					//第四张图片
					if(xiangsteady6==90){
						temp.state = 3;
						gameview.incenseList.set(5, temp);
						}
					//持续时间
					if(xiangsteady6==120){
						gameview.mhandler.sendEmptyMessage(6);
						xiangFlag6=false;
					}
				}
	
	
 }
}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public void setToDate(boolean flag){
		this.ToDate=flag;
	}
	
}
