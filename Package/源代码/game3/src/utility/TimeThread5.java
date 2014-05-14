package utility;

import java.util.ArrayList;

import mosquito.Mosquito;

import tool.ExpelIncense;
import tool.ExpelPlant;
import tool.Frog;
import tool.SpiderWeb;
import tool.killspray;

import android.os.Handler;
import android.os.Message;

import com.userView.GameViewFive;

/**
 * 
 * @author 山东大学赵宝琦、张霖、吕华富、于洪洋
 *
 */
public class TimeThread5 extends Thread {
	int i=1;//计量关卡时间
	int time=0;
	int steady1=0;//计量驱蚊草1持续时间
	int steady2=0;//计量驱蚊草2持续时间
	int steady3=0;//计量驱蚊草3持续时间
	int steady4=0;//计量驱蚊草4持续时间
	int steady5=0;//计量驱蚊草5持续时间
	int steady6=0;//计量驱蚊草6持续时间
	int steady7=0;//计量驱蚊草7持续时间
	int steady8=0;//计量驱蚊草8持续时间
	int steady9=0;//计量驱蚊草9持续时间
	int steady10=0;//计量驱蚊草10持续时间
	
	
	int bornWenzi = 0;//第二波蚊子出现时间

	boolean ToDate=true;//关卡时间
	boolean flag=true;
	boolean daojuFlag1=false;//标识驱蚊道具一是否开始计时
	boolean daojuFlag2=false;//标识驱蚊道具二是否开始计时
	boolean daojuFlag3=false;//标识驱蚊道具三是否开始计时
	boolean daojuFlag4=false;//标识驱蚊道具四是否开始计时
	boolean daojuFlag5=false;//标识驱蚊道具五是否开始计时
	boolean daojuFlag6=false;//标识驱蚊道具六是否开始计时
	boolean daojuFlag7=false;//标识驱蚊道具三是否开始计时
	boolean daojuFlag8=false;//标识驱蚊道具四是否开始计时
	boolean daojuFlag9=false;//标识驱蚊道具五是否开始计时
	boolean daojuFlag10=false;//标识驱蚊道具六是否开始计时
	
	int swatterSteady=0;//计量灭蚊灯持续时间的控制
	boolean swatterFlagCD=false;//青蛙道具CD
	boolean swatterLastTimeFlag=false;//青蛙道具持续时间的控制
	
	int spraysteady=0;//计量灭蚊剂持续时间的控制
	boolean sprayFlagCD=false;//灭蚊剂道具CD
	boolean sprayLastTimeFlag=false;//灭蚊剂道具持续时间的控制
	
	GameViewFive gameview;
	 public Handler mhandler;
	public TimeThread5(GameViewFive gameViewFive){
		this.gameview=gameViewFive;

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
				daojuFlag7=true;
				break;
			case 8:
				daojuFlag8=true;
				break;
			case 9:
				daojuFlag9=true;
				break;
			case 10:
				daojuFlag10=true;
				break;		
				
			case 11:
				swatterFlagCD=true;
			case 12:
				swatterLastTimeFlag=true;
				break;
				
			case 13:
				sprayFlagCD=true;
			case 14:
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
			gameview.Wlist.add(gameview.createWenzi1(1, 1));
			gameview.Wlist.add(gameview.createWenzi3(1, 1));
			gameview.Wlist.add(gameview.createWenzi4(1, 1));
			gameview.Wlist.add(gameview.createWenzi5(1, 1));
		}
		
		bornWenzi++;
		
