public class ChickenOracle {
    public String theTruth(int n, int eggCount, int lieCount, int liarCount) {
        int chickCount = n - eggCount;
        int countTrue = n - lieCount, countFalse = lieCount;
        int trueToFalse, falseToTrue;
        // if "The chicken"
        boolean theChicken = false;
        {
            trueToFalse = Math.max(0, eggCount - countFalse);
            falseToTrue = Math.max(0, chickCount - countTrue);
            int change = trueToFalse + falseToTrue;
            if (change == liarCount)
                theChicken = true;
            if (change < liarCount
                    && (liarCount - change) % 2 == 0
                    && Math.min(countTrue - trueToFalse, countFalse
                            - falseToTrue) * 2 >= liarCount - change)
                theChicken = true;
        }
        // if "The egg"
        boolean theEgg = false;
        {
            trueToFalse = Math.max(0, chickCount - countFalse);
            falseToTrue = Math.max(0, eggCount - countTrue);
            int change = trueToFalse + falseToTrue;
            if (change == liarCount)
                theEgg = true;
            if (change < liarCount
                    && (liarCount - change) % 2 == 0
                    && Math.min(countTrue - trueToFalse, countFalse
                            - falseToTrue) * 2 >= liarCount - change)
                theEgg = true;
        }
        // result
        if (theEgg && !theChicken)
            return "The egg";
        else if (!theEgg && theChicken)
            return "The chicken";
        else if (theEgg && theChicken)
            return "Ambiguous";
        else
            return "The oracle is a lie";
    }
}
