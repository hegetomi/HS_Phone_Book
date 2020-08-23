package phonebook;

import java.util.concurrent.TimeUnit;

public class TimeConverter {
    static int minutes;
    static int seconds;
    static long milliseconds;

    static void calcTime(long timeTaken){
         minutes = (int) TimeUnit.MILLISECONDS.toMinutes(timeTaken);
         seconds = (int) TimeUnit.MILLISECONDS.toSeconds(timeTaken) - minutes * 60;
         milliseconds = (int) timeTaken - TimeUnit.MINUTES.toMillis(minutes) - TimeUnit.SECONDS.toMillis(seconds);

    }
}
