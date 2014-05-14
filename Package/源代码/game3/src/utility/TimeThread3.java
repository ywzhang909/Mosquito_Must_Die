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

import com.userView.GameViewOne;
import com.userView.GameViewThree;
import com.userView.GameViewTwo;
/**
 * 
 * @author 山东大学赵宝琦、张霖、吕华富、于洪洋
 */
public class TimeThread3 extends Thread {
	int i=1;//计量关卡时间
	int time=0;
	int steady1=0;//计量驱蚊草1持续时间
	int steady2=0;//计量驱蚊草2持续时间
	int steady3=0;//计量驱蚊草3持续时间
	int steady4=0;//计量驱蚊草4持续时间
	int steady5=0;//计量驱蚊草5持续时间
	int steady6=0;//计量驱蚊草6持续时间
	
	int xiangsteady1=0;//计量蚊香1持续时间
	int xiangsteady2=0;//计量蚊香2持续时间
	int xiangsteady3=0;//计量蚊香3持续时间
	int xiangsteady4=0;//计量蚊香4持续时间
	
	boolean ToDate=true;//关卡时间
	boolean flag=true;
	boolean plantFlag1=false;//标识驱蚊驱蚊草1是否开始计时
	boolean plantFlag2=false;//标识驱蚊驱蚊草2是否开始计时
	boolean plantFlag3=false;//标识驱蚊驱蚊草3是否开始计时
	boolean plantFlag4=false;//标识驱蚊驱蚊草4是否开始计时
	boolean plantFlag5=false;//标识驱蚊驱蚊草5是否开始计时
	boolean plantFlag6=false;//标识驱蚊驱蚊草6是否开始计时
	
	boolean xiangFlag1=false;//标识驱蚊蚊香1是否开始计时
	boolean xiangFlag2=false;//标识驱蚊蚊香2是否开始计时
	boolean xiangFlag3=false;//标识驱蚊蚊香3是否开始计时
	boolean xiangFlag4=false;//标识驱蚊蚊香4是否开始计时
	
	
	int frogsteady=0;//计量青蛙持续时间的控制
	boolean frogFlagCD=false;//青蛙道具CD
	boolean frogLastTimeFlag=false;//青蛙道具持续时间的控制
	
	int spraysteady=0;//计量灭蚊剂持续时间的控制
	boolean sprayFlagCD=false;//灭蚊剂道具CD
	boolean sprayLastTimeFlag=false;//灭蚊剂道具持续时间的控制
	int bornWenzi = 0;//第二波蚊子出现时间

