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
 * @author ɽ����ѧ�Ա��������ء����������ں���
 *
 */
public class TimeThread5 extends Thread {
	int i=1;//�����ؿ�ʱ��
	int time=0;
	int steady1=0;//�������ò�1����ʱ��
	int steady2=0;//�������ò�2����ʱ��
	int steady3=0;//�������ò�3����ʱ��
	int steady4=0;//�������ò�4����ʱ��
	int steady5=0;//�������ò�5����ʱ��
	int steady6=0;//�������ò�6����ʱ��
	int steady7=0;//�������ò�7����ʱ��
	int steady8=0;//�������ò�8����ʱ��
	int steady9=0;//�������ò�9����ʱ��
	int steady10=0;//�������ò�10����ʱ��
	
	
	int bornWenzi = 0;//�ڶ������ӳ���ʱ��

	boolean ToDate=true;//�ؿ�ʱ��
	boolean flag=true;
	boolean daojuFlag1=false;//��ʶ���õ���һ�Ƿ�ʼ��ʱ
	boolean daojuFlag2=false;//��ʶ���õ��߶��Ƿ�ʼ��ʱ
	boolean daojuFlag3=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	boolean daojuFlag4=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	boolean daojuFlag5=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	boolean daojuFlag6=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	boolean daojuFlag7=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	boolean daojuFlag8=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	boolean daojuFlag9=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	boolean daojuFlag10=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	
	int swatterSteady=0;//�������õƳ���ʱ��Ŀ���
	boolean swatterFlagCD=false;//���ܵ���CD
	boolean swatterLastTimeFlag=false;//���ܵ��߳���ʱ��Ŀ���
	
	int spraysteady=0;//�������ü�����ʱ��Ŀ���
	boolean sprayFlagCD=false;//���ü�����CD
	boolean sprayLastTimeFlag=false;//���ü����߳���ʱ��Ŀ���
	
