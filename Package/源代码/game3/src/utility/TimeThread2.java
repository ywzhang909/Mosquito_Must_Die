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
import com.userView.GameViewTwo;
/**
 * 
 * @author 山东大学android实验室刘昭呈
 *
 */
public class TimeThread2 extends Thread {
	int i=1;//计量关卡时间
	int time=0;
	int steady1=0;//计量驱蚊草1持续时间
	int steady2=0;//计量驱蚊草2持续时间
	int steady3=0;//计量驱蚊草3持续时间
	int steady4=0;//计量驱蚊草4持续时间
	int steady5=0;//计量驱蚊草5持续时间
	int steady6=0;//计量驱蚊草6持续时间
	int bornWenzi = 0;//第二波蚊子出现时间

	boolean ToDate=true;//关卡时间
	boolean flag=true;
	boolean daojuFlag1=false;//标识驱蚊道具一是否开始计时
	boolean daojuFlag2=false;//标识驱蚊道具二是否开始计时
	boolean daojuFlag3=false;//标识驱蚊道具三是否开始计时
	boolean daojuFlag4=false;//标识驱蚊道具四是否开始计时
	boolean daojuFlag5=false;//标识驱蚊道具五是否开始计时
	boolean daojuFlag6=false;//标识驱蚊道具六是否开始计时
	
	int frogsteady=0;//计量灭蚊灯持续时间的控制
	boolean frogFlagCD=false;//青蛙道具CD
	boolean frogLastTimeFlag=false;//青蛙道具持续时间的控制
	
	int spraysteady=0;//计量灭蚊剂持续时间的控制
	boolean sprayFlagCD=false;//灭蚊剂道具CD
	boolean sprayLastTimeFlag=false;//灭蚊剂道具持续时间的控制
	
	GameViewTwo gameview;
	 public Handler mhandler;
	public TimeThread2(GameViewTwo gameview){
		this.gameview=gameview;

 mhandler=new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			//case1,case2道具生成
			case 1:
				daojuFlag1=true;
				break;
			case 2:
				daojuFlag2=true;
				break;
			case 3:
				daojuFlag3=true;
				break;
			case 4:
				daojuFlag4=true;
				break;
			case 5:
				daojuFlag5=true;
				break;
			case 6:
				daojuFlag6=true;
				break;			
			case 7:
				frogFlagCD=true;
			case 8:
				frogLastTimeFlag=true;
				break;
				
			case 9:
				sprayFlagCD=true;
			case 10:
				sprayLastTimeFlag=true;
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
			gameview.Wlist.add(gameview.createWenzi4(1, 1));
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
	if(daojuFlag1){
		
		ExpelPlant temp = gameview.expelPlantList.get(0);
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
			daojuFlag1=false;
		}
	}
	//第二个驱蚊道具
	if(daojuFlag2){
		
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
			daojuFlag2=false;
		}
	}
	//第三个驱蚊道具
		if(daojuFlag3){
			
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
				daojuFlag3=false;
			}
		}
		//第四个驱蚊道具
		if(daojuFlag4){
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
				daojuFlag4=false;
			}
		}
		//第五个驱蚊道具
		if(daojuFlag5){
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
				daojuFlag5=false;
			}
		}
		//第六个驱蚊道具
		if(daojuFlag6){
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
				daojuFlag6=false;
			}
		}
		
	
	//灭蚊剂持续时间控制
	if(sprayLastTimeFlag){
		killspray spray = gameview.sprayList.get(0);
		if(spray.level==1)
		spraysteady++;
		if(spray.level==2)
			spraysteady+=2;
		
		//第二张图片
		if(spraysteady==30){
			spray.state = 1;
			gameview.sprayList.set(0, spray);
			}
		//第三张图片
		if(spraysteady==60){
			spray.state = 2;
			gameview.sprayList.set(0, spray);
			}
		//第四张图片
		if(spraysteady==90){
			sprayLastTimeFlag=false;
			spraysteady=0;
			gameview.mhandler.sendEmptyMessage(7);
			}
		
		
	}
	
	//青蛙持续时间控制
		if(frogLastTimeFlag){
			Frog frog = gameview.frogList.get(0);
			//青蛙步数控制
			if(frogsteady==0){
				gameview.step=0;
				

			}
			if(frogsteady==15){
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=1;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}

			}
			if(frogsteady==30){
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=2;			
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==45){
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=3;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==60){
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=4;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==75){
				
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=5;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==90){
				
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=6;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==105){
				
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=7;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==120){
				
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=8;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==135){
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=9;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==150){
				
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=10;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==165){
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=11;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==180){
			
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=12;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==195){
				
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=13;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady>=210){
				frogLastTimeFlag=false;
				gameview.FrogSteady=true;
				frogsteady=0;
				gameview.mhandler.sendEmptyMessage(8);

			}
			frogsteady++;
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