		//灭蚊道具的cd控制
		if(swatterFlagCD)
		{
			gameview.angle1+=1;
			gameview.cd1=true;
			gameview.cdFlag1=false;
			if(gameview.angle1>=360)
			{   gameview.cdFlag1=true;
				gameview.angle1=0;
				this.swatterFlagCD=false;
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
		
		ExpelIncense temp = gameview.incenseList.get(0);
		if(temp.level==1)
			steady1+=2;
			else 
			steady1++;

		//第二张图片
		if(steady1==30){
			temp.state = 1;
			gameview.incenseList.set(0, temp);
			}
		//第三张图片
		if(steady1==60){
			temp.state = 2;
			gameview.incenseList.set(0, temp);	
			}
		//第四张图片
		if(steady1==90){
			temp.state = 3;
			gameview.incenseList.set(0, temp);
			}
		//持续时间
		if(steady1==120){
			gameview.mhandler.sendEmptyMessage(1);
			daojuFlag1=false;
		}
	}
	//第二个驱蚊道具
	if(daojuFlag2){
		
		ExpelIncense temp = gameview.incenseList.get(1);
		if(temp.level==1)
			steady2+=2;
			else 
			steady2+=1;

		//第二张图片
		if(steady2==30){
			temp.state = 1;
			gameview.incenseList.set(1, temp);
		}
		//第三张图片
		if(steady2==60){
			temp.state = 2;
			gameview.incenseList.set(1, temp);
		}
		//第四张图片
		if(steady2==90){
			temp.state = 3;
			gameview.incenseList.set(1, temp);
		}
		//持续时间
		if(steady2==120){
			gameview.mhandler.sendEmptyMessage(2);
			daojuFlag2=false;
		}
	}
	//第三个驱蚊道具
		if(daojuFlag3){
			
			ExpelIncense temp = gameview.incenseList.get(2);

			if(temp!=null){
			if(temp.level==1)
				steady3+=2;
				else if(temp.level==2)
				steady3+=1;
			}

			//第二张图片
			if(steady3==30){
				temp.state = 1;
				gameview.incenseList.set(2, temp);
				}
			//第三张图片
			if(steady3==60){
				temp.state = 2;
				gameview.incenseList.set(2, temp);
				}
			//第四张图片
			if(steady3==90){
				temp.state = 3;
				gameview.incenseList.set(2, temp);
				}
			//持续时间
			if(steady3==120){
				gameview.mhandler.sendEmptyMessage(3);
				daojuFlag3=false;
			}
		}
		//第四个驱蚊道具
		if(daojuFlag4){
			ExpelIncense temp = gameview.incenseList.get(3);

			if(temp.level==1)
				steady4+=2;
				else 
				steady4+=1;

			//第二张图片
			if(steady4==30){
				temp.state = 1;
				gameview.incenseList.set(3, temp);
				}
			//第三张图片
			if(steady4==60){
				temp.state = 2;
				gameview.incenseList.set(3, temp);
				}
			//第四张图片
			if(steady4==90){
				temp.state = 3;
				gameview.incenseList.set(3, temp);
				}
			//持续时间
			if(steady4==120){
				gameview.mhandler.sendEmptyMessage(4);
				daojuFlag4=false;
			}
		}
		//第五个驱蚊道具
		if(daojuFlag5){
			ExpelIncense temp = gameview.incenseList.get(4);

			if(temp.level==1)
				steady5+=2;
				else 
				steady5+=1;

			//第二张图片
			if(steady5==30){
				temp.state = 1;
				gameview.incenseList.set(4, temp);
				}
			//第三张图片
			if(steady5==60){
				temp.state = 2;
				gameview.incenseList.set(4, temp);
				}
			//第四张图片
			if(steady5==90){
				temp.state = 3;
				gameview.incenseList.set(4, temp);
				}
			//持续时间
			if(steady5==120){
				gameview.mhandler.sendEmptyMessage(5);
				daojuFlag5=false;
			}
		}
		//第六个驱蚊道具
		if(daojuFlag6){
			ExpelIncense temp = gameview.incenseList.get(5);

			if(temp.level==1)
				steady6+=2;
				else 
				steady6+=1;

			//第二张图片
			if(steady6==30){
				temp.state = 1;
				gameview.incenseList.set(5, temp);
				}
			//第三张图片
			if(steady6==60){
				temp.state = 2;
				gameview.incenseList.set(5, temp);
				}
			//第四张图片
			if(steady6==90){
				temp.state = 3;
				gameview.incenseList.set(5, temp);
				}
			//持续时间
			if(steady6==120){
				gameview.mhandler.sendEmptyMessage(6);
				daojuFlag6=false;
			}
		}
		//第七个驱蚊道具
				if(daojuFlag7){
					ExpelIncense temp = gameview.incenseList.get(6);

					if(temp.level==1)
						steady7+=2;
						else 
						steady7+=1;

					//第二张图片
					if(steady7==30){
						temp.state = 1;
						gameview.incenseList.set(6, temp);
						}
					//第三张图片
					if(steady7==60){
						temp.state = 2;
						gameview.incenseList.set(6, temp);
						}
					//第四张图片
					if(steady7==90){
						temp.state = 3;
						gameview.incenseList.set(6, temp);
						}
					//持续时间
					if(steady7==120){
						gameview.mhandler.sendEmptyMessage(7);
						daojuFlag7=false;
					}
				}
				
				//第八个驱蚊道具
				if(daojuFlag8){
					ExpelIncense temp = gameview.incenseList.get(7);

					if(temp.level==1)
						steady8+=2;
						else 
							steady8+=1;

					//第二张图片
					if(steady8==30){
						temp.state = 1;
						gameview.incenseList.set(7, temp);
						}
					//第三张图片
					if(steady8==60){
						temp.state = 2;
						gameview.incenseList.set(7, temp);
						}
					//第四张图片
					if(steady8==90){
						temp.state = 3;
						gameview.incenseList.set(7, temp);
						}
					//持续时间
					if(steady8==120){
						gameview.mhandler.sendEmptyMessage(8);
						daojuFlag8=false;
					}
				}
				
				//第九个驱蚊道具
				if(daojuFlag9){
					ExpelIncense temp = gameview.incenseList.get(8);

					if(temp.level==1)
						steady9+=2;
						else 
							steady9+=1;

					//第二张图片
					if(steady9==30){
						temp.state = 1;
						gameview.incenseList.set(8, temp);
						}
					//第三张图片
					if(steady9==60){
						temp.state = 2;
						gameview.incenseList.set(8, temp);
						}
					//第四张图片
					if(steady9==90){
						temp.state = 3;
						gameview.incenseList.set(8, temp);
						}
					//持续时间
					if(steady9==120){
						gameview.mhandler.sendEmptyMessage(9);
						daojuFlag9=false;
					}
				}
				
				//第十个驱蚊道具
				if(daojuFlag10){
					ExpelIncense temp = gameview.incenseList.get(9);

					if(temp.level==1)
						steady10+=2;
						else 
							steady10+=1;

					//第二张图片
					if(steady10==30){
						temp.state = 1;
						gameview.incenseList.set(9, temp);
						}
					//第三张图片
					if(steady10==60){
						temp.state = 2;
						gameview.incenseList.set(9, temp);
						}
					//第四张图片
					if(steady10==90){
						temp.state = 3;
						gameview.incenseList.set(9, temp);
						}
					//持续时间
					if(steady10==120){
						gameview.mhandler.sendEmptyMessage(10);
						daojuFlag10=false;
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
		if(spraysteady==90){
			sprayLastTimeFlag=false;
			spraysteady=0;
			gameview.mhandler.sendEmptyMessage(11);
			}
		
		
	}
	
	//蜘蛛网持续时间控制
		if(swatterLastTimeFlag){
			SpiderWeb swatter = gameview.webList.get(0);
			if(swatter.level==1)
				swatterSteady++;
				if(swatter.level==2)
					swatterSteady+=2;
			
			//第二张图片
			if(swatterSteady==40){
				swatter.state = 1;
				gameview.webList.set(0, swatter);
				}
			//第三张图片
			if(swatterSteady==80){
				swatter.state = 2;
				gameview.webList.set(0, swatter);
				}
			//第四张图片
			if(swatterSteady==120){
				swatter.state = 3;
				gameview.webList.set(0, swatter);
				}
			
			if(swatterSteady==160){
				swatterLastTimeFlag=false;
				swatterSteady=0;
				gameview.mhandler.sendEmptyMessage(12);
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
