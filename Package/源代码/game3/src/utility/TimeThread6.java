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
import com.userView.GameViewSix;
import com.userView.GameViewThree;
import com.userView.GameViewTwo;
/**
 * 
 * @author 山东大学android实验室刘昭呈
 *
 */
public class TimeThread6 extends Thread {
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
	
	int xiangsteady1=0;//计量蚊香1持续时间
	int xiangsteady2=0;//计量蚊香2持续时间
	int xiangsteady3=0;//计量蚊香3持续时间
	int xiangsteady4=0;//计量蚊香4持续时间
	int xiangsteady5=0;//计量蚊香5持续时间
	int xiangsteady6=0;//计量蚊香6持续时间
	int xiangsteady7=0;//计量蚊香7持续时间
	int xiangsteady8=0;//计量蚊香8持续时间
	int xiangsteady9=0;//计量蚊香9持续时间
	int xiangsteady10=0;//计量蚊香10持续时间
	
	
	
	boolean ToDate=true;//关卡时间
	boolean flag=true;
	
	boolean plantFlag1=false;//标识驱蚊驱蚊草1是否开始计时
	boolean plantFlag2=false;//标识驱蚊驱蚊草2是否开始计时
	boolean plantFlag3=false;//标识驱蚊驱蚊草3是否开始计时
	boolean plantFlag4=false;//标识驱蚊驱蚊草4是否开始计时
	boolean plantFlag5=false;//标识驱蚊驱蚊草5是否开始计时
	boolean plantFlag6=false;//标识驱蚊驱蚊草6是否开始计时
	boolean plantFlag7=false;//标识驱蚊驱蚊草7是否开始计时
	boolean plantFlag8=false;//标识驱蚊驱蚊草8是否开始计时
	boolean plantFlag9=false;//标识驱蚊驱蚊草9是否开始计时
	boolean plantFlag10=false;//标识驱蚊驱蚊草10是否开始计时
	
	
	boolean xiangFlag1=false;//标识驱蚊蚊香1是否开始计时
	boolean xiangFlag2=false;//标识驱蚊蚊香2是否开始计时
	boolean xiangFlag3=false;//标识驱蚊蚊香3是否开始计时
	boolean xiangFlag4=false;//标识驱蚊蚊香4是否开始计时
	boolean xiangFlag5=false;//标识驱蚊蚊香5是否开始计时
	boolean xiangFlag6=false;//标识驱蚊蚊香6是否开始计时
	boolean xiangFlag7=false;//标识驱蚊蚊香7是否开始计时
	boolean xiangFlag8=false;//标识驱蚊蚊香8是否开始计时
	boolean xiangFlag9=false;//标识驱蚊蚊香9是否开始计时
	boolean xiangFlag10=false;//标识驱蚊蚊香10是否开始计时
	
	
	
	
	GameViewSix gameview;
	 public Handler mhandler;
	public TimeThread6(GameViewSix gameViewSix){
		this.gameview=gameViewSix;
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
					plantFlag7=true;
					break;
				case 8:
					plantFlag8=true;
					break;					
				case 9:
					plantFlag9=true;
					break;
				case 10:
					plantFlag10=true;
					break;
					
					
				case 11:
					xiangFlag1=true;
					break;
				case 12:
					xiangFlag2=true;
					break;		
				case 13:
					xiangFlag3=true;
					break;
				case 14:
					xiangFlag4=true;
					break;
				case 15:
					xiangFlag5=true;
					break;
				case 16:
					xiangFlag6=true;
					break;							
				case 17:
					xiangFlag7=true;
					break;							
				case 18:
					xiangFlag8=true;
					break;					
				case 19:
					xiangFlag9=true;
					break;
				case 20:
					xiangFlag10=true;
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
		//灭蚊道具的cd控制
		
		
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
		Log.e("plant", ""+plantFlag1);
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
		//第七个驱蚊道具
			if(plantFlag7){
					ExpelPlant temp = gameview.expelPlantList.get(6);

					if(temp.level==1)
						steady7+=2;
						else 
						steady7+=1;

					//第二张图片
					if(steady7==30){
						temp.state = 1;
						gameview.expelPlantList.set(6, temp);
						}
					//第三张图片
					if(steady7==60){
						temp.state = 2;
						gameview.expelPlantList.set(6, temp);
						}
					//第四张图片
					if(steady7==90){
						temp.state = 3;
						gameview.expelPlantList.set(6, temp);
						}
					//持续时间
					if(steady7==120){
						gameview.mhandler.sendEmptyMessage(7);
						plantFlag7=false;
					}
				}
				
				//第八个驱蚊道具
				if(plantFlag8){
					
					ExpelPlant temp = gameview.expelPlantList.get(7);

					if(temp.level==1)
						steady8+=2;
						else 
							steady8+=1;

					//第二张图片
					if(steady8==30){
						temp.state = 1;
						gameview.expelPlantList.set(7, temp);
						}
					//第三张图片
					if(steady8==60){
						temp.state = 2;
						gameview.expelPlantList.set(7, temp);
						}
					//第四张图片
					if(steady8==90){
						temp.state = 3;
						gameview.expelPlantList.set(7, temp);
						}
					//持续时间
					if(steady8==120){
						gameview.mhandler.sendEmptyMessage(8);
						plantFlag8=false;
					}
				}
				
				//第九个驱蚊道具
				if(plantFlag9){
					ExpelPlant temp = gameview.expelPlantList.get(8);

					if(temp.level==1)
						steady9+=2;
						else 
							steady9+=1;

					//第二张图片
					if(steady9==30){
						temp.state = 1;
						gameview.expelPlantList.set(8, temp);
						}
					//第三张图片
					if(steady9==60){
						temp.state = 2;
						gameview.expelPlantList.set(8, temp);
						}
					//第四张图片
					if(steady9==90){
						temp.state = 3;
						gameview.expelPlantList.set(8, temp);
						}
					//持续时间
					if(steady9==120){
						gameview.mhandler.sendEmptyMessage(9);
						plantFlag9=false;
					}
				}
				
				//第十个驱蚊道具
				if(plantFlag10){
					ExpelPlant temp = gameview.expelPlantList.get(9);

					if(temp.level==1)
						steady10+=2;
						else 
							steady10+=1;

					//第二张图片
					if(steady10==30){
						temp.state = 1;
						gameview.expelPlantList.set(9, temp);
						}
					//第三张图片
					if(steady10==60){
						temp.state = 2;
						gameview.expelPlantList.set(9, temp);
						}
					//第四张图片
					if(steady10==90){
						temp.state = 3;
						gameview.expelPlantList.set(9, temp);
						}
					//持续时间
					if(steady10==120){
						gameview.mhandler.sendEmptyMessage(10);
						plantFlag10=false;
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
				gameview.mhandler.sendEmptyMessage(11);
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
						gameview.mhandler.sendEmptyMessage(12);
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
						gameview.mhandler.sendEmptyMessage(13);
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
						gameview.mhandler.sendEmptyMessage(14);
						xiangFlag4=false;
					}
				}
				
				
				
				//第五个蚊香道具
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
						gameview.mhandler.sendEmptyMessage(15);
						xiangFlag5=false;
					}
				}
				
				//第六个蚊香道具
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
						gameview.mhandler.sendEmptyMessage(16);
						xiangFlag6=false;
					}
				}
				//第七个蚊香道具
				if(xiangFlag7){
					ExpelIncense temp = gameview.incenseList.get(6);

					if(temp.level==1)
						xiangsteady7+=2;
						else 
							xiangsteady7+=1;

					//第二张图片
					if(xiangsteady7==30){
						temp.state = 1;
						gameview.incenseList.set(6, temp);
						}
					//第三张图片
					if(xiangsteady7==60){
						temp.state = 2;
						gameview.incenseList.set(6, temp);
						}
					//第四张图片
					if(xiangsteady7==90){
						temp.state = 3;
						gameview.incenseList.set(6, temp);
						}
					//持续时间
					if(xiangsteady7==120){
						gameview.mhandler.sendEmptyMessage(17);
						xiangFlag7=false;
					}
				}
				//第八个蚊香道具
				if(xiangFlag8){
					ExpelIncense temp = gameview.incenseList.get(7);

					if(temp.level==1)
						xiangsteady8+=2;
						else 
							xiangsteady8+=1;

					//第二张图片
					if(xiangsteady8==30){
						temp.state = 1;
						gameview.incenseList.set(7, temp);
						}
					//第三张图片
					if(xiangsteady8==60){
						temp.state = 2;
						gameview.incenseList.set(7, temp);
						}
					//第四张图片
					if(xiangsteady8==90){
						temp.state = 3;
						gameview.incenseList.set(7, temp);
						}
					//持续时间
					if(xiangsteady8==120){
						gameview.mhandler.sendEmptyMessage(18);
						xiangFlag8=false;
					}
				}
				
				//第九个蚊香道具
				if(xiangFlag9){
					ExpelIncense temp = gameview.incenseList.get(8);

					if(temp.level==1)
						xiangsteady9+=2;
						else 
							xiangsteady9+=1;

					//第二张图片
					if(xiangsteady9==30){
						temp.state = 1;
						gameview.incenseList.set(8, temp);
						}
					//第三张图片
					if(xiangsteady9==60){
						temp.state = 2;
						gameview.incenseList.set(8, temp);
						}
					//第四张图片
					if(xiangsteady9==90){
						temp.state = 3;
						gameview.incenseList.set(8, temp);
						}
					//持续时间
					if(xiangsteady9==120){
						gameview.mhandler.sendEmptyMessage(19);
						xiangFlag9=false;
					}
				}
				
				//第十个蚊香道具
				if(xiangFlag10){
					ExpelIncense temp = gameview.incenseList.get(9);

					if(temp.level==1)
						xiangsteady10+=2;
						else 
							xiangsteady10+=1;

					//第二张图片
					if(xiangsteady10==30){
						temp.state = 1;
						gameview.incenseList.set(9, temp);
						}
					//第三张图片
					if(xiangsteady10==60){
						temp.state = 2;
						gameview.incenseList.set(9, temp);
						}
					//第四张图片
					if(xiangsteady10==90){
						temp.state = 3;
						gameview.incenseList.set(9, temp);
						}
					//持续时间
					if(xiangsteady10==120){
						gameview.mhandler.sendEmptyMessage(20);
						xiangFlag10=false;
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
