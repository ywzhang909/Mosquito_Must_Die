package utility;

import java.util.ArrayList;

import mosquito.Mosquito;

import tool.ExpelIncense;
import tool.ExpelPlant;
import tool.Frog;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.userView.GameViewOne;
/**
 * 
 * @author 山东大学android实验室刘昭呈
 *
 */
public class TimeThread extends Thread {
	int i=1;//计量关卡时间
	int time=0;
	int steady1=0;//计量驱蚊草1持续时间
	int steady2=0;//计量驱蚊草2持续时间
	int steady3=0;//计量驱蚊草3持续时间
	int steady4=0;//计量驱蚊草4持续时间
	int steady5=0;//计量驱蚊草5持续时间
	int steady6=0;//计量驱蚊草6持续时间
	int killersteady=0;//计量灭蚊灯持续时间的控制
	int bornWenzi = 0;//第二波蚊子出现时间
	
	
	boolean ToDate=true;//关卡时间
	boolean flag=true;
	boolean daojuFlag1=false;//标识驱蚊道具一是否开始计时
	boolean daojuFlag2=false;//标识驱蚊道具二是否开始计时
	boolean daojuFlag3=false;//标识驱蚊道具三是否开始计时
	boolean daojuFlag4=false;//标识驱蚊道具四是否开始计时
	boolean daojuFlag5=false;//标识驱蚊道具五是否开始计时
	boolean daojuFlag6=false;//标识驱蚊道具六是否开始计时
	
	boolean killerFlagCD=false;//杀蚊子道具CD
	boolean killerLastTimeFlag=false;//杀蚊子道具持续时间的控制
	GameViewOne gameview;
	public Handler mhandler;
	public TimeThread(GameViewOne gameview){
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
				killerFlagCD=true;
			case 8:
				killerLastTimeFlag=true;
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
		
		if(bornWenzi==300){
			gameview.Wlist.add(gameview.createWenzi1(1, 1));
			gameview.Wlist.add(gameview.createWenzi2(1, 1));

		}
		
		bornWenzi++;
		//灭蚊道具的cd控制
		if(killerFlagCD)
		{
			gameview.angle+=2;
			gameview.cd=true;
			gameview.cdFlag=false;
			if(gameview.angle>=360)
			{   gameview.cdFlag=true;
				gameview.angle=0;
				this.killerFlagCD=false;
				gameview.cd=false;
			}
			
			
			gameview.changeShuijingXY(gameview.angle);
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
		
	
	//青蛙持续时间控制
	if(killerLastTimeFlag){
		Frog frog = gameview.frogList.get(0);
		//青蛙步数控制
		if(killersteady==0){
			gameview.step=0;
			

		}
		if(killersteady==10){
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
		if(killersteady==20){
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
		if(killersteady==30){
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
		if(killersteady==40){
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
		if(killersteady==50){
			
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
		if(killersteady==60){
			
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
		if(killersteady==70){
			
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
		if(killersteady==80){
			
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
		if(killersteady==90){
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
		if(killersteady==100){
			
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
		if(killersteady==110){
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
		if(killersteady==120){
		
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
		if(killersteady==130){
			
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
		if(killersteady>=140){
			killerLastTimeFlag=false;
			gameview.FrogSteady=true;
			killersteady=0;
			gameview.mhandler.sendEmptyMessage(8);

		}
		killersteady++;
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
