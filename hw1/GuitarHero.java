/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
import synthesizer.GuitarString;

public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static double[] concert = new double[37];

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString[] stringKeys = new GuitarString[37];
        for (int i = 0; i < 37; i += 1) {
            concert[i] = CONCERT_A * Math.pow(2, (i - 24) / 12.0);
            stringKeys[i] = new GuitarString(concert[i]);
        }
        int idx = 0;
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                idx = keyboard.indexOf(key);
                System.out.println(idx);
                if (idx != -1) {
                    stringKeys[idx].pluck();
                } else {
                    idx = 0;
                }
            }

            /* compute the superposition of samples */
            double sample =  stringKeys[idx].sample();
//            System.out.println(sample);

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
//            stringA.tic();
            stringKeys[idx].tic();
        }
    }
}

