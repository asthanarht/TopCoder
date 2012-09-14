package srm504_5;

import java.util.Arrays;

public class TheJackpotDivTwo {
    
    public int[] find(int[] money, int jackpot){
        
        Arrays.sort(money);
        int n = money.length;
        
        for(int num=jackpot; num>0; num--){
            boolean done = false;
            for(int i=0; i<n-1; i++)
                if(money[i]<money[i+1]){
                    money[i]++;
                    done = true;
                    break;
                }
            if(!done)
                money[n-1]++;
        }
        
        return money;
    }

}