	GameViewThree gameview;
	 public Handler mhandler;
	public TimeThread3(GameViewThree gameview){
		this.gameview=gameview;
		mhandler=new Handler(){
			public void handleMessage(Message msg){
				switch(msg.what){
				//case1,case2道具生成
				case 1:
					plantFlag1=true;
					break;
				case 2:
					plantFlag2=true;
					break;
				case 3:
					plantFlag3=true;
					break;
				case 4:
					plantFlag4=true;
					break;
				case 5:
					plantFlag5=true;
					break;
				case 6:
					plantFlag6=true;
					break;			

					
				case 7:
					sprayFlagCD=true;
				case 8:
					sprayLastTimeFlag=true;
					break;
					
				case 9:
					xiangFlag1=true;
					break;
				case 10:
					xiangFlag2=true;
					break;
				case 11:
					xiangFlag3=true;
					break;
				case 12:
					xiangFlag4=true;
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
		

		if(bornWenzi==1000){
			gameview.Wlist.add(gameview.createWenzi2(1, 1));
			gameview.Wlist.add(gameview.createWenzi1(1, 1));
		}
		
		bornWenzi++;
		//灭蚊道具的cd控制
		if(frogFlagCD)
		{
			gameview.angle1+=1;
			gameview.cd1=true;
			gameview.cdFlag1=false;
			if(gameview.angle1>=360)
			{   gameview.cdFlag1=true;
				gameview.angle1=0;
				this.frogFlagCD=false;
				gameview.cd1=false;
			}
			
			
			gameview.changeShuijingXY1(gameview.angle1);
		}	
		
		if(sprayFlagCD)
		{
			gameview.angle2+=1;
			gameview.cd2=true;
			gameview.cdFlag2=false;
			if(gameview.angle2>=360)
			{   gameview.cdFlag2=true;
				gameview.angle2=0;
				this.sprayFlagCD=false;
				gameview.cd2=false;
			}
			
			
			gameview.changeShuijingXY2(gameview.angle2);
		}				
		
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
	//第一个驱蚊道具
	if(plantFlag1){
		
		ExpelPlant temp = gameview.expelPlantList.get(0);
		boolean a = false;
		if(temp!=null){
			a=true;
			Log.e("temp",""+a);
		}
			
		else 
			Log.e("temp",""+a);

		if(temp.level==1)
			steady1+=2;
			else 
			steady1++;

		//第二张图片
		if(steady1==30){
			temp.state = 1;
			gameview.expelPlantList.set(0, temp);
			}
		//第三张图片
		if(steady1==60){
			temp.state = 2;
			gameview.expelPlantList.set(0, temp);	
			}
		//第四张图片
		if(steady1==90){
			temp.state = 3;
			gameview.expelPlantList.set(0, temp);
			}
		//持续时间
		if(steady1==120){
			gameview.mhandler.sendEmptyMessage(1);
			plantFlag1=false;
		}
	}
	//第二个驱蚊道具
	if(plantFlag2){
		
		ExpelPlant temp = gameview.expelPlantList.get(1);
		if(temp.level==1)
			steady2+=2;
			else 
			steady2+=1;

		//第二张图片
		if(steady2==30){
			temp.state = 1;
			gameview.expelPlantList.set(1, temp);
		}
		//第三张图片
		if(steady2==60){
			temp.state = 2;
			gameview.expelPlantList.set(1, temp);
		}
		//第四张图片
		if(steady2==90){
			temp.state = 3;
			gameview.expelPlantList.set(1, temp);
		}
		//持续时间
		if(steady2==120){
			gameview.mhandler.sendEmptyMessage(2);
			plantFlag2=false;
		}
	}
	//第三个驱蚊道具
		if(plantFlag3){
			
			ExpelPlant temp = gameview.expelPlantList.get(2);

			if(temp!=null){
			if(temp.level==1)
				steady3+=2;
				else if(temp.level==2)
				steady3+=1;
			}

			//第二张图片
			if(steady3==30){
				temp.state = 1;
				gameview.expelPlantList.set(2, temp);
				}
			//第三张图片
			if(steady3==60){
				temp.state = 2;
				gameview.expelPlantList.set(2, temp);
				}
			//第四张图片
			if(steady3==90){
				temp.state = 3;
				gameview.expelPlantList.set(2, temp);
				}
			//持续时间
			if(steady3==120){
				gameview.mhandler.sendEmptyMessage(3);
				plantFlag3=false;
			}
		}
		//第四个驱蚊道具
		if(plantFlag4){
			ExpelPlant temp = gameview.expelPlantList.get(3);

			if(temp.level==1)
				steady4+=2;
				else 
				steady4+=1;

			//第二张图片
			if(steady4==30){
				temp.state = 1;
				gameview.expelPlantList.set(3, temp);
				}
			//第三张图片
			if(steady4==60){
				temp.state = 2;
				gameview.expelPlantList.set(3, temp);
				}
			//第四张图片
			if(steady4==90){
				temp.state = 3;
				gameview.expelPlantList.set(3, temp);
				}
			//持续时间
			if(steady4==120){
				gameview.mhandler.sendEmptyMessage(4);
				plantFlag4=false;
			}
		}
		//第五个驱蚊道具
		if(plantFlag5){
			ExpelPlant temp = gameview.expelPlantList.get(4);

			if(temp.level==1)
				steady5+=2;
				else 
				steady5+=1;

			//第二张图片
			if(steady5==30){
				temp.state = 1;
				gameview.expelPlantList.set(4, temp);
				}
			//第三张图片
			if(steady5==60){
				temp.state = 2;
				gameview.expelPlantList.set(4, temp);
				}
			//第四张图片
			if(steady5==90){
				temp.state = 3;
				gameview.expelPlantList.set(4, temp);
				}
			//持续时间
			if(steady5==120){
				gameview.mhandler.sendEmptyMessage(5);
				plantFlag5=false;
			}
		}
		//第六个驱蚊道具
		if(plantFlag6){
			ExpelPlant temp = gameview.expelPlantList.get(5);

			if(temp.level==1)
				steady6+=2;
				else 
				steady6+=1;

			//第二张图片
			if(steady6==30){
				temp.state = 1;
				gameview.expelPlantList.set(5, temp);
				}
			//第三张图片
			if(steady6==60){
				temp.state = 2;
				gameview.expelPlantList.set(5, temp);
				}
			//第四张图片
			if(steady6==90){
				temp.state = 3;
				gameview.expelPlantList.set(5, temp);
				}
			//持续时间
			if(steady6==120){
				gameview.mhandler.sendEmptyMessage(6);
				plantFlag6=false;
			}
		}
		
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
				gameview.mhandler.sendEmptyMessage(8);
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
						gameview.mhandler.sendEmptyMessage(9);
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
						gameview.mhandler.sendEmptyMessage(10);
						xiangFlag3=false;
					}
				}
				//第四个蚊香道具
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
						gameview.mhandler.sendEmptyMessage(11);
						xiangFlag4=false;
					}
				}
	//灭蚊剂持续时间控制
	if(sprayLastTimeFlag){
		killspray spray = gameview.sprayList.get(0);
		spraysteady++;
		
		//第二张图片
		if(spraysteady==10){
			spray.state = 1;
			gameview.sprayList.set(0, spray);
			}
		//第三张图片
		if(spraysteady==20){
			spray.state = 2;
			gameview.sprayList.set(0, spray);
			}
		//第四张图片
		if(spraysteady==30){
			sprayLastTimeFlag=false;
			spraysteady=0;
			gameview.mhandler.sendEmptyMessage(7);
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
