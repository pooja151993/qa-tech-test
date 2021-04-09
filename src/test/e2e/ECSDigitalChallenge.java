package ecsdigital;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ECSDigitalChallenge {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		// Launch Application
		driver.get("http://localhost:3000/");

		// Click button, 'Render the Challenge'
		driver.findElement(By.xpath("//*[@id=\"home\"]/div/div")).click();

		// Get all three arrays
		WebElement table = driver.findElement(By.xpath("//*[@id=\"challenge\"]/div/div/div[1]/div/div[2]/table"));
		String[] rows = table.getText().split("\n");
		String[] tempArr = rows[0].split(" ");
		int[] firstArray = convertToIntArray(tempArr);
		tempArr = rows[1].split(" ");
		int[] secondArray = convertToIntArray(tempArr);
		tempArr = rows[2].split(" ");
		int[] thirdArray = convertToIntArray(tempArr);

		// Get correct index for all three arrays
		int firstChallengeSolution = _findElement(firstArray, firstArray.length);
		int secondChallengeSolution = _findElement(secondArray, secondArray.length);
		int thirdChallengeSolution = _findElement(thirdArray, thirdArray.length);

		// Enter values for all input fields and Name
		driver.findElement(By.xpath("//input[starts-with(@id,'undefined-submitchallenge1')]"))
				.sendKeys(String.valueOf(firstChallengeSolution));
		driver.findElement(By.xpath("//input[starts-with(@id,'undefined-submitchallenge2')]"))
				.sendKeys(String.valueOf(secondChallengeSolution));
		driver.findElement(By.xpath("//input[starts-with(@id,'undefined-submitchallenge3')]"))
				.sendKeys(String.valueOf(thirdChallengeSolution));
		driver.findElement(By.xpath("//input[starts-with(@id,'undefined-YourName')]")).sendKeys("Pooja Dehankar");

		// Click on the Submit button
		driver.findElement(By.xpath("//*[@id=\"challenge\"]/div/div/div[2]/div/div[2]")).click();

	}

	static int _findElement(int arr[], int size) {
		int right_sum = 0, left_sum = 0;

		// Computing right_sum
		for (int i = 1; i < size; i++)
			right_sum += arr[i];

		// Checking the point of partition
		// i.e. left_Sum == right_sum
		for (int i = 0, j = 1; j < size; i++, j++) {
			right_sum -= arr[j];
			left_sum += arr[i];

			if (left_sum == right_sum)
				return arr[i + 1];
		}

		return -1;
	}

	static int[] convertToIntArray(String arr[]) {
		int[] numbers = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {

			numbers[i] = Integer.parseInt(arr[i]);
		}
		return numbers;
	}
}