	GameViewFive gameview;
	 public Handler mhandler;
	public TimeThread5(GameViewFive gameViewFive){
		this.gameview=gameViewFive;

 mhandler=new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			//case1,case2��������
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
		
		//��ʱ����һ���ֱ仯һ��
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
		
		//���õ��ߵ�cd����
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
	//ǰ��ֻ���ӳ���
	if(i==80){
		//gameview.isBornWenzi=true;
	}
	
	//��һ��ʱ�䵽�������ж��Ƿ���Ϸʤ���ķ���
	if(i>3600){
		ToDate=false;
	}
	i++;
	//��һ�����õ���
	if(daojuFlag1){
		
		ExpelIncense temp = gameview.incenseList.get(0);
		if(temp.level==1)
			steady1+=2;
			else 
			steady1++;

		//�ڶ���ͼƬ
		if(steady1==30){
			temp.state = 1;
			gameview.incenseList.set(0, temp);
			}
		//������ͼƬ
		if(steady1==60){
			temp.state = 2;
			gameview.incenseList.set(0, temp);	
			}
		//������ͼƬ
		if(steady1==90){
			temp.state = 3;
			gameview.incenseList.set(0, temp);
			}
		//����ʱ��
		if(steady1==120){
			gameview.mhandler.sendEmptyMessage(1);
			daojuFlag1=false;
		}
	}
	//�ڶ������õ���
	if(daojuFlag2){
		
		ExpelIncense temp = gameview.incenseList.get(1);
		if(temp.level==1)
			steady2+=2;
			else 
			steady2+=1;

		//�ڶ���ͼƬ
		if(steady2==30){
			temp.state = 1;
			gameview.incenseList.set(1, temp);
		}
		//������ͼƬ
		if(steady2==60){
			temp.state = 2;
			gameview.incenseList.set(1, temp);
		}
		//������ͼƬ
		if(steady2==90){
			temp.state = 3;
			gameview.incenseList.set(1, temp);
		}
		//����ʱ��
		if(steady2==120){
			gameview.mhandler.sendEmptyMessage(2);
			daojuFlag2=false;
		}
	}
	//���������õ���
		if(daojuFlag3){
			
			ExpelIncense temp = gameview.incenseList.get(2);

			if(temp!=null){
			if(temp.level==1)
				steady3+=2;
				else if(temp.level==2)
				steady3+=1;
			}

			//�ڶ���ͼƬ
			if(steady3==30){
				temp.state = 1;
				gameview.incenseList.set(2, temp);
				}
			//������ͼƬ
			if(steady3==60){
				temp.state = 2;
				gameview.incenseList.set(2, temp);
				}
			//������ͼƬ
			if(steady3==90){
				temp.state = 3;
				gameview.incenseList.set(2, temp);
				}
			//����ʱ��
			if(steady3==120){
				gameview.mhandler.sendEmptyMessage(3);
				daojuFlag3=false;
			}
		}
		//���ĸ����õ���
		if(daojuFlag4){
			ExpelIncense temp = gameview.incenseList.get(3);

			if(temp.level==1)
				steady4+=2;
				else 
				steady4+=1;

			//�ڶ���ͼƬ
			if(steady4==30){
				temp.state = 1;
				gameview.incenseList.set(3, temp);
				}
			//������ͼƬ
			if(steady4==60){
				temp.state = 2;
				gameview.incenseList.set(3, temp);
				}
			//������ͼƬ
			if(steady4==90){
				temp.state = 3;
				gameview.incenseList.set(3, temp);
				}
			//����ʱ��
			if(steady4==120){
				gameview.mhandler.sendEmptyMessage(4);
				daojuFlag4=false;
			}
		}
		//��������õ���
		if(daojuFlag5){
			ExpelIncense temp = gameview.incenseList.get(4);

			if(temp.level==1)
				steady5+=2;
				else 
				steady5+=1;

			//�ڶ���ͼƬ
			if(steady5==30){
				temp.state = 1;
				gameview.incenseList.set(4, temp);
				}
			//������ͼƬ
			if(steady5==60){
				temp.state = 2;
				gameview.incenseList.set(4, temp);
				}
			//������ͼƬ
			if(steady5==90){
				temp.state = 3;
				gameview.incenseList.set(4, temp);
				}
			//����ʱ��
			if(steady5==120){
				gameview.mhandler.sendEmptyMessage(5);
				daojuFlag5=false;
			}
		}
		//���������õ���
		if(daojuFlag6){
			ExpelIncense temp = gameview.incenseList.get(5);

			if(temp.level==1)
				steady6+=2;
				else 
				steady6+=1;

			//�ڶ���ͼƬ
			if(steady6==30){
				temp.state = 1;
				gameview.incenseList.set(5, temp);
				}
			//������ͼƬ
			if(steady6==60){
				temp.state = 2;
				gameview.incenseList.set(5, temp);
				}
			//������ͼƬ
			if(steady6==90){
				temp.state = 3;
				gameview.incenseList.set(5, temp);
				}
			//����ʱ��
			if(steady6==120){
				gameview.mhandler.sendEmptyMessage(6);
				daojuFlag6=false;
			}
		}
		//���߸����õ���
				if(daojuFlag7){
					ExpelIncense temp = gameview.incenseList.get(6);

					if(temp.level==1)
						steady7+=2;
						else 
						steady7+=1;

					//�ڶ���ͼƬ
					if(steady7==30){
						temp.state = 1;
						gameview.incenseList.set(6, temp);
						}
					//������ͼƬ
					if(steady7==60){
						temp.state = 2;
						gameview.incenseList.set(6, temp);
						}
					//������ͼƬ
					if(steady7==90){
						temp.state = 3;
						gameview.incenseList.set(6, temp);
						}
					//����ʱ��
					if(steady7==120){
						gameview.mhandler.sendEmptyMessage(7);
						daojuFlag7=false;
					}
				}
				
				//�ڰ˸����õ���
				if(daojuFlag8){
					ExpelIncense temp = gameview.incenseList.get(7);

					if(temp.level==1)
						steady8+=2;
						else 
							steady8+=1;

					//�ڶ���ͼƬ
					if(steady8==30){
						temp.state = 1;
						gameview.incenseList.set(7, temp);
						}
					//������ͼƬ
					if(steady8==60){
						temp.state = 2;
						gameview.incenseList.set(7, temp);
						}
					//������ͼƬ
					if(steady8==90){
						temp.state = 3;
						gameview.incenseList.set(7, temp);
						}
					//����ʱ��
					if(steady8==120){
						gameview.mhandler.sendEmptyMessage(8);
						daojuFlag8=false;
					}
				}
				
				//�ھŸ����õ���
				if(daojuFlag9){
					ExpelIncense temp = gameview.incenseList.get(8);

					if(temp.level==1)
						steady9+=2;
						else 
							steady9+=1;

					//�ڶ���ͼƬ
					if(steady9==30){
						temp.state = 1;
						gameview.incenseList.set(8, temp);
						}
					//������ͼƬ
					if(steady9==60){
						temp.state = 2;
						gameview.incenseList.set(8, temp);
						}
					//������ͼƬ
					if(steady9==90){
						temp.state = 3;
						gameview.incenseList.set(8, temp);
						}
					//����ʱ��
					if(steady9==120){
						gameview.mhandler.sendEmptyMessage(9);
						daojuFlag9=false;
					}
				}
				
				//��ʮ�����õ���
				if(daojuFlag10){
					ExpelIncense temp = gameview.incenseList.get(9);

					if(temp.level==1)
						steady10+=2;
						else 
							steady10+=1;

					//�ڶ���ͼƬ
					if(steady10==30){
						temp.state = 1;
						gameview.incenseList.set(9, temp);
						}
					//������ͼƬ
					if(steady10==60){
						temp.state = 2;
						gameview.incenseList.set(9, temp);
						}
					//������ͼƬ
					if(steady10==90){
						temp.state = 3;
						gameview.incenseList.set(9, temp);
						}
					//����ʱ��
					if(steady10==120){
						gameview.mhandler.sendEmptyMessage(10);
						daojuFlag10=false;
					}
				}
	
	//���ü�����ʱ�����
	if(sprayLastTimeFlag){
		killspray spray = gameview.sprayList.get(0);
		if(spray.level==1)
			spraysteady++;
			if(spray.level==2)
				spraysteady+=2;
		
		//�ڶ���ͼƬ
		if(spraysteady==30){
			spray.state = 1;
			gameview.sprayList.set(0, spray);
			}
		//������ͼƬ
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
	
	//֩��������ʱ�����
		if(swatterLastTimeFlag){
			SpiderWeb swatter = gameview.webList.get(0);
			if(swatter.level==1)
				swatterSteady++;
				if(swatter.level==2)
					swatterSteady+=2;
			
			//�ڶ���ͼƬ
			if(swatterSteady==40){
				swatter.state = 1;
				gameview.webList.set(0, swatter);
				}
			//������ͼƬ
			if(swatterSteady==80){
				swatter.state = 2;
				gameview.webList.set(0, swatter);
				}
			//������ͼƬ
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
