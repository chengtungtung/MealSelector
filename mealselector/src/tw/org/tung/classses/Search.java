package tw.org.tung.classses;

//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class Search {
//	private String finalMeal;
//	private WebDriver driver = null;
//	
//	public Search(String finalMeal) {
//		
//		this.finalMeal=finalMeal;
//		
//		
//		 //driver的位置
//		System.setProperty("webdriver.chrome.driver", "C:/Users/User/Desktop/12_程信東_餐點選擇器/drivers/chromedriver.exe");
////		System.setProperty("webdriver.chrome.driver", "D:\\tung\\chromedriver.exe");
////		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
////		System.setProperty(getClass().getResource("/chromedriver.exe"));
////		driver = new ChromeDriver();
////		URL imgURL = Search.class.getResource("/drivers/chromedriver.exe");
////		ImageIcon img = new ImageIcon(imgURL);
//////		String chromeDriverPath = getClass().getResource("/drivers/chromedriver.exe").getPath();
////		System.setProperty("webdriver.chrome.driver",System.getProperty("img") );
//		driver = new ChromeDriver();
//		
////        //將瀏覽器放至最大
////		driver.manage().window().maximize();	
////        
////        //指定url為google首頁
////		driver.get("https://www.google.com.tw");		
////		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
////		
////        //在搜尋bar上輸入抽到的字串
////		driver.findElement(By.name("q")).sendKeys(finalMeal+"菜單");;
////        //點選搜尋
////		String str ="/html/body";
////		driver.findElement(By.xpath(str)).click();
//	}
//	
//	public void searchMeal() {
//		 //將瀏覽器放至最大
//		driver.manage().window().maximize();	
//        
//        //指定url為google首頁
//		driver.get("https://www.google.com.tw");		
////		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
//		
//        //在搜尋bar上輸入抽到的字串
//		
//		driver.findElement(By.id("APjFqb")).sendKeys(finalMeal+"菜單");
////		driver.findElement(By.name("q")).sendKeys(finalMeal+"菜單");
//        //點選搜尋
////		driver.findElement(By.id("")).click();
////		String str1 ="/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[1]";
////		String str ="/html/body";
////		driver.findElement(By.xpath(str1)).click();
////		driver.findElement(By.name("btnK")).click();
////		driver.findElement(By.name("btnK")).sendKeys(Keys.ENTER);
//		driver.findElement(By.id("APjFqb")).sendKeys(Keys.ENTER);
//	}
//	
//	public void searchLocation() {
//		 //將瀏覽器放至最大
//		driver.manage().window().maximize();	
//		
//       //指定url為google首頁
//		driver.get("https://www.google.com.tw/maps/");		
////		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
////		driver.manage().timeouts().pageLoadTimeout();
//       //在搜尋bar上輸入抽到的字串
////		String str3 = "//*[@id=\"searchboxinput\"]";
////		driver.findElement(By.xpath(str3)).sendKeys(finalMeal);
//		driver.findElement(By.id("searchboxinput")).sendKeys(finalMeal);
//		
//        //點選搜尋
////		String str ="//*[@id=\"searchbox-searchbutton\"]";  // goolemap
////		driver.findElement(By.xpath(str)).click();
//		driver.findElement(By.id("searchbox-searchbutton")).click();
//		driver.findElement(By.id("searchbox-searchbutton")).sendKeys(Keys.ENTER);
//	
//	}
//
//}
