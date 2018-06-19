package Porsche_;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PorscheCheckout {
	
	public static void checkEquality(String s1, String s2) {
		s1=s1.replaceAll("[ From$,*]","");
		double d1=Double.parseDouble(s1);
		s2=s2.replaceAll("[ From$,*]","");
		double d2=Double.parseDouble(s2);
		
		if(d1==d2) {
			System.out.println("PASS.Given values are equal. The values are: "+d1 +" and "+d2);
		}else {
			System.out.println("FAIL. NOT EQUAL.");
		}
	}
	


public static void checkEquality(String s1, String...var) {
	s1=s1.replaceAll("[ From$,*]","");
	double d1=Double.parseDouble(s1);
	double sum=0;
	for(String each:var) {
		each=each.replaceAll("[ From$,*]","");
		double dEach=Double.parseDouble(each);
		sum+=dEach;
	}
	
		if(d1==sum) {
			System.out.println("PASS. Total price is equal to the sum of given values.The values are: "+sum +" and "+d1);
		}else {
			System.out.println("FAIL. NOT A MATCH");
		}
	}


	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.manage().window().fullscreen();
		
		driver.get("https://www.porsche.com/usa/modelstart");
		driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[2]/a[1]/div/div[1]/img")).click();
		String basePrice1 = driver.findElement(By.cssSelector(".m-14-model-price")).getText();	
		
		driver.findElement(By.cssSelector(".m-01-link.m-14-build")).click();
		Set<String> mm = driver.getWindowHandles();
		Iterator<String> it = mm.iterator();
		String parent = it.next();
		String child = it.next();
		
		driver.switchTo().window(child);
	
		String basePrice2 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[1]/div[2]")).getText();
		checkEquality(basePrice1,basePrice2);
		
		String equipmentPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		checkEquality("0",equipmentPrice);
			//checking equality
		String deliveryPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[3]/div[2]")).getText();
		String totalPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		checkEquality(totalPrice,deliveryPrice,basePrice2);
		
		//selecting Miami blue
		driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_FJ5\"]/span")).click();
		String miamiBluePrice = driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_IAF\"]/div[2]/div[1]/div/div[2]")).getText();
		equipmentPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		 checkEquality(miamiBluePrice,equipmentPrice);
		 
				//checking equality
		 totalPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		 String deliveryFee=driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[3]/div[2]")).getText();
		checkEquality(totalPrice,basePrice2,equipmentPrice,deliveryFee);
		
		//selecting wheel
		driver.findElement(By.xpath("//*[@id=\"s_conf_submenu\"]/div/div")).click();
		driver.findElement(By.xpath("//*[@id=\"submenu_exterieur_x_AA_submenu_x_IRA\"]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_MXRD\"]/span/span")).click();
			//checking equality
		String wheelPrice=driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_IRA\"]/div[2]/div[1]/div/div[2]")).getText();
		equipmentPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		checkEquality(equipmentPrice,wheelPrice,miamiBluePrice);
		
		//selecting seat
		driver.findElement(By.xpath("//*[@id=\"s_conf_submenu\"]/div/div")).click();
		driver.findElement(By.xpath("//*[@id=\"submenu_interieur_x_AI_submenu_x_submenu_parent\"]/span")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"submenu_interieur_x_AI_submenu_x_submenu_seats\"]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"s_interieur_x_PP06\"]")).click();
			//checking equality
		String  seatPrice=driver.findElement(By.xpath("//*[@id=\"seats_73\"]/div[2]/div[1]/div[3]/div")).getText();
		equipmentPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		checkEquality(equipmentPrice,wheelPrice,seatPrice,miamiBluePrice);
		totalPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		checkEquality(totalPrice,basePrice2,equipmentPrice,deliveryFee);
		
		//Selecting Interior Carbon Fiber
		driver.findElement(By.xpath("//*[@id=\"s_conf_submenu\"]/div/div")).click();
		driver.findElement(By.xpath("//*[@id=\"submenu_individualization_x_individual_submenu_x_submenu_parent\"]/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"submenu_individualization_x_individual_submenu_x_IIC\"]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"vs_table_IIC_x_PEKH_x_c01_PEKH\"]")).click();
			//checking equality
		equipmentPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		String interiorTrimPrice = driver.findElement(By.xpath("//*[@id=\"vs_table_IIC_x_PEKH\"]/div[1]/div[2]/div")).getText();
		checkEquality(equipmentPrice,wheelPrice,miamiBluePrice,seatPrice,interiorTrimPrice);
		totalPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		checkEquality(totalPrice,basePrice2,equipmentPrice,deliveryFee);
		
		//Selecting Performance	
		driver.findElement(By.xpath("//*[@id=\"s_conf_submenu\"]/div/div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"submenu_individualization_x_individual_submenu_x_IMG\"]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M250_x_c11_M250\"]")).click();
		Thread.sleep(1000);
	
		driver.findElement(By.xpath("//*[@id=\"search_x_inp\"]")).sendKeys("Porsche Ceramic ");
		driver.findElement(By.xpath("//*[@id=\"search_x_M450_x_c91_M450\"]")).click();
				//checking equality
		String speed=driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M250\"]/div[1]/div[2]/div")).getText();
		String brakes=driver.findElement(By.xpath("//*[@id=\"search_x_M450\"]/div[1]/div[2]/div")).getText();
		equipmentPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		checkEquality(equipmentPrice,wheelPrice,miamiBluePrice,seatPrice,interiorTrimPrice,brakes,speed);
		totalPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		checkEquality(totalPrice,basePrice2,equipmentPrice,deliveryFee);
		
		
		



	}

}
