package TestCases;

import org.apache.commons.lang3.RandomStringUtils;

public class testCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String regex = "\\w{10}\\@gmail\\.com";
		 
		long theRandomNum = (long) (Math.random()*Math.pow(10,10));
		System.out.println(theRandomNum);
		String generatedString = RandomStringUtils.random(15,true, false);
		System.out.println(generatedString);
		String generatedString3 = RandomStringUtils.random(10,true, true)+"@gmail.com";;
		System.out.println(generatedString3);
		String generatedString1 = RandomStringUtils.randomAlphabetic(10);
		System.out.println(generatedString1);
		String generatedString2 = RandomStringUtils.randomAlphanumeric(10);
		System.out.println(generatedString2);
	}

}
