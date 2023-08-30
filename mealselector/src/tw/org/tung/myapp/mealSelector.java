package tw.org.tung.myapp;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicScrollBarUI;

//import tw.org.tung.classses.Search;
// 簡易版
public class mealSelector extends JFrame {
	private JTextField finalMealJtf, inputJtf;
	private LinkedList<JTextField> mealLogJtfLst;
	private LinkedList<JButton> allDeleteBtnLst;
	private JTextArea showAllMealJta;
	private JButton startBtn, addMealBtn, menuBtn, stopBtn, locationBtn , setMealBtn , lotteryBtn,saveBtn;
	private JPanel setMealView ,lotteryView , cards , changeBar,setMealViewTop,setMealViewCenter,lotteryViewBottom ,showAllMealJP,deleteBtnJP;
	private CardLayout cardLayout;
	private LinkedList<String> inputMealsLst = new LinkedList<>();
	private int counter ;
//	final String[] str = new String[5];
	private boolean isStart;
	private String mealReslut; 

	public mealSelector() {
		super("今天吃什麼？");
		
		// 元件初始化
		startBtn = new JButton("開始");
		addMealBtn = new JButton("加入");
		menuBtn = new JButton("搜尋菜單");
		stopBtn = new JButton("抽");
		locationBtn = new JButton("搜尋位置");
		setMealBtn = new JButton("設定餐廳");
		lotteryBtn = new JButton("抽獎");
		saveBtn = new JButton("儲存");
		setMealView = new JPanel();
		lotteryView = new JPanel();
		cards = new JPanel();
		changeBar = new JPanel();
		setMealViewTop = new JPanel();
		lotteryViewBottom = new JPanel();
		cardLayout = new CardLayout();
		finalMealJtf = new JTextField();
		inputJtf = new JTextField();
		//showAllMealJta = new JTextArea();
		showAllMealJP = new JPanel(new GridLayout(50,1));
		deleteBtnJP = new JPanel(new GridLayout(50,1));
		setMealViewCenter = new JPanel();
		mealLogJtfLst = new LinkedList<>();
		allDeleteBtnLst = new LinkedList<>();
		
		// 創建餐點LOG區的各個JTextField物件，並加入Linkedlist的mealLogJtfLst
		for (int j = 0; j < 50; j++) {
			JTextField temp = new JTextField();
			temp.setPreferredSize(new Dimension(10, 55));
			temp.setFont(new Font("微軟正黑體", Font.BOLD, 30));
			temp.setEditable(false);
			inputJtf.setForeground(Color.DARK_GRAY);
			if (j % 2 == 0) {
				temp.setBackground(new Color(241, 243, 244));
			}else {
				temp.setBackground(Color.WHITE);
			}
			mealLogJtfLst.add(temp);
		}
		// 從Linkedlist的mealLogJtfLst中依序取出各個JTextField物件，加入JPanel的showAllMealJP
		for (int i = 0; i < mealLogJtfLst.size(); i++) {
			showAllMealJP.add(mealLogJtfLst.get(i));	
		}
		for (int j = 0; j < 50; j++) {
			final int a =j ;
			JButton temp = new JButton("x");
			allDeleteBtnLst.add(temp);
			allDeleteBtnLst.get(a).setBackground(new Color(156, 205, 164));
			allDeleteBtnLst.get(a).setForeground(Color.WHITE);
			allDeleteBtnLst.get(a).setFont(new Font(null, Font.BOLD, 23));

			allDeleteBtnLst.get(a).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					allDeleteBtnLst.get(a).setBackground(new Color(132, 173, 127));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					allDeleteBtnLst.get(a).setBackground(new Color(156, 205, 164));
				}
			});
		}
		for (int i = 0; i < allDeleteBtnLst.size(); i++) {
			deleteBtnJP.add(allDeleteBtnLst.get(i));
		}
		
		// 版面配置
		// 第一層
		setLayout(new BorderLayout());
		add(changeBar,BorderLayout.NORTH);
		add(cards,BorderLayout.CENTER);
		// 第二層
		cards.setLayout(cardLayout);
		changeBar.setLayout(new FlowLayout());
		changeBar.add(setMealBtn);
		changeBar.add(lotteryBtn);
		cards.add(setMealView);
		cards.add(lotteryView);
		// 各個切換介面的版面配置
		setMealView.setLayout(new BorderLayout());
		setMealViewCenter.setLayout(new BorderLayout());
		setMealViewCenter.add(showAllMealJP,BorderLayout.CENTER);
		setMealViewCenter.add(deleteBtnJP,BorderLayout.EAST);
		JScrollPane jsp = new JScrollPane(setMealViewCenter);
		
		setMealView.add(jsp,BorderLayout.CENTER);
		setMealViewTop.setLayout(new FlowLayout());
		setMealViewTop.add(inputJtf);
		setMealViewTop.add(addMealBtn);
		setMealViewTop.add(saveBtn);
		setMealView.add(setMealViewTop,BorderLayout.NORTH);
		
		lotteryView.setLayout(new BorderLayout());
		lotteryView.add(finalMealJtf,BorderLayout.CENTER);
		lotteryView.add(lotteryViewBottom,BorderLayout.SOUTH);
		lotteryViewBottom.setLayout(new FlowLayout());
		lotteryViewBottom.add(menuBtn);
		lotteryViewBottom.add(locationBtn);
		lotteryViewBottom.add(startBtn);
		lotteryViewBottom.add(stopBtn);
		
		// 元件大小字型設定、可否編輯
		// button
		setMealBtn.setBorderPainted(false);
