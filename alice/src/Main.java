import alicebot.AliceBot;
import alicebot.ChatterBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Anna_Guscha on 3/14/2017.
 */
public class Main {

    private static AliceBot aliceBot = new AliceBot();

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        ChatterBean.main(new String[] {"gui"});
    }
}
