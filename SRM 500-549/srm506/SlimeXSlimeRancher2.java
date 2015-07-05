public class SlimeXSlimeRancher2 {

    public int train(int[] attributes) {

        int n = attributes.length;
        int weight = 0;
        int max = 0;

        for (int i = 0; i < n; i++)
            if (attributes[i] > max)
                max = attributes[i];

        for (int i = 0; i < n; i++)
            weight += (max - attributes[i]);

        return weight;
    }

}
