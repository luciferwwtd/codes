package blog;

import java.util.Random;
import java.util.Scanner;

public class blog1_6 {
public static void main(String[] args) {
   
   Random rand=new Random();
   Scanner sc=new Scanner(System.in);
   
   while(true) {
      int ranNum=rand.nextInt(100)+1;
   int ans=0;
   if(ranNum%2==0) {
      ans=2;
   }else if(ranNum%2==1) {
      ans=1;
   }
   
      System.out.print("홀(1) 짝(2) :");
      int me=sc.nextInt();
      if(ans==me) {
         System.out.println("정답입니다!");
         System.out.println(ranNum);
         break;
      }else if(ans!=me) {
         System.out.println("틀렸습니다");
         System.out.println(ranNum);
         continue;
      }
   }
}
}
