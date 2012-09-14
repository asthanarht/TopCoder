package srm145;

public class ExerciseMachine {

    public int getPercentages(String time) {

        int hour = 0;
        int minute = 0;
        int seconds = 0;
        int count = 0;

        hour = Integer.parseInt(time.substring(0, time.indexOf(":")));
        time = time.substring(time.indexOf(":") + 1);
        minute = Integer.parseInt(time.substring(0, time.indexOf(":")));
        time = time.substring(time.indexOf(":") + 1);
        seconds = Integer.parseInt(time);

        minute += hour * 60;
        seconds += minute * 60;

        for (int i = 1; i < 100; i++)
            if (seconds * i % 100 == 0)
                count++;

        return count;

    }

}