//		setMealBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		setMealBtn.setBackground(new Color(132, 173, 127));
		setMealBtn.setFocusable(false);
		setMealBtn.setForeground(Color.WHITE);
		setMealBtn.setPreferredSize(new Dimension(220, 32));
		setMealBtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		
		lotteryBtn.setBorderPainted(false);
//		lotteryBtn.setBorder(BorderFactory.createLoweredBevelBorder());
		lotteryBtn.setBackground(new Color(156, 205, 164));
		lotteryBtn.setForeground(Color.WHITE);
		lotteryBtn.setPreferredSize(new Dimension(220, 32));
		lotteryBtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		lotteryBtn.setFocusable(false);
		
		addMealBtn.setBorderPainted(false);
		addMealBtn.setBackground(new Color(156, 205, 164));
		addMealBtn.setForeground(Color.WHITE);
		addMealBtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		addMealBtn.setPreferredSize(new Dimension(80, 40));
		
		saveBtn.setBorderPainted(false);
		saveBtn.setBackground(new Color(156, 205, 164));
		saveBtn.setForeground(Color.WHITE);
		saveBtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		saveBtn.setPreferredSize(new Dimension(80, 40));
		
		menuBtn.setBorderPainted(false);
		menuBtn.setEnabled(false);
		menuBtn.setBackground(new Color(156, 205, 164));
		menuBtn.setForeground(Color.WHITE);
		menuBtn.setFont(new Font("微軟正黑體", Font.BOLD, 25));
		menuBtn.setPreferredSize(new Dimension(150, 45));
		
		locationBtn.setBorderPainted(false);
		locationBtn.setEnabled(false);
		locationBtn.setBackground(new Color(156, 205, 164));
		locationBtn.setForeground(Color.WHITE);
		locationBtn.setFont(new Font("微軟正黑體", Font.BOLD, 25));
		locationBtn.setPreferredSize(new Dimension(150, 45));
		
		startBtn.setBorderPainted(false);
		startBtn.setBackground(new Color(156, 205, 164));
		startBtn.setForeground(Color.WHITE);
		startBtn.setFont(new Font("微軟正黑體", Font.BOLD, 25));
		startBtn.setPreferredSize(new Dimension(90, 45));
		
		stopBtn.setBorderPainted(false);
		stopBtn.setEnabled(false);
		stopBtn.setBackground(new Color(156, 205, 164));
		stopBtn.setForeground(Color.WHITE);
		stopBtn.setFont(new Font("微軟正黑體", Font.BOLD, 25));
		stopBtn.setPreferredSize(new Dimension(90, 45));

		// textfield
		inputJtf.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		inputJtf.setPreferredSize(new Dimension(320,45));
		inputJtf.setBackground(new Color(241, 243, 244));
		inputJtf.setForeground(Color.DARK_GRAY);
		finalMealJtf.setFont(new Font("微軟正黑體", Font.BOLD, 50));
		finalMealJtf.setBackground(Color.WHITE);
		finalMealJtf.setEditable(false);
	
		// panel
		changeBar.setBackground(new Color(222, 225, 230));
		setMealViewTop.setBackground(Color.WHITE);
		lotteryViewBottom.setBackground(new Color(222, 225, 230));
		jsp.getVerticalScrollBar().setBackground(Color.LIGHT_GRAY);
		jsp.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			protected void configureScrollBarColors() {
				this.thumbColor = Color.GRAY;
			}
		});
		
		// 按鈕移動變顏色
		addMealBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				addMealBtn.setBackground(new Color(132, 173, 127));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				addMealBtn.setBackground(new Color(156, 205, 164));
			}
		});
		
		saveBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				saveBtn.setBackground(new Color(132, 173, 127));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				saveBtn.setBackground(new Color(156, 205, 164));
			}
		});
		
		startBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startBtn.setBackground(new Color(132, 173, 127));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startBtn.setBackground(new Color(156, 205, 164));
			}
		});
		
		
		
		// 視窗設定
		setSize(580, 500);
		setMinimumSize(new Dimension(580,500));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		// 抓檔案資料
		try {
			FileReader reader = new FileReader("dir1/save.txt");
			BufferedReader breader = new BufferedReader(reader);
			String saveMeals;
			while ((saveMeals = breader.readLine()) != null) {

				try {
					String[] row = saveMeals.split(",");
					for(int i= 0;i<row.length;i++) {
						inputMealsLst.add(i, row[i]);
					}
					
					// 測試
					System.out.println("加進來的資料有：");
					for (String name : inputMealsLst) {
						System.out.println(name);
						
					}				
				} catch (ArrayIndexOutOfBoundsException e) {
					// System.out.println("xx");
				}
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		for(int i=0;i<inputMealsLst.size();i++) {
			mealLogJtfLst.get(i).setText((i+1) + ". " + inputMealsLst.get(i));
		}
		counter =inputMealsLst.size();
		System.out.println("counter值:"+counter);
		
		// 按鈕接收動作(加入)
		addMealBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputStr = inputJtf.getText();
				// 輸入的內容重覆
				if (inputMealsLst.contains(inputStr)) {
					JOptionPane.showMessageDialog(null, "此餐廳重複輸入囉！", "重複輸入", 2); // 內容重複跳出訊息
					inputJtf.setText(""); // 輸入框清空
				} else {
					counter++;
					inputMealsLst.add(counter-1, inputStr);  // 加入LinkedList
					
					// 測試
					System.out.println("counter值:"+counter);
					for (String name : inputMealsLst) {
						System.out.println(name);
					}
					
					mealLogJtfLst.get(counter-1).setText((counter) + ". " + inputJtf.getText());
					inputJtf.setText(""); // 輸入框清空
					
									
				}
			}
		});
		
		// 按鈕接收動作(儲存)
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(counter == 0) {
					System.out.println("沒有資料");
					File file = new File("dir1/save.txt");
					file.delete();
				}else {
				String mealplus = "";

				for (int i = 0; i < inputMealsLst.size(); i++) {
					mealplus = mealplus + (inputMealsLst.get(i) + ",");
				}
				String finalmealplus = mealplus.substring(0, mealplus.length() - 1);

				FileWriter writer;
				try {
					writer = new FileWriter("dir1/save.txt");
					writer.write(finalmealplus);
					writer.flush();
					writer.close();
					System.out.println("存檔ok");
				} catch (Exception e1) {
					System.out.println(e1.toString());
				}
				}
			}
		});
		
		
		// 全部刪除鍵的觸發
		for (int i = 0; i < allDeleteBtnLst.size(); i++) {
			final int a = i;
			allDeleteBtnLst.get(a).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(a);
					if (inputMealsLst.size() <= a) {
						System.out.println("沒有資料可以刪");
					} else {
						counter--;
						for (int i = 0; i < inputMealsLst.size(); i++) {
							mealLogJtfLst.get(i).setText("");
						}
						inputMealsLst.remove(a);
						for (int i = 0; i < inputMealsLst.size(); i++) {
							mealLogJtfLst.get(i).setText((i + 1) + ". " + inputMealsLst.get(i));
						}
					}
					System.out.println("counter值:" + counter);
				}
			});
			
		}
		

		
		// 按鈕接收動作(開始)
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isStart = true;
				new Thread(new Runnable() {
					@Override
					public void run() {
						finalMeal();
						// 跑無窮迴圈，直到按下"抽"按鈕
						for (int i = 0;; ++i) {
							if (isStart != true)
								break;
							finalMealJtf.setText(String.format("   今天吃 %s", finalMeal()));
						}
					}
				}).start();
				startBtn.setEnabled(false);
				stopBtn.setEnabled(true);
				stopBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						stopBtn.setBackground(new Color(132, 173, 127));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						stopBtn.setBackground(new Color(156, 205, 164));
					}
				});
			}
		});
		
		// 按鈕接收動作(菜單)
		menuBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
