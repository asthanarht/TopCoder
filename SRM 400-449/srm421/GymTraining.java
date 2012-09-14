package srm421;

public class GymTraining {
   public int trainingTime(int needToTrain, int minPulse, int maxPulse,
         int trainChange, int restChange) {
      if (maxPulse - minPulse < trainChange)
         return -1;
      int rest = 0;
      int cur = minPulse;
      int trained = 0;
      while (trained < needToTrain) {
         if (cur + trainChange <= maxPulse) {
            cur += trainChange;
            trained++;
         }
         else {
            cur -= restChange;
            if (cur < minPulse)
               cur = minPulse;
            rest++;
         }
      }
      return needToTrain + rest;
   }
}