//				new Search(mealReslut).searchMeal();
			}
		});
		
		// 按鈕接收動作(位置)
		locationBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				new Search(mealReslut).searchLocation();
				
			}
		});
		
		// 按鈕接收動作(抽)
		stopBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isStart = false;
				String st = finalMealJtf.getText();
				st = st + " 吧！";
				new JOptionPane().showMessageDialog(null, st);
				startBtn.setEnabled(true);
				menuBtn.setEnabled(true);
				locationBtn.setEnabled(true);
				stopBtn.setEnabled(false);
				
				menuBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						menuBtn.setBackground(new Color(132, 173, 127));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						menuBtn.setBackground(new Color(156, 205, 164));
					}
				});
				locationBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						locationBtn.setBackground(new Color(132, 173, 127));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						locationBtn.setBackground(new Color(156, 205, 164));
					}
				});
			}
		});
		
		inputJtf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inputJtf.setBackground(new Color(222, 225, 230));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				inputJtf.setBackground(new Color(241, 243, 244));
			}
		});
		
		// 按鈕接收動作(輸入框)
		inputJtf.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (inputJtf.getText().equals("")) {
					inputJtf.setForeground(Color.DARK_GRAY);
					inputJtf.setText("請輸入餐點");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (inputJtf.getText().equals("請輸入餐點")) {
					inputJtf.setText("");
					inputJtf.setForeground(Color.DARK_GRAY);
				}
			}
		});
		
		// cardlayout點擊設置
		setMealBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.first(cards);
				setMealBtn.setBackground(new Color(132, 173, 127));
				lotteryBtn.setBackground(new Color(156, 205, 164));
			}
		});
		
		lotteryBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.last(cards);
				lotteryBtn.setBackground(new Color(132, 173, 127));
				setMealBtn.setBackground(new Color(156, 205, 164));
			}
		});
		
		
		
		
		
		
	}
	
	// 隨機選餐點
	private String finalMeal() {
		int nameSize = inputMealsLst.size();
		mealReslut = inputMealsLst.get((int)(Math.random()*nameSize));
		return  mealReslut;
	}
	
	public static void main(String[] args) {
		new mealSelector();
		File dir1 = new File("./dir1");
		if (dir1.exists()) {
			System.out.println("ok");
		}else {
			System.out.println("xx");
			dir1.mkdir(); // 新建路徑
		}
	}

}
